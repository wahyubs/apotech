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
 * <p>Title: Tagihan</p>
 *
 * <p>Description: Play Domain Object describing a Tagihan entity</p>
 *
 */
@Entity (name="Tagihan")
@Table (name="tagihan")
public class Tagihan extends GenericModel {

    @Id @Column(name="id_tagihan" ,length=32)
    private String idTagihan;

    @Column(name="tgl_tagihan",    nullable=true,  unique=false)
    private Date tglTagihan; 
    
    @Column(name="harga_dasar",    nullable=true,  unique=false)
    private java.lang.Integer hargaDasar; 
    
    @Column(name="pajak",    nullable=true,  unique=false)
    private java.lang.Integer pajak; 
    
    @Column(name="pajak_persen",    nullable=true,  unique=false)
    private java.lang.Integer pajakPersen; 
    
    @Column(name="administrasi",    nullable=true,  unique=false)
    private java.lang.Integer administrasi; 
    
    @Column(name="diskon",    nullable=true,  unique=false)
    private java.lang.Integer diskon; 
    
    @Column(name="diskon_persen",    nullable=true,  unique=false)
    private java.lang.Integer diskonPersen; 
    
    @Column(name="pembulatan",    nullable=true,  unique=false)
    private java.lang.Integer pembulatan; 
    
    @Column(name="jml_item",    nullable=true,  unique=false)
    private java.lang.Integer jmlItem; 
    
    @Column(name="harga_total",    nullable=true,  unique=false)
    private java.lang.Integer hargaTotal; 
    
    @Column(name="deskripsi",  length=500,  nullable=true,  unique=false)
    private String deskripsi; 
    
    @Column(name="tgl_aktivitas",    nullable=true,  unique=false)
    private Date tglAktivitas; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 
    
    @OneToMany (targetEntity=Resep.class, fetch=FetchType.LAZY, mappedBy="idTagihan", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Resep> resepIdTagihan = new HashSet<Resep>(); 
   
    /**
    * Default constructor
    */
    public Tagihan() {
    }


    public String getIdTagihan() {
        return idTagihan;
    }
	
    public void setIdTagihan (String idTagihan) {
        this.idTagihan =  idTagihan;
    }
    
    public Date getTglTagihan() {
        return tglTagihan;
    }
	
    public void setTglTagihan (Date tglTagihan) {
        this.tglTagihan =  tglTagihan;
    }
    
    public java.lang.Integer getHargaDasar() {
        return hargaDasar;
    }
	
    public void setHargaDasar (java.lang.Integer hargaDasar) {
        this.hargaDasar =  hargaDasar;
    }
    
    public java.lang.Integer getPajak() {
        return pajak;
    }
	
    public void setPajak (java.lang.Integer pajak) {
        this.pajak =  pajak;
    }
    
    public java.lang.Integer getPajakPersen() {
        return pajakPersen;
    }
	
    public void setPajakPersen (java.lang.Integer pajakPersen) {
        this.pajakPersen =  pajakPersen;
    }
    
    public java.lang.Integer getAdministrasi() {
        return administrasi;
    }
	
    public void setAdministrasi (java.lang.Integer administrasi) {
        this.administrasi =  administrasi;
    }
    
    public java.lang.Integer getDiskon() {
        return diskon;
    }
	
    public void setDiskon (java.lang.Integer diskon) {
        this.diskon =  diskon;
    }
    
    public java.lang.Integer getDiskonPersen() {
        return diskonPersen;
    }
	
    public void setDiskonPersen (java.lang.Integer diskonPersen) {
        this.diskonPersen =  diskonPersen;
    }
    
    public java.lang.Integer getPembulatan() {
        return pembulatan;
    }
	
    public void setPembulatan (java.lang.Integer pembulatan) {
        this.pembulatan =  pembulatan;
    }
    
    public java.lang.Integer getJmlItem() {
        return jmlItem;
    }
	
    public void setJmlItem (java.lang.Integer jmlItem) {
        this.jmlItem =  jmlItem;
    }
    
    public java.lang.Integer getHargaTotal() {
        return hargaTotal;
    }
	
    public void setHargaTotal (java.lang.Integer hargaTotal) {
        this.hargaTotal =  hargaTotal;
    }
    
    public String getDeskripsi() {
        return deskripsi;
    }
	
    public void setDeskripsi (String deskripsi) {
        this.deskripsi =  deskripsi;
    }
    
    public Date getTglAktivitas() {
        return tglAktivitas;
    }
	
    public void setTglAktivitas (Date tglAktivitas) {
        this.tglAktivitas =  tglAktivitas;
    }
    
    public UserPegawai getUserId () {
    	return userId;
    }
	
    public void setUserId (UserPegawai userId) {
    	this.userId = userId;
    }
    
    public Set<Resep> getResepIdTagihan() {
        if (resepIdTagihan == null){
            resepIdTagihan = new HashSet<Resep>();
        }
        return resepIdTagihan;
    }

    public void setResepIdTagihan (Set<Resep> resepIdTagihan) {
        this.resepIdTagihan = resepIdTagihan;
    }	
    
    public void addResepIdTagihan (Resep resep) {
    	    getResepIdTagihan().add(resep);
    }
    
    public String toString(){
       return tglTagihan+"";
    }
}
