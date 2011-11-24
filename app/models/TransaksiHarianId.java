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
 * Title: TransaksiHarianId
 * </p>
 * 
 * <p>
 * Description: Embedded Id describing a TransaksiHarianId entity primary key
 * </p>
 * 
 */
@Embeddable
public class TransaksiHarianId implements Serializable {

	@Column(name = "thnblntgl_transaksi", length = 8, nullable = false)
	private String thnblntgl_transaksi;

	@Column(name = "id_transaksi", length = 32, nullable = false)
	private String id_transaksi;

	@Column(name = "id_stok", length = 32, nullable = false)
	private String id_stok;
	
	public TransaksiHarianId() {
		// TODO Auto-generated constructor stub
	}

	public TransaksiHarianId(String idStok, String formatThnBlnTgl,
			String id_transaksi) {
		this.id_stok = idStok;
		this.thnblntgl_transaksi = formatThnBlnTgl;
		this.id_transaksi = id_transaksi;
	}

	public String getThnblntgl_transaksi() {
		return thnblntgl_transaksi;
	}

	public void setThnblntgl_transaksi(String thnblntgl_transaksi) {
		this.thnblntgl_transaksi = thnblntgl_transaksi;
	}

	public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
	}

	public String getId_transaksi() {
		return id_transaksi;
	}

	public void setId_transaksi(String id_transaksi) {
		this.id_transaksi = id_transaksi;
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
		return "TransaksiHarianId:" + ":" + thnblntgl_transaksi + ":"
				+ id_transaksi;
	}

}
