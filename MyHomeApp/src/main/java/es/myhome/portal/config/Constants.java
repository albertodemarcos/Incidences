package es.myhome.portal.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    
    // Regex for acceptable entities ids
    public static final String ENTITIES_ID_REGEX = "[0-9]+";
    

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "es";

    private Constants() {}
}
