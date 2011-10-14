package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.Pembelian;
import models.Supplier;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;
import tool.AutocompleteValue;

public class PembelianControl extends Controller {
	public static int AUTOCOMPLETE_MAX = 20;

	public static void transaksi(Date tglPembelian, String idSupplier,
			String hasil) {
		if (tglPembelian == null)
			tglPembelian = new Date();
		render(tglPembelian, idSupplier, hasil);
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

	public static void savePembelian(
			@Required @As("dd-MM-yyyy") Date tglPembelian,
			@Required String idSupplier) {
		Pembelian pembelian = new Pembelian();
		pembelian.setTglPembelian(tglPembelian);
		pembelian.setIdSupplier((Supplier) Supplier.findById(idSupplier));
		pembelian.validateAndSave();
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			transaksi(null, null, null);
		}
		String hasil = "Pembelian Berhasil Disimpan!";
		transaksi(tglPembelian, idSupplier, hasil);
	}
}
