package models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User_pegawai.class)
public class User_pegawai_ {

    public static volatile SingularAttribute<User_pegawai, String> userId;

    public static volatile SingularAttribute<User_pegawai, String> password;
    public static volatile SingularAttribute<User_pegawai, String> namaUser;
    public static volatile SingularAttribute<User_pegawai, String> noPeg;


    public static volatile SetAttribute<User_pegawai, HargaObat> hargaObatUserId;
    public static volatile SetAttribute<User_pegawai, ObatAlat> obatAlatUserId;
    public static volatile SetAttribute<User_pegawai, Resep> resepUserId;
    public static volatile SetAttribute<User_pegawai, StokOpname> stokOpnameUserId;
    public static volatile SetAttribute<User_pegawai, Supplier> supplierUserId;
    public static volatile SetAttribute<User_pegawai, Tagihan> tagihanUserId;


}
