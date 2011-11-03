package models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserPegawai.class)
public class UserPegawai_ {

    public static volatile SingularAttribute<UserPegawai, String> userId;

    public static volatile SingularAttribute<UserPegawai, String> passwordUser;
    public static volatile SingularAttribute<UserPegawai, String> namaUser;
    public static volatile SingularAttribute<UserPegawai, String> noPeg;
    public static volatile SingularAttribute<UserPegawai, String> jnsUser;


    public static volatile SetAttribute<UserPegawai, HargaObat> hargaObatUserId;
    public static volatile SetAttribute<UserPegawai, Konfigurasi> konfigurasiUserId;
    public static volatile SetAttribute<UserPegawai, ObatAlat> obatAlatUserId;
    public static volatile SetAttribute<UserPegawai, Pembelian> pembelianUserId;
    public static volatile SetAttribute<UserPegawai, Resep> resepUserId;
    public static volatile SetAttribute<UserPegawai, ReturPembelian> returPembelianUserId;
    public static volatile SetAttribute<UserPegawai, StokOpname> stokOpnameUserId;
    public static volatile SetAttribute<UserPegawai, Supplier> supplierUserId;
    public static volatile SetAttribute<UserPegawai, Tagihan> tagihanUserId;
    public static volatile SetAttribute<UserPegawai, TransferStok> transferStokUserId;


}
