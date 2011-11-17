package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import models.DetilPembelian;
import models.DetilReturPembelian;
import models.DetilTransferStok;
import models.DetilTransferStokId;
import models.ObatAlat;
import models.Pembelian;
import models.ReturPembelian;
import models.StokObatAlat;
import models.Supplier;
import models.TransferStok;
import play.data.binding.As;
import play.data.validation.Required;
import play.libs.MimeTypes;
import play.mvc.With;
import tool.AutocompleteValue;
import tool.CommonUtil;

@With(Secure.class)
public class PembelianControl extends BaseController {
	public static int AUTOCOMPLETE_MAX = 20;

	public static void transaksi(Pembelian pembelian, String hasil) {
		if (pembelian == null)
			pembelian = new Pembelian();
		if (pembelian.getTglPembelian() == null)
			pembelian.setTglPembelian(new Date());
		render(pembelian, hasil);
	}

	public static void transaksi_retur(ReturPembelian returPembelian,
			String hasil) {
		if (returPembelian == null)
			returPembelian = new ReturPembelian();
		if (returPembelian.getTglReturBeli() == null)
			returPembelian.setTglReturBeli(new Date());
		render(returPembelian, hasil);
	}

	public static void transfer(TransferStok transferStok, String hasil) {
		if (transferStok == null)
			transferStok = new TransferStok();
		if (transferStok.getTglTransfer() == null)
			transferStok.setTglTransfer(new Date());
		render(transferStok, hasil);
	}

	public static void monitoring(List pembelian,
			@As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir, String idSupplier,
			String idObatAlat) {
		if (pembelian == null)
			pembelian = new ArrayList();
		tglPembelianAwal = new Date();
		tglPembelianAkhir = new Date();
		render(pembelian, tglPembelianAwal, tglPembelianAkhir, idSupplier,
				idObatAlat);
	}

	public static void autocompleteSupplier(final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		List<Supplier> findAll = Supplier.find("nama_supplier like ?",
				"%" + term + "%").fetch();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			Supplier supplierTmp = (Supplier) iterator.next();
			response.add(new AutocompleteValue(supplierTmp.getIdSupplier(),
					supplierTmp.getLabelSupplier()));
		}
		renderJSON(response);
	}

	public static void autocompleteFaktur(final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		List<Pembelian> findAll = Pembelian.find("no_faktur like ?",
				"%" + term + "%").fetch();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			Pembelian pembelianTmp = (Pembelian) iterator.next();
			response.add(new AutocompleteValue(pembelianTmp.getNoFaktur(),
					pembelianTmp.getNoFaktur()));
		}
		renderJSON(response);
	}

	public static void savePembelian(String simpan, String idPembelian,
			@Required @As("dd-MM-yyyy") Date tglPembelian,
			@Required String key_idSupplier, List<String> key_kode_obat,
			@As("dd-MM-yyyy") List<Date> tglKadaluarsa,
			List<Integer> stokApotek, List<Integer> stokGudang,
			List<Integer> harga, List<Integer> ppn, List<Integer> diskon) {
		Pembelian pembelian = new Pembelian();
		pembelian.setTglPembelian(tglPembelian);
		if (pembelian.getTglPembelian() == null)
			pembelian.setTglPembelian(new Date());
		pembelian.setIdSupplier((Supplier) Supplier.findById(key_idSupplier));
		if (CommonUtil.isEmpty(idPembelian)) {
			pembelian.validateAndSave();
		} else {
			pembelian.setIdPembelian(idPembelian);
			if ("Tutup".equals(simpan))
				pembelian.setStsTransaksi("1");
			else if ("Buka".equals(simpan))
				pembelian.setStsTransaksi(null);
			pembelian = pembelian.merge();
			pembelian.validateAndSave();
			DetilPembelian.delete("id_pembelian=?", idPembelian);
		}
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transaksi(pembelian, null);
			return;
		}
		for (int i = 0; i < key_kode_obat.size(); i++) {
			if (key_kode_obat.get(i) != null
					&& !"".equals(key_kode_obat.get(i))) {
				Integer jmlTerimaApotek = stokApotek.get(i) == null ? 0
						: stokApotek.get(i);
				Integer jmlTerimaGudang = stokGudang.get(i) == null ? 0
						: stokGudang.get(i);
				DetilPembelian detilPembelian = new DetilPembelian();
				StokObatAlat stokObatAlat = new StokObatAlat();
				stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
						.findById(key_kode_obat.get(i)));
				stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
				List<StokObatAlat> fetch = StokObatAlat
						.find("id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
								stokObatAlat.getIdObatAlat().getIdObatAlat(),
								stokObatAlat.getTglKadaluarsa()).fetch();
				if (fetch.isEmpty()) {
					stokObatAlat.setJmlStokApotek(0);
					stokObatAlat.setJmlStokGudang(0);
					stokObatAlat.validateAndSave();
					if (validation.hasErrors()) {
						params.flash(); // add http parameters to the flash
										// scope
						validation.keep(); // keep the errors for the next
											// request
						transaksi(pembelian, null);
						return;
					}
				} else {
					StokObatAlat tmp = fetch.get(0);
					stokObatAlat.setIdStok(tmp.getIdStok());
					Integer jmlStokApotek = tmp.getJmlStokApotek() == null ? 0
							: tmp.getJmlStokApotek();
					Integer jmlStokGudang = tmp.getJmlStokGudang() == null ? 0
							: tmp.getJmlStokGudang();
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
				}
				detilPembelian.setIdPembelian(pembelian);
				detilPembelian.setIdStok(stokObatAlat);
				detilPembelian.setTglKadaluarsa(tglKadaluarsa.get(i));
				detilPembelian.setJmlPenerimaanApotek(jmlTerimaApotek);
				detilPembelian.setJmlPenerimaanGudang(jmlTerimaGudang);
				Integer diskonTmp = diskon.get(i) == null ? 0 : diskon.get(i);
				Integer hargaTmp = harga.get(i) == null ? 0 : harga.get(i);
				Integer ppnTmp = ppn.get(i) == null ? 10 : ppn.get(i);
				detilPembelian.setHargaPenerimaan(hargaTmp);
				detilPembelian.setPpn(ppnTmp);
				detilPembelian.setDiscountPercent(diskonTmp);
				detilPembelian.setDiscountCharge(diskonTmp * hargaTmp / 100);
				detilPembelian.validateAndSave();
				pembelian.addDetilPembelianIdPembelian(detilPembelian);
			}
		}
		String hasil = "Pembelian Berhasil Disimpan!";
		if ("Tutup".equals(simpan)) {
			for (int i = 0; i < key_kode_obat.size(); i++) {
				if (key_kode_obat.get(i) != null
						&& !"".equals(key_kode_obat.get(i))) {
					Integer jmlTerimaApotek = stokApotek.get(i) == null ? 0
							: stokApotek.get(i);
					Integer jmlTerimaGudang = stokGudang.get(i) == null ? 0
							: stokGudang.get(i);
					StokObatAlat stokObatAlat = new StokObatAlat();
					stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
							.findById(key_kode_obat.get(i)));
					stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
					List<StokObatAlat> fetch = StokObatAlat
							.find("id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
									stokObatAlat.getIdObatAlat()
											.getIdObatAlat(),
									stokObatAlat.getTglKadaluarsa()).fetch();
					if (!fetch.isEmpty()) {
						StokObatAlat tmp = fetch.get(0);
						Query createNativeQuery = Pembelian
								.em()
								.createNativeQuery(
										"select detil_pembelian.* from detil_pembelian where id_pembelian= :idPembelian and id_stok= :idStok",
										DetilPembelian.class);
						createNativeQuery.setParameter("idPembelian",
								pembelian.getIdPembelian());
						createNativeQuery.setParameter("idStok",
								tmp.getIdStok());
						DetilPembelian detilPembelian = (DetilPembelian) createNativeQuery
								.getResultList().get(0);
						stokObatAlat.setIdStok(tmp.getIdStok());
						Integer jmlStokApotek = tmp.getJmlStokApotek() == null ? 0
								: tmp.getJmlStokApotek();
						Integer jmlStokGudang = tmp.getJmlStokGudang() == null ? 0
								: tmp.getJmlStokGudang();
						detilPembelian.setStokAwalApotek(jmlStokApotek);
						detilPembelian.setStokAwalGudang(jmlStokGudang);
						stokObatAlat.setJmlStokApotek(jmlStokApotek
								+ jmlTerimaApotek);
						stokObatAlat.setJmlStokGudang(jmlStokGudang
								+ jmlTerimaGudang);
						detilPembelian.setStokAkhirApotek(jmlStokApotek
								+ jmlTerimaApotek);
						detilPembelian.setStokAkhirGudang(jmlStokGudang
								+ jmlTerimaGudang);
						Integer diskonTmp = diskon.get(i) == null ? 0 : diskon
								.get(i);
						Integer hargaTmp = harga.get(i) == null ? 0 : harga
								.get(i);
						Integer ppnTmp = ppn.get(i) == null ? 10 : ppn.get(i);
						stokObatAlat.setHargaBeliStok(hargaTmp);
						stokObatAlat.setPpnStok(ppnTmp);
						stokObatAlat.setDiscPercStok(diskonTmp);
						stokObatAlat.setDiscCharge(diskonTmp * hargaTmp / 100);
						stokObatAlat = stokObatAlat.merge();
						stokObatAlat.validateAndSave();
						detilPembelian = detilPembelian.merge();
						detilPembelian.save();
					}
				}
			}
			hasil = "Pembelian Berhasil Ditutup!";
		} else if ("Buka".equals(simpan)) {
			for (int i = 0; i < key_kode_obat.size(); i++) {
				if (key_kode_obat.get(i) != null
						&& !"".equals(key_kode_obat.get(i))) {
					Integer jmlTerimaApotek = stokApotek.get(i) == null ? 0
							: stokApotek.get(i);
					Integer jmlTerimaGudang = stokGudang.get(i) == null ? 0
							: stokGudang.get(i);
					StokObatAlat stokObatAlat = new StokObatAlat();
					stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
							.findById(key_kode_obat.get(i)));
					stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
					List<StokObatAlat> fetch = StokObatAlat
							.find("id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
									stokObatAlat.getIdObatAlat()
											.getIdObatAlat(),
									stokObatAlat.getTglKadaluarsa()).fetch();
					if (!fetch.isEmpty()) {
						StokObatAlat tmp = fetch.get(0);
						stokObatAlat.setIdStok(tmp.getIdStok());
						Integer jmlStokApotek = tmp.getJmlStokApotek() == null ? 0
								: tmp.getJmlStokApotek();
						Integer jmlStokGudang = tmp.getJmlStokGudang() == null ? 0
								: tmp.getJmlStokGudang();
						stokObatAlat.setJmlStokApotek(jmlStokApotek
								- jmlTerimaApotek);
						stokObatAlat.setJmlStokGudang(jmlStokGudang
								- jmlTerimaGudang);
						Integer diskonTmp = diskon.get(i) == null ? 0 : diskon
								.get(i);
						Integer hargaTmp = harga.get(i) == null ? 0 : harga
								.get(i);
						Integer ppnTmp = ppn.get(i) == null ? 10 : ppn.get(i);
						stokObatAlat.setHargaBeliStok(hargaTmp);
						stokObatAlat.setPpnStok(ppnTmp);
						stokObatAlat.setDiscPercStok(diskonTmp);
						stokObatAlat.setDiscCharge(diskonTmp * hargaTmp / 100);
						stokObatAlat = stokObatAlat.merge();
						stokObatAlat.validateAndSave();
					}
				}
			}
			hasil = "Pembelian Berhasil Dibuka!";
		}
		renderTemplate("PembelianControl/transaksi.html", pembelian, hasil);
	}

	public static void saveReturBeli(String simpan, String idReturBeli,
			@Required @As("dd-MM-yyyy") Date tglReturBeli,
			@Required String key_idSupplier, String key_noFakturBeli,
			String descReturBeli, List<String> key_pilihStok,
			List<Integer> returApotek, List<Integer> returGudang,
			List<Integer> redeliveryApotek, List<Integer> redeliveryGudang) {
		ReturPembelian returPembelian = new ReturPembelian();
		returPembelian.setTglReturBeli(tglReturBeli);
		returPembelian.setTglAktivitas(new Date());
		if (returPembelian.getTglReturBeli() == null)
			returPembelian.setTglReturBeli(new Date());
		returPembelian.setIdSupplier((Supplier) Supplier
				.findById(key_idSupplier));
		returPembelian.setNoFakturBeli(key_noFakturBeli);
		returPembelian.setDescReturBeli(descReturBeli);
		if (CommonUtil.isEmpty(idReturBeli)) {
			returPembelian.validateAndSave();
		} else {
			returPembelian.setIdReturBeli(idReturBeli);
			returPembelian = returPembelian.merge();
			returPembelian.validateAndSave();
			DetilReturPembelian.delete("id_retur_beli=?", idReturBeli);
		}
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transaksi_retur(returPembelian, null);
			return;
		}
		for (int i = 0; i < key_pilihStok.size(); i++) {
			if (key_pilihStok.get(i) != null
					&& !"".equals(key_pilihStok.get(i))) {
				Integer jmlReturBeliApotek = returApotek.get(i) == null ? 0
						: returApotek.get(i);
				Integer jmlReturBeliGudang = returGudang.get(i) == null ? 0
						: returGudang.get(i);
				Integer jmlRedeliveryApotek = redeliveryApotek.get(i) == null ? 0
						: redeliveryApotek.get(i);
				Integer jmlRedeliveryGudang = redeliveryGudang.get(i) == null ? 0
						: redeliveryGudang.get(i);
				DetilReturPembelian detilReturPembelian = new DetilReturPembelian();
				detilReturPembelian.setIdReturBeli(returPembelian);
				detilReturPembelian.setIdStok((StokObatAlat) StokObatAlat
						.findById(key_pilihStok.get(i)));
				detilReturPembelian.setJmlReturBeliApotek(jmlReturBeliApotek);
				detilReturPembelian.setJmlReturBeliGudang(jmlReturBeliGudang);
				detilReturPembelian.setJmlRedeliveryApotek(jmlRedeliveryApotek);
				detilReturPembelian.setJmlRedeliveryGudang(jmlRedeliveryGudang);
				detilReturPembelian.validateAndSave();
				returPembelian
						.addDetilReturPembelianIdReturBeli(detilReturPembelian);
			}
		}
		String hasil = "Retur Pembelian Berhasil Disimpan!";
		if (simpan.equals("Tutup")) {
			for (int i = 0; i < key_pilihStok.size(); i++) {
				if (key_pilihStok.get(i) != null
						&& !"".equals(key_pilihStok.get(i))) {
					StokObatAlat stokObatAlat = StokObatAlat
							.findById(key_pilihStok.get(i));
					Query createNativeQuery = Pembelian
							.em()
							.createNativeQuery(
									"select detil_retur_pembelian.* from detil_retur_pembelian where id_retur_beli= :idReturBeli and id_stok= :idStok",
									DetilReturPembelian.class);
					createNativeQuery.setParameter("idReturBeli",
							returPembelian.getIdReturBeli());
					createNativeQuery.setParameter("idStok",
							stokObatAlat.getIdStok());
					DetilReturPembelian detilReturPembelian = (DetilReturPembelian) createNativeQuery
							.getResultList().get(0);
					Integer jmlStokApotekSebelumnya = stokObatAlat
							.getJmlStokApotek();
					Integer jmlStokGudangSebelumnya = stokObatAlat
							.getJmlStokGudang();
					detilReturPembelian
							.setStokAwalApotek(jmlStokApotekSebelumnya);
					detilReturPembelian
							.setStokAwalGudang(jmlStokGudangSebelumnya);
					Integer jmlReturApotek = returApotek.get(i) == null ? 0
							: returApotek.get(i);
					Integer jmlReturGudang = returGudang.get(i) == null ? 0
							: returGudang.get(i);
					Integer jmlRedeliveryApotek = redeliveryApotek.get(i) == null ? 0
							: redeliveryApotek.get(i);
					Integer jmlRedeliveryGudang = redeliveryGudang.get(i) == null ? 0
							: redeliveryGudang.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotekSebelumnya
							- jmlReturApotek + jmlRedeliveryApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudangSebelumnya
							- jmlReturGudang + jmlRedeliveryGudang);
					detilReturPembelian
							.setStokAkhirApotek(jmlStokApotekSebelumnya
									- jmlReturApotek + jmlRedeliveryApotek);
					detilReturPembelian
							.setStokAkhirGudang(jmlStokGudangSebelumnya
									- jmlReturGudang + jmlRedeliveryGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					detilReturPembelian = detilReturPembelian.merge();
					detilReturPembelian.validateAndSave();
				}
			}
			returPembelian.setIdReturBeli(idReturBeli);
			returPembelian.setStsTransaksi("1");
			returPembelian = returPembelian.merge();
			returPembelian.validateAndSave();
			hasil = "Retur Pembelian Berhasil Ditutup!";
		} else if (simpan.equals("Buka")) {
			for (int i = 0; i < key_pilihStok.size(); i++) {
				if (key_pilihStok.get(i) != null
						&& !"".equals(key_pilihStok.get(i))) {
					StokObatAlat stokObatAlat = StokObatAlat
							.findById(key_pilihStok.get(i));
					Query createNativeQuery = Pembelian
							.em()
							.createNativeQuery(
									"select detil_retur_pembelian.* from detil_retur_pembelian where id_retur_beli= :idReturBeli and id_stok= :idStok",
									DetilReturPembelian.class);
					createNativeQuery.setParameter("idReturBeli",
							returPembelian.getIdReturBeli());
					createNativeQuery.setParameter("idStok",
							stokObatAlat.getIdStok());
					DetilReturPembelian detilReturPembelian = (DetilReturPembelian) createNativeQuery
							.getResultList().get(0);
					Integer jmlStokApotekSebelumnya = stokObatAlat
							.getJmlStokApotek();
					Integer jmlStokGudangSebelumnya = stokObatAlat
							.getJmlStokGudang();
					detilReturPembelian
							.setStokAwalApotek(jmlStokApotekSebelumnya);
					detilReturPembelian
							.setStokAwalGudang(jmlStokGudangSebelumnya);
					Integer jmlReturApotek = returApotek.get(i) == null ? 0
							: returApotek.get(i);
					Integer jmlReturGudang = returGudang.get(i) == null ? 0
							: returGudang.get(i);
					Integer jmlRedeliveryApotek = redeliveryApotek.get(i) == null ? 0
							: redeliveryApotek.get(i);
					Integer jmlRedeliveryGudang = redeliveryGudang.get(i) == null ? 0
							: redeliveryGudang.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotekSebelumnya
							+ jmlReturApotek - jmlRedeliveryApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudangSebelumnya
							+ jmlReturGudang - jmlRedeliveryGudang);
					detilReturPembelian
							.setStokAkhirApotek(jmlStokApotekSebelumnya
									+ jmlReturApotek - jmlRedeliveryApotek);
					detilReturPembelian
							.setStokAkhirGudang(jmlStokGudangSebelumnya
									+ jmlReturGudang - jmlRedeliveryGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					detilReturPembelian = detilReturPembelian.merge();
					detilReturPembelian.validateAndSave();
				}
			}
			returPembelian.setIdReturBeli(idReturBeli);
			returPembelian.setStsTransaksi(null);
			returPembelian = returPembelian.merge();
			returPembelian.validateAndSave();
			hasil = "Retur Pembelian Berhasil Dibuka!";
		}
		renderTemplate("PembelianControl/transaksi_retur.html", returPembelian,
				hasil);
	}

	public static void cariPembelian(String key_idSupplier,
			String key_idObatAlat, @As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir) {
		/*
		 * List<Pembelian> pembelian = Pembelian .find(
		 * "date_trunc('day', tgl_pembelian)>=? and date_trunc('day', tgl_pembelian)<=? and id_supplier=?"
		 * , tglPembelianAwal, tglPembelianAkhir, key_idSupplier).fetch();
		 */
		Pembelian p = new Pembelian();
		List pembelian = p.monitoring(key_idSupplier, key_idObatAlat,
				tglPembelianAwal, tglPembelianAkhir);
		renderTemplate("PembelianControl/monitoring.html", pembelian,
				tglPembelianAwal, tglPembelianAkhir, key_idSupplier,
				key_idObatAlat);
	}

	public static void lihatPembelian(String idPembelian) {
		Pembelian pembelian = Pembelian.findById(idPembelian);
		String hasil = null;
		renderTemplate("PembelianControl/transaksi.html", pembelian, hasil);
	}

	public static void showVolume(String idObatAlat) {
		ObatAlat obatAlatTmp = ObatAlat.findById(idObatAlat);
		String data = obatAlatTmp.getVolumeObatAlat();
		renderJSON(data, String.class);
	}

	public static void saveTransfer(String simpan, String idTransfer,
			@Required @As("dd-MM-yyyy") Date tglTransfer, String descTransfer,
			List<String> key_pilihStok, List<Integer> tujuan,
			List<Integer> kirim) {
		TransferStok transferStok = new TransferStok();
		transferStok.setTglTransfer(tglTransfer);
		if (transferStok.getTglTransfer() == null)
			transferStok.setTglTransfer(new Date());
		transferStok.setDescTransfer(descTransfer);
		if (CommonUtil.isEmpty(idTransfer)) {
			transferStok.validateAndSave();
		} else {
			transferStok.setIdTransfer(idTransfer);
			if ("Tutup".equals(simpan))
				transferStok.setStsTransaksi("1");
			else if ("Buka".equals(simpan))
				transferStok.setStsTransaksi(null);
			transferStok = transferStok.merge();
			transferStok.validateAndSave();
			DetilTransferStok.delete("id_transfer=?", idTransfer);
		}
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transfer(transferStok, null);
			return;
		}
		for (int i = 0; i < key_pilihStok.size(); i++) {
			if (key_pilihStok.get(i) != null
					&& !"".equals(key_pilihStok.get(i))) {
				Integer jmlKirim = kirim.get(i) == null ? 0 : kirim.get(i);
				DetilTransferStok detilTransferStok = new DetilTransferStok();
				detilTransferStok.setIdTransfer(transferStok);
				detilTransferStok.setIdStok((StokObatAlat) StokObatAlat
						.findById(key_pilihStok.get(i)));
				if (tujuan.get(i) == 1) {
					detilTransferStok.setJmlKirimApotek(jmlKirim);
				} else {
					detilTransferStok.setJmlKirimGudang(jmlKirim);
				}
				detilTransferStok.validateAndSave();
				transferStok.addDetilTransferStokIdTransfer(detilTransferStok);
			}
		}
		String hasil = "Transfer Berhasil Disimpan!";
		if ("Tutup".equals(simpan)) {
			for (int i = 0; i < key_pilihStok.size(); i++) {
				if (key_pilihStok.get(i) != null
						&& !"".equals(key_pilihStok.get(i))) {
					StokObatAlat stokObatAlat = StokObatAlat
							.findById(key_pilihStok.get(i));
					DetilTransferStok detilTransferStok = DetilTransferStok
							.findById(new DetilTransferStokId(transferStok
									.getIdTransfer(), key_pilihStok.get(i)));
					detilTransferStok.setStokAwalApotek(stokObatAlat
							.getJmlStokApotek());
					detilTransferStok.setStokAwalGudang(stokObatAlat
							.getJmlStokGudang());
					if (detilTransferStok.getJmlKirimApotek() != null
							&& detilTransferStok.getJmlKirimApotek() > 0) {
						Integer jmlStokApotek = stokObatAlat.getJmlStokApotek()
								+ detilTransferStok.getJmlKirimApotek();
						Integer jmlStokGudang = stokObatAlat.getJmlStokGudang()
								- detilTransferStok.getJmlKirimApotek();
						stokObatAlat.setJmlStokApotek(jmlStokApotek);
						stokObatAlat.setJmlStokGudang(jmlStokGudang);
					} else if (detilTransferStok.getJmlKirimGudang() != null
							&& detilTransferStok.getJmlKirimGudang() > 0) {
						Integer jmlStokApotek = stokObatAlat.getJmlStokApotek()
								- detilTransferStok.getJmlKirimGudang();
						Integer jmlStokGudang = stokObatAlat.getJmlStokGudang()
								+ detilTransferStok.getJmlKirimGudang();
						stokObatAlat.setJmlStokApotek(jmlStokApotek);
						stokObatAlat.setJmlStokGudang(jmlStokGudang);
					}
					detilTransferStok.setStokAkhirApotek(stokObatAlat
							.getJmlStokApotek());
					detilTransferStok.setStokAkhirGudang(stokObatAlat
							.getJmlStokGudang());
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
				}
			}
			hasil = "Transfer Berhasil Ditutup!";
		} else if ("Buka".equals(simpan)) {
			for (int i = 0; i < key_pilihStok.size(); i++) {
				if (key_pilihStok.get(i) != null
						&& !"".equals(key_pilihStok.get(i))) {
					StokObatAlat stokObatAlat = StokObatAlat
							.findById(key_pilihStok.get(i));
					DetilTransferStok detilTransferStok = DetilTransferStok
							.findById(new DetilTransferStokId(transferStok
									.getIdTransfer(), key_pilihStok.get(i)));
					detilTransferStok.setStokAwalApotek(stokObatAlat
							.getJmlStokApotek());
					detilTransferStok.setStokAwalGudang(stokObatAlat
							.getJmlStokGudang());
					if (detilTransferStok.getJmlKirimApotek() != null
							&& detilTransferStok.getJmlKirimApotek() > 0) {
						Integer jmlStokApotek = stokObatAlat.getJmlStokApotek()
								- detilTransferStok.getJmlKirimApotek();
						Integer jmlStokGudang = stokObatAlat.getJmlStokGudang()
								+ detilTransferStok.getJmlKirimApotek();
						stokObatAlat.setJmlStokApotek(jmlStokApotek);
						stokObatAlat.setJmlStokGudang(jmlStokGudang);
					} else if (detilTransferStok.getJmlKirimGudang() != null
							&& detilTransferStok.getJmlKirimGudang() > 0) {
						Integer jmlStokApotek = stokObatAlat.getJmlStokApotek()
								+ detilTransferStok.getJmlKirimGudang();
						Integer jmlStokGudang = stokObatAlat.getJmlStokGudang()
								- detilTransferStok.getJmlKirimGudang();
						stokObatAlat.setJmlStokApotek(jmlStokApotek);
						stokObatAlat.setJmlStokGudang(jmlStokGudang);
					}
					detilTransferStok.setStokAkhirApotek(stokObatAlat
							.getJmlStokApotek());
					detilTransferStok.setStokAkhirGudang(stokObatAlat
							.getJmlStokGudang());
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
				}
			}
			hasil = "Transfer Berhasil Ditutup!";
		}
		renderTemplate("PembelianControl/transfer.html", transferStok, hasil);
	}

	public static void showStok(String idObatAlat) {
		List<StokObatAlat> fetch = StokObatAlat.find("id_obat_alat=?",
				idObatAlat).fetch();
		final List<AutocompleteValue> resp = new ArrayList<AutocompleteValue>();
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			StokObatAlat stokObatAlat = (StokObatAlat) iterator.next();
			resp.add(new AutocompleteValue(stokObatAlat.getIdStok(),
					stokObatAlat.getLabelStok()));
		}
		renderJSON(resp);
	}

	public static void laporan(List pembelian,
			@As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir, String idSupplier,
			String idObatAlat) {
		if (pembelian == null)
			pembelian = new ArrayList();
		tglPembelianAwal = new Date();
		tglPembelianAkhir = new Date();
		render(pembelian, tglPembelianAwal, tglPembelianAkhir, idSupplier,
				idObatAlat);
	}

	public static void generateLaporan(String key_idSupplier,
			String key_idObatAlat, @As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir) {
		String sql = "select detil_pembelian.*"
				+ " from pembelian join detil_pembelian on pembelian.id_pembelian=detil_pembelian.id_pembelian"
				+ " join stok_obat_alat on detil_pembelian.id_stok=stok_obat_alat.id_stok"
				+ " join supplier on pembelian.id_supplier=supplier.id_supplier"
				+ " where sts_transaksi is not null";
		if (tglPembelianAwal != null)
			sql += " and pembelian.tgl_pembelian >= :tglPembelianAwal";
		if (tglPembelianAkhir != null)
			sql += " and pembelian.tgl_pembelian <= :tglPembelianAkhir";
		if (!key_idSupplier.equals(""))
			sql += " and pembelian.id_supplier = :key_idSupplier";
		if (!key_idObatAlat.equals(""))
			sql += " and stok_obat_alat.id_obat_alat = :key_idObatAlat";

		sql += " order by detil_pembelian.id_pembelian";
		Query createNativeQuery = Pembelian.em().createNativeQuery(sql,
				DetilPembelian.class);

		if (tglPembelianAwal != null) {
			createNativeQuery
					.setParameter("tglPembelianAwal", tglPembelianAwal);
		}
		if (tglPembelianAkhir != null) {
			createNativeQuery.setParameter("tglPembelianAkhir",
					tglPembelianAkhir);
		}
		if (!key_idSupplier.equals("")) {
			createNativeQuery.setParameter("key_idSupplier", key_idSupplier);
		}
		if (!key_idObatAlat.equals("")) {
			createNativeQuery.setParameter("key_idObatAlat", key_idObatAlat);
		}
		List detilPembelianList = null;
		try {
			detilPembelianList = createNativeQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentTypeIfNotSet(MimeTypes
				.getContentType("laporanPembelian.xls"));
		response.setHeader("Content-Disposition",
				"attachment; filename=laporanPembelian.xls");
		renderTemplate("excel/laporanPembelian.html", detilPembelianList);
	}
}
