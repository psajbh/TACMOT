package mil.dtic.cbes.utils.exceptions.security;

import mil.dtic.cbes.utils.exceptions.CxeException;


public class InvalidCredentialException extends CxeException {
	private static final long serialVersionUID = -6926624393265867590L;
	public static final String INVALID_USER_CREDENTIAL_MSG = "Invalid Credentials";

	public InvalidCredentialException(String msg) {
		super(msg);
	}

}
