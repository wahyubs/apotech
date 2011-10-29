package models;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(JenisHarga.class)
public class JenisHarga_ {

    public static volatile SingularAttribute<JenisHarga, String> idJnsHarga;

    public static volatile SingularAttribute<JenisHarga, String> jnsHarga;


    public static volatile SetAttribute<JenisHarga, HargaObat> hargaObatIdJnsHarga;
    public static volatile SetAttribute<JenisHarga, Resep> resepIdJnsHarga;


}
