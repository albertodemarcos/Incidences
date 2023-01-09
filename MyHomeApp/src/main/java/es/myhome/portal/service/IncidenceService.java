package es.myhome.portal.service;

import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.myhome.portal.domain.app.Geolocation;
import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.service.dto.IncidenceDTO;

/**
 * Service class for managing organizations.
 */
@Service
@Transactional
public class IncidenceService {

private final Logger log = LoggerFactory.getLogger(OrganizationService.class);

	
	private final IncidenceRepository incidenceRepository;
	
	public IncidenceService(IncidenceRepository incidenceRepository) {
		super();
		this.incidenceRepository = incidenceRepository;
	}


	public Incidence createIncidence(IncidenceDTO incidenceDTO) throws URISyntaxException {
		Incidence incidence = new Incidence();
		incidence.setId(incidenceDTO.getId());
		incidence.setTitle(incidenceDTO.getTitle());
		incidence.setDescription(incidenceDTO.getDescription());
		incidence.setStartDate(incidenceDTO.getStartDate());
		incidence.setEndDate(incidenceDTO.getEndDate());
		incidence.setStatus(incidenceDTO.getStatus());
		incidence.setLocation(getGeolocation(incidenceDTO));
		//incidence.setOrganization(incidenceDTO.get);
		//incidence.setPhotos(incidenceDTO.get);
		//incidence.setEmployee(incidenceDTO.get);
		incidenceRepository.save(incidence);
		log.debug("Created Information for Incidence: {}", incidence);
		return incidence;
	}
	
	@Transactional(readOnly = true)
	public Optional<Incidence> getIncidenceByIdIncidence(Long idOrganization) {
	    return null;//organizationRepository.findById(idOrganization);
	}

	/**
     * Update all information for a specific organization, and return the modified organization.
     *
     * @param organizationDTO organization to update.
     * @return updated organization.
     */
	public Optional<IncidenceDTO> updateIncidence(IncidenceDTO incidenceDTO){
		/*return Optional
	            .of(organizationRepository.findById(organizationDTO.getId()))
	            .filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(organization -> {
	        		organization.setName(organizationDTO.getName());
	        		organization.setDescription(organizationDTO.getDescription());
	        		organization.setType(organizationDTO.getType());
	        		Geolocation geolocation = organization.getGeolocation();
	        		if( geolocation == null || geolocation.getLatitude() == null || geolocation.getLongitude() == null ) {
	        			geolocation = new Geolocation();
	        		}
	        		geolocation.setLatitude(organizationDTO.getLatitude());
	        		geolocation.setLongitude(organizationDTO.getLongitude());
	        		organization.setGeolocation(geolocation);
	                log.debug("Changed Information for Organization: {}", organization);
	                return organization;
	            })
	            .map(OrganizationDTO::new);
	}
    
	public void deleteOrganization(Long idOrganization) throws Exception {
		int count = organizationRepository.countById(idOrganization);
		if(count != 1 ) {
			throw new Exception("Dont exit organization by ID: "+ idOrganization);
		}
		organizationRepository.deleteById(idOrganization);
		log.debug("Deleted Organization: {}", idOrganization);*/
		return null;
	}


	@Transactional(readOnly = true)
    public Page<IncidenceDTO> getAllManagedIncidences(Pageable pageable) {
        return  null;//organizationRepository.findAll(pageable).map(OrganizationDTO::new);
    }
	
	private Geolocation getGeolocation(IncidenceDTO incidenceDTO) {
		if( incidenceDTO == null || incidenceDTO.getLongitude() == null || incidenceDTO.getLatitude() == null ) {
			return null;
		}
		Geolocation location = new Geolocation( incidenceDTO.getLongitude(), incidenceDTO.getLatitude() );
		return location;
	}
}
