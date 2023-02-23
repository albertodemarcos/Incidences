package es.myhome.portal.domain.app;

public enum IncidenceStatus {


	PENDING				( 1, "INCIDENCE_STATUS.PENDING" ), 		// PENDIENTE
	IN_PROCESS 			( 2, "INCIDENCE_STATUS.IN_PROCESS" ), 	// EN PROCESO
	PENDING_CONFIRM		( 3, "INCIDENCE_STATUS.CONFIRM" ),		// PENDIENTE CONFIRMAR
	RESOLVED 			( 4, "INCIDENCE_STATUS.RESOLVED" ),		// RESUELTO
	CANCELED 			( 5, "INCIDENCE_STATUS.CANCELED" );		// CANCELADO
	
	
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
