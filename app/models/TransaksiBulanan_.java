package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransaksiBulanan.class)
public class TransaksiBulanan_ {

    public static volatile SingularAttribute<TransaksiBulanan, TransaksiBulananId> transaksiBulananId;

    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> stokAwalApotek;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> stokAwalGudang;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> penambahanStokApotek;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> penambahanStokGudang;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> penguranganStokApotek;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> penguranganStokGudang;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> stokAkhirApotek;
    public static volatile SingularAttribute<TransaksiBulanan, java.lang.Integer> stokAkhirGudang;

    public static volatile SingularAttribute<TransaksiBulanan, StokObatAlat> idStok;



}
