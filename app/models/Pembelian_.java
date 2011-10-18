package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Pembelian.class)
public class Pembelian_ {

	public static volatile SingularAttribute<Pembelian, String> idPembelian;

	public static volatile SingularAttribute<Pembelian, Date> tglPembelian;
	public static volatile SingularAttribute<Pembelian, Date> tglFaktur;
	public static volatile SingularAttribute<Pembelian, String> noFaktur;
	public static volatile SingularAttribute<ObatAlat, Date> tglAktivitas;

	public static volatile SingularAttribute<ObatAlat, User_pegawai> userId;
	public static volatile SingularAttribute<Pembelian, Supplier> idSupplier;

	public static volatile SetAttribute<Pembelian, DetilPembelian> detilPembelianIdPembelian;

}
