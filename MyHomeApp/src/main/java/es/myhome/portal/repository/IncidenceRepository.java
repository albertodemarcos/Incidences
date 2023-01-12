package es.myhome.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.myhome.portal.domain.app.Incidence;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

	 int countById(Long idIncidence);
	 
	 Optional<Incidence> findOneByTitle(String title);
}
