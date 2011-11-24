package models;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Query;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;
import tool.CommonUtil;

/**
 * 
 * <p>
 * Title: TransaksiHarian
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a TransaksiHarian entity
 * </p>
 * 
 */
@Entity(name = "TransaksiHarian")
@Table(name = "transaksi_harian")
public class TransaksiHarian extends GenericModel {

	@EmbeddedId
	private TransaksiHarianId transaksiHarianId;

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

	@Column(name = "urutan", nullable = true, unique = false)
	private java.lang.Integer urutan;

	@MapsId("id_stok")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stok", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private StokObatAlat idStok;

	/**
	 * Default constructor
	 */
	public TransaksiHarian() {
	}

	public TransaksiHarianId getTransaksiHarianId() {
		return transaksiHarianId;
	}

	public void setTransaksiHarianId(TransaksiHarianId transaksiHarianId) {
		this.transaksiHarianId = transaksiHarianId;
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

	public java.lang.Integer getUrutan() {
		return urutan;
	}

	public void setUrutan(java.lang.Integer urutan) {
		this.urutan = urutan;
	}

	public StokObatAlat getIdStok() {
		return idStok;
	}

	public void setIdStok(StokObatAlat idStok) {
		this.idStok = idStok;
	}

	public String toString() {
		return transaksiHarianId + "";
	}

	public static void generate(String idTransaksi, String idStok,
			Integer jmlStokApotek, Integer jmlStokGudang,
			Integer jmlTerimaApotek, Integer jmlTerimaGudang,
			Integer jmlKirimApotek, Integer jmlKirimGudang, boolean cancel) {
		if (jmlTerimaApotek == null)
			jmlTerimaApotek = 0;
		if (jmlTerimaGudang == null)
			jmlTerimaGudang = 0;
		if (jmlKirimApotek == null)
			jmlKirimApotek = 0;
		if (jmlKirimGudang == null)
			jmlKirimGudang = 0;
		TransaksiHarianId transaksiHarianId = new TransaksiHarianId(idStok,
				CommonUtil.getFormatThnBlnTgl(new Date()), idTransaksi);
		TransaksiHarian transaksiHarian = TransaksiHarian
				.findById(transaksiHarianId);
		Integer jmlTambahApotek = 0;
		Integer jmlTambahGudang = 0;
		Integer jmlKurangApotek = 0;
		Integer jmlKurangGudang = 0;
		if (transaksiHarian == null) {
			transaksiHarian = new TransaksiHarian();
			transaksiHarian.setTransaksiHarianId(transaksiHarianId);
			Query createNativeQuery = TransaksiHarian
					.em()
					.createNativeQuery(
							"select max(urutan) from transaksi_harian where id_stok=:id_stok and thnblntgl_transaksi=:thnblntgl_transaksi");
			createNativeQuery.setParameter("id_stok", idStok);
			createNativeQuery.setParameter("thnblntgl_transaksi",
					CommonUtil.getFormatThnBlnTgl(new Date()));
			Number maxUrutan = (Number) createNativeQuery.getSingleResult();
			if (maxUrutan == null) {
				transaksiHarian.setUrutan(1);
				transaksiHarian.setStokAwalApotek(jmlStokApotek);
				transaksiHarian.setStokAwalGudang(jmlStokGudang);
			} else {
				createNativeQuery = TransaksiHarian
						.em()
						.createNativeQuery(
								"select * from transaksi_harian where id_stok=:id_stok and thnblntgl_transaksi=:thnblntgl_transaksi and urutan=:urutan",
								TransaksiHarian.class);
				createNativeQuery.setParameter("urutan", maxUrutan);
				createNativeQuery.setParameter("id_stok", idStok);
				createNativeQuery.setParameter("thnblntgl_transaksi",
						CommonUtil.getFormatThnBlnTgl(new Date()));
				List resultList = createNativeQuery.getResultList();
				if (!resultList.isEmpty()) {
					TransaksiHarian tmp = (TransaksiHarian) resultList.get(0);
					transaksiHarian.setUrutan(maxUrutan.intValue() + 1);
					transaksiHarian.setStokAwalApotek(tmp.getStokAkhirApotek());
					transaksiHarian.setStokAwalGudang(tmp.getStokAkhirGudang());
				}
			}
		} else {
			jmlTambahApotek = transaksiHarian.getPenambahanStokApotek();
			jmlTambahGudang = transaksiHarian.getPenambahanStokGudang();
			jmlKurangApotek = transaksiHarian.getPenguranganStokApotek();
			jmlKurangGudang = transaksiHarian.getPenguranganStokGudang();
		}
		transaksiHarian.setIdStok((StokObatAlat) StokObatAlat.findById(idStok));
		if (cancel) {
			transaksiHarian.setPenambahanStokApotek(jmlTambahApotek
					- jmlTerimaApotek);
			transaksiHarian.setPenambahanStokGudang(jmlTambahGudang
					- jmlTerimaGudang);
			transaksiHarian.setPenguranganStokApotek(jmlKurangApotek
					- jmlKirimApotek);
			transaksiHarian.setPenguranganStokGudang(jmlKurangGudang
					- jmlKirimGudang);
		} else {
			transaksiHarian.setPenambahanStokApotek(jmlTerimaApotek
					+ jmlTambahApotek);
			transaksiHarian.setPenambahanStokGudang(jmlTerimaGudang
					+ jmlTambahGudang);
			transaksiHarian.setPenguranganStokApotek(jmlKirimApotek
					+ jmlKurangApotek);
			transaksiHarian.setPenguranganStokGudang(jmlKirimGudang
					+ jmlKurangGudang);
		}
		transaksiHarian.setStokAkhirApotek(transaksiHarian.getStokAwalApotek()
				+ transaksiHarian.getPenambahanStokApotek()
				- transaksiHarian.getPenguranganStokApotek());
		transaksiHarian.setStokAkhirGudang(transaksiHarian.getStokAwalGudang()
				+ transaksiHarian.getPenambahanStokGudang()
				- transaksiHarian.getPenguranganStokGudang());
		transaksiHarian = transaksiHarian.merge();
		transaksiHarian.save();
		Query createNativeQuery = TransaksiHarian
				.em()
				.createNativeQuery(
						"select * from transaksi_harian where id_stok=:id_stok and thnblntgl_transaksi "
								+ "|| to_char(urutan, '0000')>:thnblntgl_urutan order by thnblntgl_transaksi "
								+ "|| to_char(urutan, '0000')",
						TransaksiHarian.class);
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		createNativeQuery.setParameter("id_stok", transaksiHarian
				.getTransaksiHarianId().getId_stok());
		createNativeQuery.setParameter("thnblntgl_urutan", transaksiHarian
				.getTransaksiHarianId().getThnblntgl_transaksi()
				+ decimalFormat.format(transaksiHarian.getUrutan()));
		List<TransaksiHarian> resultList = createNativeQuery.getResultList();
		Integer stokAwalA = transaksiHarian.getStokAkhirApotek();
		Integer stokAwalG = transaksiHarian.getStokAkhirGudang();
		for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
			TransaksiHarian newerTransaction = (TransaksiHarian) iterator
					.next();
			newerTransaction.setStokAwalApotek(stokAwalA);
			newerTransaction.setStokAwalGudang(stokAwalG);
			newerTransaction.setStokAkhirApotek(newerTransaction
					.getStokAwalApotek()
					+ newerTransaction.getPenambahanStokApotek()
					- newerTransaction.getPenguranganStokApotek());
			newerTransaction.setStokAkhirGudang(newerTransaction
					.getStokAwalGudang()
					+ newerTransaction.getPenambahanStokGudang()
					- newerTransaction.getPenguranganStokGudang());
			stokAwalA = newerTransaction.getStokAkhirApotek();
			stokAwalG = newerTransaction.getStokAkhirGudang();
			newerTransaction = newerTransaction.merge();
			newerTransaction.save();
		}
	}
}
