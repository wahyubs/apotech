package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaporanJenisObat {
	@Id
	private String id_transaksi;
	private String id_obat_alat;
	private String nama_obat_alat;
	private String pemasukan_dari;
	private Integer pemasukan_jumlah;
	private Integer stok_awal;
	private String pengeluaran_untuk;
	private Integer pengeluaran_jumlah;
	private Integer stok_akhir;

	public String getId_transaksi() {
		return id_transaksi;
	}

	public void setId_transaksi(String id_transaksi) {
		this.id_transaksi = id_transaksi;
	}

	public String getId_obat_alat() {
		return id_obat_alat;
	}

	public void setId_obat_alat(String id_obat_alat) {
		this.id_obat_alat = id_obat_alat;
	}

	public String getNama_obat_alat() {
		return nama_obat_alat;
	}

	public void setNama_obat_alat(String nama_obat_alat) {
		this.nama_obat_alat = nama_obat_alat;
	}

	public String getPemasukan_dari() {
		return pemasukan_dari;
	}

	public void setPemasukan_dari(String pemasukan_dari) {
		this.pemasukan_dari = pemasukan_dari;
	}

	public Integer getPemasukan_jumlah() {
		return pemasukan_jumlah;
	}

	public void setPemasukan_jumlah(Integer pemasukan_jumlah) {
		this.pemasukan_jumlah = pemasukan_jumlah;
	}

	public Integer getStok_awal() {
		return stok_awal;
	}

	public void setStok_awal(Integer stok_awal) {
		this.stok_awal = stok_awal;
	}

	public String getPengeluaran_untuk() {
		return pengeluaran_untuk;
	}

	public void setPengeluaran_untuk(String pengeluaran_untuk) {
		this.pengeluaran_untuk = pengeluaran_untuk;
	}

	public Integer getPengeluaran_jumlah() {
		return pengeluaran_jumlah;
	}

	public void setPengeluaran_jumlah(Integer pengeluaran_jumlah) {
		this.pengeluaran_jumlah = pengeluaran_jumlah;
	}

	public Integer getStok_akhir() {
		return stok_akhir;
	}

	public void setStok_akhir(Integer stok_akhir) {
		this.stok_akhir = stok_akhir;
	}
	
	public String getSatuan_obat_alat() {
		return "-";
	}

}
