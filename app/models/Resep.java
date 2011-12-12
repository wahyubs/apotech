package models;

import java.text.SimpleDateFormat;
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

import play.db.jpa.GenericModel;
import tool.CommonUtil;

/**
 * 
 * <p>
 * Title: Resep
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a Resep entity
 * </p>
 * 
 */
@Entity(name = "Resep")
@Table(name = "resep")
public class Resep extends GenericModel implements IGeneratedModel,
		IGeneratedTransaction {

	@Id
	@Column(name = "id_resep", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idResep;

	@Column(name = "kode_resep", length = 50, nullable = true, unique = false)
	private String kodeResep;

	@Column(name = "tgl_resep", nullable = true, unique = false)
	private Date tglResep;

	@Column(name = "nama_dokter", length = 100, nullable = true, unique = false)
	private String namaDokter;

	@Column(name = "alamat_dokter", length = 200, nullable = true, unique = false)
	private String alamatDokter;

	@Column(name = "nama_pasien", length = 100, nullable = true, unique = false)
	private String namaPasien;

	@Column(name = "alamat_pasien", length = 200, nullable = true, unique = false)
	private String alamatPasien;

	@Column(name = "tgl_lahir_pasien", nullable = true, unique = false)
	private Date tglLahirPasien;

	@Column(name = "umur_pasien", nullable = true, unique = false)
	private java.lang.Integer umurPasien;

	@Column(name = "jen_kel_pasien", length = 1, nullable = true, unique = false)
	private String jenKelPasien;

	@Column(name = "tgl_penjualan", nullable = true, unique = false)
	private Date tglPenjualan;

	@GeneratedValue(generator = "MyDateIdGenerator")
	@GenericGenerator(name = "MyDateGenerator", strategy = "tool.MyDateGenerator")
	@Column(name = "tgl_aktivitas", nullable = true, unique = false)
	private Date tglAktivitas;

	@Column(name = "sts_transaksi", length = 1, nullable = true, unique = false)
	private String stsTransaksi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jns_harga", nullable = true, unique = false)
	private JenisHarga idJnsHarga;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true, unique = false)
	private UserPegawai userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tagihan", nullable = true, unique = false)
	private Tagihan idTagihan;

	@OneToMany(targetEntity = DetailResep.class, fetch = FetchType.LAZY, mappedBy = "idResep", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<DetailResep> detailResepIdResep = new HashSet<DetailResep>();

	/**
	 * Default constructor
	 */
	public Resep() {
	}

	public String getIdResep() {
		return idResep;
	}

	public void setIdResep(String idResep) {
		this.idResep = idResep;
	}

	public String getKodeResep() {
		return kodeResep;
	}

	public void setKodeResep(String kodeResep) {
		this.kodeResep = kodeResep;
	}

	public Date getTglResep() {
		return tglResep;
	}

	public void setTglResep(Date tglResep) {
		this.tglResep = tglResep;
	}

	public String getNamaDokter() {
		return namaDokter;
	}

	public void setNamaDokter(String namaDokter) {
		this.namaDokter = namaDokter;
	}

	public String getAlamatDokter() {
		return alamatDokter;
	}

	public void setAlamatDokter(String alamatDokter) {
		this.alamatDokter = alamatDokter;
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

	public Date getTglLahirPasien() {
		return tglLahirPasien;
	}

	public void setTglLahirPasien(Date tglLahirPasien) {
		this.tglLahirPasien = tglLahirPasien;
	}

	public java.lang.Integer getUmurPasien() {
		return umurPasien;
	}

	public void setUmurPasien(java.lang.Integer umurPasien) {
		this.umurPasien = umurPasien;
	}

	public String getJenKelPasien() {
		return jenKelPasien;
	}

	public void setJenKelPasien(String jenKelPasien) {
		this.jenKelPasien = jenKelPasien;
	}

	public Date getTglPenjualan() {
		return tglPenjualan;
	}

	public void setTglPenjualan(Date tglPenjualan) {
		this.tglPenjualan = tglPenjualan;
	}

	public Date getTglAktivitas() {
		return tglAktivitas;
	}

	public void setTglAktivitas(Date tglAktivitas) {
		this.tglAktivitas = tglAktivitas;
	}

	public String getStsTransaksi() {
		return stsTransaksi;
	}

	public void setStsTransaksi(String stsTransaksi) {
		this.stsTransaksi = stsTransaksi;
	}

	public JenisHarga getIdJnsHarga() {
		return idJnsHarga;
	}

	public void setIdJnsHarga(JenisHarga idJnsHarga) {
		this.idJnsHarga = idJnsHarga;
	}

	public UserPegawai getUserId() {
		return userId;
	}

	public void setUserId(UserPegawai userId) {
		this.userId = userId;
	}

	public Tagihan getIdTagihan() {
		return idTagihan;
	}

	public void setIdTagihan(Tagihan idTagihan) {
		this.idTagihan = idTagihan;
	}

	public Set<DetailResep> getDetailResepIdResep() {
		if (detailResepIdResep == null) {
			detailResepIdResep = new HashSet<DetailResep>();
		}
		return detailResepIdResep;
	}

	public void setDetailResepIdResep(Set<DetailResep> detailResepIdResep) {
		this.detailResepIdResep = detailResepIdResep;
	}

	public void addDetailResepIdResep(DetailResep detailResep) {
		getDetailResepIdResep().add(detailResep);
	}

	public String toString() {
		return kodeResep + "";
	}

	@Override
	public String getGeneratedValue() {
		return idResep;
	}

	@Override
	public Date getGeneratedDate() {
		return tglAktivitas;
	}

	public static List monitoringPenjualan(String key_idObatAlat,
			Date tglPenjualanAwal, Date tglPenjualanAkhir) {
		String sql = "select resep.id_resep, resep.tgl_penjualan, resep.kode_resep, resep.nama_pasien, count(obat_resep.id_stok)"
				+ " from resep left join detail_resep on resep.id_resep=detail_resep.id_resep"
				+" left join obat_resep on detail_resep.id_resep_dtl=obat_resep.id_resep_dtl"
				+ " left join stok_obat_alat on obat_resep.id_stok=stok_obat_alat.id_stok"
				+ " where 1=1";
		if (tglPenjualanAwal != null)
			sql += " and resep.tgl_penjualan >= :tglPenjualanAwal";
		if (tglPenjualanAkhir != null)
			sql += " and resep.tgl_penjualan <= :tglPenjualanAkhir";
		if (key_idObatAlat!=null && !key_idObatAlat.equals(""))
			sql += " and stok_obat_alat.id_obat_alat = :key_idObatAlat";

		sql += " group by resep.id_resep, resep.tgl_penjualan, resep.kode_resep, resep.nama_pasien";
		Query query = StokObatAlat.em().createNativeQuery(sql);

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		if (tglPenjualanAwal != null) {
			query.setParameter("tglPenjualanAwal", tglPenjualanAwal);
		}
		if (tglPenjualanAkhir != null) {
			query.setParameter("tglPenjualanAkhir", tglPenjualanAkhir);
		}
		if (key_idObatAlat!=null && !key_idObatAlat.equals("")) {
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
}
