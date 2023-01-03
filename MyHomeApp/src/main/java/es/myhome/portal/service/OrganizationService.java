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
import es.myhome.portal.domain.app.Organization;
import es.myhome.portal.repository.OrganizationRepository;
import es.myhome.portal.service.dto.OrganizationDTO;


/**
 * Service class for managing organizations.
 */
@Service
@Transactional
public class OrganizationService {
	
	private final Logger log = LoggerFactory.getLogger(OrganizationService.class);

	
	private final OrganizationRepository organizationRepository;
	
	public OrganizationService(OrganizationRepository organizationRepository) {
		super();
		this.organizationRepository = organizationRepository;
	}


	public Organization createOrganization(OrganizationDTO organizationDTO) throws URISyntaxException {
		Organization organization = new Organization();
		organization.setName(organizationDTO.getName());
		organization.setDescription(organizationDTO.getDescription());
		organization.setType(organizationDTO.getType());
		Geolocation geolocation = new Geolocation();
		geolocation.setLatitude(organizationDTO.getLatitude());
		geolocation.setLongitude(organizationDTO.getLongitude());
		organization.setGeolocation(geolocation);
		organizationRepository.save(organization);
		log.debug("Created Information for Organization: {}", organization);
		return organization;
	}

	/**
     * Update all information for a specific organization, and return the modified organization.
     *
     * @param organizationDTO organization to update.
     * @return updated organization.
     */
	public Optional<OrganizationDTO> updateOrganization(OrganizationDTO organizationDTO){
		return Optional
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
		log.debug("Deleted Organization: {}", idOrganization);
	}


	@Transactional(readOnly = true)
    public Page<OrganizationDTO> getAllManagedOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable).map(OrganizationDTO::new);
    }
	
}
