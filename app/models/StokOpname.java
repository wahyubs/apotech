package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.db.jpa.GenericModel;

/**
 *
 * <p>Title: StokOpname</p>
 *
 * <p>Description: Play Domain Object describing a StokOpname entity</p>
 *
 */
@Entity (name="StokOpname")
@Table (name="stok_opname")
public class StokOpname extends GenericModel implements IGeneratedModel {

    @Id @Column(name="id_stok_opname" ,length=32)
    @GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
    private String idStokOpname;

    @Column(name="tgl_aktivitas",    nullable=true,  unique=false)
    private Date tglAktivitas; 
    
    @Column(name="tgl_stok_opname",    nullable=true,  unique=false)
    private Date tglStokOpname; 
    
    @Column(name="desc_stok_opname",  length=500,  nullable=true,  unique=false)
    private String descStokOpname; 
    
    @Column(name="sts_transaksi",  length=1,  nullable=true,  unique=false)
    private String stsTransaksi; 
    
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 
    
    @OneToMany (targetEntity=DetilOpname.class, fetch=FetchType.LAZY, mappedBy="idStokOpname", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <DetilOpname> detilOpnameIdStokOpname = new HashSet<DetilOpname>(); 
   
    /**
    * Default constructor
    */
    public StokOpname() {
    }


    public String getIdStokOpname() {
        return idStokOpname;
    }
	
    public void setIdStokOpname (String idStokOpname) {
        this.idStokOpname =  idStokOpname;
    }
    
    public Date getTglAktivitas() {
        return tglAktivitas;
    }
	
    public void setTglAktivitas (Date tglAktivitas) {
        this.tglAktivitas =  tglAktivitas;
    }
    
    public Date getTglStokOpname() {
        return tglStokOpname;
    }
	
    public void setTglStokOpname (Date tglStokOpname) {
        this.tglStokOpname =  tglStokOpname;
    }
    
    public String getDescStokOpname() {
        return descStokOpname;
    }
	
    public void setDescStokOpname (String descStokOpname) {
        this.descStokOpname =  descStokOpname;
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
    
    public Set<DetilOpname> getDetilOpnameIdStokOpname() {
        if (detilOpnameIdStokOpname == null){
            detilOpnameIdStokOpname = new HashSet<DetilOpname>();
        }
        return detilOpnameIdStokOpname;
    }

    public void setDetilOpnameIdStokOpname (Set<DetilOpname> detilOpnameIdStokOpname) {
        this.detilOpnameIdStokOpname = detilOpnameIdStokOpname;
    }	
    
    public void addDetilOpnameIdStokOpname (DetilOpname detilOpname) {
    	    getDetilOpnameIdStokOpname().add(detilOpname);
    }
    
    public String toString(){
       return tglAktivitas+"";
    }
    
    @Override
	public String getGeneratedValue() {
		return idStokOpname;
	}
}
