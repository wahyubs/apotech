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
 * Title: ReturPembelian
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a ReturPembelian entity
 * </p>
 * 
 */
@Entity(name = "ReturPembelian")
@Table(name = "retur_pembelian")
public class ReturPembelian extends GenericModel implements IGeneratedModel,
		IGeneratedTransaction {

	@Id
	@Column(name = "id_retur_beli", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idReturBeli;

	@GeneratedValue(generator = "MyDateIdGenerator")
	@GenericGenerator(name = "MyDateGenerator", strategy = "tool.MyDateGenerator")
	@Column(name = "tgl_aktivitas", nullable = true, unique = false)
	private Date tglAktivitas;

	@Column(name = "tgl_retur_beli", nullable = true, unique = false)
	private Date tglReturBeli;

	@Column(name = "desc_retur_beli", length = 500, nullable = true, unique = false)
	private String descReturBeli;

	@Column(name = "sts_transaksi", length = 1, nullable = true, unique = false)
	private String stsTransaksi;

	@Column(name = "no_faktur_beli", length = 30, nullable = true, unique = false)
	private String noFakturBeli;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_supplier", nullable = true, unique = false)
	private Supplier idSupplier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true, unique = false)
	private UserPegawai userId;

	@OneToMany(targetEntity = DetilReturPembelian.class, fetch = FetchType.LAZY, mappedBy = "idReturBeli", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<DetilReturPembelian> detilReturPembelianIdReturBeli = new HashSet<DetilReturPembelian>();

	/**
	 * Default constructor
	 */
	public ReturPembelian() {
	}

	public String getIdReturBeli() {
		return idReturBeli;
	}

	public void setIdReturBeli(String idReturBeli) {
		this.idReturBeli = idReturBeli;
	}

	public Date getTglAktivitas() {
		return tglAktivitas;
	}

	public void setTglAktivitas(Date tglAktivitas) {
		this.tglAktivitas = tglAktivitas;
	}

	public Date getTglReturBeli() {
		return tglReturBeli;
	}

	public void setTglReturBeli(Date tglReturBeli) {
		this.tglReturBeli = tglReturBeli;
	}

	public String getDescReturBeli() {
		return descReturBeli;
	}

	public void setDescReturBeli(String descReturBeli) {
		this.descReturBeli = descReturBeli;
	}

	public String getStsTransaksi() {
		return stsTransaksi;
	}

	public void setStsTransaksi(String stsTransaksi) {
		this.stsTransaksi = stsTransaksi;
	}

	public String getNoFakturBeli() {
		return noFakturBeli;
	}

	public void setNoFakturBeli(String noFakturBeli) {
		this.noFakturBeli = noFakturBeli;
	}

	public Supplier getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Supplier idSupplier) {
		this.idSupplier = idSupplier;
	}

	public UserPegawai getUserId() {
		return userId;
	}

	public void setUserId(UserPegawai userId) {
		this.userId = userId;
	}

	public Set<DetilReturPembelian> getDetilReturPembelianIdReturBeli() {
		if (detilReturPembelianIdReturBeli == null) {
			detilReturPembelianIdReturBeli = new HashSet<DetilReturPembelian>();
		}
		return detilReturPembelianIdReturBeli;
	}

	public void setDetilReturPembelianIdReturBeli(
			Set<DetilReturPembelian> detilReturPembelianIdReturBeli) {
		this.detilReturPembelianIdReturBeli = detilReturPembelianIdReturBeli;
	}

	public void addDetilReturPembelianIdReturBeli(
			DetilReturPembelian detilReturPembelian) {
		getDetilReturPembelianIdReturBeli().add(detilReturPembelian);
	}

	public String toString() {
		return tglAktivitas + "";
	}

	@Override
	public String getGeneratedValue() {
		return idReturBeli;
	}

	@Override
	public Date getGeneratedDate() {
		return tglAktivitas;
	}
}
