package es.myhome.portal.service;

import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.Photo;
import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.repository.PhotoRepository;
import es.myhome.portal.service.dto.PhotoDTO;

/**
 * Service class for managing photos.
 */
@Service
@Transactional
public class PhotoService {
	
	private final Logger log = LoggerFactory.getLogger(PhotoService.class);
	
	private final IncidenceRepository incidenceRepository;
	
	private final PhotoRepository photoRepository;

	public PhotoService(PhotoRepository photoRepository, IncidenceRepository incidenceRepository) {
		super();
		this.photoRepository = photoRepository;
		this.incidenceRepository = incidenceRepository;
	}
	
	public Photo createPhoto(PhotoDTO photoDTO) throws URISyntaxException {
		Photo photo = new Photo();
		photo.setName(photoDTO.getName());
		if(photoDTO.getIdIncidence() != null ) {
			Incidence incidence = incidenceRepository.findById(photoDTO.getIdIncidence()).orElse(null);
			photo.setIncidence(incidence);
		}
		
		/*
		incidence.setLocation(getGeolocation(incidenceDTO));
		*/
		photoRepository.save(photo);
		log.debug("Created Information for Photo: {}", photo);
		return photo;
	}
	
	@Transactional(readOnly = true)
	public Optional<Photo> getPhotoByIdPhoto(Long idPhoto) {
	    return photoRepository.findById(idPhoto);
	}

	/**
     * Update all information for a specific organization, and return the modified organization.
     *
     * @param organizationDTO organization to update.
     * @return updated organization.
     */
	public Optional<PhotoDTO> updatePhoto(PhotoDTO photoDTO){
		return Optional
			.of(photoRepository.findById(photoDTO.getId()))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.map(photo -> {
				photo.setName(photoDTO.getName());
				//photo.setLocation(getGeolocation(photoDTO));
				log.debug("Changed Information for Photo: {}", photo);
				return photo;
	        })
	        .map(PhotoDTO::new);
	}
    
	public void deletePhoto(Long idPhoto) throws Exception {
		photoRepository.deleteById(idPhoto);
		log.debug("Deleted Photo: {}", idPhoto);
	}

}
