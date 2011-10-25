package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

/**
 * 
 * <p>
 * Title: DetilPembelian
 * </p>
 * 
 * <p>
 * Description: Play Domain Object describing a DetilPembelian entity
 * </p>
 * 
 */
@Entity(name = "DetilPembelian")
@Table(name = "detil_pembelian")
public class DetilPembelian extends GenericModel {

	@EmbeddedId
	protected DetilPembelianId detilPembelianId;

	@Column(name = "jml_penerimaan_apotek", nullable = true, unique = false)
	private java.lang.Integer jmlPenerimaanApotek;

	@Column(name = "tgl_kadaluarsa", nullable = true, unique = false)
	private Date tglKadaluarsa;

	@Column(name = "jml_penerimaan_gudang", nullable = true, unique = false)
	private java.lang.Integer jmlPenerimaanGudang;
	
	@Column(name="harga_penerimaan",    nullable=true,  unique=false)
    private java.lang.Integer hargaPenerimaan; 
    
    @Column(name="ppn",    nullable=true,  unique=false)
    private java.lang.Integer ppn; 
    
    @Column(name="discount_percent",    nullable=true,  unique=false)
    private java.lang.Integer discountPercent; 
    
    @Column(name="discount_charge",    nullable=true,  unique=false)
    private java.lang.Integer discountCharge; 

	@MapsId ("id_pembelian")
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "id_pembelian", nullable = false, unique = false, insertable = false, updatable = false)
	private Pembelian idPembelian;

	@MapsId ("id_stok")
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "id_stok", nullable = false, unique = false, insertable = false, updatable = false)
	private StokObatAlat idStok;

	/**
	 * Default constructor
	 */
	public DetilPembelian() {
		this.detilPembelianId = new DetilPembelianId();
	}

	public DetilPembelianId getDetilPembelianId() {
		return detilPembelianId;
	}

	public void setDetilPembelianId(DetilPembelianId detilPembelianId) {
		this.detilPembelianId = detilPembelianId;
	}

	public java.lang.Integer getJmlPenerimaanApotek() {
		return jmlPenerimaanApotek;
	}

	public void setJmlPenerimaanApotek(java.lang.Integer jmlPenerimaanApotek) {
		this.jmlPenerimaanApotek = jmlPenerimaanApotek;
	}

	public Date getTglKadaluarsa() {
		return tglKadaluarsa;
	}

	public void setTglKadaluarsa(Date tglKadaluarsa) {
		this.tglKadaluarsa = tglKadaluarsa;
	}

	public java.lang.Integer getJmlPenerimaanGudang() {
		return jmlPenerimaanGudang;
	}

	public void setJmlPenerimaanGudang(java.lang.Integer jmlPenerimaanGudang) {
		this.jmlPenerimaanGudang = jmlPenerimaanGudang;
	}
	
	public java.lang.Integer getHargaPenerimaan() {
        return hargaPenerimaan;
    }
	
    public void setHargaPenerimaan (java.lang.Integer hargaPenerimaan) {
        this.hargaPenerimaan =  hargaPenerimaan;
    }
    
    public java.lang.Integer getPpn() {
        return ppn;
    }
	
    public void setPpn (java.lang.Integer ppn) {
        this.ppn =  ppn;
    }
    
    public java.lang.Integer getDiscountPercent() {
        return discountPercent;
    }
	
    public void setDiscountPercent (java.lang.Integer discountPercent) {
        this.discountPercent =  discountPercent;
    }
    
    public java.lang.Integer getDiscountCharge() {
        return discountCharge;
    }
	
    public void setDiscountCharge (java.lang.Integer discountCharge) {
        this.discountCharge =  discountCharge;
    }

	public Pembelian getIdPembelian() {
		return idPembelian;
	}

	public void setIdPembelian(Pembelian idPembelian) {
		this.idPembelian = idPembelian;
	}

	public StokObatAlat getIdStok() {
		return idStok;
	}

	public void setIdStok(StokObatAlat idStok) {
		this.idStok = idStok;
	}

	public String toString() {
		return detilPembelianId + "";
	}

	@PrePersist
	public void testPresist() {
		System.out.println(detilPembelianId);
		System.out.println("_" + idPembelian);
	}
	
	@PostPersist
	public void testPostPresist() {
		System.out.println(detilPembelianId);
		System.out.println("_" + idPembelian);
	}
}
