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
import play.db.jpa.JPABase;
import play.mvc.Controller;
import tool.AutocompleteValue;

public class PembelianControl extends Controller {
	public static int AUTOCOMPLETE_MAX = 20;

	public static void transaksi(Pembelian pembelian, String hasil) {
		if (pembelian == null)
			pembelian = new Pembelian();
		if (pembelian.getTglPembelian() == null)
			pembelian.setTglPembelian(new Date());
		render(pembelian, hasil);
	}

	public static void monitoring() {

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

	public static void savePembelian(
			@Required @As("dd-MM-yyyy") Date tglPembelian,
			@Required String key_idSupplier, List<String> key_kode_obat,
			@As("dd-MM-yyyy") List<Date> tglKadaluarsa,
			List<Integer> stokApotek, List<Integer> stokGudang,
			List<String> harga) {
		Pembelian pembelian = new Pembelian();
		pembelian.setTglPembelian(tglPembelian);
		pembelian.setIdSupplier((Supplier) Supplier.findById(key_idSupplier));
		pembelian.validateAndSave();
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transaksi(pembelian, null);
			return;
		}
		for (int i = 0; i < key_kode_obat.size(); i++) {
			DetilPembelian detilPembelian = new DetilPembelian();
			StokObatAlat stokObatAlat = new StokObatAlat();
			stokObatAlat.setIdObatAlat((ObatAlat) ObatAlat
					.findById(key_kode_obat.get(i)));
			stokObatAlat.setTglKadaluarsa(tglKadaluarsa.get(i));
			List<StokObatAlat> fetch = StokObatAlat.find(
					"id_obat_alat=? and date_trunc('day', tgl_kadaluarsa)=?",
					stokObatAlat.getIdObatAlat().getIdObatAlat(),
					stokObatAlat.getTglKadaluarsa()).fetch();
			stokObatAlat.setJmlStokApotek(stokApotek.get(i));
			stokObatAlat.setJmlStokGudang(stokGudang.get(i));
			if (fetch.isEmpty()) {
				stokObatAlat.validateAndSave();
				if (validation.hasErrors()) {
					params.flash(); // add http parameters to the flash scope
					validation.keep(); // keep the errors for the next request
					transaksi(pembelian, null);
					return;
				}
			} else {
				StokObatAlat tmp = fetch.get(0);
				stokObatAlat.setIdStok(tmp.getIdStok());
				stokObatAlat=stokObatAlat.merge();
				stokObatAlat.save();
			}
		}
		String hasil = "Pembelian Berhasil Disimpan!";
		transaksi(null, hasil);
	}
}
