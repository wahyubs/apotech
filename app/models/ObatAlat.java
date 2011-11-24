package models;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.db.jpa.GenericModel;

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
public class ObatAlat extends GenericModel implements IGeneratedModel,
		IGeneratedTransaction {

	@Id
	@Column(name = "id_obat_alat", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idObatAlat;

	@Column(name = "nama_obat_alat", length = 100, nullable = true, unique = false)
	private String namaObatAlat;

	@Column(name = "kode_obat_alat", length = 20, nullable = true, unique = false)
	private String kodeObatAlat;

	@Column(name = "harga_terakhir", nullable = true, unique = false)
	private java.lang.Integer hargaTerakhir;

	@Column(name = "volume_obat_alat", length = 30, nullable = true, unique = false)
	private String volumeObatAlat;

	@GeneratedValue(generator = "MyDateIdGenerator")
	@GenericGenerator(name = "MyDateGenerator", strategy = "tool.MyDateGenerator")
	@Column(name = "tgl_aktivitas", nullable = true, unique = false)
	private Date tglAktivitas;

	@Column(name = "minimum_stok", nullable = true, unique = false)
	private java.lang.Integer minimumStok;

	@Column(name = "satuan_obat_alat", length = 30, nullable = true, unique = false)
	private String satuanObatAlat;

	@Column(name = "kategori_obat", length = 1, nullable = true, unique = false)
	private String kategoriObat;

	@Column(name = "bentuk_obat_alat", length = 30, nullable = true, unique = false)
	private String bentukObatAlat;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jns_obat_alat", nullable = true, unique = false)
	private JenisObatAlat idJnsObatAlat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true, unique = false)
	private UserPegawai userId;

	@OneToMany(targetEntity = HargaObat.class, fetch = FetchType.LAZY, mappedBy = "idObatAlat", cascade = CascadeType.REMOVE)
	private Set<HargaObat> hargaObatIdObatAlat = new HashSet<HargaObat>();

	@OneToMany(targetEntity = StokObatAlat.class, fetch = FetchType.LAZY, mappedBy = "idObatAlat", cascade = CascadeType.REMOVE)
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

	public String getKodeObatAlat() {
		return kodeObatAlat;
	}

	public void setKodeObatAlat(String kodeObatAlat) {
		this.kodeObatAlat = kodeObatAlat;
	}

	public java.lang.Integer getHargaTerakhir() {
		return hargaTerakhir;
	}

	public void setHargaTerakhir(java.lang.Integer hargaTerakhir) {
		this.hargaTerakhir = hargaTerakhir;
	}

	public String getVolumeObatAlat() {
		return volumeObatAlat;
	}

	public void setVolumeObatAlat(String volumeObatAlat) {
		this.volumeObatAlat = volumeObatAlat;
	}

	public Date getTglAktivitas() {
		return tglAktivitas;
	}

	public void setTglAktivitas(Date tglAktivitas) {
		this.tglAktivitas = tglAktivitas;
	}

	public JenisObatAlat getIdJnsObatAlat() {
		return idJnsObatAlat;
	}

	public void setIdJnsObatAlat(JenisObatAlat idJnsObatAlat) {
		this.idJnsObatAlat = idJnsObatAlat;
	}

	public UserPegawai getUserId() {
		return userId;
	}

	public void setUserId(UserPegawai userId) {
		this.userId = userId;
	}

	public java.lang.Integer getMinimumStok() {
		return minimumStok;
	}

	public void setMinimumStok(java.lang.Integer minimumStok) {
		this.minimumStok = minimumStok;
	}
	
	public String getSatuanObatAlat() {
		return satuanObatAlat;
	}

	public void setSatuanObatAlat(String satuanObatAlat) {
		this.satuanObatAlat = satuanObatAlat;
	}

	public String getKategoriObat() {
		return kategoriObat;
	}

	public void setKategoriObat(String kategoriObat) {
		this.kategoriObat = kategoriObat;
	}

	public String getBentukObatAlat() {
		return bentukObatAlat;
	}

	public void setBentukObatAlat(String bentukObatAlat) {
		this.bentukObatAlat = bentukObatAlat;
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

	public String getLabelObat() {
		return getKodeObatAlat() + " - " + getNamaObatAlat();
	}

	@Override
	public String getGeneratedValue() {
		return idObatAlat;
	}

	public Integer getTotalStokApotek() {
		Query createNativeQuery = StokObatAlat
				.em()
				.createNativeQuery(
						"select sum(x.jml_stok_apotek) from stok_obat_alat x where x.id_obat_alat= :idObatAlat");
		createNativeQuery.setParameter("idObatAlat", idObatAlat);
		Number totalStokApotek = (Number) createNativeQuery.getSingleResult();
		return totalStokApotek == null ? 0 : totalStokApotek.intValue();
	}

	public Integer getTotalStokGudang() {
		Query createNativeQuery = StokObatAlat
				.em()
				.createNativeQuery(
						"select sum(x.jml_stok_gudang) from stok_obat_alat x where x.id_obat_alat= :idObatAlat");
		createNativeQuery.setParameter("idObatAlat", idObatAlat);
		Number totalStokGudang = (Number) createNativeQuery.getSingleResult();
		return totalStokGudang == null ? 0 : totalStokGudang.intValue();
	}

	public String getClassBaris() {
		Integer totalStok = getTotalStokApotek() + getTotalStokGudang();
		if (totalStok == 0) {
			return "stokKosong";
		} else if (minimumStok != null && totalStok < minimumStok) {
			return "stokKurang";
		}
		return "stokNormal";
	}

	@Override
	public Date getGeneratedDate() {
		return tglAktivitas;
	}
}
