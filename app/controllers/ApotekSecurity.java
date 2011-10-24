package controllers;

import java.util.List;

import models.UserPegawai;

public class ApotekSecurity extends Secure.Security {

	public static boolean authenticate(String username, String password) {
		List<UserPegawai> fetch = UserPegawai.find(
				"user_id=? and password_user=?", username, password).fetch();
		return !fetch.isEmpty();
	}
}
