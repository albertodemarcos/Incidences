package es.myhome.portal.service.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Embedded;

import es.myhome.portal.domain.app.Geolocation;
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
	private boolean activated = true;
	private OrganizationType type;
	private Geolocation geolocation;
    private String createdBy;
    private Instant createdDate;
    private String lastModifiedBy;
    private Instant lastModifiedDate;

	public OrganizationDTO() {
		// Empty constructor needed for Jackson.

	}

	public OrganizationDTO(Organization organization) {
		super();
		this.id = organization.getId();
		this.name = organization.getName();
		this.description = organization.getDescription();
		this.type = organization.getType();
		this.activated = organization.isActivated();
		this.geolocation = organization.getGeolocation();
		this.createdBy = organization.getCreatedBy();
		this.createdDate = organization.getCreatedDate();
		this.lastModifiedBy = organization.getLastModifiedBy();
		this.lastModifiedDate = organization.getLastModifiedDate();
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
	
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

	public OrganizationType getType() {
		return type;
	}

	public void setType(OrganizationType type) {
		this.type = type;
	}
	
	@Embedded
	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}
	
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

	
}
