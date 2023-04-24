package es.myhome.portal.service.dto;

import java.io.Serializable;
import java.util.Date;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.IncidenceStatus;
import es.myhome.portal.domain.app.PriorityType;

public class IncidenceListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8159692362357863223L;
	
	
	private Long id;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private IncidenceStatus status;
	private PriorityType priority;
	private String nameOrganization;
	private String nameEmployee;
	private GeolocationDTO location;
	
	public IncidenceListDTO() {
		// Empty constructor needed for Jackson.
	}

	public IncidenceListDTO(Incidence incidence) {
		super();
		this.id = incidence.getId();
		this.title = incidence.getTitle();
		this.description = incidence.getDescription();
		this.startDate = incidence.getStartDate();
		this.endDate = incidence.getEndDate();
		this.status = incidence.getStatus();
		this.priority = incidence.getPriority(); 
		this.nameOrganization = incidence.getOrganization() != null ? incidence.getOrganization().getName() : null;
		this.nameEmployee = incidence.getEmployee() != null ? incidence.getEmployee().getFirstName() : null;
		if( incidence.getLocation() != null ) {
			this.location = new GeolocationDTO(incidence.getLocation().getLongitude(), incidence.getLocation().getLatitude());
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

	public PriorityType getPriority() {
		return priority;
	}

	public void setPriority(PriorityType priority) {
		this.priority = priority;
	}

	public String getNameOrganization() {
		return nameOrganization;
	}

	public void setNameOrganization(String nameOrganization) {
		this.nameOrganization = nameOrganization;
	}

	public String getNameEmployee() {
		return nameEmployee;
	}

	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	public GeolocationDTO getLocation() {
		return location;
	}

	public void setLocation(GeolocationDTO location) {
		this.location = location;
	}

}
