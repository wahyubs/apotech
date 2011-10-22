package models;

import java.sql.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetilOpname.class)
public class DetilOpname_ {

    public static volatile SingularAttribute<DetilOpname, DetilOpnameId> detilOpnameId;

    public static volatile SingularAttribute<DetilOpname, java.lang.Integer> jmlApotekSebelumnya;
    public static volatile SingularAttribute<DetilOpname, java.lang.Integer> jmlApotekSekarang;
    public static volatile SingularAttribute<DetilOpname, Date> tglKadaluarsa;
    public static volatile SingularAttribute<DetilOpname, java.lang.Integer> jmlGudangSebelumnya;
    public static volatile SingularAttribute<DetilOpname, java.lang.Integer> jmlGudangSekarang;
    public static volatile SingularAttribute<DetilOpname, Integer> jmlSebelumnya;
    public static volatile SingularAttribute<DetilOpname, Integer> jmlSekarang;

    public static volatile SingularAttribute<DetilOpname, StokObatAlat> idStok;
    public static volatile SingularAttribute<DetilOpname, StokOpname> idStokOpname;



}
