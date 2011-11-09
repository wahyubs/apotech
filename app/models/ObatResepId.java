package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * <p>
 * Title: ObatResepId
 * </p>
 * 
 * <p>
 * Description: Embedded Id describing a ObatResepId entity primary key
 * </p>
 * 
 */
@Embeddable
public class ObatResepId implements Serializable {

	@Column(name = "id_stok", length = 32, nullable = false)
	private String id_stok;

	@Column(name = "id_resep_dtl", length = 32, nullable = false)
	private String id_resep_dtl;

	public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
	}

	public String getId_resep_dtl() {
		return id_resep_dtl;
	}

	public void setId_resep_dtl(String id_resep_dtl) {
		this.id_resep_dtl = id_resep_dtl;
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
		return "ObatResepId:" + ":" + id_stok + ":" + id_resep_dtl;
	}

}
