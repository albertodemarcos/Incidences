package es.myhome.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.myhome.portal.domain.users.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
