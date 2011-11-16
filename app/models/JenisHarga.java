package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

/**
 *
 * <p>Title: JenisHarga</p>
 *
 * <p>Description: Play Domain Object describing a JenisHarga entity</p>
 *
 */
@Entity (name="JenisHarga")
@Table (name="jenis_harga")
public class JenisHarga extends GenericModel {

    @Id @Column(name="id_jns_harga" ,length=32)
    private String idJnsHarga;

    @Column(name="jns_harga",  length=50,  nullable=true,  unique=false)
    private String jnsHarga; 
    
    @Column(name="kode_jns_harga",  length=20,  nullable=true,  unique=false)
    private String kodeJnsHarga; 
    
    @OneToMany (targetEntity=HargaObat.class, fetch=FetchType.LAZY, mappedBy="idJnsHarga", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <HargaObat> hargaObatIdJnsHarga = new HashSet<HargaObat>(); 
   
    @OneToMany (targetEntity=Resep.class, fetch=FetchType.LAZY, mappedBy="idJnsHarga", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Resep> resepIdJnsHarga = new HashSet<Resep>(); 
   
    /**
    * Default constructor
    */
    public JenisHarga() {
    }


    public String getIdJnsHarga() {
        return idJnsHarga;
    }
	
    public void setIdJnsHarga (String idJnsHarga) {
        this.idJnsHarga =  idJnsHarga;
    }
    
    public String getJnsHarga() {
        return jnsHarga;
    }
	
    public void setJnsHarga (String jnsHarga) {
        this.jnsHarga =  jnsHarga;
    }
    
    public String getKodeJnsHarga() {
        return kodeJnsHarga;
    }
	
    public void setKodeJnsHarga (String kodeJnsHarga) {
        this.kodeJnsHarga =  kodeJnsHarga;
    }
    
    public Set<HargaObat> getHargaObatIdJnsHarga() {
        if (hargaObatIdJnsHarga == null){
            hargaObatIdJnsHarga = new HashSet<HargaObat>();
        }
        return hargaObatIdJnsHarga;
    }

    public void setHargaObatIdJnsHarga (Set<HargaObat> hargaObatIdJnsHarga) {
        this.hargaObatIdJnsHarga = hargaObatIdJnsHarga;
    }	
    
    public void addHargaObatIdJnsHarga (HargaObat hargaObat) {
    	    getHargaObatIdJnsHarga().add(hargaObat);
    }
    
    public Set<Resep> getResepIdJnsHarga() {
        if (resepIdJnsHarga == null){
            resepIdJnsHarga = new HashSet<Resep>();
        }
        return resepIdJnsHarga;
    }

    public void setResepIdJnsHarga (Set<Resep> resepIdJnsHarga) {
        this.resepIdJnsHarga = resepIdJnsHarga;
    }	
    
    public void addResepIdJnsHarga (Resep resep) {
    	    getResepIdJnsHarga().add(resep);
    }
    
    public String toString(){
       return jnsHarga+"";
    }
}
