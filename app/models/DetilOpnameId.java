package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * <p>
 * Title: DetilOpnameId
 * </p>
 * 
 * <p>
 * Description: Embedded Id describing a DetilOpnameId entity primary key
 * </p>
 * 
 */
@Embeddable
public class DetilOpnameId implements Serializable {
	@Column(name = "id_stok", length = 32, nullable = false)
	private String id_stok;

	@Column(name = "id_stok_opname", length = 32, nullable = false)
	private String id_stok_opname;

	public String getId_stok() {
		return id_stok;
	}

	public void setId_stok(String id_stok) {
		this.id_stok = id_stok;
	}

	public String getId_stok_opname() {
		return id_stok_opname;
	}

	public void setId_stok_opname(String id_stok_opname) {
		this.id_stok_opname = id_stok_opname;
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
		return "DetilOpnameId:" + ":" + id_stok_opname + ":" + id_stok;
	}

}
