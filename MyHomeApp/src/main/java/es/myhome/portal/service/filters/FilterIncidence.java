package es.myhome.portal.service.filters;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import es.myhome.portal.domain.app.IncidenceStatus;
import es.myhome.portal.domain.app.PriorityType;

public class FilterIncidence extends GlobalFilter implements IFilterHttpParam, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579647575884063825L;
	
	private String title;
	private Date startDate;
	private Date endDate;
	private IncidenceStatus status;
	private PriorityType priority;
	private String nameOrganization;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
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
	

}
