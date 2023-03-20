package es.myhome.portal.service.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import es.myhome.portal.domain.app.Photo;

public class PhotoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -881459254614706445L;

	private Long id;
	private String name;
	private MultipartFile photo;
	private Long idIncidence;

	public PhotoDTO() {
		// Empty constructor needed for Jackson.
	}
	
	public PhotoDTO(Photo photo) {
		super();
		this.id = photo.getId();
		this.name = photo.getName();
		this.idIncidence = photo.getIncidence() != null ? photo.getIncidence().getId() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public Long getIdIncidence() {
		return idIncidence;
	}

	public void setIdIncidence(Long idIncidence) {
		this.idIncidence = idIncidence;
	}


}
