package models;

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
 * Title: ObatResep
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a ObatResep entity
 * </p>
 * 
 */
@Entity(name = "ObatResep")
@Table(name = "obat_resep")
public class ObatResep extends GenericModel {

	@EmbeddedId
	private ObatResepId obatResepId;

	@Column(name = "jml_obat_resep", nullable = true, unique = false)
	private java.lang.Integer jmlObatResep;

	@Column(name = "harga_obat", nullable = true, unique = false)
	private java.lang.Integer hargaObat;

	@MapsId("id_resep_dtl")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_resep_dtl", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private DetailResep idResepDtl;

	@MapsId("id_stok")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stok", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private StokObatAlat idStok;

	/**
	 * Default constructor
	 */
	public ObatResep() {
		obatResepId = new ObatResepId();
	}

	public ObatResepId getObatResepId() {
		return obatResepId;
	}

	public void setObatResepId(ObatResepId obatResepId) {
		this.obatResepId = obatResepId;
	}

	public java.lang.Integer getJmlObatResep() {
		return jmlObatResep;
	}

	public void setJmlObatResep(java.lang.Integer jmlObatResep) {
		this.jmlObatResep = jmlObatResep;
	}

	public java.lang.Integer getHargaObat() {
		return hargaObat;
	}

	public void setHargaObat(java.lang.Integer hargaObat) {
		this.hargaObat = hargaObat;
	}

	public DetailResep getIdResepDtl() {
		return idResepDtl;
	}

	public void setIdResepDtl(DetailResep idResepDtl) {
		this.idResepDtl = idResepDtl;
	}

	public StokObatAlat getIdStok() {
		return idStok;
	}

	public void setIdStok(StokObatAlat idStok) {
		this.idStok = idStok;
	}

	public String toString() {
		return obatResepId + "";
	}
}
