package es.myhome.portal.service.dto;

import java.io.Serializable;

public class ImporterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7330264067875819436L;

	private Integer objectid;
	private String codigoine;
	private String nameunit;
	private GeoPoint2d geo_point_2d;
	
	public ImporterDTO() {
		super();
	}

	public Integer getObjectid() {
		return objectid;
	}

	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}

	public String getCodigoine() {
		return codigoine;
	}

	public void setCodigoine(String codigoine) {
		this.codigoine = codigoine;
	}

	public String getNameunit() {
		return nameunit;
	}

	public void setNameunit(String nameunit) {
		this.nameunit = nameunit;
	}

	public GeoPoint2d getGeo_point_2d() {
		return geo_point_2d;
	}

	public void setGeo_point_2d(GeoPoint2d geo_point_2d) {
		this.geo_point_2d = geo_point_2d;
	}

	public class GeoPoint2d {
		private String lon;
		private String lat;
		
		public GeoPoint2d() {
			super();
		}

		public String getLon() {
			return lon;
		}

		public void setLon(String lon) {
			this.lon = lon;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

	}
}
