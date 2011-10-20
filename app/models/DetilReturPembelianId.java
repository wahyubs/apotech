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
    private String idStok;

    public String getIdStok() {
        return idStok;
    }
	
    public void setIdStok (String idStok) {
        this.idStok =  idStok;
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
        + ":" + idStok
        ;
    }
    
}
