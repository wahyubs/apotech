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
    private String idTransfer;

    public String getIdTransfer() {
        return idTransfer;
    }
	
    public void setIdTransfer (String idTransfer) {
        this.idTransfer =  idTransfer;
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
        + ":" + idTransfer
        ;
    }
    
}
