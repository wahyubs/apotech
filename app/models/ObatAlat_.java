package models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ObatAlat.class)
public class ObatAlat_ {

    public static volatile SingularAttribute<ObatAlat, String> idObatAlat;

    public static volatile SingularAttribute<ObatAlat, String> namaObatAlat;
    public static volatile SingularAttribute<ObatAlat, String> jenisObatAlat;
    public static volatile SingularAttribute<ObatAlat, java.lang.Integer> hargaTerakhir;


    public static volatile SetAttribute<ObatAlat, HargaObat> hargaObatIdObatAlat;
    public static volatile SetAttribute<ObatAlat, StokObatAlat> stokObatAlatIdObatAlat;


}
