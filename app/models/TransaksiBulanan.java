package models;

import java.util.Calendar;
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
import play.db.jpa.JPABase;
import tool.CommonUtil;

/**
 * 
 * <p>
 * Title: TransaksiBulanan
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a TransaksiBulanan entity
 * </p>
 * 
 */
@Entity(name = "TransaksiBulanan")
@Table(name = "transaksi_bulanan")
public class TransaksiBulanan extends GenericModel {

	@EmbeddedId
	private TransaksiBulananId transaksiBulananId;

	@Column(name = "stok_awal_apotek", nullable = true, unique = false)
	private java.lang.Integer stokAwalApotek;

	@Column(name = "stok_awal_gudang", nullable = true, unique = false)
	private java.lang.Integer stokAwalGudang;

	@Column(name = "penambahan_stok_apotek", nullable = true, unique = false)
	private java.lang.Integer penambahanStokApotek;

	@Column(name = "penambahan_stok_gudang", nullable = true, unique = false)
	private java.lang.Integer penambahanStokGudang;

	@Column(name = "pengurangan_stok_apotek", nullable = true, unique = false)
	private java.lang.Integer penguranganStokApotek;

	@Column(name = "pengurangan_stok_gudang", nullable = true, unique = false)
	private java.lang.Integer penguranganStokGudang;

	@Column(name = "stok_akhir_apotek", nullable = true, unique = false)
	private java.lang.Integer stokAkhirApotek;

	@Column(name = "stok_akhir_gudang", nullable = true, unique = false)
	private java.lang.Integer stokAkhirGudang;

	@MapsId("id_stok")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stok", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private StokObatAlat idStok;

	/**
	 * Default constructor
	 */
	public TransaksiBulanan() {
	}

	public TransaksiBulananId getTransaksiBulananId() {
		return transaksiBulananId;
	}

	public void setTransaksiBulananId(TransaksiBulananId transaksiBulananId) {
		this.transaksiBulananId = transaksiBulananId;
	}

	public java.lang.Integer getStokAwalApotek() {
		return stokAwalApotek;
	}

	public void setStokAwalApotek(java.lang.Integer stokAwalApotek) {
		this.stokAwalApotek = stokAwalApotek;
	}

	public java.lang.Integer getStokAwalGudang() {
		return stokAwalGudang;
	}

	public void setStokAwalGudang(java.lang.Integer stokAwalGudang) {
		this.stokAwalGudang = stokAwalGudang;
	}

	public java.lang.Integer getPenambahanStokApotek() {
		return penambahanStokApotek;
	}

	public void setPenambahanStokApotek(java.lang.Integer penambahanStokApotek) {
		this.penambahanStokApotek = penambahanStokApotek;
	}

	public java.lang.Integer getPenambahanStokGudang() {
		return penambahanStokGudang;
	}

	public void setPenambahanStokGudang(java.lang.Integer penambahanStokGudang) {
		this.penambahanStokGudang = penambahanStokGudang;
	}

	public java.lang.Integer getPenguranganStokApotek() {
		return penguranganStokApotek;
	}

	public void setPenguranganStokApotek(java.lang.Integer penguranganStokApotek) {
		this.penguranganStokApotek = penguranganStokApotek;
	}

	public java.lang.Integer getPenguranganStokGudang() {
		return penguranganStokGudang;
	}

	public void setPenguranganStokGudang(java.lang.Integer penguranganStokGudang) {
		this.penguranganStokGudang = penguranganStokGudang;
	}

	public java.lang.Integer getStokAkhirApotek() {
		return stokAkhirApotek;
	}

	public void setStokAkhirApotek(java.lang.Integer stokAkhirApotek) {
		this.stokAkhirApotek = stokAkhirApotek;
	}

	public java.lang.Integer getStokAkhirGudang() {
		return stokAkhirGudang;
	}

	public void setStokAkhirGudang(java.lang.Integer stokAkhirGudang) {
		this.stokAkhirGudang = stokAkhirGudang;
	}

	public StokObatAlat getIdStok() {
		return idStok;
	}

	public void setIdStok(StokObatAlat idStok) {
		this.idStok = idStok;
	}

	public String toString() {
		return transaksiBulananId + "";
	}

	public static void generate(String idStok, Integer jmlStokApotek,
			Integer jmlStokGudang, Integer jmlTerimaApotek,
			Integer jmlTerimaGudang, Integer jmlKirimApotek,
			Integer jmlKirimGudang, boolean cancel) {
		if (jmlTerimaApotek == null)
			jmlTerimaApotek = 0;
		if (jmlTerimaGudang == null)
			jmlTerimaGudang = 0;
		if (jmlKirimApotek == null)
			jmlKirimApotek = 0;
		if (jmlKirimGudang == null)
			jmlKirimGudang = 0;
		TransaksiBulananId transaksiBulananId = new TransaksiBulananId(idStok,
				CommonUtil.getFormatThnBln(new Date()));
		TransaksiBulanan transaksiBulanan = TransaksiBulanan
				.findById(transaksiBulananId);
		Integer jmlTambahApotek = 0;
		Integer jmlTambahGudang = 0;
		Integer jmlKurangApotek = 0;
		Integer jmlKurangGudang = 0;
		if (transaksiBulanan == null) {
			transaksiBulanan = new TransaksiBulanan();
			transaksiBulanan.setTransaksiBulananId(transaksiBulananId);
			Calendar instance = Calendar.getInstance();
			instance.add(Calendar.MONTH, -1);
			TransaksiBulanan bulanLalu = TransaksiBulanan
					.findById(new TransaksiBulananId(idStok, CommonUtil
							.getFormatThnBln(instance.getTime())));
			if (bulanLalu == null) {
				transaksiBulanan.setStokAwalApotek(jmlStokApotek);
				transaksiBulanan.setStokAwalGudang(jmlStokGudang);
			} else {
				transaksiBulanan.setStokAwalApotek(bulanLalu
						.getStokAkhirApotek());
				transaksiBulanan.setStokAwalGudang(bulanLalu
						.getStokAkhirGudang());
			}
		} else {
			jmlTambahApotek = transaksiBulanan.getPenambahanStokApotek();
			jmlTambahGudang = transaksiBulanan.getPenambahanStokGudang();
			jmlKurangApotek = transaksiBulanan.getPenguranganStokApotek();
			jmlKurangGudang = transaksiBulanan.getPenguranganStokGudang();
		}
		transaksiBulanan
				.setIdStok((StokObatAlat) StokObatAlat.findById(idStok));
		if (cancel) {
			transaksiBulanan.setPenambahanStokApotek(jmlTambahApotek
					- jmlTerimaApotek);
			transaksiBulanan.setPenambahanStokGudang(jmlTambahGudang
					- jmlTerimaGudang);
			transaksiBulanan.setPenguranganStokApotek(jmlKurangApotek
					- jmlKirimApotek);
			transaksiBulanan.setPenguranganStokGudang(jmlKurangGudang
					- jmlKirimGudang);
		} else {
			transaksiBulanan.setPenambahanStokApotek(jmlTerimaApotek
					+ jmlTambahApotek);
			transaksiBulanan.setPenambahanStokGudang(jmlTerimaGudang
					+ jmlTambahGudang);
			transaksiBulanan.setPenguranganStokApotek(jmlKirimApotek
					+ jmlKurangApotek);
			transaksiBulanan.setPenguranganStokGudang(jmlKirimGudang
					+ jmlKurangGudang);
		}
		transaksiBulanan.setStokAkhirApotek(transaksiBulanan
				.getStokAwalApotek()
				+ transaksiBulanan.getPenambahanStokApotek()
				- transaksiBulanan.getPenguranganStokApotek());
		transaksiBulanan.setStokAkhirGudang(transaksiBulanan
				.getStokAwalGudang()
				+ transaksiBulanan.getPenambahanStokGudang()
				- transaksiBulanan.getPenguranganStokGudang());
		transaksiBulanan = transaksiBulanan.merge();
		transaksiBulanan.save();
	}
}
