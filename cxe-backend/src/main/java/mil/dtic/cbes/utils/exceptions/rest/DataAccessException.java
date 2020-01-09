package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class DataAccessException extends CxeException{
    private static final long serialVersionUID = -3658039547067981126L;
    
    private String msg;
    
    public DataAccessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }


}
