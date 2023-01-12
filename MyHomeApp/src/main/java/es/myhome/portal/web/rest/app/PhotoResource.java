package es.myhome.portal.web.rest.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.myhome.portal.config.Constants;
import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.repository.PhotoRepository;
import es.myhome.portal.service.PhotoService;
import es.myhome.portal.service.dto.IncidenceDTO;
import es.myhome.portal.utilities.FilterUtils;
import es.myhome.portal.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
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
     * {@code POST  /incidences}  : Creates a new incidence.
     * <p>
     * The incidence not needs to be admin on creation.
     *
     * @param incidenceDTO the incidence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incidence, or with status {@code 400 (Bad Request)} if the title is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title is already in incidence.
     */
    @PostMapping("/incidences")
    public ResponseEntity<Incidence> createIncidence(@Valid @RequestBody IncidenceDTO incidenceDTO) throws URISyntaxException {
        log.debug("REST request to save Incidence : {}", incidenceDTO);
        /*Incidence newIncidence = null;
        if (incidenceDTO.getId() != null) {
 			throw new BadRequestAlertException("A new incidence cannot already have an ID", "incidenceManagement", "idexists");
        } else if (incidenceRepository.findOneByTitle(incidenceDTO.getTitle().toLowerCase()).isPresent()) { // Lowercase the incidence title before comparing with database
        	throw new NameOrganizationAlreadyUsedException();
        }  
        newIncidence = incidenceService.createIncidence(incidenceDTO);
        return ResponseEntity
                .created(new URI("/api/incidences/" + newIncidence.getId() ))
                .headers(HeaderUtil.createAlert(applicationName, "incidenceService.created", newIncidence.getId().toString() ))
                .body(newIncidence);*/
        return null;
    }
    
    /**
     * {@code PUT /incidences} : Updates an existing Incidence.
     *
     * @param incidenceDTO the incidence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incidence.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title don't already in use.
     */
    @PutMapping("/incidences")
    public ResponseEntity<IncidenceDTO> updateIncidence(@Valid @RequestBody IncidenceDTO incidenceDTO) {
        /*log.debug("REST request to update Incidence : {}", incidenceDTO);       
        Optional<Incidence> existingIncidence = incidenceRepository.findById(incidenceDTO.getId());        
        if (!existingIncidence.isPresent() ) {        	
        	throw new BadRequestAlertException("A incidence don't exist", "incidenceManagement", "idnotexists");
        }        
        Optional<IncidenceDTO> updatedIncidence = incidenceService.updateIncidence(incidenceDTO);
        return ResponseUtil.wrapOrNotFound(updatedIncidence, HeaderUtil.createAlert(applicationName, "incidenceManagement.updated", incidenceDTO.getTitle() ) );*/
        return null;
    }
    
    /**
     * {@code GET /incidences/:idIncidenceStr} : get the "id" incidence.
     *
     * @param idIncidenceStr the id of the incidence to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "idIncidenceStr" incidence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incidences/{idIncidenceStr}")
    public ResponseEntity<IncidenceDTO> getIncidence(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idIncidenceStr) throws IllegalArgumentException, Exception {
        /*log.debug("REST request to get Incidence : {}", idIncidenceStr);
        return ResponseUtil.wrapOrNotFound(incidenceService.getIncidenceByIdIncidence(parseIdIncidence(idIncidenceStr)).map(IncidenceDTO::new));*/
    	return null;
    }

    /**
     * {@code DELETE /incidences/:idIncidenceStr} : delete the "id" Incidence.
     *
     * @param idIncidenceStr the id of the incidence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incidences/{idIncidenceStr}")
    public ResponseEntity<Void> deleteIncidence(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idIncidenceStr) throws IllegalArgumentException, Exception {
        /*log.debug("REST request to delete Incidence: {}", idIncidenceStr);
        incidenceService.deleteIncidence( parseIdIncidence(idIncidenceStr) );
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "incidenceService.deleted", idIncidenceStr )).build();*/
        return null;
    }
    
    /**
     * {@code GET /incidences} : getIncidences with all the details - calling for all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all incidences.
     */
    /*@GetMapping("/incidences")
    public ResponseEntity<List<IncidenceDTO>> getIncidences(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {        
    	log.debug("REST request to get all Incidence");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }
        final Page<IncidenceDTO> page = incidenceService.getAllManagedIncidences(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }*/
    
    /*private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(FilterUtils.INCIDENCE_RESOURCE_ALLOWED_ORDERED_PROPERTIES::contains);
    }*/
    
    private Long parseIdIncidence(String idIncidenceStr ) throws IllegalArgumentException {
    	return Long.parseLong( idIncidenceStr.replaceAll("\\D+", "") );    	
    }
	
	
	
	
	
	
	

}
