package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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

	@Id
	@Column(name = "id_harga", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idHarga;

	@Column(name = "thn_bln_harga", length = 6, nullable = true, unique = false)
	private String thnBlnHarga;

	@Column(name = "harga_obat", nullable = true, unique = false)
	private java.lang.Integer hargaObat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_obat_alat", nullable = true, unique = false)
	private ObatAlat idObatAlat;

	/**
	 * Default constructor
	 */
	public HargaObat() {
	}

	public String getIdHarga() {
		return idHarga;
	}

	public void setIdHarga(String idHarga) {
		this.idHarga = idHarga;
	}

	public String getThnBlnHarga() {
		return thnBlnHarga;
	}

	public void setThnBlnHarga(String thnBlnHarga) {
		this.thnBlnHarga = thnBlnHarga;
	}

	public java.lang.Integer getHargaObat() {
		return hargaObat;
	}

	public void setHargaObat(java.lang.Integer hargaObat) {
		this.hargaObat = hargaObat;
	}

	public ObatAlat getIdObatAlat() {
		return idObatAlat;
	}

	public void setIdObatAlat(ObatAlat idObatAlat) {
		this.idObatAlat = idObatAlat;
	}

	public String toString() {
		return thnBlnHarga + "";
	}
}
