package models;

import java.io.Serializable;

public class LaporanJenisObatId implements Serializable {
	private String id_transaksi;
	private String id_obat_alat;

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return obj.toString().equals(this.toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public String toString() {
		return id_transaksi + "," + id_obat_alat;
	}
}
