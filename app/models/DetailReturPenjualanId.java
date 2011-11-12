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
 * <p>Title: DetailReturPenjualanId</p>
 *
 * <p>Description: Embedded Id describing a DetailReturPenjualanId entity primary key</p>
 *
 */
@Embeddable
public class DetailReturPenjualanId implements Serializable {

    @Column(name="id_stok" ,length=32 ,nullable=false)
    private String id_stok;
    
    @Column(name="id_retur_jual" ,length=32 ,nullable=false)
    private String id_retur_jual;

    public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
	}

	public String getId_retur_jual() {
		return id_retur_jual;
	}

	public void setId_retur_jual(String id_retur_jual) {
		this.id_retur_jual = id_retur_jual;
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
        return "DetailReturPenjualanId:" 
        + ":" + id_retur_jual+ ":" + id_stok
        ;
    }
    
}
