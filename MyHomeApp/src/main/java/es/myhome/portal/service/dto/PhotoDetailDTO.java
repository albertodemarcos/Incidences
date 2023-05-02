package es.myhome.portal.service.dto;

import java.io.Serializable;

import es.myhome.portal.domain.app.Photo;

public class PhotoDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1428613478787412784L;
	
	private static final String BUCKET_NAME = "myhomeapp";

	private Long id;
	private Long idIncidence;
	private String name;
	private String bucket;
	private String imagenUrl;

	public PhotoDetailDTO() {
		super();// Empty constructor needed for Jackson.
	}

	public PhotoDetailDTO(Photo photo) {
		super();
		this.id = photo.getId();
		this.idIncidence = photo.getIncidence().getId();
		this.name = photo.getName();
		this.bucket = BUCKET_NAME;
		this.imagenUrl = photo.getImageUrl();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdIncidence() {
		return idIncidence;
	}

	public void setIdIncidence(Long idIncidence) {
		this.idIncidence = idIncidence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}


	public String getImagenUrl() {
		return imagenUrl;
	}


	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	
}
