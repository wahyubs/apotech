package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Resep.class)
public class Resep_ {

    public static volatile SingularAttribute<Resep, String> idResep;

    public static volatile SingularAttribute<Resep, String> kodeResep;
    public static volatile SingularAttribute<Resep, Date> tglResep;
    public static volatile SingularAttribute<Resep, String> namaDokter;
    public static volatile SingularAttribute<Resep, String> alamatDokter;
    public static volatile SingularAttribute<Resep, String> namaPasien;
    public static volatile SingularAttribute<Resep, String> alamatPasien;
    public static volatile SingularAttribute<Resep, Date> tglLahirPasien;
    public static volatile SingularAttribute<Resep, java.lang.Integer> umurPasien;
    public static volatile SingularAttribute<Resep, String> jenKelPasien;
    public static volatile SingularAttribute<Resep, Date> tglPenjualan;

    public static volatile SingularAttribute<Resep, Tagihan> idTagihan;

    public static volatile SetAttribute<Resep, DetailResep> detailResepIdResep;


}
