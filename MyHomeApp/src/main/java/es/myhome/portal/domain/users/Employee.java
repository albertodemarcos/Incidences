package es.myhome.portal.domain.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import es.myhome.portal.domain.app.Organization;

/**
 * A user.
 */
@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5050999088109036831L;

	/**
	 * Employee Identification Number
	 */
	@Size(max = 255)
	@Column(name = "identification_number")
	private String identificationNumber;
	
	@Size(max = 255)
	@Column(name = "alias")
	private String alias;
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Organization organization;

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	

}
