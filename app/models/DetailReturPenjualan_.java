package models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DetailReturPenjualan.class)
public class DetailReturPenjualan_ {

	public static volatile SingularAttribute<DetailReturPenjualan, DetailReturPenjualanId> detailReturPenjualanId;

	public static volatile SingularAttribute<DetailReturPenjualan, java.lang.Integer> jmlRetur;
	public static volatile SingularAttribute<DetailReturPenjualan, java.lang.Integer> hargaRetur;
	public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAwalGudang;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirApotek;
    public static volatile SingularAttribute<DetilPembelian, java.lang.Integer> stokAkhirGudang;

	public static volatile SingularAttribute<DetailReturPenjualan, ReturPenjualan> idReturJual;
	public static volatile SingularAttribute<DetailReturPenjualan, StokObatAlat> idStok;

}
