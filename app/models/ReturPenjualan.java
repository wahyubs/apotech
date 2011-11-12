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
 * <p>
 * Title: ReturPenjualan
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a ReturPenjualan entity
 * </p>
 * 
 */
@Entity(name = "ReturPenjualan")
@Table(name = "retur_penjualan")
public class ReturPenjualan extends GenericModel {

	@Id
	@Column(name = "id_retur_jual", length = 32)
	private String idReturJual;

	@Column(name = "tgl_aktivitas", nullable = true, unique = false)
	private Date tglAktivitas;

	@Column(name = "tgl_retur_jual", nullable = true, unique = false)
	private Date tglReturJual;

	@Column(name = "sts_transaksi", length = 1, nullable = true, unique = false)
	private String stsTransaksi;

	@Column(name = "nama_pasien", length = 100, nullable = true, unique = false)
	private String namaPasien;

	@Column(name = "alamat_pasien", length = 200, nullable = true, unique = false)
	private String alamatPasien;

	@Column(name = "kode_resep", length = 50, nullable = true, unique = false)
	private String kodeResep;

	@Column(name = "kode_retur", length = 50, nullable = true, unique = false)
	private String kodeRetur;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true, unique = false)
	private UserPegawai userId;

	@OneToMany(targetEntity = DetailReturPenjualan.class, fetch = FetchType.LAZY, mappedBy = "idReturJual", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<DetailReturPenjualan> detailReturPenjualanIdReturJual = new HashSet<DetailReturPenjualan>();

	/**
	 * Default constructor
	 */
	public ReturPenjualan() {
	}

	public String getIdReturJual() {
		return idReturJual;
	}

	public void setIdReturJual(String idReturJual) {
		this.idReturJual = idReturJual;
	}

	public Date getTglAktivitas() {
		return tglAktivitas;
	}

	public void setTglAktivitas(Date tglAktivitas) {
		this.tglAktivitas = tglAktivitas;
	}

	public Date getTglReturJual() {
		return tglReturJual;
	}

	public void setTglReturJual(Date tglReturJual) {
		this.tglReturJual = tglReturJual;
	}

	public String getStsTransaksi() {
		return stsTransaksi;
	}

	public void setStsTransaksi(String stsTransaksi) {
		this.stsTransaksi = stsTransaksi;
	}

	public String getNamaPasien() {
		return namaPasien;
	}

	public void setNamaPasien(String namaPasien) {
		this.namaPasien = namaPasien;
	}

	public String getAlamatPasien() {
		return alamatPasien;
	}

	public void setAlamatPasien(String alamatPasien) {
		this.alamatPasien = alamatPasien;
	}

	public String getKodeResep() {
		return kodeResep;
	}

	public void setKodeResep(String kodeResep) {
		this.kodeResep = kodeResep;
	}

	public String getKodeRetur() {
		return kodeRetur;
	}

	public void setKodeRetur(String kodeRetur) {
		this.kodeRetur = kodeRetur;
	}

	public UserPegawai getUserId() {
		return userId;
	}

	public void setUserId(UserPegawai userId) {
		this.userId = userId;
	}

	public Set<DetailReturPenjualan> getDetailReturPenjualanIdReturJual() {
		if (detailReturPenjualanIdReturJual == null) {
			detailReturPenjualanIdReturJual = new HashSet<DetailReturPenjualan>();
		}
		return detailReturPenjualanIdReturJual;
	}

	public void setDetailReturPenjualanIdReturJual(
			Set<DetailReturPenjualan> detailReturPenjualanIdReturJual) {
		this.detailReturPenjualanIdReturJual = detailReturPenjualanIdReturJual;
	}

	public void addDetailReturPenjualanIdReturJual(
			DetailReturPenjualan detailReturPenjualan) {
		getDetailReturPenjualanIdReturJual().add(detailReturPenjualan);
	}

	public String toString() {
		return tglAktivitas + "";
	}
}
