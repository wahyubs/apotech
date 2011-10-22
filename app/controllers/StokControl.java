package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.DetilOpname;
import models.DetilPembelian;
import models.ObatAlat;
import models.Pembelian;
import models.StokObatAlat;
import models.StokOpname;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;
import tool.AutocompleteValue;
import tool.CommonUtil;

public class StokControl extends Controller {
	public static int AUTOCOMPLETE_MAX = 20;
	
	public static void transaksi(StokOpname stokOpname, String hasil) {
		if (stokOpname == null)
			stokOpname = new StokOpname();
		if (stokOpname.getTglStokOpname() == null)
			stokOpname.setTglStokOpname(new Date());
		render(stokOpname, hasil);
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
	
	public static void saveOpname(String idStokOpname,
			@Required @As("dd-MM-yyyy") Date tglStokOpname,
			@Required String descStokOpname, List<String> key_kode_obat,
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
					Integer jmlStokApotek = tmp.getJmlStokApotek() == null ? 0
							: tmp.getJmlStokApotek();
					Integer jmlStokGudang = tmp.getJmlStokGudang() == null ? 0
							: tmp.getJmlStokGudang();
					stokObatAlat.setJmlStokApotek(jmlStokApotek);
					stokObatAlat.setJmlStokGudang(jmlStokGudang);
					stokObatAlat = stokObatAlat.merge();
					stokObatAlat.validateAndSave();
				}
				detilOpname.setIdStokOpname(stokOpname);
				detilOpname.setIdStok(stokObatAlat);
				detilOpname.setJmlApotekSekarang(jmlApotekSekarang);
				detilOpname.setTglKadaluarsa(tglKadaluarsa.get(i));
				detilOpname.setJmlGudangSekarang(jmlGudangSekarang);
				detilOpname.setJmlSekarang(jmlSekarang);
				detilOpname.validateAndSave();
				stokOpname.addDetilOpnameIdStokOpname(detilOpname);

			}
		}
		
		String hasil = "Stok Opname Berhasil Disimpan!";
		//stokOpname.setDescStokOpname(stokOpname.getDescStokOpname() != null ? stokOpname.getDescStokOpname() : "");
		renderTemplate("StokControl/transaksi.html", stokOpname, hasil);
		
	}
}
