package models;

import java.util.Date;

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
 * <p>Title: DetilPembelian</p>
 *
 * <p>Description: Play Domain Object describing a DetilPembelian entity</p>
 *
 */
@Entity (name="DetilPembelian")
@Table (name="detil_pembelian")
public class DetilPembelian extends GenericModel {

    @EmbeddedId
    private DetilPembelianId detilPembelianId;
    
    @Column(name="jml_penerimaan",    nullable=true,  unique=false)
    private java.lang.Integer jmlPenerimaan; 
    
    @Column(name="tgl_kadaluarsa",    nullable=true,  unique=false)
    private Date tglKadaluarsa; 
    
    @Column(name="stok_gudang",  length=1,  nullable=true,  unique=false)
    private String stokGudang; 
    
    @MapsId ("$localColumnName")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_pembelian", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private Pembelian idPembelian; 
    
    @MapsId ("id_pembelian")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokObatAlat idStok; 
    
    /**
    * Default constructor
    */
    public DetilPembelian() {
    }


    public DetilPembelianId getDetilPembelianId() {
        return detilPembelianId;
    }
	
    public void setDetilPembelianId (DetilPembelianId detilPembelianId) {
        this.detilPembelianId =  detilPembelianId;
    }
    
    public java.lang.Integer getJmlPenerimaan() {
        return jmlPenerimaan;
    }
	
    public void setJmlPenerimaan (java.lang.Integer jmlPenerimaan) {
        this.jmlPenerimaan =  jmlPenerimaan;
    }
    
    public Date getTglKadaluarsa() {
        return tglKadaluarsa;
    }
	
    public void setTglKadaluarsa (Date tglKadaluarsa) {
        this.tglKadaluarsa =  tglKadaluarsa;
    }
    
    public String getStokGudang() {
        return stokGudang;
    }
	
    public void setStokGudang (String stokGudang) {
        this.stokGudang =  stokGudang;
    }
    
    public Pembelian getIdPembelian () {
    	return idPembelian;
    }
	
    public void setIdPembelian (Pembelian idPembelian) {
    	this.idPembelian = idPembelian;
    }
    
    public StokObatAlat getIdStok () {
    	return idStok;
    }
	
    public void setIdStok (StokObatAlat idStok) {
    	this.idStok = idStok;
    }
    
    public String toString(){
       return detilPembelianId+"";
    }
}
