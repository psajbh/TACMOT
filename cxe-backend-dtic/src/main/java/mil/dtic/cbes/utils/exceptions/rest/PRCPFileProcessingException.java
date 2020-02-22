package mil.dtic.cbes.utils.exceptions.rest;

public class PRCPFileProcessingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PRCPFileProcessingException(String message){
		super(message);
	}
	
	public PRCPFileProcessingException(String message, Throwable cause){
		super(message, cause);
	}
}
