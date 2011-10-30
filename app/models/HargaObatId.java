package models;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

/**
 * 
 * <p>
 * Title: HargaObatId
 * </p>
 * 
 * <p>
 * Description: Embedded Id describing a HargaObatId entity primary key
 * </p>
 * 
 */
@Embeddable
public class HargaObatId implements Serializable {

	@Column(name = "thn_bln_harga", length = 6, nullable = false)
	private String thn_bln_harga;

	@Column(name = "id_obat_alat", length = 32, nullable = false)
	private String id_obat_alat;

	@Column(name = "id_jns_harga", length = 32, nullable = false)
	private String id_jns_harga;

	public String getThn_bln_harga() {
		return thn_bln_harga;
	}

	public void setThn_bln_harga(String thn_bln_harga) {
		this.thn_bln_harga = thn_bln_harga;
	}

	public String getId_obat_alat() {
		return id_obat_alat;
	}

	public void setId_obat_alat(String id_obat_alat) {
		this.id_obat_alat = id_obat_alat;
	}

	public String getId_jns_harga() {
		return id_jns_harga;
	}

	public void setId_jns_harga(String id_jns_harga) {
		this.id_jns_harga = id_jns_harga;
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
		return "HargaObatId:" + ":" + thn_bln_harga + ":" + id_obat_alat + ":"
				+ id_jns_harga;
	}

}
