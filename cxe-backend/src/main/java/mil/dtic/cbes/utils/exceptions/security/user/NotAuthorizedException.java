package mil.dtic.cbes.utils.exceptions.security.user;

public class NotAuthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NotAuthorizedException(String message){
		super(message);
	}
	
	public NotAuthorizedException(String message, Throwable cause){
		super(message, cause);
	}
}
