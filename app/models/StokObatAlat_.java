package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StokObatAlat.class)
public class StokObatAlat_ {

    public static volatile SingularAttribute<StokObatAlat, String> idStok;

    public static volatile SingularAttribute<StokObatAlat, Date> tglKadaluarsa;
    public static volatile SingularAttribute<StokObatAlat, java.lang.Integer> jmlStokApotek;
    public static volatile SingularAttribute<StokObatAlat, java.lang.Integer> jmlStokGudang;
    public static volatile SingularAttribute<StokObatAlat, java.lang.Integer> hargaBeliStok;
    public static volatile SingularAttribute<StokObatAlat, java.lang.Integer> ppnStok;
    public static volatile SingularAttribute<StokObatAlat, java.lang.Integer> discPercStok;
    public static volatile SingularAttribute<StokObatAlat, java.lang.Integer> discCharge;

    public static volatile SingularAttribute<StokObatAlat, ObatAlat> idObatAlat;

    public static volatile SetAttribute<StokObatAlat, DetailReturPenjualan> detailReturPenjualanIdStok;
    public static volatile SetAttribute<StokObatAlat, DetilOpname> detilOpnameIdStok;
    public static volatile SetAttribute<StokObatAlat, DetilPembelian> detilPembelianIdStok;
    public static volatile SetAttribute<StokObatAlat, DetilReturPembelian> detilReturPembelianIdStok;
    public static volatile SetAttribute<StokObatAlat, DetilTransferStok> detilTransferStokIdStok;
    public static volatile SetAttribute<StokObatAlat, ObatResep> obatResepIdStok;
}
