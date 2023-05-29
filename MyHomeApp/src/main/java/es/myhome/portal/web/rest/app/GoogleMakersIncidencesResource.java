package es.myhome.portal.web.rest.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.myhome.portal.service.GoogleMakersIncidencesService;
import es.myhome.portal.service.dto.GoogleMarkerIncidenceDTO;
import es.myhome.portal.service.filters.FilterGoogleMarkerIncidence;

@RestController
@RequestMapping("/api")
public class GoogleMakersIncidencesResource {

	private final Logger log = LoggerFactory.getLogger(GoogleMakersIncidencesResource.class);
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	
	private final GoogleMakersIncidencesService googleMakersIncidencesService;
	
	public GoogleMakersIncidencesResource(GoogleMakersIncidencesService googleMakersIncidencesService) {
		super();
		this.googleMakersIncidencesService = googleMakersIncidencesService;
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
        
    	if (!onlyContainsAllowedProperties(filters)) {
            return ResponseEntity.badRequest().build();
        }
        
        List<GoogleMarkerIncidenceDTO> markers = this.googleMakersIncidencesService.getIncidencesMarkersByLocation(filters);
        
        return new ResponseEntity<>(markers, HttpStatus.OK);
    }
    
    private boolean onlyContainsAllowedProperties(FilterGoogleMarkerIncidence filters) {
        
    	boolean isValid = false;
    	
    	if( filters.getaNord() != null && filters.getaEst() != null && filters.getaSud() != null && filters.getaOvest() != null ) {
    		
    		isValid = true;
    	}
    	
    	return isValid;
    }
	
}
