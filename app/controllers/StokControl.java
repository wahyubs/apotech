package controllers;

import java.util.Date;

import models.StokOpname;
import play.mvc.Controller;

public class StokControl extends Controller {
	public static void transaksi(StokOpname stokOpname, String hasil) {
		if (stokOpname == null)
			stokOpname = new StokOpname();
		if (stokOpname.getTglStokOpname() == null)
			stokOpname.setTglStokOpname(new Date());
		render(stokOpname, hasil);
	}

	public static void saveOpname() {
		transaksi(null, null);
	}
}
