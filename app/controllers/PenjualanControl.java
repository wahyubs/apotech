package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import models.DetailResep;
import models.DetailReturPenjualan;
import models.HargaObat;
import models.JenisHarga;
import models.ObatAlat;
import models.ObatResep;
import models.Resep;
import models.ReturPenjualan;
import models.StokObatAlat;
import models.StokOpname;
import models.TransaksiBulanan;
import models.TransaksiHarian;
import play.data.binding.As;
import play.data.validation.Required;
import play.libs.MimeTypes;
import play.mvc.With;
import tool.AutocompleteValue;
import tool.CommonUtil;
import tool.MyIdGenerator;

@With(Secure.class)
public class PenjualanControl extends BaseController {
	public static void transaksi_retur(ReturPenjualan returPenjualan,
			String hasil) {
		if (returPenjualan == null)
			returPenjualan = new ReturPenjualan();
		if (returPenjualan.getTglReturJual() == null)
			returPenjualan.setTglReturJual(new Date());
		render(returPenjualan, hasil);
	}

	public static void saveReturJual(String simpan, String idReturJual,
			@Required @As("dd-MM-yyyy") Date tglReturJual,
			String key_kodeResep, String namaPasien,
			List<String> key_pilihStok, List<Integer> jmlRetur,
			List<Integer> hargaRetur) {
		ReturPenjualan returPenjualan = new ReturPenjualan();
		returPenjualan.setTglReturJual(tglReturJual);
		returPenjualan.setTglAktivitas(new Date());
		if (returPenjualan.getTglReturJual() == null)
			returPenjualan.setTglReturJual(new Date());
		returPenjualan.setNamaPasien(namaPasien);
		returPenjualan.setKodeResep(key_kodeResep);
		if (CommonUtil.isEmpty(idReturJual)) {
			returPenjualan.validateAndSave();
		} else {
			returPenjualan.setIdReturJual(idReturJual);
			returPenjualan = returPenjualan.merge();
			returPenjualan.validateAndSave();
			DetailReturPenjualan.delete("id_retur_beli=?", idReturJual);
		}
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transaksi_retur(returPenjualan, null);
			return;
		}
		for (int i = 0; i < key_pilihStok.size(); i++) {
			if (key_pilihStok.get(i) != null
					&& !"".equals(key_pilihStok.get(i))) {
				Integer jmlReturJual = jmlRetur.get(i) == null ? 0 : jmlRetur
						.get(i);
				Integer hargaReturJual = hargaRetur.get(i) == null ? 0
						: hargaRetur.get(i);
				DetailReturPenjualan detailReturPenjualan = new DetailReturPenjualan();
				detailReturPenjualan.setIdReturJual(returPenjualan);
				detailReturPenjualan.setIdStok((StokObatAlat) StokObatAlat
						.findById(key_pilihStok.get(i)));
				detailReturPenjualan.setJmlRetur(jmlReturJual);
				detailReturPenjualan.setHargaRetur(hargaReturJual);
				detailReturPenjualan.validateAndSave();
				returPenjualan
						.addDetailReturPenjualanIdReturJual(detailReturPenjualan);
			}
		}
		String hasil = "Retur Penjualan Berhasil Disimpan!";
		if (simpan.equals("Tutup")) {
			for (int i = 0; i < key_pilihStok.size(); i++) {
				if (key_pilihStok.get(i) != null
						&& !"".equals(key_pilihStok.get(i))) {
					StokObatAlat stokObatAlat = StokObatAlat
							.findById(key_pilihStok.get(i));
					Query createNativeQuery = ReturPenjualan
							.em()
							.createNativeQuery(
									"select detail_retur_penjualan.* from detail_retur_penjualan where id_retur_jual= :idReturJual and id_stok= :idStok",
									DetailReturPenjualan.class);
					createNativeQuery.setParameter("idReturJual",
							returPenjualan.getIdReturJual());
					createNativeQuery.setParameter("idStok",
							stokObatAlat.getIdStok());
					DetailReturPenjualan detilReturPenjualan = (DetailReturPenjualan) createNativeQuery
							.getResultList().get(0);
					Integer jmlStokApotekSebelumnya = stokObatAlat
							.getJmlStokApotek();
					Integer jmlStokGudangSebelumnya = stokObatAlat
							.getJmlStokGudang();
					detilReturPenjualan
							.setStokAwalApotek(jmlStokApotekSebelumnya);
					detilReturPenjualan
							.setStokAwalGudang(jmlStokGudangSebelumnya);
					Integer jmlReturJual = jmlRetur.get(i) == null ? 0
							: jmlRetur.get(i);
					Integer hargaReturJual = hargaRetur.get(i) == null ? 0
							: hargaRetur.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotekSebelumnya
							+ jmlReturJual);
					detilReturPenjualan
							.setStokAkhirApotek(jmlStokApotekSebelumnya
									+ jmlReturJual);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					detilReturPenjualan = detilReturPenjualan.merge();
					detilReturPenjualan.validateAndSave();
					TransaksiBulanan.generate(stokObatAlat.getIdStok(),
							jmlStokApotekSebelumnya, jmlStokGudangSebelumnya,
							jmlReturJual, 0, 0, 0, false);
					TransaksiHarian.generate(returPenjualan.getIdReturJual(),
							stokObatAlat.getIdStok(), jmlStokApotekSebelumnya,
							jmlStokGudangSebelumnya, jmlReturJual, 0, 0, 0,
							false);
				}
			}
			returPenjualan.setIdReturJual(idReturJual);
			returPenjualan.setStsTransaksi("1");
			returPenjualan = returPenjualan.merge();
			returPenjualan.validateAndSave();
			hasil = "Retur Penjualan Berhasil Ditutup!";
		} else if (simpan.equals("Buka")) {
			for (int i = 0; i < key_pilihStok.size(); i++) {
				if (key_pilihStok.get(i) != null
						&& !"".equals(key_pilihStok.get(i))) {
					StokObatAlat stokObatAlat = StokObatAlat
							.findById(key_pilihStok.get(i));
					Query createNativeQuery = ReturPenjualan
							.em()
							.createNativeQuery(
									"select detail_retur_penjualan.* from detail_retur_penjualan where id_retur_jual= :idReturJual and id_stok= :idStok",
									DetailReturPenjualan.class);
					createNativeQuery.setParameter("idReturJual",
							returPenjualan.getIdReturJual());
					createNativeQuery.setParameter("idStok",
							stokObatAlat.getIdStok());
					DetailReturPenjualan detilReturPenjualan = (DetailReturPenjualan) createNativeQuery
							.getResultList().get(0);
					Integer jmlStokApotekSebelumnya = stokObatAlat
							.getJmlStokApotek();
					Integer jmlStokGudangSebelumnya = stokObatAlat
							.getJmlStokGudang();
					detilReturPenjualan
							.setStokAwalApotek(jmlStokApotekSebelumnya);
					detilReturPenjualan
							.setStokAwalGudang(jmlStokGudangSebelumnya);
					Integer jmlReturJual = jmlRetur.get(i) == null ? 0
							: jmlRetur.get(i);
					Integer hargaReturJual = hargaRetur.get(i) == null ? 0
							: hargaRetur.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotekSebelumnya
							- jmlReturJual);
					detilReturPenjualan
							.setStokAkhirApotek(jmlStokApotekSebelumnya
									- jmlReturJual);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					detilReturPenjualan = detilReturPenjualan.merge();
					detilReturPenjualan.validateAndSave();
					TransaksiBulanan.generate(stokObatAlat.getIdStok(),
							jmlStokApotekSebelumnya, jmlStokGudangSebelumnya,
							jmlReturJual, 0, 0, 0, true);
					TransaksiHarian.generate(returPenjualan.getIdReturJual(),
							stokObatAlat.getIdStok(), jmlStokApotekSebelumnya,
							jmlStokGudangSebelumnya, jmlReturJual, 0, 0, 0,
							true);
				}
			}
			returPenjualan.setIdReturJual(idReturJual);
			returPenjualan.setStsTransaksi(null);
			returPenjualan = returPenjualan.merge();
			returPenjualan.validateAndSave();
			hasil = "Retur Penjualan Berhasil Dibuka!";
		}
		renderTemplate("PenjualanControl/transaksi_retur.html", returPenjualan,
				hasil);
	}

	public static void autocompleteResep(final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		List<Resep> findAll = Resep.find("kode_resep like ?", "%" + term + "%")
				.fetch();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			Resep resepTmp = (Resep) iterator.next();
			response.add(new AutocompleteValue(resepTmp.getKodeResep(),
					resepTmp.getKodeResep()));
		}
		renderJSON(response);
	}

	public static void savePenjualan(String simpan, String idResep,
			String kodeResep, String namaPasien, String alamatPasien,
			String namaDokter, String alamatDokter,
			@As("dd-MM-yyyy") Date tglPenjualan, String key_idObatAlat,
			String key_pilihStok, Integer jumlahObatAlat, Integer hargaObatAlat) {
		Resep resep = new Resep();
		String hargaTotalPenjualan = null;
		if (CommonUtil.isNotEmpty(idResep))
			resep.setIdResep(idResep);
		resep.setKodeResep(kodeResep);
		resep.setTglPenjualan(tglPenjualan);
		resep.setTglResep(tglPenjualan);
		resep.setNamaPasien(namaPasien);
		resep.setAlamatPasien(alamatPasien);
		resep.setNamaDokter(namaDokter);
		resep.setAlamatDokter(alamatDokter);
		if ("Tutup".equals(simpan))
			resep.setStsTransaksi("1");
		else if ("Buka".equals(simpan))
			resep.setStsTransaksi(null);
		resep = resep.merge();
		resep = resep.save();
		if (CommonUtil.isNotEmpty(key_idObatAlat)) {
			if (!"Tutup".equals(simpan)) {
				DetailResep detailResep = new DetailResep();
				detailResep.setIdResep(resep);
				detailResep.setJmlObat(jumlahObatAlat);
				detailResep = detailResep.merge();
				detailResep = detailResep.save();
				ObatResep obatResep = new ObatResep();
				obatResep.setIdStok((StokObatAlat) StokObatAlat
						.findById(key_pilihStok));
				obatResep.setJmlObatResep(jumlahObatAlat);
				obatResep.setHargaObat(hargaObatAlat);
				obatResep.setIdResepDtl(detailResep);
				obatResep = obatResep.merge();
				obatResep.save();
			}
			List<DetailResep> fetch = DetailResep.find("id_resep=?",
					resep.getIdResep()).fetch();
			for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
				DetailResep tmp = (DetailResep) iterator.next();
				resep.addDetailResepIdResep(tmp);
			}
			if ("Tutup".equals(simpan)) {
				List<DetailResep> dtlResepList = DetailResep.find("id_resep=?",
						resep.getIdResep()).fetch();
				for (Iterator iterator = dtlResepList.iterator(); iterator
						.hasNext();) {
					DetailResep dtlTmp = (DetailResep) iterator.next();
					Set<ObatResep> obatResepIdResepDtl = dtlTmp
							.getObatResepIdResepDtl();
					for (Iterator iterator2 = obatResepIdResepDtl.iterator(); iterator2
							.hasNext();) {
						ObatResep obatResepTmp = (ObatResep) iterator2.next();
						StokObatAlat stokObatAlat = obatResepTmp.getIdStok();
						Integer jmlStokA = stokObatAlat.getJmlStokApotek();
						Integer jmlStokG = stokObatAlat.getJmlStokGudang();
						obatResepTmp.setStokAwalApotek(stokObatAlat
								.getJmlStokApotek());
						obatResepTmp.setStokAwalGudang(stokObatAlat
								.getJmlStokGudang());
						if (obatResepTmp.getJmlObatResep() != null
								&& obatResepTmp.getJmlObatResep() > 0) {
							Integer jmlStokApotek = stokObatAlat
									.getJmlStokApotek()
									- obatResepTmp.getJmlObatResep();
							stokObatAlat.setJmlStokApotek(jmlStokApotek);
							obatResepTmp.setStokAkhirApotek(stokObatAlat
									.getJmlStokApotek());
							obatResepTmp.setStokAkhirGudang(stokObatAlat
									.getJmlStokGudang());
							stokObatAlat = stokObatAlat.merge();
							stokObatAlat.validateAndSave();
							obatResepTmp = obatResepTmp.merge();
							obatResepTmp.save();
							TransaksiBulanan.generate(stokObatAlat.getIdStok(),
									jmlStokA, jmlStokG, 0, 0,
									obatResepTmp.getJmlObatResep(), 0, false);
							TransaksiHarian.generate(dtlTmp.getIdResepDtl(),
									stokObatAlat.getIdStok(), jmlStokA,
									jmlStokG, 0, 0,
									obatResepTmp.getJmlObatResep(), 0, false);
						}
					}
				}
			} else if ("Buka".equals(simpan)) {
				List<DetailResep> dtlResepList = DetailResep.find("id_resep=?",
						resep.getIdResep()).fetch();
				for (Iterator iterator = dtlResepList.iterator(); iterator
						.hasNext();) {
					DetailResep dtlTmp = (DetailResep) iterator.next();
					Set<ObatResep> obatResepIdResepDtl = dtlTmp
							.getObatResepIdResepDtl();
					for (Iterator iterator2 = obatResepIdResepDtl.iterator(); iterator2
							.hasNext();) {
						ObatResep obatResepTmp = (ObatResep) iterator2.next();
						StokObatAlat stokObatAlat = obatResepTmp.getIdStok();
						Integer jmlStokA = stokObatAlat.getJmlStokApotek();
						Integer jmlStokG = stokObatAlat.getJmlStokGudang();
						obatResepTmp.setStokAwalApotek(stokObatAlat
								.getJmlStokApotek());
						obatResepTmp.setStokAwalGudang(stokObatAlat
								.getJmlStokGudang());
						if (obatResepTmp.getJmlObatResep() != null
								&& obatResepTmp.getJmlObatResep() > 0) {
							Integer jmlStokApotek = stokObatAlat
									.getJmlStokApotek()
									+ obatResepTmp.getJmlObatResep();
							stokObatAlat.setJmlStokApotek(jmlStokApotek);
							obatResepTmp.setStokAkhirApotek(stokObatAlat
									.getJmlStokApotek());
							obatResepTmp.setStokAkhirGudang(stokObatAlat
									.getJmlStokGudang());
							stokObatAlat = stokObatAlat.merge();
							stokObatAlat.validateAndSave();
							obatResepTmp = obatResepTmp.merge();
							obatResepTmp.save();
							TransaksiBulanan.generate(stokObatAlat.getIdStok(),
									jmlStokA, jmlStokG, 0, 0,
									obatResepTmp.getJmlObatResep(), 0, true);
							TransaksiHarian.generate(dtlTmp.getIdResepDtl(),
									stokObatAlat.getIdStok(), jmlStokA,
									jmlStokG, 0, 0,
									obatResepTmp.getJmlObatResep(), 0, true);
						}
					}
				}
			}
			DecimalFormat decimalFormat = new DecimalFormat("###########0");
			DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
			formatSymbols.setGroupingSeparator('.');
			formatSymbols.setDecimalSeparator(',');
			decimalFormat.setDecimalFormatSymbols(formatSymbols);
			decimalFormat.setGroupingUsed(true);
			decimalFormat.setGroupingSize(3);
			Integer hargaTotal = 0;
			List<DetailResep> dtlResepList = DetailResep.find("id_resep=?",
					resep.getIdResep()).fetch();
			for (Iterator iterator = dtlResepList.iterator(); iterator
					.hasNext();) {
				DetailResep detailResepTmp = (DetailResep) iterator.next();
				List<ObatResep> obatResepList = ObatResep.find(
						"id_resep_dtl=?", detailResepTmp.getIdResepDtl())
						.fetch();
				for (Iterator iterator2 = obatResepList.iterator(); iterator2
						.hasNext();) {
					ObatResep obatResepTmp = (ObatResep) iterator2.next();
					if (obatResepTmp.getHargaObat() != null
							&& obatResepTmp.getJmlObatResep() != null)
						hargaTotal += obatResepTmp.getHargaObat()
								* obatResepTmp.getJmlObatResep();
				}
			}
			hargaTotalPenjualan = decimalFormat.format(hargaTotal);
		}
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		renderTemplate("PenjualanControl/transaksi.html", resep,
				jenisHargaList, hargaTotalPenjualan);
	}

	public static void transaksi(Resep resep, String hasil) {
		if (resep == null)
			resep = new Resep();
		if (resep.getKodeResep() == null)
			resep.setKodeResep((String) MyIdGenerator.generateKode("resep",
					CommonUtil.getFormatThn(new Date()), true));
		if (resep.getTglPenjualan() == null)
			resep.setTglPenjualan(new Date());
		if (resep.getNamaPasien() == null)
			resep.setNamaPasien("Pasien Umum");
		if (resep.getAlamatPasien() == null)
			resep.setAlamatPasien("-");
		if (resep.getNamaDokter() == null)
			resep.setNamaDokter("Dokter Umum");
		if (resep.getAlamatDokter() == null)
			resep.setAlamatDokter("-");
		String key_idObatAlat = null;
		Integer stokApotek = null;
		Integer jumlahObatAlat = null;
		String hargaTotalPenjualan = "0";
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		render(resep, key_idObatAlat, stokApotek, jumlahObatAlat,
				jenisHargaList, hargaTotalPenjualan);
	}

	public static void showKategori(String idObatAlat) {
		final List data = new ArrayList();
		ObatAlat obatAlat = ObatAlat.findById(idObatAlat);
		data.add(obatAlat.getKategoriObat());
		renderJSON(data);
	}

	public static void showHargaStok(String idObatAlat, String idJnsHarga) {
		final List<AutocompleteValue> resp = new ArrayList<AutocompleteValue>();
		Query createNativeQuery = StokObatAlat
				.em()
				.createNativeQuery(
						"select sum(x.jml_stok_apotek) from stok_obat_alat x where x.id_obat_alat= :idObatAlat");
		createNativeQuery.setParameter("idObatAlat", idObatAlat);
		Number stokApotek = (Number) createNativeQuery.getSingleResult();
		DecimalFormat decimalFormat = new DecimalFormat("###########0");
		decimalFormat.setDecimalSeparatorAlwaysShown(false);
		resp.add(new AutocompleteValue(stokApotek == null ? "0" : decimalFormat
				.format(stokApotek), "stokApotek"));
		resp.add(new AutocompleteValue(stokApotek == null ? "0" : "1",
				"jumlahObatAlat"));
		List<HargaObat> fetch = HargaObat.find(
				"id_obat_alat=? and id_jns_harga=? and active_sts='A'",
				idObatAlat, idJnsHarga).fetch();
		if (!fetch.isEmpty()) {
			HargaObat hargaObat = fetch.get(0);
			resp.add(new AutocompleteValue(
					hargaObat.getHargaObat() == null ? "0" : decimalFormat
							.format(hargaObat.getHargaObat()), "hargaObatAlat"));
		}
		renderJSON(resp);
	}

	public static void showStok(String idObatAlat) {
		List<StokObatAlat> fetch = StokObatAlat
				.find("id_obat_alat=? and (tgl_kadaluarsa is null or date_trunc('day', tgl_kadaluarsa)>?) order by tgl_kadaluarsa asc",
						idObatAlat, new Date()).fetch();
		final List<AutocompleteValue> resp = new ArrayList<AutocompleteValue>();
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			StokObatAlat stokObatAlat = (StokObatAlat) iterator.next();
			resp.add(new AutocompleteValue(stokObatAlat.getIdStok(),
					stokObatAlat.getLabelStok()));
		}
		renderJSON(resp);
	}

	public static void laporan(List obatResep,
			@As("dd-MM-yyyy") Date tglJualAwal,
			@As("dd-MM-yyyy") Date tglJualAkhir, String idObatAlat) {
		if (obatResep == null)
			obatResep = new ArrayList();
		tglJualAwal = new Date();
		tglJualAkhir = new Date();
		render(obatResep, tglJualAwal, tglJualAkhir, idObatAlat);
	}

	public static void generateLaporan(String key_idObatAlat,
			@As("dd-MM-yyyy") Date tglJualAwal,
			@As("dd-MM-yyyy") Date tglJualAkhir) {
		String sql = "select obat_resep.*"
				+ " from detail_resep join obat_resep on obat_resep.id_resep_dtl=detail_resep.id_resep_dtl"
				+ " join resep on detail_resep.id_resep=resep.id_resep"
				+ " join stok_obat_alat on obat_resep.id_stok=stok_obat_alat.id_stok"
				+ " where sts_transaksi is not null";
		if (tglJualAwal != null)
			sql += " and resep.tgl_penjualan >= :tglJualAwal";
		if (tglJualAkhir != null)
			sql += " and resep.tgl_penjualan <= :tglJualAkhir";
		if (!key_idObatAlat.equals(""))
			sql += " and stok_obat_alat.id_obat_alat = :key_idObatAlat";

		sql += " order by resep.id_resep";
		Query createNativeQuery = StokOpname.em().createNativeQuery(sql,
				ObatResep.class);

		if (tglJualAwal != null) {
			createNativeQuery.setParameter("tglJualAwal", tglJualAwal);
		}
		if (tglJualAkhir != null) {
			createNativeQuery.setParameter("tglJualAkhir", tglJualAkhir);
		}
		if (!key_idObatAlat.equals("")) {
			createNativeQuery.setParameter("key_idObatAlat", key_idObatAlat);
		}
		List obatResepList = null;
		try {
			obatResepList = createNativeQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentTypeIfNotSet(MimeTypes
				.getContentType("laporanPenjualan.xls"));
		response.setHeader("Content-Disposition",
				"attachment; filename=laporanPenjualan.xls");
		renderTemplate("excel/laporanPenjualan.html", obatResepList);
	}

	public static void monitoring_penjualan(List penjualanList,
			@As("dd-MM-yyyy") Date tglPenjualanAwal,
			@As("dd-MM-yyyy") Date tglPenjualanAkhir, String idObatAlat) {
		if (penjualanList == null)
			penjualanList = new ArrayList();
		tglPenjualanAwal = new Date();
		tglPenjualanAkhir = new Date();
		render(penjualanList, tglPenjualanAwal, tglPenjualanAkhir, idObatAlat);
	}

	public static void cariPenjualan(String key_idObatAlat,
			@As("dd-MM-yyyy") Date tglPenjualanAwal,
			@As("dd-MM-yyyy") Date tglPenjualanAkhir) {
		List penjualanList = Resep.monitoringPenjualan(key_idObatAlat,
				tglPenjualanAwal, tglPenjualanAkhir);
		renderTemplate("PenjualanControl/monitoring_penjualan.html",
				penjualanList, tglPenjualanAwal, tglPenjualanAkhir,
				key_idObatAlat);
	}

	public static void lihatPenjualan(String idResep) {
		Resep resep = Resep.findById(idResep);
		DecimalFormat decimalFormat = new DecimalFormat("###########0");
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setGroupingSeparator('.');
		formatSymbols.setDecimalSeparator(',');
		decimalFormat.setDecimalFormatSymbols(formatSymbols);
		decimalFormat.setGroupingUsed(true);
		decimalFormat.setGroupingSize(3);
		String hargaTotalPenjualan = null;
		Integer hargaTotal = 0;
		List<DetailResep> dtlResepList = DetailResep.find("id_resep=?",
				resep.getIdResep()).fetch();
		for (Iterator iterator = dtlResepList.iterator(); iterator.hasNext();) {
			DetailResep detailResepTmp = (DetailResep) iterator.next();
			List<ObatResep> obatResepList = ObatResep.find("id_resep_dtl=?",
					detailResepTmp.getIdResepDtl()).fetch();
			for (Iterator iterator2 = obatResepList.iterator(); iterator2
					.hasNext();) {
				ObatResep obatResepTmp = (ObatResep) iterator2.next();
				if (obatResepTmp.getHargaObat() != null
						&& obatResepTmp.getJmlObatResep() != null)
					hargaTotal += obatResepTmp.getHargaObat()
							* obatResepTmp.getJmlObatResep();
			}
		}
		hargaTotalPenjualan = decimalFormat.format(hargaTotal);
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		renderTemplate("PenjualanControl/transaksi.html", resep,
				jenisHargaList, hargaTotalPenjualan);
	}
}
