package models;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.db.jpa.GenericModel;
import tool.CommonUtil;

/**
 * 
 * <p>
 * Title: DetailResep
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a DetailResep entity
 * </p>
 * 
 */
@Entity(name = "DetailResep")
@Table(name = "detail_resep")
public class DetailResep extends GenericModel implements IGeneratedModel {

	@Id
	@Column(name = "id_resep_dtl", length = 32)
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator")
	private String idResepDtl;

	@Column(name = "jml_obat", nullable = true, unique = false)
	private java.lang.Integer jmlObat;

	@Column(name = "dosis_obat", length = 100, nullable = true, unique = false)
	private String dosisObat;

	@Column(name = "urutan_obat", nullable = true, unique = false)
	private java.lang.Integer urutanObat;

	@Column(name = "deskripsi", length = 500, nullable = true, unique = false)
	private String deskripsi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_resep", nullable = true, unique = false)
	private Resep idResep;

	@OneToMany(targetEntity = ObatResep.class, fetch = FetchType.LAZY, mappedBy = "idResepDtl", cascade = CascadeType.REMOVE)
	// , cascade=CascadeType.ALL)
	private Set<ObatResep> obatResepIdResepDtl = new HashSet<ObatResep>();

	/**
	 * Default constructor
	 */
	public DetailResep() {
	}

	public String getIdResepDtl() {
		return idResepDtl;
	}

	public void setIdResepDtl(String idResepDtl) {
		this.idResepDtl = idResepDtl;
	}

	public java.lang.Integer getJmlObat() {
		return jmlObat;
	}

	public void setJmlObat(java.lang.Integer jmlObat) {
		this.jmlObat = jmlObat;
	}

	public String getDosisObat() {
		return dosisObat;
	}

	public void setDosisObat(String dosisObat) {
		this.dosisObat = dosisObat;
	}

	public java.lang.Integer getUrutanObat() {
		return urutanObat;
	}

	public void setUrutanObat(java.lang.Integer urutanObat) {
		this.urutanObat = urutanObat;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Resep getIdResep() {
		return idResep;
	}

	public void setIdResep(Resep idResep) {
		this.idResep = idResep;
	}

	public Set<ObatResep> getObatResepIdResepDtl() {
		if (obatResepIdResepDtl == null) {
			obatResepIdResepDtl = new HashSet<ObatResep>();
		}
		return obatResepIdResepDtl;
	}

	public void setObatResepIdResepDtl(Set<ObatResep> obatResepIdResepDtl) {
		this.obatResepIdResepDtl = obatResepIdResepDtl;
	}

	public void addObatResepIdResepDtl(ObatResep obatResep) {
		getObatResepIdResepDtl().add(obatResep);
	}

	public String toString() {
		return jmlObat + "";
	}

	public String getLabelObatResep() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<ObatResep> fetch = ObatResep.find("id_resep_dtl=?", idResepDtl)
				.fetch();
		String hasil = "";
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			ObatResep obatResep = (ObatResep) iterator.next();
			String namaObatAlat = obatResep.getIdStok().getIdObatAlat()
					.getNamaObatAlat();
			String kodeObatAlat = obatResep.getIdStok().getIdObatAlat()
					.getKodeObatAlat();
			String tglKadaluarsa = obatResep.getIdStok().getTglKadaluarsa() == null ? "-"
					: dateFormat.format(obatResep.getIdStok()
							.getTglKadaluarsa());
			hasil += kodeObatAlat + " - " + namaObatAlat + " (" + tglKadaluarsa
					+ ") ";
		}
		return hasil;
	}
	
	public String getLabelHargaObat() {
		DecimalFormat decimalFormat = new DecimalFormat("#######0");
		List<ObatResep> fetch = ObatResep.find("id_resep_dtl=?", idResepDtl)
				.fetch();
		String hasil = "";
		for (Iterator iterator = fetch.iterator(); iterator.hasNext();) {
			ObatResep obatResep = (ObatResep) iterator.next();
			String hargaObat = obatResep.getHargaObat()==null?"":decimalFormat.format(obatResep.getHargaObat());
			hasil += hargaObat + " ";
		}
		return hasil;
	}

	@Override
	public String getGeneratedValue() {
		return idResepDtl;
	}
}
