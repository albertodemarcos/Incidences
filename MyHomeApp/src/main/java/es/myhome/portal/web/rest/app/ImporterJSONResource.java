package es.myhome.portal.web.rest.app;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ImporterJSONResource {

	
	private final Logger log = LoggerFactory.getLogger(ImporterJSONResource.class);
	
	
	/**
     * {@code POST  /json/organizations}  : Creates a new organization.
     * <p>
     * The organization needs to be admin on creation.
     *
     * @param organizationDTO the organization to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organization, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @GetMapping(path="/organizations/importer")
    public ResponseEntity<String> getImportOrganizations() throws URISyntaxException {
       
    	log.debug("REST request to load file Organization : {}");   
        return ResponseEntity.ok("Todo ok");
    }
	
	
}
