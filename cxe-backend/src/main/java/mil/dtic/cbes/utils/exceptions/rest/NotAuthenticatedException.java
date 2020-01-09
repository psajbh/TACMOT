package mil.dtic.cbes.utils.exceptions.rest;

//import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.utils.exceptions.CxeException;

//@Slf4j
public class NotAuthenticatedException extends CxeException{
    private static final long serialVersionUID = 6292737146852261168L;
    private String msg;

    public NotAuthenticatedException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }


}
