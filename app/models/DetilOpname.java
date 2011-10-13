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
 * <p>Title: DetilOpname</p>
 *
 * <p>Description: Play Domain Object describing a DetilOpname entity</p>
 *
 */
@Entity (name="DetilOpname")
@Table (name="detil_opname")
public class DetilOpname extends GenericModel {

    @EmbeddedId
    private DetilOpnameId detilOpnameId;
    
    @Column(name="jml_sebelumnya",    nullable=true,  unique=false)
    private java.lang.Integer jmlSebelumnya; 
    
    @Column(name="jml_sekarang",    nullable=true,  unique=false)
    private java.lang.Integer jmlSekarang; 
    
    @Column(name="tgl_kadaluarsa",    nullable=true,  unique=false)
    private Date tglKadaluarsa; 
    
    @MapsId ("$localColumnName")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokObatAlat idStok; 
    
    @MapsId ("id_stok")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok_opname", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokOpname idStokOpname; 
    
    /**
    * Default constructor
    */
    public DetilOpname() {
    }


    public DetilOpnameId getDetilOpnameId() {
        return detilOpnameId;
    }
	
    public void setDetilOpnameId (DetilOpnameId detilOpnameId) {
        this.detilOpnameId =  detilOpnameId;
    }
    
    public java.lang.Integer getJmlSebelumnya() {
        return jmlSebelumnya;
    }
	
    public void setJmlSebelumnya (java.lang.Integer jmlSebelumnya) {
        this.jmlSebelumnya =  jmlSebelumnya;
    }
    
    public java.lang.Integer getJmlSekarang() {
        return jmlSekarang;
    }
	
    public void setJmlSekarang (java.lang.Integer jmlSekarang) {
        this.jmlSekarang =  jmlSekarang;
    }
    
    public Date getTglKadaluarsa() {
        return tglKadaluarsa;
    }
	
    public void setTglKadaluarsa (Date tglKadaluarsa) {
        this.tglKadaluarsa =  tglKadaluarsa;
    }
    
    public StokObatAlat getIdStok () {
    	return idStok;
    }
	
    public void setIdStok (StokObatAlat idStok) {
    	this.idStok = idStok;
    }
    
    public StokOpname getIdStokOpname () {
    	return idStokOpname;
    }
	
    public void setIdStokOpname (StokOpname idStokOpname) {
    	this.idStokOpname = idStokOpname;
    }
    
    public String toString(){
       return detilOpnameId+"";
    }
}
