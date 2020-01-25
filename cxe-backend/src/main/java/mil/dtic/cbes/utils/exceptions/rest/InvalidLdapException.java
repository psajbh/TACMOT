package mil.dtic.cbes.utils.exceptions.rest;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidLdapException extends CxeException{
    private static final long serialVersionUID = 4812324648894424621L;

    public InvalidLdapException(String msg) {
        super(msg);
    }

}
