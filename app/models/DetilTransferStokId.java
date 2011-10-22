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
 * <p>Title: DetilTransferStokId</p>
 *
 * <p>Description: Embedded Id describing a DetilTransferStokId entity primary key</p>
 *
 */
@Embeddable
public class DetilTransferStokId implements Serializable {

    @Column(name="id_transfer" ,length=32 ,nullable=false)
    private String id_transfer;
    
    @Column(name="id_stok" ,length=32 ,nullable=false)
    private String id_stok;

    public String getId_transfer() {
		return id_transfer;
	}

	public void setId_transfer(String id_transfer) {
		this.id_transfer = id_transfer;
	}

	public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
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
        return "DetilTransferStokId:" 
        + ":" + id_transfer+ ":" + id_stok
        ;
    }
    
}
