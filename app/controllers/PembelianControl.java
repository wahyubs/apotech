package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.Supplier;
import play.mvc.Controller;
import tool.AutocompleteValue;

public class PembelianControl extends Controller {
	public static int AUTOCOMPLETE_MAX = 10;

	public static void transaksi() {
		render();
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

	public static void savePembelian(String tglPembelian, String idSupplier) {
		// TODO:
		String hasil = "Pembelian Berhasil Disimpan!";
		renderTemplate("PembelianControl/transaksi.html", hasil);
	}
}
