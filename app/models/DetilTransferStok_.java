package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetilTransferStok.class)
public class DetilTransferStok_ {

    public static volatile SingularAttribute<DetilTransferStok, DetilTransferStokId> detilTransferStokId;

    public static volatile SingularAttribute<DetilTransferStok, java.lang.Integer> jmlKirimApotek;
    public static volatile SingularAttribute<DetilTransferStok, java.lang.Integer> jmlKirimGudang;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalGudang;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirGudang;

    public static volatile SingularAttribute<DetilTransferStok, StokObatAlat> idStok;
    public static volatile SingularAttribute<DetilTransferStok, TransferStok> idTransfer;



}
