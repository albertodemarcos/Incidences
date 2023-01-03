package es.myhome.portal.domain.app;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "jhi_photo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Photo implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -218338551868797492L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_sequence")
	@SequenceGenerator(name = "photo_sequence", sequenceName="sequence_photo", allocationSize=1)
	private Long id;

	@Size(max = 255)
	@Column(name = "name", length = 255)
	private String name;	

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="incidence_id")
	private Incidence incidence;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Incidence getIncidence() {
		return incidence;
	}
	
	public void setIncidence(Incidence incidence) {
		this.incidence = incidence;
	}
	
	
	
	
	
	
}
