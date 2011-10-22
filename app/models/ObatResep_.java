package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ObatResep.class)
public class ObatResep_ {

    public static volatile SingularAttribute<ObatResep, ObatResepId> obatResepId;

    public static volatile SingularAttribute<ObatResep, java.lang.Integer> jmlObatResep;
    public static volatile SingularAttribute<ObatResep, java.lang.Integer> hargaObat;

    public static volatile SingularAttribute<ObatResep, DetailResep> idResepDtl;
    public static volatile SingularAttribute<ObatResep, StokObatAlat> idStok;



}
