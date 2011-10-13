package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * <p>Title: DetilPembelianId</p>
 *
 * <p>Description: Embedded Id describing a DetilPembelianId entity primary key</p>
 *
 */
@Embeddable
public class DetilPembelianId implements Serializable {

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
        return "DetilPembelianId:" 
        + ":" + idStok
        ;
    }
    
}
