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
 * <p>Title: DetilReturPembelianId</p>
 *
 * <p>Description: Embedded Id describing a DetilReturPembelianId entity primary key</p>
 *
 */
@Embeddable
public class DetilReturPembelianId implements Serializable {

    @Column(name="id_stok" ,length=32 ,nullable=false)
    private String id_stok;
    
    @Column(name="id_retur_beli" ,length=32 ,nullable=false)
    private String id_retur_beli;

    public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
	}

	public String getId_retur_beli() {
		return id_retur_beli;
	}

	public void setId_retur_beli(String id_retur_beli) {
		this.id_retur_beli = id_retur_beli;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return obj.toString().equals(this.toString());
    }
 
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
 
    @Override
    public String toString() {
        return "DetilReturPembelianId:" 
        + ":" + id_stok+ ":" + id_retur_beli
        ;
    }
    
}
