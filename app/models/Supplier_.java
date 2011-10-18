package models;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Supplier.class)
public class Supplier_ {

	public static volatile SingularAttribute<Supplier, String> idSupplier;

	public static volatile SingularAttribute<Supplier, String> namaSupplier;
	public static volatile SingularAttribute<Supplier, String> alamatSupplier;
	public static volatile SingularAttribute<Supplier, String> kotaSupplier;
	public static volatile SingularAttribute<ObatAlat, Date> tglAktivitas;

	public static volatile SingularAttribute<ObatAlat, User_pegawai> userId;

	public static volatile SetAttribute<Supplier, Pembelian> pembelianIdSupplier;

}
