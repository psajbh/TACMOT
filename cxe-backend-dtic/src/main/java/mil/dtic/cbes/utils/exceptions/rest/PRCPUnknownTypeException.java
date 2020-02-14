package mil.dtic.cbes.utils.exceptions.rest;

public class PRCPUnknownTypeException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public PRCPUnknownTypeException(String message) {
		super(message);
	}
	
	public PRCPUnknownTypeException(String message, Throwable cause){
		super(message, cause);
	}
}
