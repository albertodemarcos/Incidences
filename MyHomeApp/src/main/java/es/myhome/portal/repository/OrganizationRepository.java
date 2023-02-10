package es.myhome.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.myhome.portal.domain.app.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {
	
	 Optional<Organization> findOneByName(String name);
	 
	 int countById(Long idOrganization);

}
