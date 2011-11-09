package tool;

import java.io.*;
import java.text.*;
import java.util.*;

import models.ApotekConstant;
import models.IGeneratedModel;
import models.Konfigurasi;

import org.hibernate.*;
import org.hibernate.engine.*;
import org.hibernate.id.*;

public class MyIdGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object arg1)
			throws HibernateException {
		if (arg1 instanceof IGeneratedModel) {
			IGeneratedModel generatedModel = (IGeneratedModel) arg1;
			if (generatedModel.getGeneratedValue() == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Serializable id = dateFormat.format(new Date()) + "QQ";
				return id;
			} else {
				return generatedModel.getGeneratedValue();
			}
		}
		return null;
	}

	public static Serializable generateKode(String className, String tahun,
			boolean plus) {
		Konfigurasi konfigurasi = null;
		int sequence = 1;
		List<Konfigurasi> fetch = Konfigurasi.find("kode_konfig=?",
				ApotekConstant.KODE_BUATAN + "_" + className + "_" + tahun)
				.fetch();
		if (!fetch.isEmpty()) {
			konfigurasi = fetch.get(0);
			if (CommonUtil.isNotEmpty(konfigurasi.getNilaiKonfig())) {
				sequence = Integer.parseInt(konfigurasi.getNilaiKonfig());
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		if (konfigurasi == null)
			konfigurasi = new Konfigurasi();
		konfigurasi.setKodeKonfig(ApotekConstant.KODE_BUATAN + "_" + className
				+ "_" + tahun);
		konfigurasi
				.setNamaKonfig("Kode untuk " + className + " tahun " + tahun);
		if (plus) {
			konfigurasi.setNilaiKonfig(decimalFormat.format(sequence + 1));
			konfigurasi = konfigurasi.merge();
			konfigurasi.save();
		} else
			konfigurasi.setNilaiKonfig(decimalFormat.format(sequence));
		return tahun + konfigurasi.getNilaiKonfig();
	}
}
