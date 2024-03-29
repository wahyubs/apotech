package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

/**
 *
 * <p>Title: DetailReturPenjualan</p>
 *
 * <p>Description: Play Domain Object describing a DetailReturPenjualan entity</p>
 *
 */
@Entity (name="DetailReturPenjualan")
@Table (name="detail_retur_penjualan")
public class DetailReturPenjualan extends GenericModel {

    @EmbeddedId
    private DetailReturPenjualanId detailReturPenjualanId;
    
    @Column(name="jml_retur",    nullable=true,  unique=false)
    private java.lang.Integer jmlRetur; 
    
    @Column(name="harga_retur",    nullable=true,  unique=false)
    private java.lang.Integer hargaRetur; 

    @Column(name="stok_awal_apotek",    nullable=true,  unique=false)
    private java.lang.Integer stokAwalApotek; 
    
    @Column(name="stok_awal_gudang",    nullable=true,  unique=false)
    private java.lang.Integer stokAwalGudang; 
    
    @Column(name="stok_akhir_apotek",    nullable=true,  unique=false)
    private java.lang.Integer stokAkhirApotek; 
    
    @Column(name="stok_akhir_gudang",    nullable=true,  unique=false)
    private java.lang.Integer stokAkhirGudang; 
    
    @MapsId ("id_retur_jual")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_retur_jual", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private ReturPenjualan idReturJual; 
    
    @MapsId ("id_stok")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokObatAlat idStok; 
    
    /**
    * Default constructor
    */
    public DetailReturPenjualan() {
    }


    public DetailReturPenjualanId getDetailReturPenjualanId() {
        return detailReturPenjualanId;
    }
	
    public void setDetailReturPenjualanId (DetailReturPenjualanId detailReturPenjualanId) {
        this.detailReturPenjualanId =  detailReturPenjualanId;
    }
    
    public java.lang.Integer getJmlRetur() {
        return jmlRetur;
    }
	
    public void setJmlRetur (java.lang.Integer jmlRetur) {
        this.jmlRetur =  jmlRetur;
    }
    
    public java.lang.Integer getHargaRetur() {
        return hargaRetur;
    }
	
    public void setHargaRetur (java.lang.Integer hargaRetur) {
        this.hargaRetur =  hargaRetur;
    }
    
    public java.lang.Integer getStokAwalApotek() {
		return stokAwalApotek;
	}


	public void setStokAwalApotek(java.lang.Integer stokAwalApotek) {
		this.stokAwalApotek = stokAwalApotek;
	}


	public java.lang.Integer getStokAwalGudang() {
		return stokAwalGudang;
	}


	public void setStokAwalGudang(java.lang.Integer stokAwalGudang) {
		this.stokAwalGudang = stokAwalGudang;
	}


	public java.lang.Integer getStokAkhirApotek() {
		return stokAkhirApotek;
	}


	public void setStokAkhirApotek(java.lang.Integer stokAkhirApotek) {
		this.stokAkhirApotek = stokAkhirApotek;
	}


	public java.lang.Integer getStokAkhirGudang() {
		return stokAkhirGudang;
	}


	public void setStokAkhirGudang(java.lang.Integer stokAkhirGudang) {
		this.stokAkhirGudang = stokAkhirGudang;
	}


	public ReturPenjualan getIdReturJual () {
    	return idReturJual;
    }
	
    public void setIdReturJual (ReturPenjualan idReturJual) {
    	this.idReturJual = idReturJual;
    }
    
    public StokObatAlat getIdStok () {
    	return idStok;
    }
	
    public void setIdStok (StokObatAlat idStok) {
    	this.idStok = idStok;
    }
    
    public String toString(){
       return detailReturPenjualanId+"";
    }
}
