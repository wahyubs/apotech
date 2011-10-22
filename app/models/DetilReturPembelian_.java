package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetilReturPembelian.class)
public class DetilReturPembelian_ {

    public static volatile SingularAttribute<DetilReturPembelian, DetilReturPembelianId> detilReturPembelianId;

    public static volatile SingularAttribute<DetilReturPembelian, java.lang.Integer> jmlReturBeliApotek;
    public static volatile SingularAttribute<DetilReturPembelian, java.lang.Integer> jmlReturBeliGudang;

    public static volatile SingularAttribute<DetilReturPembelian, ReturPembelian> idReturBeli;
    public static volatile SingularAttribute<DetilReturPembelian, StokObatAlat> idStok;



}
