package es.myhome.portal.specification.incidence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.PriorityType;

public class CustomerSpecificationIncidenceWithPriority implements Specification<Incidence> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2065527187098086896L;

	private PriorityType priority;
	
	public CustomerSpecificationIncidenceWithPriority(PriorityType priority) {
		super();
		this.priority = priority;
	}

	@Override
	public Predicate toPredicate(Root<Incidence> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if (this.priority == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
		return criteriaBuilder.equal(root.get("priority"), this.priority);
	}

}
