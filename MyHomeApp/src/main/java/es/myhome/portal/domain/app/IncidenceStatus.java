package es.myhome.portal.domain.app;

public enum IncidenceStatus {


	PENDING				( 1, "INCIDENCE_STATUS.PENDING" ), 		// PENDIENTE (0)
	IN_PROCESS 			( 2, "INCIDENCE_STATUS.IN_PROCESS" ), 	// EN PROCESO (1)
	PENDING_CONFIRM		( 3, "INCIDENCE_STATUS.CONFIRM" ),		// PENDIENTE CONFIRMAR (2)
	RESOLVED 			( 4, "INCIDENCE_STATUS.RESOLVED" ),		// RESUELTO (3)
	CANCELED 			( 5, "INCIDENCE_STATUS.CANCELED" );		// CANCELADO (4)
	
	
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
