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
 * Title: DetilOpname
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a DetilOpname entity
 * </p>
 * 
 */
@Entity(name = "DetilOpname")
@Table(name = "detil_opname")
public class DetilOpname extends GenericModel {

	@EmbeddedId
	private DetilOpnameId detilOpnameId;

	@Column(name = "jml_apotek_sebelumnya", nullable = true, unique = false)
	private java.lang.Integer jmlApotekSebelumnya;

	@Column(name = "jml_apotek_sekarang", nullable = true, unique = false)
	private java.lang.Integer jmlApotekSekarang;

	@Column(name = "tgl_kadaluarsa", nullable = true, unique = false)
	private Date tglKadaluarsa;

	@Column(name = "jml_gudang_sebelumnya", nullable = true, unique = false)
	private java.lang.Integer jmlGudangSebelumnya;

	@Column(name = "jml_gudang_sekarang", nullable = true, unique = false)
	private java.lang.Integer jmlGudangSekarang;

	@Column(name = "jml_sebelumnya", nullable = true, unique = false)
	private Integer jmlSebelumnya;

	@Column(name = "jml_sekarang", nullable = true, unique = false)
	private Integer jmlSekarang;

	@MapsId("id_stok")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stok", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private StokObatAlat idStok;

	@MapsId("id_stok_opname")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stok_opname", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private StokOpname idStokOpname;

	/**
	 * Default constructor
	 */
	public DetilOpname() {
		this.detilOpnameId = new DetilOpnameId();
	}

	public DetilOpnameId getDetilOpnameId() {
		return detilOpnameId;
	}

	public void setDetilOpnameId(DetilOpnameId detilOpnameId) {
		this.detilOpnameId = detilOpnameId;
	}

	public java.lang.Integer getJmlApotekSebelumnya() {
		return jmlApotekSebelumnya;
	}

	public void setJmlApotekSebelumnya(java.lang.Integer jmlApotekSebelumnya) {
		this.jmlApotekSebelumnya = jmlApotekSebelumnya;
	}

	public java.lang.Integer getJmlApotekSekarang() {
		return jmlApotekSekarang;
	}

	public void setJmlApotekSekarang(java.lang.Integer jmlApotekSekarang) {
		this.jmlApotekSekarang = jmlApotekSekarang;
	}

	public Date getTglKadaluarsa() {
		return tglKadaluarsa;
	}

	public void setTglKadaluarsa(Date tglKadaluarsa) {
		this.tglKadaluarsa = tglKadaluarsa;
	}

	public java.lang.Integer getJmlGudangSebelumnya() {
		return jmlGudangSebelumnya;
	}

	public void setJmlGudangSebelumnya(java.lang.Integer jmlGudangSebelumnya) {
		this.jmlGudangSebelumnya = jmlGudangSebelumnya;
	}

	public java.lang.Integer getJmlGudangSekarang() {
		return jmlGudangSekarang;
	}

	public void setJmlGudangSekarang(java.lang.Integer jmlGudangSekarang) {
		this.jmlGudangSekarang = jmlGudangSekarang;
	}

	public Integer getJmlSebelumnya() {
		return jmlSebelumnya;
	}

	public void setJmlSebelumnya(Integer jmlSebelumnya) {
		this.jmlSebelumnya = jmlSebelumnya;
	}

	public Integer getJmlSekarang() {
		return jmlSekarang;
	}

	public void setJmlSekarang(Integer jmlSekarang) {
		this.jmlSekarang = jmlSekarang;
	}

	public StokObatAlat getIdStok() {
		return idStok;
	}

	public void setIdStok(StokObatAlat idStok) {
		this.idStok = idStok;
	}

	public StokOpname getIdStokOpname() {
		return idStokOpname;
	}

	public void setIdStokOpname(StokOpname idStokOpname) {
		this.idStokOpname = idStokOpname;
	}

	public String toString() {
		return detilOpnameId + "";
	}

	public Integer getJmlKoreksiApotek() {
		return (jmlApotekSekarang == null ? 0 : jmlApotekSekarang)
				- (jmlApotekSebelumnya == null ? 0 : jmlApotekSebelumnya);
	}

	public Integer getJmlKoreksiGudang() {
		return (jmlGudangSekarang == null ? 0 : jmlGudangSekarang)
				- (jmlGudangSebelumnya == null ? 0 : jmlGudangSebelumnya);
	}
}
