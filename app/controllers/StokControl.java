package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.ApotekConstant;
import models.DetilOpname;
import models.DetilPembelian;
import models.HargaObat;
import models.HargaObatId;
import models.JenisHarga;
import models.JenisObatAlat;
import models.LaporanJenisObat;
import models.ObatAlat;
import models.Pembelian;
import models.StokObatAlat;
import models.StokOpname;
import models.TransaksiBulanan;
import models.TransaksiHarian;
import play.data.binding.As;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.libs.MimeTypes;
import play.mvc.Controller;
import play.mvc.With;
import tool.AutocompleteValue;
import tool.CommonUtil;

@With(Secure.class)
public class StokControl extends BaseController {
	public static int AUTOCOMPLETE_MAX = 20;

	public static void transaksi(StokOpname stokOpname, String hasil) {
		if (stokOpname == null)
			stokOpname = new StokOpname();
		if (stokOpname.getTglStokOpname() == null)
			stokOpname.setTglStokOpname(new Date());
		render(stokOpname, hasil);
	}

	public static void saveOpname(String idStokOpname,
			@Required @As("dd-MM-yyyy") Date tglStokOpname,
			String descStokOpname, String simpan, List<String> key_kode_obat,
			@As("dd-MM-yyyy") List<Date> tglKadaluarsa,
			List<Integer> stokApotekSekarang, List<Integer> stokGudangSekarang) {
		StokOpname stokOpname = new StokOpname();
		stokOpname.setTglStokOpname(tglStokOpname);
		if (stokOpname.getTglStokOpname() == null)
			stokOpname.setTglStokOpname(new Date());
		stokOpname.setDescStokOpname(descStokOpname);
		stokOpname.setTglAktivitas(new Date());
		if (CommonUtil.isEmpty(idStokOpname)) {
			stokOpname.validateAndSave();
		} else {
			stokOpname.setIdStokOpname(idStokOpname);
			stokOpname = stokOpname.merge();
			stokOpname.validateAndSave();
			DetilOpname.delete("id_stok_opname=?", idStokOpname);
		}
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transaksi(stokOpname, null);
			return;
		}
		for (int i = 0; i < key_kode_obat.size(); i++) {
			if (key_kode_obat.get(i) != null
					&& !"".equals(key_kode_obat.get(i))) {
				Integer jmlApotekSekarang = stokApotekSekarang.get(i) == null ? 0
						: stokApotekSekarang.get(i);
				Integer jmlGudangSekarang = stokGudangSekarang.get(i) == null ? 0
						: stokGudangSekarang.get(i);
				Integer jmlSekarang = jmlApotekSekarang + jmlGudangSekarang;
				DetilOpname detilOpname = new DetilOpname();
				StokObatAlat stokObatAlat = new StokObatAlat();
				stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
						.findById(key_kode_obat.get(i)));
				stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
				List<StokObatAlat> fetch = StokObatAlat
						.find("id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
								stokObatAlat.getIdObatAlat().getIdObatAlat(),
								stokObatAlat.getTglKadaluarsa()).fetch();
				Integer jmlStokApotek = 0;
				Integer jmlStokGudang = 0;
				if (fetch.isEmpty()) {
					stokObatAlat.setJmlStokApotek(0);
					stokObatAlat.setJmlStokGudang(0);
					stokObatAlat.validateAndSave();
					if (validation.hasErrors()) {
						params.flash(); // add http parameters to the flash
										// scope
						validation.keep(); // keep the errors for the next
											// request
						transaksi(stokOpname, null);
						return;
					}
				} else {
					StokObatAlat tmp = fetch.get(0);
					stokObatAlat.setIdStok(tmp.getIdStok());
					jmlStokApotek = tmp.getJmlStokApotek() == null ? 0 : tmp
							.getJmlStokApotek();
					jmlStokGudang = tmp.getJmlStokGudang() == null ? 0 : tmp
							.getJmlStokGudang();
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
				}
				Integer totalStok = jmlStokApotek + jmlStokGudang;
				detilOpname.setIdStokOpname(stokOpname);
				detilOpname.setIdStok(stokObatAlat);
				detilOpname.setJmlApotekSebelumnya(jmlStokApotek);
				detilOpname.setJmlApotekSekarang(jmlApotekSekarang);
				detilOpname.setTglKadaluarsa(tglKadaluarsa.get(i));
				detilOpname.setJmlGudangSebelumnya(jmlStokGudang);
				detilOpname.setJmlGudangSekarang(jmlGudangSekarang);
				detilOpname.setJmlSebelumnya(totalStok);
				detilOpname.setJmlSekarang(jmlSekarang);
				detilOpname.validateAndSave();
				stokOpname.addDetilOpnameIdStokOpname(detilOpname);
			}
		}

		String hasil = "Stok Opname Berhasil Disimpan!";

		if (simpan.equals("Tutup")) {
			for (int i = 0; i < key_kode_obat.size(); i++) {
				if (key_kode_obat.get(i) != null
						&& !"".equals(key_kode_obat.get(i))) {
					StokObatAlat stokObatAlat = new StokObatAlat();
					stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
							.findById(key_kode_obat.get(i)));
					stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
					List<StokObatAlat> fetch = StokObatAlat
							.find("id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
									stokObatAlat.getIdObatAlat()
											.getIdObatAlat(),
									stokObatAlat.getTglKadaluarsa()).fetch();
					StokObatAlat tmp = fetch.get(0);
					Integer jmlStokApotekSblm = tmp.getJmlStokApotek();
					Integer jmlStokGudangSblm = tmp.getJmlStokGudang();
					stokObatAlat.setIdStok(tmp.getIdStok());
					Integer jmlStokApotek = stokApotekSekarang.get(i) == null ? 0
							: stokApotekSekarang.get(i);
					Integer jmlStokGudang = stokGudangSekarang.get(i) == null ? 0
							: stokGudangSekarang.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					TransaksiBulanan.generate(tmp.getIdStok(),
							jmlStokApotekSblm, jmlStokGudangSblm, jmlStokApotek
									- jmlStokApotekSblm > 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm > 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, jmlStokApotek
									- jmlStokApotekSblm < 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm < 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, false);
					TransaksiHarian.generate(stokOpname.getIdStokOpname(),
							tmp.getIdStok(), jmlStokApotekSblm,
							jmlStokGudangSblm, jmlStokApotek
									- jmlStokApotekSblm > 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm > 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, jmlStokApotek
									- jmlStokApotekSblm < 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm < 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, false);
				}
			}
			stokOpname.setIdStokOpname(idStokOpname);
			stokOpname.setStsTransaksi("1");
			stokOpname = stokOpname.merge();
			stokOpname.validateAndSave();

			hasil = "Stok Opname Berhasil Ditutup!";
		} else if (simpan.equals("Buka")) {
			for (int i = 0; i < key_kode_obat.size(); i++) {
				if (key_kode_obat.get(i) != null
						&& !"".equals(key_kode_obat.get(i))) {
					StokObatAlat stokObatAlat = new StokObatAlat();
					stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
							.findById(key_kode_obat.get(i)));
					stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
					List<StokObatAlat> fetch = StokObatAlat
							.find("id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
									stokObatAlat.getIdObatAlat()
											.getIdObatAlat(),
									stokObatAlat.getTglKadaluarsa()).fetch();
					StokObatAlat tmp = fetch.get(0);
					Integer jmlStokApotekSblm = tmp.getJmlStokApotek();
					Integer jmlStokGudangSblm = tmp.getJmlStokGudang();
					stokObatAlat.setIdStok(tmp.getIdStok());
					Integer jmlStokApotek = stokApotekSekarang.get(i) == null ? 0
							: stokApotekSekarang.get(i);
					Integer jmlStokGudang = stokGudangSekarang.get(i) == null ? 0
							: stokGudangSekarang.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					TransaksiBulanan.generate(tmp.getIdStok(),
							jmlStokApotekSblm, jmlStokGudangSblm, jmlStokApotek
									- jmlStokApotekSblm > 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm > 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, jmlStokApotek
									- jmlStokApotekSblm < 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm < 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, true);
					TransaksiHarian.generate(stokOpname.getIdStokOpname(),
							tmp.getIdStok(), jmlStokApotekSblm,
							jmlStokGudangSblm, jmlStokApotek
									- jmlStokApotekSblm > 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm > 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, jmlStokApotek
									- jmlStokApotekSblm < 0 ? jmlStokApotek
									- jmlStokApotekSblm : 0, jmlStokGudang
									- jmlStokGudangSblm < 0 ? jmlStokGudang
									- jmlStokGudangSblm : 0, true);
				}
			}
			stokOpname.setIdStokOpname(idStokOpname);
			stokOpname.setStsTransaksi(null);
			stokOpname = stokOpname.merge();
			stokOpname.validateAndSave();

			hasil = "Stok Opname Berhasil Dibuka!";
		}

		renderTemplate("StokControl/transaksi.html", stokOpname, hasil);

	}

	public static void showQtySblmnya(String idObatAlat,
			@As("dd-MM-yyyy") Date tglKadaluarsa) {
		List<StokObatAlat> fetch = StokObatAlat.find(
				"id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
				idObatAlat, tglKadaluarsa).fetch();
		final List data = new ArrayList();
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			StokObatAlat stokObatAlat = (StokObatAlat) iterator.next();
			Integer jmlStokApotek = stokObatAlat.getJmlStokApotek();
			Integer jmlStokGudang = stokObatAlat.getJmlStokGudang();
			Integer totalStok = jmlStokApotek + jmlStokGudang;
			data.add(jmlStokApotek);
			data.add(jmlStokGudang);
			data.add(totalStok);
		}
		renderJSON(data);
	}

	public static void monitoring_stok() {
		List jenisObatList = JenisObatAlat.findAll();
		render(jenisObatList);
	}

	public static void cariStok(String jnsObatAlat, String key_idObatAlat) {
		List jenisObatList = JenisObatAlat.findAll();
		String sql = "select * from obat_alat where 1=1 ";
		if (CommonUtil.isNotEmpty(key_idObatAlat)) {
			sql += "and id_obat_alat=:key_idObatAlat ";
		}
		if (CommonUtil.isNotEmpty(jnsObatAlat)) {
			sql += "and id_jns_obat_alat=:jnsObatAlat ";
		}
		sql += "order by id_obat_alat";
		Query createNativeQuery = ObatAlat.em().createNativeQuery(sql,
				ObatAlat.class);
		if (CommonUtil.isNotEmpty(key_idObatAlat))
			createNativeQuery.setParameter("key_idObatAlat", key_idObatAlat);
		if (CommonUtil.isNotEmpty(jnsObatAlat))
			createNativeQuery.setParameter("jnsObatAlat", jnsObatAlat);
		List<ObatAlat> stokGlobalList = createNativeQuery.getResultList();
		renderTemplate("StokControl/monitoring_stok.html", jnsObatAlat,
				key_idObatAlat, stokGlobalList, jenisObatList);
	}

	public static void hargaObat() {
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		render(jenisHargaList);
	}

	public static void saveHarga(String key_idObatAlat, Integer hargaRata,
			Map<String, String> laba, Map<String, String> hargaJual) {
		String thnBlnHarga = CommonUtil.getFormatThnBln(new Date());
		HargaObat.delete("id_obat_alat=? and active_sts='A'", key_idObatAlat);
		Set keySet = laba.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String idJnsHarga = (String) iterator.next();
			String percentLaba = laba.get(idJnsHarga);
			String hargaJualTmp = hargaJual.get(idJnsHarga);
			HargaObat hargaObat = new HargaObat();
			hargaObat.setHargaObatId(new HargaObatId(key_idObatAlat,
					idJnsHarga, thnBlnHarga));
			hargaObat.setIdObatAlat((ObatAlat) ObatAlat
					.findById(key_idObatAlat));
			hargaObat.setIdJnsHarga((JenisHarga) JenisHarga
					.findById(idJnsHarga));
			hargaObat.setPercentLaba(Integer.parseInt(percentLaba));
			hargaObat.setHargaObat(Double.parseDouble(hargaJualTmp));
			hargaObat.setActiveSts("A");
			hargaObat.save();
		}
		ObatAlat tmp = ObatAlat.findById(key_idObatAlat);
		String labelObatAlat = tmp.getLabelObat();
		String volumeObatAlat = tmp.getVolumeObatAlat();
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		renderTemplate("StokControl/hargaObat.html", key_idObatAlat,
				labelObatAlat, volumeObatAlat, thnBlnHarga, hargaRata, laba,
				hargaJual, jenisHargaList);
	}

	public static void showHarga(String idObatAlat) {
		final List<AutocompleteValue> resp = new ArrayList<AutocompleteValue>();
		ObatAlat obatAlatTmp = ObatAlat.findById(idObatAlat);
		String data = obatAlatTmp.getVolumeObatAlat();
		resp.add(new AutocompleteValue(data, "volume"));
		Query createNativeQuery = StokObatAlat
				.em()
				.createNativeQuery(
						"select avg(harga_beli_stok-coalesce(disc_charge,0)+((harga_beli_stok-coalesce(disc_charge,0))*coalesce(ppn_stok,0)/100)) from stok_obat_alat x where x.id_obat_alat= :idObatAlat");
		createNativeQuery.setParameter("idObatAlat", idObatAlat);
		Number hargaRata = (Number) createNativeQuery.getSingleResult();
		DecimalFormat decimalFormat = new DecimalFormat("###########0");
		decimalFormat.setDecimalSeparatorAlwaysShown(false);
		resp.add(new AutocompleteValue(hargaRata != null ? decimalFormat
				.format(hargaRata) : null, "hargaRata"));
		String thnBlnHarga = CommonUtil.getFormatThnBln(new Date());
		List<HargaObat> fetch = HargaObat.find(
				"id_obat_alat=? and active_sts='A'", idObatAlat).fetch();
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			HargaObat hargaObat = (HargaObat) iterator.next();
			thnBlnHarga = hargaObat.getHargaObatId().getThn_bln_harga();
			resp.add(new AutocompleteValue("" + hargaObat.getPercentLaba(),
					"laba." + hargaObat.getIdJnsHarga().getIdJnsHarga()));
			resp.add(new AutocompleteValue("" + hargaObat.getHargaObat(),
					"hargaJual." + hargaObat.getIdJnsHarga().getIdJnsHarga()));
		}
		resp.add(new AutocompleteValue(thnBlnHarga, "thnBlnHarga"));
		renderJSON(resp);
	}

	public static void monitoring_opname(List stokOpname,
			@As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir, String idObatAlat) {
		if (stokOpname == null)
			stokOpname = new ArrayList();
		tglPembelianAwal = new Date();
		tglPembelianAkhir = new Date();
		render(stokOpname, tglPembelianAwal, tglPembelianAkhir, idObatAlat);
	}

	public static void cariOpname(String key_idObatAlat,
			@As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir) {
		StokOpname so = new StokOpname();
		List stokOpname = so.monitoringOpname(key_idObatAlat, tglPembelianAwal,
				tglPembelianAkhir);
		renderTemplate("StokControl/monitoring_opname.html", stokOpname,
				tglPembelianAwal, tglPembelianAkhir, key_idObatAlat);
	}

	public static void lihatOpname(String idStokOpname) {
		StokOpname stokOpname = StokOpname.findById(idStokOpname);
		String hasil = null;
		renderTemplate("StokControl/transaksi.html", stokOpname, hasil);
	}

	public static void autocompleteKodeObatDanJenis(final String jnsObatAlat,
			final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		String sql = "select * from obat_alat where 1=1 ";
		if (CommonUtil.isNotEmpty(jnsObatAlat))
			sql += "and id_jns_obat_alat=:jnsObatAlat ";
		if (CommonUtil.isNotEmpty(term))
			sql += "and lower(nama_obat_alat) like lower(:term) or lower(kode_obat_alat) like lower(:term) ";
		Query createNativeQuery = ObatAlat.em().createNativeQuery(sql,
				ObatAlat.class);
		if (CommonUtil.isNotEmpty(jnsObatAlat))
			createNativeQuery.setParameter("jnsObatAlat", jnsObatAlat);
		if (CommonUtil.isNotEmpty(term))
			createNativeQuery.setParameter("term", "%" + term + "%");
		List<ObatAlat> findAll = createNativeQuery.getResultList();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			ObatAlat obatAlatTmp = (ObatAlat) iterator.next();
			response.add(new AutocompleteValue(obatAlatTmp.getIdObatAlat(),
					obatAlatTmp.getKodeObatAlat() + " - "
							+ obatAlatTmp.getNamaObatAlat()));
		}
		renderJSON(response);
	}

	public static void laporan(List stokOpname,
			@As("dd-MM-yyyy") Date tglOpnameAwal,
			@As("dd-MM-yyyy") Date tglOpnameAkhir, String idObatAlat) {
		if (stokOpname == null)
			stokOpname = new ArrayList();
		tglOpnameAwal = new Date();
		tglOpnameAkhir = new Date();
		render(stokOpname, tglOpnameAwal, tglOpnameAkhir, idObatAlat);
	}

	public static void generateLaporan(String key_idObatAlat,
			@As("dd-MM-yyyy") Date tglOpnameAwal,
			@As("dd-MM-yyyy") Date tglOpnameAkhir) {
		String sql = "select detil_opname.*"
				+ " from stok_opname join detil_opname on stok_opname.id_stok_opname=detil_opname.id_stok_opname"
				+ " join stok_obat_alat on detil_opname.id_stok=stok_obat_alat.id_stok"
				+ " where sts_transaksi is not null";
		if (tglOpnameAwal != null)
			sql += " and stok_opname.tgl_stok_opname >= :tglOpnameAwal";
		if (tglOpnameAkhir != null)
			sql += " and stok_opname.tgl_stok_opname <= :tglOpnameAkhir";
		if (!key_idObatAlat.equals(""))
			sql += " and stok_obat_alat.id_obat_alat = :key_idObatAlat";

		sql += " order by detil_opname.id_stok_opname";
		Query createNativeQuery = StokOpname.em().createNativeQuery(sql,
				DetilOpname.class);

		if (tglOpnameAwal != null) {
			createNativeQuery.setParameter("tglOpnameAwal", tglOpnameAwal);
		}
		if (tglOpnameAkhir != null) {
			createNativeQuery.setParameter("tglOpnameAkhir", tglOpnameAkhir);
		}
		if (!key_idObatAlat.equals("")) {
			createNativeQuery.setParameter("key_idObatAlat", key_idObatAlat);
		}
		List detilOpnameList = null;
		try {
			detilOpnameList = createNativeQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentTypeIfNotSet(MimeTypes
				.getContentType("laporanStokOpname.xls"));
		response.setHeader("Content-Disposition",
				"attachment; filename=laporanStokOpname.xls");
		renderTemplate("excel/laporanStokOpname.html", detilOpnameList);
	}

	public static void laporanPsikotropika(Integer[] tahunList,
			Integer blnFilter, Integer thnFilter) {
		Calendar instance = Calendar.getInstance();
		blnFilter = instance.get(Calendar.MONTH) + 1;
		thnFilter = instance.get(Calendar.YEAR);
		if (tahunList == null) {
			tahunList = new Integer[9];
			instance.add(Calendar.YEAR, -3);
			for (int i = 0; i < tahunList.length; i++) {
				tahunList[i] = instance.get(Calendar.YEAR);
				instance.add(Calendar.YEAR, 1);
			}
		}
		render(tahunList, blnFilter, thnFilter);
	}

	public static void generateLaporanPsikotropika(String generate,
			Integer blnFilter, Integer thnFilter) {
		if ("Rekap Psikotropika".equals(generate)) {
			generateRekapPsikotropika(blnFilter, thnFilter, "2");
		} else if ("Detail Psikotropika".equals(generate)) {
			generateDetailPsikotropika(blnFilter, thnFilter, "2");
		} else if ("Rekap Narkotika".equals(generate)) {
			generateRekapPsikotropika(blnFilter, thnFilter, "3");
		} else if ("Detail Narkotika".equals(generate)) {
			generateDetailPsikotropika(blnFilter, thnFilter, "3");
		}
	}

	public static void generateRekapPsikotropika(Integer blnFilter,
			Integer thnFilter, String kategori) {
		String blnharian = "";
		String blnbulanan = "";
		String blnsblmbulanan = "";
		if (blnFilter != null) {
			blnharian = " and transaksi_harian.thnblntgl_transaksi between :thnblnAwal and :thnblnAkhir ";
			blnbulanan = " and thnbln_transaksi= :thnbln ";
			blnsblmbulanan = " and thnbln_transaksi< :thnbln ";
		}
		String sql = "select coalesce(masuk.id_obat_alat,keluar.id_obat_alat) as id_obat_alat, "
				+ "coalesce(masuk.id_obat_alat,keluar.id_obat_alat) as id_transaksi, "
				+ "coalesce(masuk.nama_obat_alat,keluar.nama_obat_alat) as nama_obat_alat,"
				+ "stok_awal+coalesce(("
				+ "select sum(stok_akhir_apotek+stok_akhir_gudang) from transaksi_bulanan tb "
				+ "join stok_obat_alat soa on tb.id_stok=soa.id_stok "
				+ "where soa.id_obat_alat=coalesce(masuk.id_obat_alat,keluar.id_obat_alat) "
				+ blnsblmbulanan
				+ "),0) as stok_awal,'' as pemasukan_dari,coalesce(pemasukan_jumlah,0) as pemasukan_jumlah,"
				+ "'' as pengeluaran_untuk,coalesce(pengeluaran_jumlah,0) as pengeluaran_jumlah,stok_akhir+coalesce(("
				+ "select sum(stok_akhir_apotek+stok_akhir_gudang) from transaksi_bulanan tb "
				+ "join stok_obat_alat soa on tb.id_stok=soa.id_stok "
				+ "where soa.id_obat_alat=coalesce(masuk.id_obat_alat,keluar.id_obat_alat) "
				+ blnsblmbulanan
				+ "),0) as stok_akhir "
				+ "from (select stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat, "
				+ "sum(transaksi_harian.penambahan_stok_apotek+transaksi_harian.penambahan_stok_gudang) as pemasukan_jumlah  "
				+ "from stok_obat_alat join obat_alat on obat_alat.id_obat_alat=stok_obat_alat.id_obat_alat  "
				+ "join transaksi_harian on transaksi_harian.id_stok=stok_obat_alat.id_stok  "
				+ "join pembelian on pembelian.id_pembelian=transaksi_harian.id_transaksi  "
				+ "join supplier on supplier.id_supplier=pembelian.id_supplier  "
				+ "where obat_alat.kategori_obat= :kategori "
				+ blnharian
				+ "group by stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat) masuk "
				+ "full outer join (select stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat, "
				+ "sum(transaksi_harian.pengurangan_stok_apotek+transaksi_harian.pengurangan_stok_gudang) as pengeluaran_jumlah  "
				+ "from stok_obat_alat join obat_alat on obat_alat.id_obat_alat=stok_obat_alat.id_obat_alat  "
				+ "join transaksi_harian on transaksi_harian.id_stok=stok_obat_alat.id_stok  "
				+ "join detail_resep on detail_resep.id_resep_dtl=transaksi_harian.id_transaksi  "
				+ "join resep on detail_resep.id_resep=resep.id_resep  "
				+ "where obat_alat.kategori_obat= :kategori "
				+ blnharian
				+ "group by stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat) keluar  "
				+ "on masuk.id_obat_alat=keluar.id_obat_alat "
				+ "join (select id_obat_alat,sum(stok_awal_apotek+stok_awal_gudang) as stok_awal,"
				+ "sum(stok_akhir_apotek+stok_akhir_gudang) as stok_akhir from transaksi_bulanan "
				+ "join stok_obat_alat on transaksi_bulanan.id_stok=stok_obat_alat.id_stok where thnbln_transaksi is not null "
				+ blnbulanan
				+ "group by id_obat_alat) transaksi "
				+ "on transaksi.id_obat_alat=coalesce(masuk.id_obat_alat,keluar.id_obat_alat)";
		Query createNativeQuery = StokOpname.em().createNativeQuery(sql,
				LaporanJenisObat.class);
		if (blnFilter != null) {
			DecimalFormat blnFormat = new DecimalFormat("00");
			String thnbln = thnFilter + blnFormat.format(blnFilter);
			createNativeQuery.setParameter("thnbln", thnbln);
			String thnblnAwal = thnFilter + blnFormat.format(blnFilter) + "01";
			createNativeQuery.setParameter("thnblnAwal", thnblnAwal);
			String thnblnAkhir = thnFilter + blnFormat.format(blnFilter) + "31";
			createNativeQuery.setParameter("thnblnAkhir", thnblnAkhir);
		}
		createNativeQuery.setParameter("kategori", kategori);
		List psikotropikaList = null;
		try {
			psikotropikaList = createNativeQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String blnNama = ApotekConstant.getNamaBulan(blnFilter);
		response.setContentTypeIfNotSet(MimeTypes
				.getContentType("rekapLaporanPsikotropika.xls"));
		response.setHeader("Content-Disposition",
				"attachment; filename=rekapLaporanPsikotropika.xls");
		renderTemplate("excel/rekapLaporanPsikotropika.html", psikotropikaList,
				blnNama, thnFilter);
	}

	public static void generateDetailPsikotropika(Integer blnFilter,
			Integer thnFilter, String kategori) {
		String blnharian = "";
		if (blnFilter != null) {
			blnharian = " and transaksi_harian.thnblntgl_transaksi between :thnblnAwal and :thnblnAkhir ";
		}
		String sql = "select detail.id_obat_alat, "
				+ "detail.id_transaksi, "
				+ "detail.nama_obat_alat,"
				+ "stok_awal+coalesce((select sum(stok_akhir_apotek+stok_akhir_gudang) as stok_awal "
				+ "from transaksi_harian th join stok_obat_alat soa on th.id_stok=soa.id_stok "
				+ "where soa.id_obat_alat=detail.id_obat_alat "
				+ "and th.id_transaksi<detail.id_transaksi),0) as stok_awal,pemasukan_dari,coalesce(pemasukan_jumlah,0) as pemasukan_jumlah,"
				+ "pengeluaran_untuk,coalesce(pengeluaran_jumlah,0) as pengeluaran_jumlah,"
				+ "stok_akhir+coalesce((select sum(stok_akhir_apotek+stok_akhir_gudang) as stok_awal "
				+ "from transaksi_harian th join stok_obat_alat soa on th.id_stok=soa.id_stok "
				+ "where soa.id_obat_alat=detail.id_obat_alat "
				+ "and th.id_transaksi<detail.id_transaksi),0) as stok_akhir "
				+ "from ((select id_transaksi,stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat, supplier.nama_supplier as pemasukan_dari,  "
				+ "sum(transaksi_harian.penambahan_stok_apotek+transaksi_harian.penambahan_stok_gudang) as pemasukan_jumlah, '' as pengeluaran_untuk, 0 as pengeluaran_jumlah "
				+ "from stok_obat_alat join obat_alat on obat_alat.id_obat_alat=stok_obat_alat.id_obat_alat  "
				+ "join transaksi_harian on transaksi_harian.id_stok=stok_obat_alat.id_stok  "
				+ "join pembelian on pembelian.id_pembelian=transaksi_harian.id_transaksi  "
				+ "join supplier on supplier.id_supplier=pembelian.id_supplier  "
				+ "where obat_alat.kategori_obat= :kategori "
				+ blnharian
				+ "group by id_transaksi,stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat,supplier.nama_supplier) "
				+ "union all (select id_transaksi,stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat, resep.nama_pasien as pengeluaran_untuk, "
				+ "sum(transaksi_harian.pengurangan_stok_apotek+transaksi_harian.pengurangan_stok_gudang) as pengeluaran_jumlah, '' as pemasukan_dari, 0 as pemasukan_jumlah "
				+ "from stok_obat_alat join obat_alat on obat_alat.id_obat_alat=stok_obat_alat.id_obat_alat  "
				+ "join transaksi_harian on transaksi_harian.id_stok=stok_obat_alat.id_stok  "
				+ "join detail_resep on detail_resep.id_resep_dtl=transaksi_harian.id_transaksi  "
				+ "join resep on detail_resep.id_resep=resep.id_resep  "
				+ "where obat_alat.kategori_obat= :kategori "
				+ blnharian
				+ "group by id_transaksi,stok_obat_alat.id_obat_alat,obat_alat.nama_obat_alat,resep.nama_pasien)) detail "
				+ "join (select id_transaksi,id_obat_alat,sum(stok_awal_apotek+stok_awal_gudang) as stok_awal,"
				+ "sum(stok_akhir_apotek+stok_akhir_gudang) as stok_akhir from transaksi_harian "
				+ "join stok_obat_alat on transaksi_harian.id_stok=stok_obat_alat.id_stok where thnblntgl_transaksi is not null "
				+ "group by id_transaksi,id_obat_alat) transaksi "
				+ "on transaksi.id_obat_alat=detail.id_obat_alat and transaksi.id_transaksi=detail.id_transaksi";
		sql += " order by detail.id_obat_alat,detail.id_transaksi";
		Query createNativeQuery = StokOpname.em().createNativeQuery(sql,
				LaporanJenisObat.class);
		if (blnFilter != null) {
			DecimalFormat blnFormat = new DecimalFormat("00");
			String thnblnAwal = thnFilter + blnFormat.format(blnFilter) + "01";
			createNativeQuery.setParameter("thnblnAwal", thnblnAwal);
			String thnblnAkhir = thnFilter + blnFormat.format(blnFilter) + "31";
			createNativeQuery.setParameter("thnblnAkhir", thnblnAkhir);
		}
		createNativeQuery.setParameter("kategori", kategori);
		List psikotropikaList = null;
		try {
			psikotropikaList = createNativeQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String blnNama = ApotekConstant.getNamaBulan(blnFilter);
		response.setContentTypeIfNotSet(MimeTypes
				.getContentType("laporanPsikotropika.xls"));
		response.setHeader("Content-Disposition",
				"attachment; filename=laporanPsikotropika.xls");
		renderTemplate("excel/laporanPsikotropika.html", psikotropikaList,
				blnNama, thnFilter);
	}
}
