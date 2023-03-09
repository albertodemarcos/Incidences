package es.myhome.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.service.dto.GoogleMarkerIncidenceDTO;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

	 int countById(Long idIncidence);
	 
	 Optional<Incidence> findOneByTitle(String title);
	 
	 @Query(" select new es.myhome.portal.service.dto.GoogleMarkerIncidenceDTO(i) " +
		" from Incidence i " +
		" where i.location.latitude <= :aNord " +
		" and i.location.latitude >= :aSud " +
		" and i.location.longitude <= :aEst " +
		" and i.location.longitude >= :aOvest ")
	 List<GoogleMarkerIncidenceDTO> findByLocationIncidences(
			 @Param("aNord") Double aNord, 		//lat
			 @Param("aEst") Double aEst,   		//lng
			 @Param("aSud") Double aSud,   		//lat
			 @Param("aOvest") Double aOvest ); 	//lng
	 
	 
}
