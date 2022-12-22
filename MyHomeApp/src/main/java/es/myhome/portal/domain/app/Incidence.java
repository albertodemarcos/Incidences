package es.myhome.portal.domain.app;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "jhi_incidence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Incidence {

	private Long id;
	private String title;
	private String description;
	private Date start;
	private Date end;
	private IncidenceStatus status;
	private Boolean resolved;
	private Geolocation location;
	private Organization organization;
	private List<Photo> photos;
	// private Citizen citizen; //this user create incidence
	// private Employee employee; //this user finish incidence
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_sequence")
    @SequenceGenerator(name = "organization_sequence", sequenceName="sequence_organization", allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Size(max = 255)
	@Column(name = "title", length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Type(type="org.hibernate.type.TextType")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Enumerated(EnumType.STRING)
	public IncidenceStatus getStatus() {
		return status;
	}

	public void setStatus(IncidenceStatus status) {
		this.status = status;
	}

	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}

	@Embedded
	public Geolocation getLocation() {
		return location;
	}

	public void setLocation(Geolocation location) {
		this.location = location;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@OneToMany(mappedBy="incidence")
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}
