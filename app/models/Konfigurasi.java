package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.db.jpa.GenericModel;

/**
 *
 * <p>Title: Konfigurasi</p>
 *
 * <p>Description: Play Domain Object describing a Konfigurasi entity</p>
 *
 */
@Entity (name="Konfigurasi")
@Table (name="konfigurasi")
public class Konfigurasi extends GenericModel implements IGeneratedModel {

    @Id 
    @Column(name="id_konfig" ,length=32)
    @GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
    private String idKonfig;

    @Column(name="kode_konfig",  length=50,  nullable=true,  unique=false)
    private String kodeKonfig; 
    
    @Column(name="nama_konfig",  length=100,  nullable=true,  unique=false)
    private String namaKonfig; 
    
    @Column(name="nilai_konfig",  length=100,  nullable=true,  unique=false)
    private String nilaiKonfig; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 
    
    /**
    * Default constructor
    */
    public Konfigurasi() {
    }


    public String getIdKonfig() {
        return idKonfig;
    }
	
    public void setIdKonfig (String idKonfig) {
        this.idKonfig =  idKonfig;
    }
    
    public String getKodeKonfig() {
        return kodeKonfig;
    }
	
    public void setKodeKonfig (String kodeKonfig) {
        this.kodeKonfig =  kodeKonfig;
    }
    
    public String getNamaKonfig() {
        return namaKonfig;
    }
	
    public void setNamaKonfig (String namaKonfig) {
        this.namaKonfig =  namaKonfig;
    }
    
    public String getNilaiKonfig() {
        return nilaiKonfig;
    }
	
    public void setNilaiKonfig (String nilaiKonfig) {
        this.nilaiKonfig =  nilaiKonfig;
    }
    
    public UserPegawai getUserId () {
    	return userId;
    }
	
    public void setUserId (UserPegawai userId) {
    	this.userId = userId;
    }
    
    public String toString(){
       return kodeKonfig+"";
    }


	@Override
	public String getGeneratedValue() {
		return idKonfig;
	}
}
