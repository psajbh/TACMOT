package mil.dtic.cbes.utils.exceptions.rest;

import java.io.IOException;

public class ResourceNotFoundException extends IOException {
	private static final long serialVersionUID = 1L;
	
	public static final String USER_GUIDE_NOT_FOUND = "User guide file was not found on the server.";

	public ResourceNotFoundException(String message){
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause){
		super(message, cause);
	}

}
