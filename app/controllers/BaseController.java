package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.ObatAlat;
import play.mvc.Controller;
import tool.AutocompleteValue;

public class BaseController extends Controller {
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
}
