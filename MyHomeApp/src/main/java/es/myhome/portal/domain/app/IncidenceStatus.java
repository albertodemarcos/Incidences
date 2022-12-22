package es.myhome.portal.domain.app;

public enum IncidenceStatus {


	PENDING				( 1, "INCIDENCE_STATUS.PENDING" ), 		// PENDIENTE
	IN_PROCESS 			( 2, "INCIDENCE_STATUS.IN_PROCESS" ), 	// EN_PROCESO
	RESOLVED 			( 3, "INCIDENCE_STATUS.RESOLVED" ),		// RESUELTO
	CANCELED 			( 4, "INCIDENCE_STATUS.CANCELED" );		// CANCELADO
	
	
	private final int value;
	private final String message;
	
	IncidenceStatus( int value, String message ){
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
