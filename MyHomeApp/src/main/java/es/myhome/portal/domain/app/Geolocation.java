package es.myhome.portal.domain.app;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Geolocation {

	private Double longitude;
	private Double latitude;

	public Geolocation() {
		super();
	}

	public Geolocation(Double longitude, Double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Size(max = 255)
	@Column(name = "longitude", nullable = true)
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Size(max = 255)
	@Column(name = "latitude", nullable = true)
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
}
