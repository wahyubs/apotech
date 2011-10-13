package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(HargaObat.class)
public class HargaObat_ {

    public static volatile SingularAttribute<HargaObat, String> idHarga;

    public static volatile SingularAttribute<HargaObat, String> thnBlnHarga;
    public static volatile SingularAttribute<HargaObat, java.lang.Integer> hargaObat;

    public static volatile SingularAttribute<HargaObat, ObatAlat> idObatAlat;



}
