package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

/**
 *
 * <p>Title: TransferStok</p>
 *
 * <p>Description: Play Domain Object describing a TransferStok entity</p>
 *
 */
@Entity (name="TransferStok")
@Table (name="transfer_stok")
public class TransferStok extends GenericModel {

    @Id @Column(name="id_transfer" ,length=32)
    private String idTransfer;

    @Column(name="tgl_aktivitas",    nullable=true,  unique=false)
    private Date tglAktivitas; 
    
    @Column(name="tgl_transfer",    nullable=true,  unique=false)
    private Date tglTransfer; 
    
    @Column(name="desc_transfer",  length=500,  nullable=true,  unique=false)
    private String descTransfer; 
    
    @Column(name="sts_transaksi",  length=1,  nullable=true,  unique=false)
    private String stsTransaksi; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 
    
    @OneToMany (targetEntity=DetilTransferStok.class, fetch=FetchType.LAZY, mappedBy="idTransfer", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <DetilTransferStok> detilTransferStokIdTransfer = new HashSet<DetilTransferStok>(); 
   
    /**
    * Default constructor
    */
    public TransferStok() {
    }


    public String getIdTransfer() {
        return idTransfer;
    }
	
    public void setIdTransfer (String idTransfer) {
        this.idTransfer =  idTransfer;
    }
    
    public Date getTglAktivitas() {
        return tglAktivitas;
    }
	
    public void setTglAktivitas (Date tglAktivitas) {
        this.tglAktivitas =  tglAktivitas;
    }
    
    public Date getTglTransfer() {
        return tglTransfer;
    }
	
    public void setTglTransfer (Date tglTransfer) {
        this.tglTransfer =  tglTransfer;
    }
    
    public String getDescTransfer() {
        return descTransfer;
    }
	
    public void setDescTransfer (String descTransfer) {
        this.descTransfer =  descTransfer;
    }
    
    public String getStsTransaksi() {
        return stsTransaksi;
    }
	
    public void setStsTransaksi (String stsTransaksi) {
        this.stsTransaksi =  stsTransaksi;
    }
    
    public UserPegawai getUserId () {
    	return userId;
    }
	
    public void setUserId (UserPegawai userId) {
    	this.userId = userId;
    }
    
    public Set<DetilTransferStok> getDetilTransferStokIdTransfer() {
        if (detilTransferStokIdTransfer == null){
            detilTransferStokIdTransfer = new HashSet<DetilTransferStok>();
        }
        return detilTransferStokIdTransfer;
    }

    public void setDetilTransferStokIdTransfer (Set<DetilTransferStok> detilTransferStokIdTransfer) {
        this.detilTransferStokIdTransfer = detilTransferStokIdTransfer;
    }	
    
    public void addDetilTransferStokIdTransfer (DetilTransferStok detilTransferStok) {
    	    getDetilTransferStokIdTransfer().add(detilTransferStok);
    }
    
    public String toString(){
       return tglAktivitas+"";
    }
}
