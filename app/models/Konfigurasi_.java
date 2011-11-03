package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Konfigurasi.class)
public class Konfigurasi_ {

    public static volatile SingularAttribute<Konfigurasi, String> idKonfig;

    public static volatile SingularAttribute<Konfigurasi, String> kodeKonfig;
    public static volatile SingularAttribute<Konfigurasi, String> namaKonfig;
    public static volatile SingularAttribute<Konfigurasi, String> nilaiKonfig;

    public static volatile SingularAttribute<Konfigurasi, UserPegawai> userId;



}
