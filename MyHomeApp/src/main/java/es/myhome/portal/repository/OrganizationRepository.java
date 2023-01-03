package es.myhome.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.myhome.portal.domain.app.Organization;
import es.myhome.portal.domain.users.User;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	
	
	
	 Optional<Organization> findOneByName(String name);
	 
	 int countById(Long idOrganization);

}
