package controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import models.DetailResep;
import models.DetilTransferStok;
import models.DetilTransferStokId;
import models.HargaObat;
import models.JenisHarga;
import models.ObatResep;
import models.ObatResepId;
import models.Resep;
import models.StokObatAlat;
import play.data.binding.As;
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

	public static void savePenjualan(String simpan,String idResep, String kodeResep,
			@As("dd-MM-yyyy") Date tglPenjualan, String key_idObatAlat,
			String key_pilihStok, Integer jumlahObatAlat, Integer hargaObatAlat) {
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
		DetailResep detailResep = new DetailResep();
		detailResep.setIdResep(resep);
		detailResep.setJmlObat(jumlahObatAlat);
		detailResep = detailResep.merge();
		detailResep = detailResep.save();
		ObatResep obatResep = new ObatResep();
		obatResep
				.setIdStok((StokObatAlat) StokObatAlat.findById(key_pilihStok));
		obatResep.setJmlObatResep(jumlahObatAlat);
		obatResep.setHargaObat(hargaObatAlat);
		obatResep.setIdResepDtl(detailResep);
		obatResep = obatResep.merge();
		obatResep.save();
		List<DetailResep> fetch = DetailResep.find("id_resep=?",
				resep.getIdResep()).fetch();
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			DetailResep tmp = (DetailResep) iterator.next();
			resep.addDetailResepIdResep(tmp);
		}
		if ("Tutup".equals(simpan)) {
			if (key_pilihStok != null
					&& !"".equals(key_pilihStok)) {
				StokObatAlat stokObatAlat = StokObatAlat
						.findById(key_pilihStok);
				ObatResep dtl = ObatResep
						.findById(new ObatResepId(detailResep
								.getIdResepDtl(), key_pilihStok));
				if (dtl.getJmlObatResep() != null
						&& dtl.getJmlObatResep() > 0) {
					Integer jmlStokApotek = stokObatAlat.getJmlStokApotek()
							- dtl.getJmlObatResep();
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
				} 
				stokObatAlat = stokObatAlat.merge();
				stokObatAlat.validateAndSave();
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
		List<StokObatAlat> fetch = StokObatAlat.find(
				"id_obat_alat=? order by tgl_kadaluarsa asc", idObatAlat)
				.fetch();
		final List<AutocompleteValue> resp = new ArrayList<AutocompleteValue>();
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			StokObatAlat stokObatAlat = (StokObatAlat) iterator.next();
			resp.add(new AutocompleteValue(stokObatAlat.getIdStok(),
					stokObatAlat.getLabelStok()));
		}
		renderJSON(resp);
	}
}
