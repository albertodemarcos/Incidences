package es.myhome.portal.web.rest.app;

import es.myhome.portal.web.rest.errors.BadRequestAlertException;
import es.myhome.portal.web.rest.errors.ErrorConstants;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class NameOrganizationAlreadyUsedException extends BadRequestAlertException {

	private static final long serialVersionUID = 1L;

    public NameOrganizationAlreadyUsedException() {
        super(ErrorConstants.NAME_ORGANIZATION_ALREADY_USED_TYPE, "Organization name already used!", "organizationManagement", "organizationexists");
    }
}
