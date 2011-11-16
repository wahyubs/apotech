package controllers;

import java.util.List;

import play.libs.Crypto;
import tool.CommonUtil;

import models.ApotekConstant;
import models.Konfigurasi;
import models.UserPegawai;

public class ApotekSecurity extends Secure.Security {

	public static boolean authenticate(String username, String password) {
		password = Crypto.passwordHash(password);
		List<UserPegawai> fetch = UserPegawai.find(
				"user_id=? and password_user=?", username, password).fetch();
		if (!fetch.isEmpty()) {
			UserPegawai userPegawai = fetch.get(0);
			session.put("admin", null);
			session.put("stok", null);
			session.put("pembelian", null);
			session.put("penjualan", null);
			if (ApotekConstant.ADMIN.equals(userPegawai.getJnsUser())) {
				session.put("admin", true);
				session.put("stok", true);
				session.put("pembelian", true);
				session.put("penjualan", true);
			}
			List<Konfigurasi> konfigurasiList = Konfigurasi.find(
					"kode_konfig=? and user_id=?",
					ApotekConstant.KONFIGURASI_HAK, userPegawai.getUserId())
					.fetch();
			if (!konfigurasiList.isEmpty()) {
				Konfigurasi konfigurasi = konfigurasiList.get(0);
				String nilaiKonfig = konfigurasi.getNilaiKonfig();
				if (CommonUtil.isNotEmpty(nilaiKonfig)) {
					String[] split = nilaiKonfig.split(";");
					for (int i = 0; i < split.length; i++) {
						session.put(split[i], true);
					}
				}
			}
		}
		return !fetch.isEmpty();
	}
}
