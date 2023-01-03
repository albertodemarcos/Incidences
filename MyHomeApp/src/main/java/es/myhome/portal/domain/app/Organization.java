package es.myhome.portal.domain.app;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "jhi_organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_sequence")
	@SequenceGenerator(name = "organization_sequence", sequenceName="sequence_organization", allocationSize=1)
	private Long id;

	@Size(max = 255)
	@Column(name = "name", length = 255)
	private String name;
	
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private OrganizationType type;
	
	@Embedded
	private Geolocation geolocation;

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

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	
}
