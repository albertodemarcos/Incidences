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
import es.myhome.portal.repository.OrganizationRepository;
import es.myhome.portal.repository.PhotoRepository;
import es.myhome.portal.service.dto.IncidenceDTO;
import es.myhome.portal.service.dto.PhotoDTO;

/**
 * Service class for managing photos.
 */
@Service
@Transactional
public class PhotoService {
	
	private final Logger log = LoggerFactory.getLogger(PhotoService.class);
	
	private final OrganizationRepository organizationRepository;
	
	private final IncidenceRepository incidenceRepository;
	
	private final PhotoRepository photoRepository;

	public PhotoService(OrganizationRepository organizationRepository, PhotoRepository photoRepository, IncidenceRepository incidenceRepository) {
		super();
		this.organizationRepository = organizationRepository;
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
		/*return Optional
	            .of(PhotoRepository.findById(PhotoDTO.getId()))
	            .filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(photo -> {
	        		photo.setTitle(PhotoDTO.getTitle());
	        		Photo.setLocation(getGeolocation(PhotoDTO));
	                log.debug("Changed Information for Photo: {}", Photo);
	                return photo;
	            })
	            .map(PhotoDTO::new);*/
		return null;
	}
    
	public void deletePhoto(Long idPhoto) throws Exception {
		///organizationRepository.deleteById(idPhoto);
		log.debug("Deleted Photo: {}", idPhoto);
	}

}
