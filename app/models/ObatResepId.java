package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * <p>Title: ObatResepId</p>
 *
 * <p>Description: Embedded Id describing a ObatResepId entity primary key</p>
 *
 */
@Embeddable
public class ObatResepId implements Serializable {

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
        return "ObatResepId:" 
        + ":" + idStok
        ;
    }
    
}
