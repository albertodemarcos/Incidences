package es.myhome.portal.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.IncidenceStatus;

public class IncidenceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2967254717700332630L;

	private Long id;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private IncidenceStatus status;
	private Long idOrganization;
	private List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
	private Long idEmployee;
	private Double longitude;
	private Double latitude;
	
	public IncidenceDTO() {
		// Empty constructor needed for Jackson.
	}

	public IncidenceDTO(Incidence incidence) {
		super();
		this.id = incidence.getId();
		this.title = incidence.getTitle();
		this.description = incidence.getDescription();
		this.startDate = incidence.getStartDate();
		this.endDate = incidence.getEndDate();
		this.status = incidence.getStatus();		
		this.idOrganization = incidence.getOrganization() != null ? incidence.getOrganization().getId() : null;
		this.idEmployee = incidence.getEmployee() != null ? incidence.getEmployee().getId() : null;
		if( incidence.getLocation() != null ) {
			this.longitude = incidence.getLocation().getLongitude();
			this.latitude = incidence.getLocation().getLatitude();			
		}
		if( incidence.getPhotos() != null && incidence.getPhotos().size() > 0 ) {
			incidence.getPhotos().stream().map(p -> p).map(PhotoDTO::new);			
		}
	}


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

	public Long getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}

	public List<PhotoDTO> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoDTO> photos) {
		this.photos = photos;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
