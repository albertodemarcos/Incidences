package es.myhome.portal.service.dto;

import java.io.Serializable;
import java.util.Date;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.IncidenceStatus;
import es.myhome.portal.domain.app.PriorityType;

public class IncidenceKanbanDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2493712062803176110L;

	private Long id;
	private String title;
	private Date startDate;
	private IncidenceStatus status;
	private PriorityType priority;
	private Boolean visible;
	
	public IncidenceKanbanDTO() {
		super();		
	}

	public IncidenceKanbanDTO(Incidence incidence) {
		super();
		this.id = incidence.getId();
		this.title = incidence.getTitle();
		this.startDate = incidence.getStartDate();
		this.status = incidence.getStatus();
		this.priority = incidence.getPriority();
		this.visible = Boolean.TRUE;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
