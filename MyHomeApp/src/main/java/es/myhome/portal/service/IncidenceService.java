package es.myhome.portal.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.myhome.portal.domain.app.Geolocation;
import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.Organization;
import es.myhome.portal.domain.app.Photo;
import es.myhome.portal.domain.users.Employee;
import es.myhome.portal.domain.users.User;
import es.myhome.portal.repository.EmployeeRepository;
import es.myhome.portal.repository.IncidenceRepository;
import es.myhome.portal.repository.OrganizationRepository;
import es.myhome.portal.service.dto.IncidenceDTO;
import es.myhome.portal.service.dto.IncidenceKanbanDTO;
import es.myhome.portal.service.dto.IncidenceListDTO;
import es.myhome.portal.service.dto.PhotoDTO;
import es.myhome.portal.service.dto.PhotoDetailDTO;
import es.myhome.portal.service.filters.FilterIncidence;
import es.myhome.portal.specification.incidence.CustomerSpecificationIncidenceWithDate;
import es.myhome.portal.specification.incidence.CustomerSpecificationIncidenceWithNameOrganization;
import es.myhome.portal.specification.incidence.CustomerSpecificationIncidenceWithPriority;
import es.myhome.portal.specification.incidence.CustomerSpecificationIncidenceWithStatus;
import es.myhome.portal.specification.incidence.CustomerSpecificationIncidenceWithTitle;

/**
 * Service class for managing incidences.
 */
@Service
@Transactional
public class IncidenceService {

	private final Logger log = LoggerFactory.getLogger(IncidenceService.class);
	
	private final IncidenceRepository incidenceRepository;
	
	private final OrganizationRepository organizationRepository;
	
	private final EmployeeRepository employeeRepository;
	
	private final PhotoService photoService;
	
	private final UserService userService;
	
	private final String FIELD_START_DATE = "startDate";
	private final String FIELD_END_DATE = "endDate";
	
	public IncidenceService(IncidenceRepository incidenceRepository, OrganizationRepository organizationRepository,
			EmployeeRepository employeeRepository, PhotoService photoService, UserService userService) {
		super();
		this.incidenceRepository = incidenceRepository;
		this.organizationRepository = organizationRepository;
		this.employeeRepository = employeeRepository;
		this.photoService = photoService;
		this.userService = userService;
	}

	public Incidence createIncidence(IncidenceDTO incidenceDTO) throws URISyntaxException {
		
		Incidence incidence = new Incidence();
		
		incidence.setId(incidenceDTO.getId());
		incidence.setTitle(incidenceDTO.getTitle());
		incidence.setDescription(incidenceDTO.getDescription());
		incidence.setStartDate(incidenceDTO.getStartDate());		
		incidence.setStatus(incidenceDTO.getStatus());
		incidence.setPriority(incidenceDTO.getPriority());
		
		Geolocation geolocation = getGeolocation(incidenceDTO);
		incidence.setLocation(geolocation);
		
		if(incidenceDTO.getIdOrganization() != null ) {
			Organization organization = organizationRepository.findById(incidenceDTO.getIdOrganization()).orElse(null);
			incidence.setOrganization(organization);
		}
		
		if(incidenceDTO.getIdEmployee() != null) {
			Employee employee = employeeRepository.findById(incidenceDTO.getIdEmployee()).orElse(null);
			incidence.setEmployee(employee);
		}
		
		incidenceRepository.save(incidence);
		log.info("Created Information for Incidence: {}", incidence);
		
		if( CollectionUtils.isNotEmpty( incidenceDTO.getPhotos() ) ) {
			for( MultipartFile file : incidenceDTO.getPhotos() ) {
				Photo photo = photoService.createPhoto(incidence, file);
				incidence.getPhotos().add(photo);
			}
		}
		
		incidenceRepository.save(incidence);
		log.debug("Persist Incidence id={} with Photos", incidence.getId() );
		return incidence;
	}
	
	@Transactional(readOnly = true)
	public Optional<Incidence> getIncidenceByIdIncidence(Long idIncidence) {
	    return incidenceRepository.findById(idIncidence);
	}

	/**
     * Update all information for a specific organization, and return the modified organization.
     *
     * @param organizationDTO organization to update.
     * @return updated organization.
     */
	public Optional<IncidenceDTO> updateIncidence(IncidenceDTO incidenceDTO){
		return Optional
	            .of(incidenceRepository.findById(incidenceDTO.getId()))
	            .filter(Optional::isPresent)
	            .map(Optional::get)
	            .map(incidence -> {
	        		incidence.setTitle(incidenceDTO.getTitle());
	        		incidence.setDescription(incidenceDTO.getDescription());
	        		//incidence.setStartDate(incidenceDTO.getStartDate());
	        		//incidence.setEndDate(incidenceDTO.getEndDate());
	        		incidence.setStatus(incidenceDTO.getStatus());
	        		incidence.setPriority(incidenceDTO.getPriority());
	        		incidence.setLocation(getGeolocation(incidenceDTO));
	                log.debug("Changed Information for Incidence: {}", incidence);
	                return incidence;
	            })
	            .map(IncidenceDTO::new);
	}
	
	/**
     * Update all information for a specific organization, and return the modified organization.
     *
     * @param organizationDTO organization to update.
     * @return updated organization.
     */
	public Optional<IncidenceDTO> updateEmployeeIncidence(Incidence incidence){
		
		Optional<User> userOptional = userService.getUserWithAuthorities();
		
		Employee employee = employeeRepository.findById(userOptional.orElse(null).getId()).orElse(null);
		
		incidence.setEmployee(employee);
		
		this.incidenceRepository.save(incidence);
		
		return Optional.of(incidence).map(IncidenceDTO::new);
	}
    
	public void deleteIncidence(Long idIncidence) throws Exception {
		incidenceRepository.deleteById(idIncidence);
		log.debug("Deleted Incidence: {}", idIncidence);
	}

	@Transactional(readOnly = true)
	public List<IncidenceKanbanDTO> getAllIncidences(){
		
		log.debug("getAllIncidences()");
		
		List<IncidenceKanbanDTO> list = incidenceRepository.findAllIncidences();
		
		return list;
	}

	@Transactional(readOnly = true)
    public Page<IncidenceListDTO> getAllManagedIncidences(FilterIncidence filters, Pageable pageable) {
		
		log.debug("getIncidenceByIdIncidence(idIncidence)");
		
		if( filters != null ) {
			Specification<Incidence> specifications = Specification.where(new CustomerSpecificationIncidenceWithTitle(filters.getTitle()))
					.and(new CustomerSpecificationIncidenceWithStatus(filters.getStatus()))
					.and(new CustomerSpecificationIncidenceWithPriority(filters.getPriority()))
					.and(new CustomerSpecificationIncidenceWithDate(filters.getStartDate(), FIELD_START_DATE ))
					.and(new CustomerSpecificationIncidenceWithDate(filters.getEndDate(), FIELD_END_DATE));
			
			return incidenceRepository.findAll(specifications, pageable).map(IncidenceListDTO::new);
		}
		
        return incidenceRepository.findAll(pageable).map(IncidenceListDTO::new);
    }
	
	@Transactional(readOnly = true)
	public Optional<IncidenceDTO> getIncidenceDTOById(Long idIncidence) {
	    
		log.debug("getIncidenceByIdIncidence(idIncidence={})",idIncidence);
		
		Optional<Incidence> incidenceOpt = incidenceRepository.findById(idIncidence);
		
		if( incidenceOpt.isEmpty() ) {
			
			log.error("The incidence with id={} is null", idIncidence);
			
			return null;
		}

		Optional<User> user = userService.getUserWithAuthorities();
		
		if( onlyContainsEmployeeUser(incidenceOpt, user) ) {
			
			return incidenceOpt.map(IncidenceDTO::new);
		}
		
		IncidenceDTO incidenceDTO = new IncidenceDTO( incidenceOpt.orElse(null) );
		
		for(Photo photo: incidenceOpt.orElse(null).getPhotos()) {
			
			PhotoDetailDTO photoDTO = new PhotoDetailDTO(photo);
			
			incidenceDTO.getPhotosDTO().add(photoDTO);
		}
	    
		return Optional.of(incidenceDTO); 
	}

	private boolean onlyContainsEmployeeUser(Optional<Incidence> incidenceOpt, Optional<User> user) {
		log.debug("onlyContainsEmployeeUser");
		if( user.isEmpty() || CollectionUtils.isEmpty(incidenceOpt.orElse(null).getPhotos()) ) {
			return false;
		}
		return user.orElse(null).getAuthorities().stream().filter( a -> a.getName().contains("ROLE_EMPLOYEE")  ).count() > 0;
	}
	
	private Geolocation getGeolocation(IncidenceDTO incidenceDTO) {
		if( incidenceDTO == null || incidenceDTO.getLongitude() == null || incidenceDTO.getLatitude() == null ) {
			return null;
		}
		Geolocation location = new Geolocation( incidenceDTO.getLongitude(), incidenceDTO.getLatitude() );
		return location;
	}
	
}
