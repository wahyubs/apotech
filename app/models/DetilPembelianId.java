package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * <p>
 * Title: DetilPembelianId
 * </p>
 * 
 * <p>
 * Description: Embedded Id describing a DetilPembelianId entity primary key
 * </p>
 * 
 */
@Embeddable
public class DetilPembelianId implements Serializable {

	@Column(name = "id_stok", length = 32, nullable = false)
	private String id_stok;

	@Column(name = "id_pembelian", length = 32, nullable = false)
	private String id_pembelian;

	public DetilPembelianId(String id_stok, String id_pembelian) {
		this.id_stok=id_stok;
		this.id_pembelian=id_pembelian;
	}

	public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
	}

	public String getId_pembelian() {
		return id_pembelian;
	}

	public void setId_pembelian(String id_pembelian) {
		this.id_pembelian = id_pembelian;
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
		return "DetilPembelianId:" + ":" + id_stok + ":" + id_pembelian;
	}

}
