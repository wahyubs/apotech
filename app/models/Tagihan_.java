package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Tagihan.class)
public class Tagihan_ {

	public static volatile SingularAttribute<Tagihan, String> idTagihan;

	public static volatile SingularAttribute<Tagihan, Date> tglTagihan;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> hargaDasar;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> pajak;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> pajakPersen;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> administrasi;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> diskon;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> diskonPersen;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> pembulatan;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> jmlItem;
	public static volatile SingularAttribute<Tagihan, java.lang.Integer> hargaTotal;
	public static volatile SingularAttribute<Tagihan, String> deskripsi;
	public static volatile SingularAttribute<ObatAlat, Date> tglAktivitas;

	public static volatile SingularAttribute<ObatAlat, User_pegawai> userId;

	public static volatile SetAttribute<Tagihan, Resep> resepIdTagihan;

}
