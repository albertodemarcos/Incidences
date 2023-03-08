package es.myhome.portal.service.dto;

import java.io.Serializable;

import es.myhome.portal.domain.app.Geolocation;
import es.myhome.portal.domain.app.Incidence;

public class GoogleMarkerIncidenceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5486534978422697791L;

	private Long id; // idIncidence
	private String title;
	private Geolocation location;

	public GoogleMarkerIncidenceDTO() {
		super();
	}

	public GoogleMarkerIncidenceDTO(Long id, String title, Geolocation location) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
	}

	public GoogleMarkerIncidenceDTO(Incidence incidence) {
		super();
		this.id = incidence.getId();
		this.title = incidence.getTitle();
		this.location = incidence.getLocation();
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

	public Geolocation getLocation() {
		return location;
	}

	public void setLocation(Geolocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "GoogleMarkerIncidenceDTO [id=" + id + ", title=" + title + ", location=[latitude:"
				+ location.getLatitude() + ", longitude:" + location.getLongitude() + "]";
	}

}
