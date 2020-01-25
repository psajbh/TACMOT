package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class InvalidLdapException extends CxeException{
    private static final long serialVersionUID = 4812324648894424621L;

    public InvalidLdapException(String msg) {
        super(msg);
    }

}
