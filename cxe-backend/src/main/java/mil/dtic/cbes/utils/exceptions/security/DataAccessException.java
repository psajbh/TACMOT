package mil.dtic.cbes.utils.exceptions.security;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class DataAccessException extends CxeException{
    private static final long serialVersionUID = -3658039547067981126L;
    
    public DataAccessException(String msg) {
        super(msg);
    }

}
