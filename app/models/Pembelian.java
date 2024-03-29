package models;

import java.util.Date;
import java.util.HashSet;
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

import play.data.binding.As;
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
public class Pembelian extends GenericModel implements IGeneratedModel,
IGeneratedTransaction {

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

	@GeneratedValue(generator = "MyDateIdGenerator")
	@GenericGenerator(name = "MyDateGenerator", strategy = "tool.MyDateGenerator")
	@Column(name = "tgl_aktivitas", nullable = true, unique = false)
	private Date tglAktivitas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true, unique = false)
	private UserPegawai userId;

	@Column(name = "sts_transaksi", length = 1, nullable = true, unique = false)
	private String stsTransaksi;

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

	public void setTglAktivitas(Date tglAktivitas) {
		this.tglAktivitas = tglAktivitas;
	}

	public UserPegawai getUserId() {
		return userId;
	}

	public void setUserId(UserPegawai userId) {
		this.userId = userId;
	}

	public String getStsTransaksi() {
		return stsTransaksi;
	}

	public void setStsTransaksi(String stsTransaksi) {
		this.stsTransaksi = stsTransaksi;
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

	public List monitoring(String key_idSupplier, String key_idObatAlat,
			@As("dd-MM-yyyy") Date tglPembelianAwal,
			@As("dd-MM-yyyy") Date tglPembelianAkhir) {
		String sql = "select pembelian.id_pembelian, pembelian.tgl_pembelian, supplier.nama_supplier,"
				+ " sum(detil_pembelian.jml_penerimaan_apotek) as jmlapotek, sum(detil_pembelian.jml_penerimaan_gudang) as jmlgudang, sum(detil_pembelian.harga_penerimaan) as harga, sum(detil_pembelian.discount_charge) as diskon"
				+ " from pembelian left join detil_pembelian on pembelian.id_pembelian=detil_pembelian.id_pembelian"
				+ " left join stok_obat_alat on detil_pembelian.id_stok=stok_obat_alat.id_stok"
				+ " join supplier on pembelian.id_supplier=supplier.id_supplier"
				+ " where 1=1";
		if (tglPembelianAwal != null)
			sql += " and pembelian.tgl_pembelian >= :tglPembelianAwal";
		if (tglPembelianAkhir != null)
			sql += " and pembelian.tgl_pembelian <= :tglPembelianAkhir";
		if (!key_idSupplier.equals(""))
			sql += " and pembelian.id_supplier = :key_idSupplier";
		if (!key_idObatAlat.equals(""))
			sql += " and stok_obat_alat.id_obat_alat = :key_idObatAlat";

		sql += " group by pembelian.id_pembelian, pembelian.tgl_pembelian, supplier.nama_supplier";
		Query query = StokObatAlat.em().createNativeQuery(sql);

		if (tglPembelianAwal != null) {
			query.setParameter("tglPembelianAwal", tglPembelianAwal);
		}
		if (tglPembelianAkhir != null) {
			query.setParameter("tglPembelianAkhir", tglPembelianAkhir);
		}
		if (!key_idSupplier.equals("")) {
			query.setParameter("key_idSupplier", key_idSupplier);
		}
		if (!key_idObatAlat.equals("")) {
			query.setParameter("key_idObatAlat", key_idObatAlat);
		}
		List a = null;
		try {
			a = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public String getGeneratedValue() {
		return idPembelian;
	}
	
	@Override
	public Date getGeneratedDate() {
		return tglAktivitas;
	}
}
