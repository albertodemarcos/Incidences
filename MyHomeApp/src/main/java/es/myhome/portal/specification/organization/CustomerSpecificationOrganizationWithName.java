package es.myhome.portal.specification.organization;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Organization;

public class CustomerSpecificationOrganizationWithName implements Specification<Organization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2647262502013151093L;
	
	private String name;
	
	public CustomerSpecificationOrganizationWithName(String name) {
		super();
		this.name = name;
	}

	@Override
	public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if (this.name == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
        //return criteriaBuilder.like(root.get("name"), this.name.toLowerCase());
		/*return criteriaBuilder.like(
				criteriaBuilder.function("unaccent"
		                 , String.class
		                 , root.get("name")
		                 , criteriaBuilder.literal(" ")
		                 , criteriaBuilder.literal(""))
		          , "%" + this.name.toUpperCase().replace(" ", "") + "%"		
		);*/
		return criteriaBuilder.like(
						criteriaBuilder.function("unaccent",         
											String.class,
											criteriaBuilder.lower(root.get("name"))),
											"%" + StringUtils.stripAccents(this.name) + "%");
		}


	
}
