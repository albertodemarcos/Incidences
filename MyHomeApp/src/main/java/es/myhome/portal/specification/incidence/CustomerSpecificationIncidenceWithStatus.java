package es.myhome.portal.specification.incidence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.IncidenceStatus;

public class CustomerSpecificationIncidenceWithStatus implements Specification<Incidence> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7531843605368046191L;
	
	private IncidenceStatus status;
	
	public CustomerSpecificationIncidenceWithStatus(IncidenceStatus status) {
		super();
		this.status = status;
	}


	@Override
	public Predicate toPredicate(Root<Incidence> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if (this.status == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
		return criteriaBuilder.equal(root.get("status"), this.status);
	}

}
