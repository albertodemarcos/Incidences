package es.myhome.portal.domain.app;

public enum PriorityType {

	LOW		(1, "PRIORITY_TYPE.LOW"), 		// PENDIENTE
	MEDIUM	(2, "PRIORITY_TYPE.MEDIUM"), 	// EN PROCESO
	HIGH	(3, "PRIORITY_TYPE.HIGH"); 		// PENDIENTE CONFIRMAR

	private final int value;
	private final String message;

	private PriorityType(int value, String message) {
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
