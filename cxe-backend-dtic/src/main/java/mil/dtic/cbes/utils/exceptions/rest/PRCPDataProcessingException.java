package mil.dtic.cbes.utils.exceptions.rest;

public class PRCPDataProcessingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PRCPDataProcessingException(String message){
		super(message);
	}
	
	public PRCPDataProcessingException(String message, Throwable cause){
		super(message, cause);
	}
}
