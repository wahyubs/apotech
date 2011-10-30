package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;
import play.libs.Crypto;
import tool.CommonUtil;

/**
 * 
 * <p>
 * Title: UserPegawai
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a UserPegawai entity
 * </p>
 * 
 */
@Entity(name = "UserPegawai")
@Table(name = "USER_PEGAWAI")
public class UserPegawai extends GenericModel {

	@Id
	@Column(name = "user_id", length = 32)
	private String userId;

	@Column(name = "password_user", length = 32, nullable = true, unique = false)
	private String passwordUser;

	@Column(name = "nama_user", length = 100, nullable = true, unique = false)
	private String namaUser;

	@Column(name = "no_peg", length = 50, nullable = true, unique = false)
	private String noPeg;

	@Column(name = "jns_user", length = 1, nullable = true, unique = false)
	private String jnsUser;

	@OneToMany(targetEntity = HargaObat.class, fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<HargaObat> hargaObatUserId = new HashSet<HargaObat>();

	@OneToMany(targetEntity = ObatAlat.class, fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<ObatAlat> obatAlatUserId = new HashSet<ObatAlat>();

	@OneToMany(targetEntity = Resep.class, fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<Resep> resepUserId = new HashSet<Resep>();

	@OneToMany(targetEntity = StokOpname.class, fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<StokOpname> stokOpnameUserId = new HashSet<StokOpname>();

	@OneToMany(targetEntity = Supplier.class, fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<Supplier> supplierUserId = new HashSet<Supplier>();

	@OneToMany(targetEntity = Tagihan.class, fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<Tagihan> tagihanUserId = new HashSet<Tagihan>();

	/**
	 * Default constructor
	 */
	public UserPegawai() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public String getNamaUser() {
		return namaUser;
	}

	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}

	public String getNoPeg() {
		return noPeg;
	}

	public void setNoPeg(String noPeg) {
		this.noPeg = noPeg;
	}

	public String getJnsUser() {
		return jnsUser;
	}

	public void setJnsUser(String jnsUser) {
		this.jnsUser = jnsUser;
	}

	public Set<HargaObat> getHargaObatUserId() {
		if (hargaObatUserId == null) {
			hargaObatUserId = new HashSet<HargaObat>();
		}
		return hargaObatUserId;
	}

	public void setHargaObatUserId(Set<HargaObat> hargaObatUserId) {
		this.hargaObatUserId = hargaObatUserId;
	}

	public void addHargaObatUserId(HargaObat hargaObat) {
		getHargaObatUserId().add(hargaObat);
	}

	public Set<ObatAlat> getObatAlatUserId() {
		if (obatAlatUserId == null) {
			obatAlatUserId = new HashSet<ObatAlat>();
		}
		return obatAlatUserId;
	}

	public void setObatAlatUserId(Set<ObatAlat> obatAlatUserId) {
		this.obatAlatUserId = obatAlatUserId;
	}

	public void addObatAlatUserId(ObatAlat obatAlat) {
		getObatAlatUserId().add(obatAlat);
	}

	public Set<Resep> getResepUserId() {
		if (resepUserId == null) {
			resepUserId = new HashSet<Resep>();
		}
		return resepUserId;
	}

	public void setResepUserId(Set<Resep> resepUserId) {
		this.resepUserId = resepUserId;
	}

	public void addResepUserId(Resep resep) {
		getResepUserId().add(resep);
	}

	public Set<StokOpname> getStokOpnameUserId() {
		if (stokOpnameUserId == null) {
			stokOpnameUserId = new HashSet<StokOpname>();
		}
		return stokOpnameUserId;
	}

	public void setStokOpnameUserId(Set<StokOpname> stokOpnameUserId) {
		this.stokOpnameUserId = stokOpnameUserId;
	}

	public void addStokOpnameUserId(StokOpname stokOpname) {
		getStokOpnameUserId().add(stokOpname);
	}

	public Set<Supplier> getSupplierUserId() {
		if (supplierUserId == null) {
			supplierUserId = new HashSet<Supplier>();
		}
		return supplierUserId;
	}

	public void setSupplierUserId(Set<Supplier> supplierUserId) {
		this.supplierUserId = supplierUserId;
	}

	public void addSupplierUserId(Supplier supplier) {
		getSupplierUserId().add(supplier);
	}

	public Set<Tagihan> getTagihanUserId() {
		if (tagihanUserId == null) {
			tagihanUserId = new HashSet<Tagihan>();
		}
		return tagihanUserId;
	}

	public void setTagihanUserId(Set<Tagihan> tagihanUserId) {
		this.tagihanUserId = tagihanUserId;
	}

	public void addTagihanUserId(Tagihan tagihan) {
		getTagihanUserId().add(tagihan);
	}

	public String toString() {
		return userId + ":" + namaUser;
	}

	@Override
	public void _save() {
		if (CommonUtil.isEmpty(passwordUser) && userId != null) {
			UserPegawai userPegawai = UserPegawai.findById(userId);
			passwordUser = userPegawai.getPasswordUser();
		}
		passwordUser = Crypto.passwordHash(passwordUser);
		super._save();
	}
}
