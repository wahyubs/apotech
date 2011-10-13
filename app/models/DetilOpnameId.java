package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * <p>Title: DetilOpnameId</p>
 *
 * <p>Description: Embedded Id describing a DetilOpnameId entity primary key</p>
 *
 */
@Embeddable
public class DetilOpnameId implements Serializable {

    @Column(name="id_stok_opname" ,length=32 ,nullable=false)
    private String idStokOpname;

    public String getIdStokOpname() {
        return idStokOpname;
    }
	
    public void setIdStokOpname (String idStokOpname) {
        this.idStokOpname =  idStokOpname;
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
        return "DetilOpnameId:" 
        + ":" + idStokOpname
        ;
    }
    
}
