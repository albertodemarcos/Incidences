package es.myhome.portal.specification.incidence;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.myhome.portal.domain.app.Incidence;

public class CustomerSpecificationIncidenceWithDate implements Specification<Incidence> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7066358186739345476L;
	
	private Date dateFilter;
	private String nameField;

	public CustomerSpecificationIncidenceWithDate(Date dateFilter, String nameField) {
		super();
		this.dateFilter = dateFilter;
		this.nameField = nameField;
	}
	
	@Override
	public Predicate toPredicate(Root<Incidence> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if (this.dateFilter == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
		
		Date start = setTime(this.dateFilter, 0,0,0,0);
		Date end = setTime(this.dateFilter, 23,59,59,999);
		
		return criteriaBuilder.between(root.<Date>get(nameField), start, end);
	}

	public static Date setTime( final Date date, final int hourOfDay, final int minute, final int second, final int ms )
	{
	    final Calendar gc = Calendar.getInstance();
	    gc.setTime( date );
	    gc.set( Calendar.HOUR_OF_DAY, hourOfDay );
	    gc.set( Calendar.MINUTE, minute );
	    gc.set( Calendar.SECOND, second );
	    gc.set( Calendar.MILLISECOND, ms );
	    return gc.getTime();
	}
}
