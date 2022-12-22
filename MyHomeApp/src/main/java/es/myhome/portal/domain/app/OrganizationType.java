package es.myhome.portal.domain.app;

public enum OrganizationType {

	TOWN_HALL				( 1, "TYPE_ORGANIZATION.TOWN_HALL" ),
	COMMONWEALTH 			( 2, "TYPE_ORGANIZATION.COMMONWEALTH" ),
	ADMINISTRATIVE_REGIONS 	( 3, "TYPE_ORGANIZATION.ADMINISTRATIVE_REGIONS" ),
	STATE 					( 4, "TYPE_ORGANIZATION.STATE" ),
	CONFEDERATION			( 5, "TYPE_ORGANIZATION.CONFEDERATION" ),
	PRINCIPAL				( 6, "TYPE_ORGANIZATION.PRINCIPAL");
	
	
	private final int value;
	private final String message;
	
	OrganizationType( int value, String message ){
		this.value = value;
		this.message = message; 
	}
	
	public String getMessage() {
		return message; 
	}
	
	public int getValue() {
		return value; 
	}
	
}
