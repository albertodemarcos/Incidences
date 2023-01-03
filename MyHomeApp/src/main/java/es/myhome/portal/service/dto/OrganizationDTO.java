package es.myhome.portal.service.dto;

import java.io.Serializable;

import es.myhome.portal.domain.app.Organization;
import es.myhome.portal.domain.app.OrganizationType;

public class OrganizationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9019439303347262841L;

	private Long id;
	private String name;
	private String description;
	private OrganizationType type;
	private Double longitude;
	private Double latitude;

	public OrganizationDTO() {
		// Empty constructor needed for Jackson.

	}

	public OrganizationDTO(Organization organization) {
		super();
		this.id = organization.getId();
		this.name = organization.getName();
		this.description = organization.getDescription();
		this.type = organization.getType();
		if (organization.getGeolocation() != null) {
			this.longitude = organization.getGeolocation().getLongitude();
			this.latitude = organization.getGeolocation().getLatitude();
		}
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrganizationType getType() {
		return type;
	}

	public void setType(OrganizationType type) {
		this.type = type;
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
