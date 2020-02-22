package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DataAccessException extends CxeException{
    private static final long serialVersionUID = -3658039547067981126L;
    
    public static final String USER_DTO_CAPTURE_FAILURE = "Failed to capture user object";
    public static final String USER_ENTITY_CAPTURE_FAILURE = "Failed to capture user from database";

    
    public DataAccessException(String msg) {
        super(msg);
    }

}
