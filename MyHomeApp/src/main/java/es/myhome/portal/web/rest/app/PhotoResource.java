package es.myhome.portal.web.rest.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.myhome.portal.config.Constants;
import es.myhome.portal.domain.app.Photo;
import es.myhome.portal.repository.PhotoRepository;
import es.myhome.portal.service.PhotoService;
import es.myhome.portal.service.dto.PhotoDTO;
import es.myhome.portal.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class PhotoResource {
	
private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	
	private final PhotoService photoService;
	
	private final PhotoRepository photoRepository;

	public PhotoResource(PhotoService photoService, PhotoRepository photoRepository) {
		super();
		this.photoService = photoService;
		this.photoRepository = photoRepository;
	}
	
	
	/**
     * {@code POST  /photos}  : Creates a new photo.
     * <p>
     * The photo not needs to be admin on creation.
     *
     * @param photoDTO the photo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new photo, or with status {@code 400 (Bad Request)} if the title is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title is already in photo.
     */
    @PostMapping("/photos")
    public ResponseEntity<Photo> createIncidence(@Valid @RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to save Incidence : {}", photoDTO);
        Photo newPhoto = null;
        if (photoDTO.getId() != null) {
 			throw new BadRequestAlertException("A new photo cannot already have an ID", "photoManagement", "idexists");
        /*} else if (photoRepository.findOneByTitle(photoDTO.getTitle().toLowerCase()).isPresent()) { // Lowercase the photo title before comparing with database
        	throw new NameOrganizationAlreadyUsedException();*/
        }  
        newPhoto = photoService.createPhoto(photoDTO);
        return ResponseEntity
                .created(new URI("/api/photos/" + newPhoto.getId() ))
                .headers(HeaderUtil.createAlert(applicationName, "photoService.created", newPhoto.getId().toString() ))
                .body(newPhoto);
        
    }
    
    /**
     * {@code PUT /photos} : Updates an existing Photo.
     *
     * @param photoDTO the photo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated photo.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title don't already in use.
     */
    @PutMapping("/photos")
    public ResponseEntity<PhotoDTO> updatePhoto(@Valid @RequestBody PhotoDTO photoDTO) {
        log.debug("REST request to update Photo : {}", photoDTO);       
        Optional<Photo> existingPhoto = photoRepository.findById(photoDTO.getId());        
        if (!existingPhoto.isPresent() ) {        	
        	throw new BadRequestAlertException("A photo don't exist", "photoManagement", "idnotexists");
        }        
        Optional<PhotoDTO> updatedPhoto = photoService.updatePhoto(photoDTO);
        return ResponseUtil.wrapOrNotFound(updatedPhoto, HeaderUtil.createAlert(applicationName, "photoManagement.updated", photoDTO.getName() ) );
    }
    
    /**
     * {@code GET /photos/:idPhotoStr} : get the "id" photo.
     *
     * @param idPhotoStr the id of the photo to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "idPhotoStr" photo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/photos/{idPhotoStr}")
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idPhotoStr) throws IllegalArgumentException, Exception {
        log.debug("REST request to get Photo : {}", idPhotoStr);
        return ResponseUtil.wrapOrNotFound(photoService.getPhotoByIdPhoto(parseIdPhoto(idPhotoStr)).map(PhotoDTO::new));
    }

    /**
     * {@code DELETE /photos/:idPhotoStr} : delete the "id" Photo.
     *
     * @param idPhotoStr the id of the photo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/photos/{idPhotoStr}")
    public ResponseEntity<Void> deletePhoto(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idPhotoStr) throws IllegalArgumentException, Exception {
        log.debug("REST request to delete Photo: {}", idPhotoStr);
        photoService.deletePhoto( parseIdPhoto(idPhotoStr) );
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "photoService.deleted", idPhotoStr )).build();
    }
    
    /**
     * {@code GET /photos} : getPhotos with all the details - calling for all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all photos.
     */
    /*@GetMapping("/photos")
    public ResponseEntity<List<PhotoDTO>> getPhotos(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {        
    	log.debug("REST request to get all Photo");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }
        final Page<PhotoDTO> page = photoService.getAllManagedPhotos(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }*/
    
    /*private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(FilterUtils.INCIDENCE_RESOURCE_ALLOWED_ORDERED_PROPERTIES::contains);
    }*/
    
    private Long parseIdPhoto(String idPhotoStr ) throws IllegalArgumentException {
    	return Long.parseLong( idPhotoStr.replaceAll("\\D+", "") );    	
    }
	
	
	
	
	
	
	

}
