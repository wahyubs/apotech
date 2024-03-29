package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ObatResep.class)
public class ObatResep_ {

    public static volatile SingularAttribute<ObatResep, ObatResepId> obatResepId;

    public static volatile SingularAttribute<ObatResep, java.lang.Integer> jmlObatResep;
    public static volatile SingularAttribute<ObatResep, java.lang.Integer> hargaObat;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalGudang;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirGudang;

    public static volatile SingularAttribute<ObatResep, DetailResep> idResepDtl;
    public static volatile SingularAttribute<ObatResep, StokObatAlat> idStok;



}
