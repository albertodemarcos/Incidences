package es.myhome.portal.web.rest.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.service.IncidenceService;
import es.myhome.portal.service.dto.GoogleMarkerIncidenceDTO;
import es.myhome.portal.service.dto.IncidenceDTO;
import es.myhome.portal.service.filters.FilterGoogleMarkerIncidence;
import es.myhome.portal.utilities.FilterUtils;
import tech.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class GoogleMakersIncidencesResource {

private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	
	private final IncidenceService incidenceService;
	
	private final IncidenceRepository incidenceRepository;

	public GoogleMakersIncidencesResource(IncidenceService incidenceService, IncidenceRepository incidenceRepository) {
		super();
		this.incidenceService = incidenceService;
		this.incidenceRepository = incidenceRepository;
	}
	
	/**
     * {@code GET /incidences} : getIncidences with all the details - calling for all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all incidences.
     */
    @GetMapping("/markers")
    public ResponseEntity<List<GoogleMarkerIncidenceDTO>> getIncidences(FilterGoogleMarkerIncidence filters) {        
    	
    	log.debug("REST request to get all Incidence");
        
    	/*if (!onlyContainsAllowedProperties(null)) {
            return ResponseEntity.badRequest().build();
        }*/
        //final Page<IncidenceDTO> page = incidenceService.getAllManagedIncidences(pageable);
        
        List<GoogleMarkerIncidenceDTO> markers = null;
        
       // HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), incidenceMarkers);
        
        return new ResponseEntity<>(markers, /*headers,*/ HttpStatus.OK);
    }
    
    /*private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(FilterUtils.INCIDENCE_RESOURCE_ALLOWED_ORDERED_PROPERTIES::contains);
    }*/
	
}
