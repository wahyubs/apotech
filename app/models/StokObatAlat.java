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
 * <p>Title: StokObatAlat</p>
 *
 * <p>Description: Play Domain Object describing a StokObatAlat entity</p>
 *
 */
@Entity (name="StokObatAlat")
@Table (name="stok_obat_alat")
public class StokObatAlat extends GenericModel {

    @Id @Column(name="id_stok" ,length=32)
    private String idStok;

    @Column(name="tgl_kadaluarsa",    nullable=true,  unique=false)
    private Date tglKadaluarsa; 
    
    @Column(name="jml_stok",    nullable=true,  unique=false)
    private java.lang.Integer jmlStok; 
    
    @Column(name="stok_gudang",  length=1,  nullable=true,  unique=false)
    private String stokGudang; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_obat_alat",  nullable=true,  unique=false  )
    private ObatAlat idObatAlat; 
    
    @OneToMany (targetEntity=DetilOpname.class, fetch=FetchType.LAZY, mappedBy="idStok", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <DetilOpname> detilOpnameIdStok = new HashSet<DetilOpname>(); 
   
    @OneToMany (targetEntity=DetilPembelian.class, fetch=FetchType.LAZY, mappedBy="idStok", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <DetilPembelian> detilPembelianIdStok = new HashSet<DetilPembelian>(); 
   
    @OneToMany (targetEntity=ObatResep.class, fetch=FetchType.LAZY, mappedBy="idStok", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ObatResep> obatResepIdStok = new HashSet<ObatResep>(); 
   
    /**
    * Default constructor
    */
    public StokObatAlat() {
    }


    public String getIdStok() {
        return idStok;
    }
	
    public void setIdStok (String idStok) {
        this.idStok =  idStok;
    }
    
    public Date getTglKadaluarsa() {
        return tglKadaluarsa;
    }
	
    public void setTglKadaluarsa (Date tglKadaluarsa) {
        this.tglKadaluarsa =  tglKadaluarsa;
    }
    
    public java.lang.Integer getJmlStok() {
        return jmlStok;
    }
	
    public void setJmlStok (java.lang.Integer jmlStok) {
        this.jmlStok =  jmlStok;
    }
    
    public String getStokGudang() {
        return stokGudang;
    }
	
    public void setStokGudang (String stokGudang) {
        this.stokGudang =  stokGudang;
    }
    
    public ObatAlat getIdObatAlat () {
    	return idObatAlat;
    }
	
    public void setIdObatAlat (ObatAlat idObatAlat) {
    	this.idObatAlat = idObatAlat;
    }
    
    public Set<DetilOpname> getDetilOpnameIdStok() {
        if (detilOpnameIdStok == null){
            detilOpnameIdStok = new HashSet<DetilOpname>();
        }
        return detilOpnameIdStok;
    }

    public void setDetilOpnameIdStok (Set<DetilOpname> detilOpnameIdStok) {
        this.detilOpnameIdStok = detilOpnameIdStok;
    }	
    
    public void addDetilOpnameIdStok (DetilOpname detilOpname) {
    	    getDetilOpnameIdStok().add(detilOpname);
    }
    
    public Set<DetilPembelian> getDetilPembelianIdStok() {
        if (detilPembelianIdStok == null){
            detilPembelianIdStok = new HashSet<DetilPembelian>();
        }
        return detilPembelianIdStok;
    }

    public void setDetilPembelianIdStok (Set<DetilPembelian> detilPembelianIdStok) {
        this.detilPembelianIdStok = detilPembelianIdStok;
    }	
    
    public void addDetilPembelianIdStok (DetilPembelian detilPembelian) {
    	    getDetilPembelianIdStok().add(detilPembelian);
    }
    
    public Set<ObatResep> getObatResepIdStok() {
        if (obatResepIdStok == null){
            obatResepIdStok = new HashSet<ObatResep>();
        }
        return obatResepIdStok;
    }

    public void setObatResepIdStok (Set<ObatResep> obatResepIdStok) {
        this.obatResepIdStok = obatResepIdStok;
    }	
    
    public void addObatResepIdStok (ObatResep obatResep) {
    	    getObatResepIdStok().add(obatResep);
    }
    
    public String toString(){
       return tglKadaluarsa+"";
    }
}
