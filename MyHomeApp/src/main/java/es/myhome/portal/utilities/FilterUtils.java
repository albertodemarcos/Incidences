package es.myhome.portal.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterUtils {

	public static final List<String> PUBLIC_USER_RESOURCE_ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList("id", "login", "firstName", "lastName", "email", "activated", "langKey")
    );
	
	public static final List<String> USER_RESOURCE_ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id",
            "login",
            "firstName",
            "lastName",
            "email",
            "activated",
            "langKey",
            "createdBy",
            "createdDate",
            "lastModifiedBy",
            "lastModifiedDate"
        )
    );
	
	public static final List<String> ORGANIZATION_RESOURCE_ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id",
            "name",
            "type",
            "activated",
            "createdBy",
            "createdDate",
            "lastModifiedBy",
            "lastModifiedDate"
        )
    );
	
	public static final List<String> INCIDENCE_RESOURCE_ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id",
    		"title",
    		"startDate",
    		"endDate",
    		"status",
    		"priority"
        )
    );
	
	public static final List<String> PHOTO_RESOURCE_ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id"
        )
    );
	
}
