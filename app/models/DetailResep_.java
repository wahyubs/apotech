package models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetailResep.class)
public class DetailResep_ {

    public static volatile SingularAttribute<DetailResep, String> idResepDtl;

    public static volatile SingularAttribute<DetailResep, java.lang.Integer> jmlObat;
    public static volatile SingularAttribute<DetailResep, String> dosisObat;
    public static volatile SingularAttribute<DetailResep, java.lang.Integer> urutanObat;
    public static volatile SingularAttribute<DetailResep, String> deskripsi;

    public static volatile SingularAttribute<DetailResep, Resep> idResep;

    public static volatile SetAttribute<DetailResep, ObatResep> obatResepIdResepDtl;


}
