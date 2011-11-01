package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

/**
 * 
 * <p>
 * Title: HargaObat
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a HargaObat entity
 * </p>
 * 
 */
@Entity(name = "HargaObat")
@Table(name = "harga_obat")
public class HargaObat extends GenericModel {

	@EmbeddedId
	private HargaObatId hargaObatId;

	@Column(name = "tgl_aktivitas", nullable = true, unique = false)
	private Date tglAktivitas;

	@Column(name = "harga_obat", nullable = true, unique = false)
	private java.lang.Double hargaObat;

	@Column(name = "active_sts", length = 1, nullable = true, unique = false)
	private String activeSts;

	@Column(name = "percent_laba", nullable = true, unique = false)
	private java.lang.Integer percentLaba;

	@MapsId("id_jns_harga")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jns_harga", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private JenisHarga idJnsHarga;

	@MapsId("id_obat_alat")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obat_alat", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private ObatAlat idObatAlat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true, unique = false)
	private UserPegawai userId;

	/**
	 * Default constructor
	 */
	public HargaObat() {
		hargaObatId = new HargaObatId(null, null, null);
	}

	public HargaObatId getHargaObatId() {
		return hargaObatId;
	}

	public void setHargaObatId(HargaObatId hargaObatId) {
		this.hargaObatId = hargaObatId;
	}

	public Date getTglAktivitas() {
		return tglAktivitas;
	}

	public void setTglAktivitas(Date tglAktivitas) {
		this.tglAktivitas = tglAktivitas;
	}

	public java.lang.Double getHargaObat() {
		return hargaObat;
	}

	public void setHargaObat(java.lang.Double hargaObat) {
		this.hargaObat = hargaObat;
	}

	public String getActiveSts() {
		return activeSts;
	}

	public void setActiveSts(String activeSts) {
		this.activeSts = activeSts;
	}

	public java.lang.Integer getPercentLaba() {
		return percentLaba;
	}

	public void setPercentLaba(java.lang.Integer percentLaba) {
		this.percentLaba = percentLaba;
	}

	public JenisHarga getIdJnsHarga() {
		return idJnsHarga;
	}

	public void setIdJnsHarga(JenisHarga idJnsHarga) {
		this.idJnsHarga = idJnsHarga;
	}

	public ObatAlat getIdObatAlat() {
		return idObatAlat;
	}

	public void setIdObatAlat(ObatAlat idObatAlat) {
		this.idObatAlat = idObatAlat;
	}

	public UserPegawai getUserId() {
		return userId;
	}

	public void setUserId(UserPegawai userId) {
		this.userId = userId;
	}

	public String toString() {
		return hargaObatId + "";
	}
}
