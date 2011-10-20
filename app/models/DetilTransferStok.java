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
 * <p>Title: DetilTransferStok</p>
 *
 * <p>Description: Play Domain Object describing a DetilTransferStok entity</p>
 *
 */
@Entity (name="DetilTransferStok")
@Table (name="detil_transfer_stok")
public class DetilTransferStok extends GenericModel {

    @EmbeddedId
    private DetilTransferStokId detilTransferStokId;
    
    @Column(name="jml_kirim_apotek",    nullable=true,  unique=false)
    private java.lang.Integer jmlKirimApotek; 
    
    @Column(name="jml_kirim_gudang",    nullable=true,  unique=false)
    private java.lang.Integer jmlKirimGudang; 
    
    @MapsId ("$localColumnName")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_stok", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private StokObatAlat idStok; 
    
    @MapsId ("id_stok")
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_transfer", nullable=false,  unique=false , insertable=false, updatable=false )
    @Required
    private TransferStok idTransfer; 
    
    /**
    * Default constructor
    */
    public DetilTransferStok() {
    }


    public DetilTransferStokId getDetilTransferStokId() {
        return detilTransferStokId;
    }
	
    public void setDetilTransferStokId (DetilTransferStokId detilTransferStokId) {
        this.detilTransferStokId =  detilTransferStokId;
    }
    
    public java.lang.Integer getJmlKirimApotek() {
        return jmlKirimApotek;
    }
	
    public void setJmlKirimApotek (java.lang.Integer jmlKirimApotek) {
        this.jmlKirimApotek =  jmlKirimApotek;
    }
    
    public java.lang.Integer getJmlKirimGudang() {
        return jmlKirimGudang;
    }
	
    public void setJmlKirimGudang (java.lang.Integer jmlKirimGudang) {
        this.jmlKirimGudang =  jmlKirimGudang;
    }
    
    public StokObatAlat getIdStok () {
    	return idStok;
    }
	
    public void setIdStok (StokObatAlat idStok) {
    	this.idStok = idStok;
    }
    
    public TransferStok getIdTransfer () {
    	return idTransfer;
    }
	
    public void setIdTransfer (TransferStok idTransfer) {
    	this.idTransfer = idTransfer;
    }
    
    public String toString(){
       return detilTransferStokId+"";
    }
}
