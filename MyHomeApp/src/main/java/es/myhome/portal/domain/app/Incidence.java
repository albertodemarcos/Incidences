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

import es.myhome.portal.domain.users.Employee;

@Entity
@Table(name = "jhi_incidence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Incidence {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incidence_sequence")
	@SequenceGenerator(name = "incidence_sequence", sequenceName="sequence_incidence", allocationSize=1)
	private Long id;

	@Size(max = 255)
	@Column(name = "title", length = 255)
	private String title;

	@Lob
	@Type(type="org.hibernate.type.TextType")
	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private IncidenceStatus status;
	
	@Embedded
	private Geolocation location;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Organization organization;
	
	@OneToMany(mappedBy="incidence", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<Photo> photos;
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Employee employee;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public IncidenceStatus getStatus() {
		return status;
	}

	public void setStatus(IncidenceStatus status) {
		this.status = status;
	}

	public Geolocation getLocation() {
		return location;
	}

	public void setLocation(Geolocation location) {
		this.location = location;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
