package mil.dtic.cbes.utils.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransformerException extends CxeException{
    private static final long serialVersionUID = 6292737146852261168L;

    public TransformerException(String msg) {
        super(msg);
    }

}
