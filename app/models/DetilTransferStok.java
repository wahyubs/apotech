package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

/**
 * 
 * <p>
 * Title: DetilTransferStok
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a DetilTransferStok entity
 * </p>
 * 
 */
@Entity(name = "DetilTransferStok")
@Table(name = "detil_transfer_stok")
public class DetilTransferStok extends GenericModel {

	@EmbeddedId
	private DetilTransferStokId detilTransferStokId;

	@Column(name = "jml_kirim_apotek", nullable = true, unique = false)
	private java.lang.Integer jmlKirimApotek;

	@Column(name = "jml_kirim_gudang", nullable = true, unique = false)
	private java.lang.Integer jmlKirimGudang;
	
	@Column(name="stok_awal_apotek",    nullable=true,  unique=false)
    private java.lang.Integer stokAwalApotek; 
    
    @Column(name="stok_awal_gudang",    nullable=true,  unique=false)
    private java.lang.Integer stokAwalGudang; 
    
    @Column(name="stok_akhir_apotek",    nullable=true,  unique=false)
    private java.lang.Integer stokAkhirApotek; 
    
    @Column(name="stok_akhir_gudang",    nullable=true,  unique=false)
    private java.lang.Integer stokAkhirGudang; 

	@MapsId("id_stok")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_stok", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private StokObatAlat idStok;

	@MapsId("id_transfer")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_transfer", nullable = false, unique = false, insertable = false, updatable = false)
	@Required
	private TransferStok idTransfer;

	/**
	 * Default constructor
	 */
	public DetilTransferStok() {
		this.detilTransferStokId = new DetilTransferStokId(null, null);
	}

	public DetilTransferStokId getDetilTransferStokId() {
		return detilTransferStokId;
	}

	public void setDetilTransferStokId(DetilTransferStokId detilTransferStokId) {
		this.detilTransferStokId = detilTransferStokId;
	}

	public java.lang.Integer getJmlKirimApotek() {
		return jmlKirimApotek;
	}

	public void setJmlKirimApotek(java.lang.Integer jmlKirimApotek) {
		this.jmlKirimApotek = jmlKirimApotek;
	}

	public java.lang.Integer getJmlKirimGudang() {
		return jmlKirimGudang;
	}

	public void setJmlKirimGudang(java.lang.Integer jmlKirimGudang) {
		this.jmlKirimGudang = jmlKirimGudang;
	}

	public java.lang.Integer getStokAwalApotek() {
		return stokAwalApotek;
	}

	public void setStokAwalApotek(java.lang.Integer stokAwalApotek) {
		this.stokAwalApotek = stokAwalApotek;
	}

	public java.lang.Integer getStokAwalGudang() {
		return stokAwalGudang;
	}

	public void setStokAwalGudang(java.lang.Integer stokAwalGudang) {
		this.stokAwalGudang = stokAwalGudang;
	}

	public java.lang.Integer getStokAkhirApotek() {
		return stokAkhirApotek;
	}

	public void setStokAkhirApotek(java.lang.Integer stokAkhirApotek) {
		this.stokAkhirApotek = stokAkhirApotek;
	}

	public java.lang.Integer getStokAkhirGudang() {
		return stokAkhirGudang;
	}

	public void setStokAkhirGudang(java.lang.Integer stokAkhirGudang) {
		this.stokAkhirGudang = stokAkhirGudang;
	}

	public StokObatAlat getIdStok() {
		return idStok;
	}

	public void setIdStok(StokObatAlat idStok) {
		this.idStok = idStok;
	}

	public TransferStok getIdTransfer() {
		return idTransfer;
	}

	public void setIdTransfer(TransferStok idTransfer) {
		this.idTransfer = idTransfer;
	}

	public String toString() {
		return detilTransferStokId + "";
	}

	public Integer getJmlKirim() {
		return jmlKirimGudang != null ? jmlKirimGudang : jmlKirimApotek;
	}

	public Integer getTujuan() {
		return jmlKirimGudang != null && jmlKirimGudang > 0 ? 2 : 1;
	}
}
