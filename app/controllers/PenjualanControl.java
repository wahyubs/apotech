package controllers;

import java.util.Date;

import models.Resep;
import play.mvc.Controller;

public class PenjualanControl extends Controller {
	public static void transaksi(Resep resep, String hasil) {
		if (resep == null)
			resep = new Resep();
		if (resep.getTglPenjualan() == null)
			resep.setTglPenjualan(new Date());
		render(resep, hasil);
	}

	public static void savePenjualan() {
		transaksi(null, null);
	}
}
