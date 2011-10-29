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
 * <p>Title: Resep</p>
 *
 * <p>Description: Play Domain Object describing a Resep entity</p>
 *
 */
@Entity (name="Resep")
@Table (name="resep")
public class Resep extends GenericModel {

    @Id @Column(name="id_resep" ,length=32)
    private String idResep;

    @Column(name="kode_resep",  length=50,  nullable=true,  unique=false)
    private String kodeResep; 
    
    @Column(name="tgl_resep",    nullable=true,  unique=false)
    private Date tglResep; 
    
    @Column(name="nama_dokter",  length=100,  nullable=true,  unique=false)
    private String namaDokter; 
    
    @Column(name="alamat_dokter",  length=200,  nullable=true,  unique=false)
    private String alamatDokter; 
    
    @Column(name="nama_pasien",  length=100,  nullable=true,  unique=false)
    private String namaPasien; 
    
    @Column(name="alamat_pasien",  length=200,  nullable=true,  unique=false)
    private String alamatPasien; 
    
    @Column(name="tgl_lahir_pasien",    nullable=true,  unique=false)
    private Date tglLahirPasien; 
    
    @Column(name="umur_pasien",    nullable=true,  unique=false)
    private java.lang.Integer umurPasien; 
    
    @Column(name="jen_kel_pasien",  length=1,  nullable=true,  unique=false)
    private String jenKelPasien; 
    
    @Column(name="tgl_penjualan",    nullable=true,  unique=false)
    private Date tglPenjualan; 
    
    @Column(name="tgl_aktivitas",    nullable=true,  unique=false)
    private Date tglAktivitas; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_jns_harga",  nullable=true,  unique=false  )
    private JenisHarga idJnsHarga; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="id_tagihan",  nullable=true,  unique=false  )
    private Tagihan idTagihan; 
    
    @OneToMany (targetEntity=DetailResep.class, fetch=FetchType.LAZY, mappedBy="idResep", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <DetailResep> detailResepIdResep = new HashSet<DetailResep>(); 
   
    /**
    * Default constructor
    */
    public Resep() {
    }


    public String getIdResep() {
        return idResep;
    }
	
    public void setIdResep (String idResep) {
        this.idResep =  idResep;
    }
    
    public String getKodeResep() {
        return kodeResep;
    }
	
    public void setKodeResep (String kodeResep) {
        this.kodeResep =  kodeResep;
    }
    
    public Date getTglResep() {
        return tglResep;
    }
	
    public void setTglResep (Date tglResep) {
        this.tglResep =  tglResep;
    }
    
    public String getNamaDokter() {
        return namaDokter;
    }
	
    public void setNamaDokter (String namaDokter) {
        this.namaDokter =  namaDokter;
    }
    
    public String getAlamatDokter() {
        return alamatDokter;
    }
	
    public void setAlamatDokter (String alamatDokter) {
        this.alamatDokter =  alamatDokter;
    }
    
    public String getNamaPasien() {
        return namaPasien;
    }
	
    public void setNamaPasien (String namaPasien) {
        this.namaPasien =  namaPasien;
    }
    
    public String getAlamatPasien() {
        return alamatPasien;
    }
	
    public void setAlamatPasien (String alamatPasien) {
        this.alamatPasien =  alamatPasien;
    }
    
    public Date getTglLahirPasien() {
        return tglLahirPasien;
    }
	
    public void setTglLahirPasien (Date tglLahirPasien) {
        this.tglLahirPasien =  tglLahirPasien;
    }
    
    public java.lang.Integer getUmurPasien() {
        return umurPasien;
    }
	
    public void setUmurPasien (java.lang.Integer umurPasien) {
        this.umurPasien =  umurPasien;
    }
    
    public String getJenKelPasien() {
        return jenKelPasien;
    }
	
    public void setJenKelPasien (String jenKelPasien) {
        this.jenKelPasien =  jenKelPasien;
    }
    
    public Date getTglPenjualan() {
        return tglPenjualan;
    }
	
    public void setTglPenjualan (Date tglPenjualan) {
        this.tglPenjualan =  tglPenjualan;
    }
    
    public Date getTglAktivitas() {
        return tglAktivitas;
    }
	
    public void setTglAktivitas (Date tglAktivitas) {
        this.tglAktivitas =  tglAktivitas;
    }
    
    public JenisHarga getIdJnsHarga () {
    	return idJnsHarga;
    }
	
    public void setIdJnsHarga (JenisHarga idJnsHarga) {
    	this.idJnsHarga = idJnsHarga;
    }
    
    public UserPegawai getUserId () {
    	return userId;
    }
	
    public void setUserId (UserPegawai userId) {
    	this.userId = userId;
    }
    
    public Tagihan getIdTagihan () {
    	return idTagihan;
    }
	
    public void setIdTagihan (Tagihan idTagihan) {
    	this.idTagihan = idTagihan;
    }
    
    public Set<DetailResep> getDetailResepIdResep() {
        if (detailResepIdResep == null){
            detailResepIdResep = new HashSet<DetailResep>();
        }
        return detailResepIdResep;
    }

    public void setDetailResepIdResep (Set<DetailResep> detailResepIdResep) {
        this.detailResepIdResep = detailResepIdResep;
    }	
    
    public void addDetailResepIdResep (DetailResep detailResep) {
    	    getDetailResepIdResep().add(detailResep);
    }
    
    public String toString(){
       return kodeResep+"";
    }
}
