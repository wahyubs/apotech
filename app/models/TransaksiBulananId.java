package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * <p>
 * Title: TransaksiBulananId
 * </p>
 * 
 * <p>
 * Description: Embedded Id describing a TransaksiBulananId entity primary key
 * </p>
 * 
 */
@Embeddable
public class TransaksiBulananId implements Serializable {

	@Column(name = "thnbln_transaksi", length = 6, nullable = false)
	private String thnbln_transaksi;

	@Column(name = "id_stok", length = 32, nullable = false)
	private String id_stok;

	public TransaksiBulananId() {
		// TODO Auto-generated constructor stub
	}

	public TransaksiBulananId(String idStok, String thnblnTransaksi) {
		this.id_stok = idStok;
		this.thnbln_transaksi = thnblnTransaksi;
	}

	public String getThnbln_transaksi() {
		return thnbln_transaksi;
	}

	public void setThnbln_transaksi(String thnblnTransaksi) {
		this.thnbln_transaksi = thnblnTransaksi;
	}

	public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
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

	@Override
	public String toString() {
		return "TransaksiBulananId:" + ":" + thnbln_transaksi;
	}

}
