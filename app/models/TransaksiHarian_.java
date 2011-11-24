package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransaksiHarian.class)
public class TransaksiHarian_ {

    public static volatile SingularAttribute<TransaksiHarian, TransaksiHarianId> transaksiHarianId;

    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> stokAwalApotek;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> stokAwalGudang;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> penambahanStokApotek;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> penambahanStokGudang;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> penguranganStokApotek;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> penguranganStokGudang;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> stokAkhirApotek;
    public static volatile SingularAttribute<TransaksiHarian, java.lang.Integer> stokAkhirGudang;
    public static volatile SingularAttribute<TransaksiHarian, String> idTransaksi;

    public static volatile SingularAttribute<TransaksiHarian, StokObatAlat> idStok;



}
