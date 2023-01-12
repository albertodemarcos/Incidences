package es.myhome.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.myhome.portal.domain.users.Employee;

/**
 * Spring Data JPA repository for the {@link Employee} entity.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
