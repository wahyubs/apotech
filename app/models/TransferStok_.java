package models;

import java.sql.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransferStok.class)
public class TransferStok_ {

    public static volatile SingularAttribute<TransferStok, String> idTransfer;

    public static volatile SingularAttribute<TransferStok, Date> tglAktivitas;
    public static volatile SingularAttribute<TransferStok, Date> tglTransfer;
    public static volatile SingularAttribute<TransferStok, String> descTransfer;
    public static volatile SingularAttribute<TransferStok, String> stsTransaksi;

    public static volatile SingularAttribute<TransferStok, UserPegawai> userId;

    public static volatile SetAttribute<TransferStok, DetilTransferStok> detilTransferStokIdTransfer;


}
