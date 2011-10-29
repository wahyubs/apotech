package models;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(HargaObat.class)
public class HargaObat_ {

    public static volatile SingularAttribute<HargaObat, HargaObatId> hargaObatId;

    public static volatile SingularAttribute<HargaObat, Date> tglAktivitas;
    public static volatile SingularAttribute<HargaObat, java.lang.Integer> hargaObat;
    public static volatile SingularAttribute<HargaObat, String> activeSts;
    public static volatile SingularAttribute<HargaObat, java.lang.Integer> percentLaba;

    public static volatile SingularAttribute<HargaObat, JenisHarga> idJnsHarga;
    public static volatile SingularAttribute<HargaObat, ObatAlat> idObatAlat;
    public static volatile SingularAttribute<HargaObat, UserPegawai> userId;



}
