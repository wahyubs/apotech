package models;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetilPembelian.class)
public class DetilPembelian_ {

    public static volatile SingularAttribute<DetilPembelian, DetilPembelianId> detilPembelianId;

    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> jmlPenerimaan;
    public static volatile SingularAttribute<DetilPembelian, Date> tglKadaluarsa;
    public static volatile SingularAttribute<DetilPembelian, String> stokGudang;

    public static volatile SingularAttribute<DetilPembelian, Pembelian> idPembelian;
    public static volatile SingularAttribute<DetilPembelian, StokObatAlat> idStok;



}
