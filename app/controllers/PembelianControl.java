package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.DetilPembelian;
import models.ObatAlat;
import models.Pembelian;
import models.StokObatAlat;
import models.Supplier;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;
import tool.AutocompleteValue;
import tool.CommonUtil;

public class PembelianControl extends Controller {
	public static int AUTOCOMPLETE_MAX = 20;

	public static void transaksi(Pembelian pembelian, String hasil) {
		if (pembelian == null)
			pembelian = new Pembelian();
		if (pembelian.getTglPembelian() == null)
			pembelian.setTglPembelian(new Date());
		render(pembelian, hasil);
	}

	public static void transfer() {
		render();
	}

	public static void monitoring(Date tglPembelianAwal,
			Date tglPembelianAkhir, String idSupplier, String idObatAlat) {
		tglPembelianAwal = new Date();
		tglPembelianAkhir = new Date();
		render(tglPembelianAwal, tglPembelianAkhir, idSupplier, idObatAlat);
	}

	public static void autocompleteSupplier(final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		List<Supplier> findAll = Supplier.find("nama_supplier like ?",
				"%" + term + "%").fetch();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			Supplier supplierTmp = (Supplier) iterator.next();
			response.add(new AutocompleteValue(supplierTmp.getIdSupplier(),
					supplierTmp.getNamaSupplier() + " ("
							+ supplierTmp.getKotaSupplier() + ")"));
		}
		renderJSON(response);
	}

	public static void autocompleteKodeObat(final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		List<ObatAlat> findAll = ObatAlat
				.find("lower(nama_obat_alat) like lower(?) or lower(kode_obat_alat) like lower(?)",
						"%" + term + "%", "%" + term + "%").fetch();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			ObatAlat obatAlatTmp = (ObatAlat) iterator.next();
			response.add(new AutocompleteValue(obatAlatTmp.getIdObatAlat(),
					obatAlatTmp.getKodeObatAlat() + " - "
							+ obatAlatTmp.getNamaObatAlat()));
		}
		renderJSON(response);
	}

	public static void savePembelian(String idPembelian,
			@Required @As("dd-MM-yyyy") Date tglPembelian,
			@Required String key_idSupplier, List<String> key_kode_obat,
			@As("dd-MM-yyyy") List<Date> tglKadaluarsa,
			List<Integer> stokApotek, List<Integer> stokGudang,
			List<String> harga) {
		Pembelian pembelian = new Pembelian();
		pembelian.setTglPembelian(tglPembelian);
		if (pembelian.getTglPembelian() == null)
			pembelian.setTglPembelian(new Date());
		pembelian.setIdSupplier((Supplier) Supplier.findById(key_idSupplier));
		if (CommonUtil.isEmpty(idPembelian)) {
			pembelian.validateAndSave();
		} else {
			pembelian.setIdPembelian(idPembelian);
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
				detilPembelian.validateAndSave();

			}
		}
		pembelian = Pembelian.findById(pembelian.getIdPembelian());
		String hasil = "Pembelian Berhasil Disimpan!";
		pembelian
				.getIdSupplier()
				.setNamaSupplier(
						(pembelian.getIdSupplier().getNamaSupplier() != null ? pembelian
								.getIdSupplier().getNamaSupplier() : "")
								+ " ("
								+ (pembelian.getIdSupplier().getKotaSupplier() != null ? pembelian
										.getIdSupplier().getKotaSupplier()
										: "-") + ")");
		renderTemplate("PembelianControl/transaksi.html", pembelian, hasil);
	}

	public static void cariPembelian() {
		render();
	}
	
	public static void showVolume(String idObatAlat) {
		renderText("");
	}
}
