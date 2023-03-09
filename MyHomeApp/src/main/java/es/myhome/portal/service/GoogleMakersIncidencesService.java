package es.myhome.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.service.dto.GoogleMarkerIncidenceDTO;
import es.myhome.portal.service.filters.FilterGoogleMarkerIncidence;

/**
 * Service class for managing incidences markers.
 */
@Service
@Transactional
public class GoogleMakersIncidencesService {
	
	private final Logger log = LoggerFactory.getLogger(IncidenceService.class);
	
	private final IncidenceRepository incidenceRepository;
	
	public GoogleMakersIncidencesService(IncidenceRepository incidenceRepository) {
		super();
		this.incidenceRepository = incidenceRepository;
	}
	
	@Transactional(readOnly = true)
	public List<GoogleMarkerIncidenceDTO> getIncidencesMarkersByLocation(FilterGoogleMarkerIncidence filters) {
		
		log.info("getIncidencesMarkersByLocation(filters={})", filters.toString() );
		
		List<GoogleMarkerIncidenceDTO> incidencesMarker = incidenceRepository.findByLocationIncidences(filters.getaNord(), filters.getaEst(), filters.getaSud(), filters.getaOvest());
		
		return incidencesMarker;
	}

}
