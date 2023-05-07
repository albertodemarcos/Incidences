package es.myhome.portal.web.rest.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.myhome.portal.config.Constants;
import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.service.IncidenceService;
import es.myhome.portal.service.dto.IncidenceDTO;
import es.myhome.portal.service.dto.IncidenceKanbanDTO;
import es.myhome.portal.service.dto.IncidenceListDTO;
import es.myhome.portal.service.filters.FilterIncidence;
import es.myhome.portal.utilities.FilterUtils;
import es.myhome.portal.web.rest.errors.BadRequestAlertException;
import es.myhome.portal.web.rest.errors.NameOrganizationAlreadyUsedException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class IncidenceResource {

	private final Logger log = LoggerFactory.getLogger(IncidenceResource.class);
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	
	private final IncidenceService incidenceService;
	
	private final IncidenceRepository incidenceRepository;

	public IncidenceResource(IncidenceService incidenceService, IncidenceRepository incidenceRepository) {
		super();
		this.incidenceService = incidenceService;
		this.incidenceRepository = incidenceRepository;
	}
	
	@InitBinder("incidence")
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		log.info("Tenemos: title={}, description={}, idOrganization={}, status={}, priority={}, longitude={}, latitude: {}, startDate={}",
			request.getParameter("title"), request.getParameter("description"), request.getParameter("idOrganization"),
			request.getParameter("status"),	request.getParameter("priority"), request.getParameter("longitude"),
			request.getParameter("latitude"), request.getParameter("startDate"));
		request.getParameter("photos");
	}
	
	/**
     * {@code POST  /incidences}  : Creates a new incidence.
     * <p>
     * The incidence not needs to be admin on creation.
     * 
     * e//,
    		//@RequestParam(name = "photos", required = false) Part[] photos
    		//@RequestPart(name = "photos") List<MultipartFile> photos
     *
     * @param incidenceDTO the incidence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incidence, or with status {@code 400 (Bad Request)} if the title is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title is already in incidence.
     */
    @PostMapping(path = "/incidences/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<Incidence> createIncidence(@ModelAttribute(name = "incidence") IncidenceDTO incidence	) throws URISyntaxException { //
    	log.debug("REST request to save Incidence : {}", incidence.toString());
        Incidence newIncidence = null;
        if (incidence.getId() != null) {
 			throw new BadRequestAlertException("A new incidence cannot already have an ID", "incidenceManagement", "idexists");
        } else if (incidenceRepository.findOneByTitle(incidence.getTitle().toLowerCase()).isPresent()) { // Lowercase the incidence title before comparing with database
        	throw new NameOrganizationAlreadyUsedException();
        }  
        newIncidence = incidenceService.createIncidence(incidence);
        return ResponseEntity
        		.created(new URI("/api/incidences/" + newIncidence.getId() ))
        		.headers(HeaderUtil.createAlert(applicationName, "incidenceService.created", newIncidence.getId().toString() ))
        		.body(newIncidence);
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
        log.debug("REST request to update Incidence : {}", incidenceDTO);       
        Optional<Incidence> existingIncidence = incidenceRepository.findById(incidenceDTO.getId());        
        if (!existingIncidence.isPresent() ) {        	
        	throw new BadRequestAlertException("A incidence don't exist", "incidenceManagement", "idnotexists");
        }        
        Optional<IncidenceDTO> updatedIncidence = incidenceService.updateIncidence(incidenceDTO);
        return ResponseUtil.wrapOrNotFound(updatedIncidence, HeaderUtil.createAlert(applicationName, "incidenceManagement.updated", incidenceDTO.getTitle() ) );
    }
    
    /**
     * {@code PUT /incidences} : Updates an existing Incidence.
     *
     * @param incidenceDTO the incidence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incidence.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title don't already in use.
     */
    @PutMapping("/incidences/update-status")
    public ResponseEntity<IncidenceKanbanDTO> updateStatusIncidence(@Valid @RequestBody IncidenceKanbanDTO incidenceDTO) {        
    	log.debug("REST request to update Incidence : {}", incidenceDTO);       
        Optional<Incidence> existingIncidence = incidenceRepository.findById(incidenceDTO.getId());        
        if (!existingIncidence.isPresent() ) {        	
        	throw new BadRequestAlertException("A incidence don't exist", "incidenceManagement", "idnotexists");
        }        
        Optional<IncidenceKanbanDTO> updatedIncidence = incidenceService.updateStatusIncidence(incidenceDTO);
        return ResponseUtil.wrapOrNotFound(updatedIncidence, HeaderUtil.createAlert(applicationName, "incidenceManagement.updated", incidenceDTO.getTitle() ) );
    }
    
    /**
     * {@code PUT /incidences} : Updates an existing Incidence.
     *
     * @param incidenceDTO the incidence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incidence.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the title don't already in use.
     */
    @GetMapping("/incidences/update-employee")
    public ResponseEntity<IncidenceDTO> updateEmployeeIncidence(@Valid @RequestBody Long idIncidence) {
        log.debug("REST request to update Incidence : {}", idIncidence);       
        Optional<Incidence> existingIncidence = incidenceRepository.findById(idIncidence);        
        if (!existingIncidence.isPresent() ) {        	
        	throw new BadRequestAlertException("A incidence don't exist", "incidenceManagement", "idnotexists");
        }    
        Incidence incidence = existingIncidence.orElse(null);
        Optional<IncidenceDTO> updatedIncidence = incidenceService.updateEmployeeIncidence(incidence);
        return ResponseUtil.wrapOrNotFound(updatedIncidence, HeaderUtil.createAlert(applicationName, "incidenceManagement.updated", incidence.getTitle() ) );
    }

    /**
     * {@code GET /incidences} : getIncidences with all the details - calling for all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all incidences.
     */
    @GetMapping("/incidences")
    public ResponseEntity<List<IncidenceListDTO>> getIncidences(FilterIncidence filters, Pageable pageable) {        
    	log.debug("REST request to get all Incidence");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }
        final Page<IncidenceListDTO> page = incidenceService.getAllManagedIncidences(filters, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * {@code GET /incidences} : getIncidences with all the details - calling for all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all incidences.
     */
    @GetMapping("/incidences/kanban")
    public ResponseEntity<List<IncidenceKanbanDTO>> getKanbanIncidences() {        
    	log.debug("REST request to get all Incidence for kanban");
        final List<IncidenceKanbanDTO> list = incidenceService.getAllIncidences();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * {@code GET /incidences/:idIncidenceStr} : get the "id" incidence.
     *
     * @param idIncidenceStr the id of the incidence to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "idIncidenceStr" incidence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incidences/{idIncidenceStr}")
    public ResponseEntity<IncidenceDTO> getIncidence(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idIncidenceStr) throws IllegalArgumentException, Exception {
        log.debug("REST request to get Incidence : {}", idIncidenceStr);
        //return ResponseUtil.wrapOrNotFound(incidenceService.getIncidenceByIdIncidence(parseIdIncidence(idIncidenceStr)).map(IncidenceDTO::new));
        return ResponseUtil.wrapOrNotFound(incidenceService.getIncidenceDTOById(this.parseIdIncidence(idIncidenceStr)));
    }

    /**
     * {@code DELETE /incidences/:idIncidenceStr} : delete the "id" Incidence.
     *
     * @param idIncidenceStr the id of the incidence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incidences/{idIncidenceStr}")
    public ResponseEntity<Void> deleteIncidence(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idIncidenceStr) throws IllegalArgumentException, Exception {
        log.debug("REST request to delete Incidence: {}", idIncidenceStr);
        incidenceService.deleteIncidence( parseIdIncidence(idIncidenceStr) );
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "incidenceService.deleted", idIncidenceStr )).build();
    }
    
    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(FilterUtils.INCIDENCE_RESOURCE_ALLOWED_ORDERED_PROPERTIES::contains);
    }
    
    private Long parseIdIncidence(String idIncidenceStr ) throws IllegalArgumentException {
    	return Long.parseLong( idIncidenceStr.replaceAll("\\D+", "") );    	
    }
	
}
