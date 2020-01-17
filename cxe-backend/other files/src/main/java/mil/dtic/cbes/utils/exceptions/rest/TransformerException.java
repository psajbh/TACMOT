package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class TransformerException extends CxeException{
    private static final long serialVersionUID = 6292737146852261168L;
    private String msg;

    public TransformerException(String msg) {
        super(msg);
        this.msg = msg;
    }
    
    public String getMessage() {
        return msg;
    }



}
