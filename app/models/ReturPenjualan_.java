package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReturPenjualan.class)
public class ReturPenjualan_ {

    public static volatile SingularAttribute<ReturPenjualan, String> idReturJual;

    public static volatile SingularAttribute<ReturPenjualan, Date> tglAktivitas;
    public static volatile SingularAttribute<ReturPenjualan, Date> tglReturJual;
    public static volatile SingularAttribute<ReturPenjualan, String> stsTransaksi;
    public static volatile SingularAttribute<ReturPenjualan, String> namaPasien;
    public static volatile SingularAttribute<ReturPenjualan, String> alamatPasien;
    public static volatile SingularAttribute<ReturPenjualan, String> kodeResep;
    public static volatile SingularAttribute<ReturPenjualan, String> kodeRetur;

    public static volatile SingularAttribute<ReturPenjualan, UserPegawai> userId;

    public static volatile SetAttribute<ReturPenjualan, DetailReturPenjualan> detailReturPenjualanIdReturJual;


}
