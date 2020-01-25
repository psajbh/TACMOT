package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class NotAuthenticatedException extends CxeException{
    private static final long serialVersionUID = 6292737146852261168L;

    public NotAuthenticatedException(String msg) {
        super(msg);
    }

}
