package es.myhome.portal.specification.incidence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Incidence;
import es.myhome.portal.domain.app.Organization;

public class CustomerSpecificationIncidenceWithNameOrganization implements Specification<Incidence> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 386947065176281312L;
	
	private String nameOrganization;
	
	public CustomerSpecificationIncidenceWithNameOrganization(String nameOrganization) {
		super();
		this.nameOrganization = nameOrganization;
	}

	@Override
	public Predicate toPredicate(Root<Incidence> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		
		if ( StringUtils.isBlank(this.nameOrganization) ) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
		
		Join<Incidence, Organization> leftJoin = root.join("organization", JoinType.LEFT);
		
		return criteriaBuilder.like(
				criteriaBuilder.function("unaccent",         
									String.class,
									criteriaBuilder.lower(leftJoin.get("organization.name"))),
									"%" + StringUtils.stripAccents(this.nameOrganization) + "%");
	}

}
