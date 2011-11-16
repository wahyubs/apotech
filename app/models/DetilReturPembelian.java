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
 * <p>Title: DetilReturPembelian</p>
 *
 * <p>Description: Play Domain Object describing a DetilReturPembelian entity</p>
 *
 */
@Entity (name="DetilReturPembelian")
@Table (name="detil_retur_pembelian")
public class DetilReturPembelian extends GenericModel {

    @EmbeddedId
    private DetilReturPembelianId detilReturPembelianId;
    
    @Column(name="jml_retur_beli_apotek",    nullable=true,  unique=false)
    private java.lang.Integer jmlReturBeliApotek; 
    
    @Column(name="jml_retur_beli_gudang",    nullable=true,  unique=false)
    private java.lang.Integer jmlReturBeliGudang; 
    
    @Column(name="jml_redelivery_apotek",    nullable=true,  unique=false)
    private java.lang.Integer jmlRedeliveryApotek; 
    
    @Column(name="jml_redelivery_gudang",    nullable=true,  unique=false)
    private java.lang.Integer jmlRedeliveryGudang; 
    
    @Column(name="stok_awal_apotek",    nullable=true,  unique=false)
    private java.lang.Integer stokAwalApotek; 
    
    @Column(name="stok_awal_gudang",    nullable=true,  unique=false)
    private java.lang.Integer stokAwalGudang; 
    
    @Column(name="stok_akhir_apotek",    nullable=true,  unique=false)
    private java.lang.Integer stokAkhirApotek; 
    
    @Column(name="stok_akhir_gudang",    nullable=true,  unique=false)
    private java.lang.Integer stokAkhirGudang; 
    
    @MapsId ("id_retur_beli")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_retur_beli", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private ReturPembelian idReturBeli; 
    
    @MapsId ("id_stok")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokObatAlat idStok; 
    
    /**
    * Default constructor
    */
    public DetilReturPembelian() {
    	this.detilReturPembelianId =  new DetilReturPembelianId();
    }


    public DetilReturPembelianId getDetilReturPembelianId() {
        return detilReturPembelianId;
    }
	
    public void setDetilReturPembelianId (DetilReturPembelianId detilReturPembelianId) {
        this.detilReturPembelianId =  detilReturPembelianId;
    }
    
    public java.lang.Integer getJmlReturBeliApotek() {
        return jmlReturBeliApotek;
    }
	
    public void setJmlReturBeliApotek (java.lang.Integer jmlReturBeliApotek) {
        this.jmlReturBeliApotek =  jmlReturBeliApotek;
    }
    
    public java.lang.Integer getJmlReturBeliGudang() {
        return jmlReturBeliGudang;
    }
	
    public void setJmlReturBeliGudang (java.lang.Integer jmlReturBeliGudang) {
        this.jmlReturBeliGudang =  jmlReturBeliGudang;
    }
    
    public java.lang.Integer getJmlRedeliveryApotek() {
        return jmlRedeliveryApotek;
    }
	
    public void setJmlRedeliveryApotek (java.lang.Integer jmlRedeliveryApotek) {
        this.jmlRedeliveryApotek =  jmlRedeliveryApotek;
    }
    
    public java.lang.Integer getJmlRedeliveryGudang() {
        return jmlRedeliveryGudang;
    }
	
    public void setJmlRedeliveryGudang (java.lang.Integer jmlRedeliveryGudang) {
        this.jmlRedeliveryGudang =  jmlRedeliveryGudang;
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


	public ReturPembelian getIdReturBeli () {
    	return idReturBeli;
    }
	
    public void setIdReturBeli (ReturPembelian idReturBeli) {
    	this.idReturBeli = idReturBeli;
    }
    
    public StokObatAlat getIdStok () {
    	return idStok;
    }
	
    public void setIdStok (StokObatAlat idStok) {
    	this.idStok = idStok;
    }
    
    public String toString(){
       return detilReturPembelianId+"";
    }
}
