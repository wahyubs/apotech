package controllers;

import play.*;
import play.mvc.*;
import tool.AutocompleteValue;

import java.util.*;

import controllers.Secure.Security;

import models.*;

public class Application extends Controller {

	public static void index(boolean logout) {
		String hasil = logout ? "Anda Berhasil Logout" : null;
		render(hasil);
	}

	public static void saveConfigColor(String colorId) {
		String connected = Security.connected();
		List<Konfigurasi> fetch = Konfigurasi.find(
				"kode_konfig=? and user_id=?",
				ApotekConstant.KONFIGURASI_WARNA, connected).fetch();
		Konfigurasi konfigurasi;
		if (fetch.size() > 0) {
			konfigurasi = fetch.get(0);
			konfigurasi.setNilaiKonfig(colorId);
			konfigurasi.merge();
		} else {
			konfigurasi = new Konfigurasi();
			konfigurasi.setKodeKonfig(ApotekConstant.KONFIGURASI_WARNA);
			konfigurasi
					.setNamaKonfig("Konfigurasi untuk menyimpan warna dasar aplikasi apotek");
			konfigurasi
					.setUserId((UserPegawai) UserPegawai.findById(connected));
			konfigurasi.setNilaiKonfig(colorId);
		}
		konfigurasi.save();
	}
	
	public static void getConfigColor(String userId) {
		List<Konfigurasi> fetch = Konfigurasi.find(
				"kode_konfig=? and user_id=?",
				ApotekConstant.KONFIGURASI_WARNA, userId).fetch();
		if (fetch.size() > 0) {
			Konfigurasi konfigurasi = fetch.get(0);
			String data = konfigurasi.getNilaiKonfig();
			renderJSON(data, String.class);
		}
	}

}