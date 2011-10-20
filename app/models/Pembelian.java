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
 * <p>
 * Title: Pembelian
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a Pembelian entity
 * </p>
 * 
 */
@Entity(name = "Pembelian")
@Table(name = "pembelian")
public class Pembelian extends GenericModel implements IGeneratedModel{

	@Id
	@Column(name = "id_pembelian", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idPembelian;

	@Column(name = "tgl_pembelian", nullable = true, unique = false)
	private Date tglPembelian;

	@Column(name = "tgl_faktur", nullable = true, unique = false)
	private Date tglFaktur;

	@Column(name = "no_faktur", length = 30, nullable = true, unique = false)
	private String noFaktur;
	
	@Column(name="tgl_aktivitas",    nullable=true,  unique=false)
    private Date tglAktivitas; 
	
	@ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_supplier", nullable = true, unique = false)
	private Supplier idSupplier;

	@OneToMany(targetEntity = DetilPembelian.class, fetch = FetchType.LAZY, mappedBy = "idPembelian", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<DetilPembelian> detilPembelianIdPembelian = new HashSet<DetilPembelian>();

	/**
	 * Default constructor
	 */
	public Pembelian() {
	}

	public String getIdPembelian() {
		return idPembelian;
	}

	public void setIdPembelian(String idPembelian) {
		this.idPembelian = idPembelian;
	}

	public Date getTglPembelian() {
		return tglPembelian;
	}

	public void setTglPembelian(Date tglPembelian) {
		this.tglPembelian = tglPembelian;
	}

	public Date getTglFaktur() {
		return tglFaktur;
	}

	public void setTglFaktur(Date tglFaktur) {
		this.tglFaktur = tglFaktur;
	}

	public String getNoFaktur() {
		return noFaktur;
	}

	public void setNoFaktur(String noFaktur) {
		this.noFaktur = noFaktur;
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

	public Supplier getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Supplier idSupplier) {
		this.idSupplier = idSupplier;
	}

	public Set<DetilPembelian> getDetilPembelianIdPembelian() {
		if (detilPembelianIdPembelian == null) {
			detilPembelianIdPembelian = new HashSet<DetilPembelian>();
		}
		return detilPembelianIdPembelian;
	}

	public void setDetilPembelianIdPembelian(
			Set<DetilPembelian> detilPembelianIdPembelian) {
		this.detilPembelianIdPembelian = detilPembelianIdPembelian;
	}

	public void addDetilPembelianIdPembelian(DetilPembelian detilPembelian) {
		getDetilPembelianIdPembelian().add(detilPembelian);
	}

	public String toString() {
		return tglPembelian + "";
	}

	@Override
	public String getGeneratedValue() {
		return idPembelian;
	}
}
