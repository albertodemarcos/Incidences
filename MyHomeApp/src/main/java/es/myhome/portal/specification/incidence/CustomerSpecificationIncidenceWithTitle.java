package es.myhome.portal.specification.incidence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Incidence;

public class CustomerSpecificationIncidenceWithTitle implements Specification<Incidence> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094435449967698881L;
	
	private String title;
	
	public CustomerSpecificationIncidenceWithTitle(String title) {
		super();
		this.title = title;
	}
	
	@Override
	public Predicate toPredicate(Root<Incidence> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if (this.title == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
		return criteriaBuilder.like(
						criteriaBuilder.function("unaccent",         
											String.class,
											criteriaBuilder.lower(root.get("title"))),
											"%" + StringUtils.stripAccents(this.title) + "%");
	}

}
