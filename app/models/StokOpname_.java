package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StokOpname.class)
public class StokOpname_ {

    public static volatile SingularAttribute<StokOpname, String> idStokOpname;

    public static volatile SingularAttribute<StokOpname, Date> tglAktivitas;
    public static volatile SingularAttribute<StokOpname, Date> tglStokOpname;
    public static volatile SingularAttribute<StokOpname, String> descStokOpname;
    public static volatile SingularAttribute<StokOpname, String> stsTransaksi;

    public static volatile SingularAttribute<StokOpname, UserPegawai> userId;

    public static volatile SetAttribute<StokOpname, DetilOpname> detilOpnameIdStokOpname;


}
