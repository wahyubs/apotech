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
    
    @MapsId ("$localColumnName")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_retur_beli", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private ReturPembelian idReturBeli; 
    
    @MapsId ("id_retur_beli")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokObatAlat idStok; 
    
    /**
    * Default constructor
    */
    public DetilReturPembelian() {
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