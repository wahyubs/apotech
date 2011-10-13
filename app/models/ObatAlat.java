package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.db.jpa.GenericModel;
import tool.MyIdGenerator;

/**
 * 
 * <p>
 * Title: ObatAlat
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a ObatAlat entity
 * </p>
 * 
 */
@Entity(name = "ObatAlat")
@Table(name = "obat_alat")
public class ObatAlat extends GenericModel {

	@Id
	@Column(name = "id_obat_alat", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idObatAlat;

	@Column(name = "nama_obat_alat", length = 100, nullable = true, unique = false)
	private String namaObatAlat;

	@Column(name = "jenis_obat_alat", length = 1, nullable = true, unique = false)
	private String jenisObatAlat;

	@Column(name = "harga_terakhir", nullable = true, unique = false)
	private java.lang.Integer hargaTerakhir;

	@OneToMany(targetEntity = HargaObat.class, fetch = FetchType.LAZY, mappedBy = "idObatAlat", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<HargaObat> hargaObatIdObatAlat = new HashSet<HargaObat>();

	@OneToMany(targetEntity = StokObatAlat.class, fetch = FetchType.LAZY, mappedBy = "idObatAlat", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<StokObatAlat> stokObatAlatIdObatAlat = new HashSet<StokObatAlat>();

	/**
	 * Default constructor
	 */
	public ObatAlat() {
	}

	public String getIdObatAlat() {
		return idObatAlat;
	}

	public void setIdObatAlat(String idObatAlat) {
		this.idObatAlat = idObatAlat;
	}

	public String getNamaObatAlat() {
		return namaObatAlat;
	}

	public void setNamaObatAlat(String namaObatAlat) {
		this.namaObatAlat = namaObatAlat;
	}

	public String getJenisObatAlat() {
		return jenisObatAlat;
	}

	public void setJenisObatAlat(String jenisObatAlat) {
		this.jenisObatAlat = jenisObatAlat;
	}

	public java.lang.Integer getHargaTerakhir() {
		return hargaTerakhir;
	}

	public void setHargaTerakhir(java.lang.Integer hargaTerakhir) {
		this.hargaTerakhir = hargaTerakhir;
	}

	public Set<HargaObat> getHargaObatIdObatAlat() {
		if (hargaObatIdObatAlat == null) {
			hargaObatIdObatAlat = new HashSet<HargaObat>();
		}
		return hargaObatIdObatAlat;
	}

	public void setHargaObatIdObatAlat(Set<HargaObat> hargaObatIdObatAlat) {
		this.hargaObatIdObatAlat = hargaObatIdObatAlat;
	}

	public void addHargaObatIdObatAlat(HargaObat hargaObat) {
		getHargaObatIdObatAlat().add(hargaObat);
	}

	public Set<StokObatAlat> getStokObatAlatIdObatAlat() {
		if (stokObatAlatIdObatAlat == null) {
			stokObatAlatIdObatAlat = new HashSet<StokObatAlat>();
		}
		return stokObatAlatIdObatAlat;
	}

	public void setStokObatAlatIdObatAlat(
			Set<StokObatAlat> stokObatAlatIdObatAlat) {
		this.stokObatAlatIdObatAlat = stokObatAlatIdObatAlat;
	}

	public void addStokObatAlatIdObatAlat(StokObatAlat stokObatAlat) {
		getStokObatAlatIdObatAlat().add(stokObatAlat);
	}

	public String toString() {
		return namaObatAlat + "";
	}
}
