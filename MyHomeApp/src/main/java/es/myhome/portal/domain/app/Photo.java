package es.myhome.portal.domain.app;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

public class Photo {

	
	private Long id;
	private String name;	
	private Organization organization;
	private Incidence incidence;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Size(max = 255)
	@Column(name = "name", length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Organization getOrganization() {
		return organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name="bank_id", nullable=false)
	public Incidence getIncidence() {
		return incidence;
	}
	public void setIncidence(Incidence incidence) {
		this.incidence = incidence;
	}
	
	
	
	
	
	
}
