package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReturPembelian.class)
public class ReturPembelian_ {

    public static volatile SingularAttribute<ReturPembelian, String> idReturBeli;

    public static volatile SingularAttribute<ReturPembelian, Date> tglAktivitas;
    public static volatile SingularAttribute<ReturPembelian, Date> tglReturBeli;
    public static volatile SingularAttribute<ReturPembelian, String> descReturBeli;
    public static volatile SingularAttribute<ReturPembelian, String> stsTransaksi;
    public static volatile SingularAttribute<ReturPembelian, String> noFakturBeli;

    public static volatile SingularAttribute<ReturPembelian, Supplier> idSupplier;
    public static volatile SingularAttribute<ReturPembelian, UserPegawai> userId;

    public static volatile SetAttribute<ReturPembelian, DetilReturPembelian> detilReturPembelianIdReturBeli;


}
