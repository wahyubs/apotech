package controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import models.DetailResep;
import models.DetilOpname;
import models.DetilTransferStok;
import models.DetilTransferStokId;
import models.HargaObat;
import models.JenisHarga;
import models.ObatResep;
import models.ObatResepId;
import models.Resep;
import models.StokObatAlat;
import models.StokOpname;
import play.data.binding.As;
import play.libs.MimeTypes;
import play.mvc.Controller;
import play.mvc.With;
import tool.AutocompleteValue;
import tool.CommonUtil;
import tool.MyIdGenerator;

@With(Secure.class)
public class PenjualanControl extends BaseController {
	public static void transaksiSusulan(Resep resep, String hasil) {
		if (resep == null)
			resep = new Resep();
		if (resep.getTglPenjualan() == null)
			resep.setTglPenjualan(new Date());
		render(resep, hasil);
	}

	public static void savePenjualan(String simpan, String idResep,
			String kodeResep, @As("dd-MM-yyyy") Date tglPenjualan,
			String key_idObatAlat, String key_pilihStok,
			Integer jumlahObatAlat, Integer hargaObatAlat) {
		Resep resep = new Resep();
		if (CommonUtil.isNotEmpty(idResep))
			resep.setIdResep(idResep);
		resep.setKodeResep(kodeResep);
		resep.setTglPenjualan(tglPenjualan);
		resep.setTglResep(tglPenjualan);
		if ("Tutup".equals(simpan))
			resep.setStsTransaksi("1");
		resep = resep.merge();
		resep = resep.save();
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
					obatResepTmp.setStokAwalApotek(stokObatAlat
							.getJmlStokApotek());
					obatResepTmp.setStokAwalGudang(stokObatAlat
							.getJmlStokGudang());
					if (obatResepTmp.getJmlObatResep() != null
							&& obatResepTmp.getJmlObatResep() > 0) {
						Integer jmlStokApotek = stokObatAlat.getJmlStokApotek()
								- obatResepTmp.getJmlObatResep();
						stokObatAlat.setJmlStokApotek(jmlStokApotek);
					}
					obatResepTmp.setStokAkhirApotek(stokObatAlat
							.getJmlStokApotek());
					obatResepTmp.setStokAkhirGudang(stokObatAlat
							.getJmlStokGudang());
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
					obatResepTmp = obatResepTmp.merge();
					obatResepTmp.save();
				}
			}
		}
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		renderTemplate("PenjualanControl/transaksi.html", resep, jenisHargaList);
	}

	public static void transaksi(Resep resep, String hasil) {
		if (resep == null)
			resep = new Resep();
		if (resep.getKodeResep() == null)
			resep.setKodeResep((String) MyIdGenerator.generateKode("resep",
					CommonUtil.getFormatThn(new Date()), true));
		if (resep.getTglPenjualan() == null)
			resep.setTglPenjualan(new Date());
		String key_idObatAlat = null;
		Integer stokApotek = null;
		Integer jumlahObatAlat = null;
		List<JenisHarga> jenisHargaList = JenisHarga.findAll();
		render(resep, key_idObatAlat, stokApotek, jumlahObatAlat,
				jenisHargaList);
	}

	public static void showHargaStok(String idObatAlat, String idJnsHarga) {
		final List<AutocompleteValue> resp = new ArrayList<AutocompleteValue>();
		Query createNativeQuery = StokObatAlat
				.em()
				.createNativeQuery(
						"select sum(x.jml_stok_apotek) from stok_obat_alat x where x.id_obat_alat= :idObatAlat");
		createNativeQuery.setParameter("idObatAlat", idObatAlat);
		Number stokApotek = (Number) createNativeQuery.getSingleResult();
		DecimalFormat decimalFormat = new DecimalFormat();
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
}
