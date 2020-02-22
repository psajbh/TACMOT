package mil.dtic.cbes.utils.exceptions;

public class CxeException extends RuntimeException{
    private static final long serialVersionUID = -5025939040113236907L;

    public CxeException(String message) {
		super(message);
	}
	
	public CxeException(String message, Throwable cause) {
		super(message, cause);
	}

}