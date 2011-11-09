package models;

import java.text.SimpleDateFormat;
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
 * <p>
 * Title: StokObatAlat
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a StokObatAlat entity
 * </p>
 * 
 */
@Entity(name = "StokObatAlat")
@Table(name = "stok_obat_alat")
public class StokObatAlat extends GenericModel implements IGeneratedModel {

	@Id
	@Column(name = "id_stok", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idStok;

	@Column(name = "tgl_kadaluarsa", nullable = true, unique = false)
	private Date tglKadaluarsa;

	@Column(name = "jml_stok_apotek", nullable = true, unique = false)
	private java.lang.Integer jmlStokApotek;

	@Column(name = "jml_stok_gudang", nullable = true, unique = false)
	private java.lang.Integer jmlStokGudang;
	
	@Column(name="harga_beli_stok",    nullable=true,  unique=false)
    private java.lang.Integer hargaBeliStok; 
    
    @Column(name="ppn_stok",    nullable=true,  unique=false)
    private java.lang.Integer ppnStok; 
    
    @Column(name="disc_perc_stok",    nullable=true,  unique=false)
    private java.lang.Integer discPercStok; 
    
    @Column(name="disc_charge",    nullable=true,  unique=false)
    private java.lang.Integer discCharge; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obat_alat", nullable = true, unique = false)
	private ObatAlat idObatAlat;

	@OneToMany(targetEntity = DetilOpname.class, fetch = FetchType.LAZY, mappedBy = "idStok", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<DetilOpname> detilOpnameIdStok = new HashSet<DetilOpname>();

	@OneToMany(targetEntity = DetilPembelian.class, fetch = FetchType.LAZY, mappedBy = "idStok", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<DetilPembelian> detilPembelianIdStok = new HashSet<DetilPembelian>();

	@OneToMany(targetEntity = ObatResep.class, fetch = FetchType.LAZY, mappedBy = "idStok", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<ObatResep> obatResepIdStok = new HashSet<ObatResep>();

	/**
	 * Default constructor
	 */
	public StokObatAlat() {
	}

	public String getIdStok() {
		return idStok;
	}

	public void setIdStok(String idStok) {
		this.idStok = idStok;
	}

	public Date getTglKadaluarsa() {
		return tglKadaluarsa;
	}

	public void setTglKadaluarsa(Date tglKadaluarsa) {
		this.tglKadaluarsa = tglKadaluarsa;
	}

	public java.lang.Integer getJmlStokApotek() {
		return jmlStokApotek;
	}

	public void setJmlStokApotek(java.lang.Integer jmlStokApotek) {
		this.jmlStokApotek = jmlStokApotek;
	}

	public java.lang.Integer getJmlStokGudang() {
		return jmlStokGudang;
	}

	public void setJmlStokGudang(java.lang.Integer jmlStokGudang) {
		this.jmlStokGudang = jmlStokGudang;
	}
	
	public java.lang.Integer getHargaBeliStok() {
        return hargaBeliStok;
    }
	
    public void setHargaBeliStok (java.lang.Integer hargaBeliStok) {
        this.hargaBeliStok =  hargaBeliStok;
    }
    
    public java.lang.Integer getPpnStok() {
        return ppnStok;
    }
	
    public void setPpnStok (java.lang.Integer ppnStok) {
        this.ppnStok =  ppnStok;
    }
    
    public java.lang.Integer getDiscPercStok() {
        return discPercStok;
    }
	
    public void setDiscPercStok (java.lang.Integer discPercStok) {
        this.discPercStok =  discPercStok;
    }
    
    public java.lang.Integer getDiscCharge() {
        return discCharge;
    }
	
    public void setDiscCharge (java.lang.Integer discCharge) {
        this.discCharge =  discCharge;
    }

	public ObatAlat getIdObatAlat() {
		return idObatAlat;
	}

	public void setIdObatAlat(ObatAlat idObatAlat) {
		this.idObatAlat = idObatAlat;
	}

	public Set<DetilOpname> getDetilOpnameIdStok() {
		if (detilOpnameIdStok == null) {
			detilOpnameIdStok = new HashSet<DetilOpname>();
		}
		return detilOpnameIdStok;
	}

	public void setDetilOpnameIdStok(Set<DetilOpname> detilOpnameIdStok) {
		this.detilOpnameIdStok = detilOpnameIdStok;
	}

	public void addDetilOpnameIdStok(DetilOpname detilOpname) {
		getDetilOpnameIdStok().add(detilOpname);
	}

	public Set<DetilPembelian> getDetilPembelianIdStok() {
		if (detilPembelianIdStok == null) {
			detilPembelianIdStok = new HashSet<DetilPembelian>();
		}
		return detilPembelianIdStok;
	}

	public void setDetilPembelianIdStok(Set<DetilPembelian> detilPembelianIdStok) {
		this.detilPembelianIdStok = detilPembelianIdStok;
	}

	public void addDetilPembelianIdStok(DetilPembelian detilPembelian) {
		getDetilPembelianIdStok().add(detilPembelian);
	}

	public Set<ObatResep> getObatResepIdStok() {
		if (obatResepIdStok == null) {
			obatResepIdStok = new HashSet<ObatResep>();
		}
		return obatResepIdStok;
	}

	public void setObatResepIdStok(Set<ObatResep> obatResepIdStok) {
		this.obatResepIdStok = obatResepIdStok;
	}

	public void addObatResepIdStok(ObatResep obatResep) {
		getObatResepIdStok().add(obatResep);
	}

	public String toString() {
		return tglKadaluarsa + "";
	}

	public String getLabelStok() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String format = getTglKadaluarsa() == null ? "-" : dateFormat
				.format(getTglKadaluarsa());
		return "exp:" + format + ", Apotek:" + getJmlStokApotek() + ", Gudang:"
				+ getJmlStokGudang();
	}

	@Override
	public String getGeneratedValue() {
		return idStok;
	}
}
