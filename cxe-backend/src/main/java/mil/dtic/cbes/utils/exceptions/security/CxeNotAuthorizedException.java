package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CxeNotAuthorizedException extends CxeException{
    private static final long serialVersionUID = 4812324648894424621L;

    public CxeNotAuthorizedException(String msg) {
        super(msg);
    }

}
