package es.myhome.portal.service.filters;

import java.io.Serializable;


import es.myhome.portal.domain.app.OrganizationType;

public class FilterOrganization extends GlobalFilter implements IFilterHttpParam, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4515366658219978700L;
	
	private String name;
	private Boolean activated;
	private OrganizationType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public OrganizationType getType() {
		return type;
	}
	public void setType(OrganizationType type) {
		this.type = type;
	}
    
    
    
}
