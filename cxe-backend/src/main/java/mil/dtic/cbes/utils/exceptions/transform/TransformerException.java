package mil.dtic.cbes.utils.exceptions.transform;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransformerException extends CxeException{
    private static final long serialVersionUID = 6292737146852261168L;
    public static final String TRANSFORM_DTO_TO_ENTITY_EXCEPTION = "Failed to transform dto object";
    public static final String TRANSFORM_ENTITY_TO_DTO_EXCEPTION = "Failed to transform entity object";
    public static final String TRANSFORM_DTO_NULL = "Failed to transform - dto is null";
    public static final String TRANSFORM_ENTITY_NULL  = "Failed to transform - entity is null";
    
    public TransformerException(String msg) {
        super(msg);
    }

}
