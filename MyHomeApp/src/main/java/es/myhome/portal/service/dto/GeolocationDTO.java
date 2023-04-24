package es.myhome.portal.service.dto;

import java.io.Serializable;

public class GeolocationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9050452922481512324L;
	
	private Double longitude;
	private Double latitude;

	public GeolocationDTO() {
		super();
	}

	public GeolocationDTO(Double longitude, Double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
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
