package es.myhome.portal.domain.app;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;



@Entity
@Table(name = "jhi_photo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Photo implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -218338551868797492L;
	
	public final String PATH_BUCKET = "/incidences/photos/";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_sequence")
	@SequenceGenerator(name = "photo_sequence", sequenceName="sequence_photo", allocationSize=1)
	private Long id;

	@Size(max = 255)
	@Column(name = "name", length = 255)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private PhotoType type;
	
	@Column(name = "size")
	private Long size;
	
	@Lob
	@Type(type="org.hibernate.type.TextType")
	@Column(name = "image_url")
    private String imageUrl;

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
	
	public PhotoType getType() {
		return type;
	}

	public void setType(PhotoType type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Incidence getIncidence() {
		return incidence;
	}
	
	public void setIncidence(Incidence incidence) {
		this.incidence = incidence;
	}
	
	@Transient
	public String getPathPhoto() {
		
		return (this.PATH_BUCKET + this.getName());
	}
	
}
