package es.myhome.portal.service;

import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.Photo;
import es.myhome.portal.domain.app.PhotoType;
import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.repository.PhotoRepository;
import es.myhome.portal.service.cloud.CloudStorageS3Service;
import es.myhome.portal.service.dto.PhotoDTO;

/**
 * Service class for managing photos.
 */
@Service
@Transactional
public class PhotoService {
	
	private final Logger log = LoggerFactory.getLogger(PhotoService.class);
	
	public final String PATH_BUCKET = "incidences/photos/";
	private final String BUCKET_NAME = "myhomeapp";
	
	private final IncidenceRepository incidenceRepository;
	
	private final PhotoRepository photoRepository;
	
	private final CloudStorageS3Service cloudStorageS3Service;

	public PhotoService(PhotoRepository photoRepository, IncidenceRepository incidenceRepository, CloudStorageS3Service cloudStorageS3Service) {
		super();
		this.photoRepository = photoRepository;
		this.incidenceRepository = incidenceRepository;
		this.cloudStorageS3Service = cloudStorageS3Service;
	}
	
	public Photo createPhoto(Incidence incidence, MultipartFile file) {
		
		Photo photo = new Photo();
		
		try {
			
			PhotoType type = this.getTypeOfNameFile(file);
			String imageUrl = this.getImagenUrl(incidence.getId(), file);
			
			photo.setName(file.getName());
			photo.setType(type);
			photo.setSize( file.getSize() );
			photo.setImageUrl( imageUrl );
			photo.setIncidence(incidence);
			
			this.cloudStorageS3Service.uploadFileFromIncidence(BUCKET_NAME, imageUrl, file);
			
		}catch(Exception e) {
			
			log.error("Error! The imagen is null or is wrong and not persist");
			log.error("Error: {}", e.getLocalizedMessage() );
			log.error(e.getMessage(), e);
			return null;
		}
		
		return photo;
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

	private PhotoType getTypeOfNameFile(MultipartFile file) {		
		PhotoType type = null;
		try {
			String extension = getExtensionFile(file);			
			type = PhotoType.valueOf(extension.toUpperCase());			
		} catch(Exception e) {
			log.error("Error! The imagen don't type and return null");
			log.error("Error: {}", e.getLocalizedMessage() );
			log.error(e.getMessage(), e);
		}
		return type;
	}

	private String getExtensionFile(MultipartFile file) throws Exception {		
		String originalFilename = file.getOriginalFilename().replace(".",";");		
		String[] nameFile = originalFilename.split(";");		
		return nameFile[1];
	}
	
	public String getImagenUrl(Long idIncidence, MultipartFile file) throws Exception {
		log.info("getImagenUrl(idIncidence={}, fileName={}, fileSize={})", idIncidence, file.getName(), file.getSize() );
		String imageUrl = null;
		try {
			String extension = getExtensionFile(file);
			imageUrl = this.PATH_BUCKET + idIncidence.toString() + "_" + file.getName()+"."+extension;
			log.info("getImagenUrl() -> return imageUrl={}", imageUrl);
		}catch(Exception e) {
			log.error("Error! The imagen don't type and return null");
			log.error("Error: {}", e.getLocalizedMessage() );
			log.error(e.getMessage(), e);
		}
		return imageUrl;
	}
}
