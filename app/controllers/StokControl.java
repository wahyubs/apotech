package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import models.DetilOpname;
import models.HargaObat;
import models.HargaObatId;
import models.JenisHarga;
import models.ObatAlat;
import models.StokObatAlat;
import models.StokOpname;
import play.data.binding.As;
import play.data.validation.Required;
import play.db.jpa.JPABase;
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
					stokObatAlat.setIdStok(tmp.getIdStok());
					Integer jmlStokApotek = stokApotekSekarang.get(i) == null ? 0
							: stokApotekSekarang.get(i);
					Integer jmlStokGudang = stokGudangSekarang.get(i) == null ? 0
							: stokGudangSekarang.get(i);
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
				}
			}
			stokOpname.setIdStokOpname(idStokOpname);
			stokOpname.setStsTransaksi("1");
			stokOpname = stokOpname.merge();
			stokOpname.validateAndSave();

			hasil = "Stok Opname Berhasil Ditutup!";
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
		render();
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
						"select avg(x.harga_beli_stok) from stok_obat_alat x where x.id_obat_alat= :idObatAlat");
		createNativeQuery.setParameter("idObatAlat", idObatAlat);
		Number hargaRata = (Number) createNativeQuery.getSingleResult();
		DecimalFormat decimalFormat = new DecimalFormat();
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
}
