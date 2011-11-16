package models;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetilPembelian.class)
public class DetilPembelian_ {

    public static volatile SingularAttribute<DetilPembelian, DetilPembelianId> detilPembelianId;

    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> jmlPenerimaanApotek;
    public static volatile SingularAttribute<DetilPembelian, Date> tglKadaluarsa;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> jmlPenerimaanGudang;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> hargaPenerimaan;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> ppn;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> discountPercent;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> discountCharge;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalGudang;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirGudang;

    public static volatile SingularAttribute<DetilPembelian, Pembelian> idPembelian;
    public static volatile SingularAttribute<DetilPembelian, StokObatAlat> idStok;



}
