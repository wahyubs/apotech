package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import play.db.jpa.GenericModel;

/**
 *
 * <p>Title: JenisObatAlat</p>
 *
 * <p>Description: Play Domain Object describing a JenisObatAlat entity</p>
 *
 */
@Entity (name="JenisObatAlat")
@Table (name="jenis_obat_alat")
public class JenisObatAlat extends GenericModel implements IGeneratedModel {

	@Id
	@GeneratedValue(generator = "MyIdGenerator")
	@GenericGenerator(name = "MyIdGenerator", strategy = "tool.MyIdGenerator") 
	@Column(name="id_jns_obat_alat" ,length=32)
    private String idJnsObatAlat;

    @Column(name="jns_obat_alat",  length=50,  nullable=true,  unique=false)
    private String jnsObatAlat; 
    
    @OneToMany (targetEntity=ObatAlat.class, fetch=FetchType.LAZY, mappedBy="idJnsObatAlat", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ObatAlat> obatAlatIdJnsObatAlat = new HashSet<ObatAlat>(); 
   
    /**
    * Default constructor
    */
    public JenisObatAlat() {
    }


    public String getIdJnsObatAlat() {
        return idJnsObatAlat;
    }
	
    public void setIdJnsObatAlat (String idJnsObatAlat) {
        this.idJnsObatAlat =  idJnsObatAlat;
    }
    
    public String getJnsObatAlat() {
        return jnsObatAlat;
    }
	
    public void setJnsObatAlat (String jnsObatAlat) {
        this.jnsObatAlat =  jnsObatAlat;
    }
    
    public Set<ObatAlat> getObatAlatIdJnsObatAlat() {
        if (obatAlatIdJnsObatAlat == null){
            obatAlatIdJnsObatAlat = new HashSet<ObatAlat>();
        }
        return obatAlatIdJnsObatAlat;
    }

    public void setObatAlatIdJnsObatAlat (Set<ObatAlat> obatAlatIdJnsObatAlat) {
        this.obatAlatIdJnsObatAlat = obatAlatIdJnsObatAlat;
    }	
    
    public void addObatAlatIdJnsObatAlat (ObatAlat obatAlat) {
    	    getObatAlatIdJnsObatAlat().add(obatAlat);
    }
    
    public String toString(){
       return jnsObatAlat+"";
    }


	@Override
	public String getGeneratedValue() {
		return idJnsObatAlat;
	}
}
