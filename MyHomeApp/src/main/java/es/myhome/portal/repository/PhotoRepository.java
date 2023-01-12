package es.myhome.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.myhome.portal.domain.app.Photo;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>  {

}
