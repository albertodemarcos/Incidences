package es.myhome.portal.service.filters;

import java.io.Serializable;
import java.time.Instant;

public abstract class GlobalFilter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8615509938038597037L;
	
	protected String createdBy;
	protected Instant createdDate;
	protected String lastModifiedBy;
	protected Instant lastModifiedDate;

	public GlobalFilter() {
		super();
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