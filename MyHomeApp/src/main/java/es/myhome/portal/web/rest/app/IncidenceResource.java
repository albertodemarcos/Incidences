package es.myhome.portal.web.rest.app;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.myhome.portal.config.Constants;
import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.security.AuthoritiesConstants;
import es.myhome.portal.service.IncidenceService;
import es.myhome.portal.service.dto.IncidenceDTO;
import es.myhome.portal.service.dto.OrganizationDTO;
import es.myhome.portal.web.rest.errors.BadRequestAlertException;
import es.myhome.portal.web.rest.errors.LoginAlreadyUsedException;

@RestController
@RequestMapping("/api")
public class IncidenceResource {

	private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	
	private final IncidenceService incidenceService;
	
	private final IncidenceRepository incidenceRepository;

	public IncidenceResource(IncidenceService incidenceService, IncidenceRepository incidenceRepository) {
		super();
		this.incidenceService = incidenceService;
		this.incidenceRepository = incidenceRepository;
	}
	
	/**
     * {@code POST  /admin/incidences}  : Creates a new organization.
     * <p>
     * The incidence not needs to be admin on creation.
     *
     * @param organizationDTO the organization to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organization, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the login or email is already in use.
     */
    @PostMapping("/incidences")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Incidence> createIncidence(@Valid @RequestBody IncidenceDTO incidenceDTO) throws URISyntaxException {
        log.debug("REST request to save Incidence : {}", incidenceDTO);
        /*Organization newOrganization = null;
        if (organizationDTO.getId() != null) {
 			throw new BadRequestAlertException("A new organization cannot already have an ID", "organizationManagement", "idexists");
        } else if (organizationRepository.findOneByName(organizationDTO.getName().toLowerCase()).isPresent()) { // Lowercase the user login before comparing with database
        	throw new NameOrganizationAlreadyUsedException();
        }  
        newOrganization = organizationService.createOrganization(organizationDTO);
        return ResponseEntity
                .created(new URI("/api/admin/organizations/" + newOrganization.getId() ))
                .headers(HeaderUtil.createAlert(applicationName, "organizationService.created", newOrganization.getId().toString() ))
                .body(newOrganization);*/
        return null;
    }

    /**
     * {@code PUT /incidences} : Updates an existing User.
     *
     * @param userDTO the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already in use.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already in use.
     */
    @PutMapping("/incidences")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<IncidenceDTO> updateIncidence(@Valid @RequestBody IncidenceDTO incidenceDTO) {
        log.debug("REST request to update Organization : {}", incidenceDTO);       
        /*Optional<Organization> existingOrganization = organizationRepository.findById(organizationDTO.getId());        
        if (!existingOrganization.isPresent() ) {        	
        	throw new BadRequestAlertException("A organization don't exist", "organizationManagement", "idnotexists");
        }        
        Optional<OrganizationDTO> updatedOrganization = organizationService.updateOrganization(organizationDTO);
        return ResponseUtil.wrapOrNotFound(updatedOrganization, HeaderUtil.createAlert(applicationName, "organizationManagement.updated", organizationDTO.getName() ) );*/
        return null;
    }

    /**
     * {@code GET /admin/incidences} : getOrganization with all the details - calling this are only allowed for the administrators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/incidences")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<IncidenceDTO>> getIncidence(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {        
    	log.debug("REST request to get all Organizations for an admin");        
        /*if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }
        final Page<OrganizationDTO> page = organizationService.getAllManagedOrganizations(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
    	return null;
    }

    /**
     * {@code GET /admin/organizations/:idOrganizationStr} : get the "id" organization.
     *
     * @param login the login of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "idOrganization" organization, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incidences/{idOrganizationStr}")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<IncidenceDTO> getIncidence(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idOrganizationStr) throws IllegalArgumentException, Exception {
        log.debug("REST request to get Organization : {}", idOrganizationStr);
        //return ResponseUtil.wrapOrNotFound(organizationService.getOrganizationByIdOrganization(parseIdOrganization(idOrganizationStr)).map(OrganizationDTO::new));
        return null;
    }

    /**
     * {@code DELETE /admin/organizations/:idOrganizationStr} : delete the "id" Organization.
     *
     * @param login the login of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incidences/{idOrganization}")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteIncidence(@PathVariable @Pattern(regexp = Constants.ENTITIES_ID_REGEX) String idOrganizationStr) throws IllegalArgumentException, Exception {
        log.debug("REST request to delete Organization: {}", idOrganizationStr);
        /*organizationService.deleteOrganization( parseIdOrganization(idOrganizationStr) );
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "organizationService.deleted", idOrganizationStr )).build();*/
        return null;
    }
    
    /*
    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(FilterUtils.ORGANIZATION_RESOURCE_ALLOWED_ORDERED_PROPERTIES::contains);
    }
    
    private Long parseIdOrganization(String idOrganizationStr ) throws IllegalArgumentException {
    	return Long.parseLong( idOrganizationStr.replaceAll("\\D+", "") );    	
    }
	*/
}
