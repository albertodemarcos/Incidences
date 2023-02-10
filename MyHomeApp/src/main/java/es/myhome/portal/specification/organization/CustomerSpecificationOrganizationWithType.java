package es.myhome.portal.specification.organization;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Organization;
import es.myhome.portal.domain.app.OrganizationType;

public class CustomerSpecificationOrganizationWithType implements Specification<Organization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125711612765997728L;
	
	private OrganizationType type;
	

	public CustomerSpecificationOrganizationWithType(OrganizationType type) {
		super();
		this.type = type;
	}


	@Override
	public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if (this.type == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
		return criteriaBuilder.equal(root.get("type"), this.type);
	}

}
