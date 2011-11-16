package models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(JenisObatAlat.class)
public class JenisObatAlat_ {

    public static volatile SingularAttribute<JenisObatAlat, String> idJnsObatAlat;

    public static volatile SingularAttribute<JenisObatAlat, String> jnsObatAlat;
    public static volatile SingularAttribute<JenisObatAlat, String> kodeJnsObatAlat;


    public static volatile SetAttribute<JenisObatAlat, ObatAlat> obatAlatIdJnsObatAlat;


}
