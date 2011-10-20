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
 * Title: Supplier
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a Supplier entity
 * </p>
 * 
 */
@Entity(name = "Supplier")
@Table(name = "supplier")
public class Supplier extends GenericModel implements IGeneratedModel{

	@Id
	@Column(name = "id_supplier", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idSupplier;

	@Column(name = "nama_supplier", length = 100, nullable = true, unique = false)
	private String namaSupplier;

	@Column(name = "alamat_supplier", length = 255, nullable = true, unique = false)
	private String alamatSupplier;

	@Column(name = "kota_supplier", length = 50, nullable = true, unique = false)
	private String kotaSupplier;
	
	@Column(name="tgl_aktivitas",    nullable=true,  unique=false)
    private Date tglAktivitas; 
	
	@ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",  nullable=true,  unique=false  )
    private UserPegawai userId; 

	@OneToMany(targetEntity = Pembelian.class, fetch = FetchType.LAZY, mappedBy = "idSupplier", cascade = CascadeType.REMOVE)
	private Set<Pembelian> pembelianIdSupplier = new HashSet<Pembelian>();

	/**
	 * Default constructor
	 */
	public Supplier() {
	}

	public String getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(String idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getNamaSupplier() {
		return namaSupplier;
	}

	public void setNamaSupplier(String namaSupplier) {
		this.namaSupplier = namaSupplier;
	}

	public String getAlamatSupplier() {
		return alamatSupplier;
	}

	public void setAlamatSupplier(String alamatSupplier) {
		this.alamatSupplier = alamatSupplier;
	}

	public String getKotaSupplier() {
		return kotaSupplier;
	}

	public void setKotaSupplier(String kotaSupplier) {
		this.kotaSupplier = kotaSupplier;
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

	public Set<Pembelian> getPembelianIdSupplier() {
		if (pembelianIdSupplier == null) {
			pembelianIdSupplier = new HashSet<Pembelian>();
		}
		return pembelianIdSupplier;
	}

	public void setPembelianIdSupplier(Set<Pembelian> pembelianIdSupplier) {
		this.pembelianIdSupplier = pembelianIdSupplier;
	}

	public void addPembelianIdSupplier(Pembelian pembelian) {
		getPembelianIdSupplier().add(pembelian);
	}

	public String toString() {
		return namaSupplier + "";
	}

	@Override
	public String getGeneratedValue() {
		return idSupplier;
	}
}
