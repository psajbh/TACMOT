package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;


public class InvalidCredentialException extends CxeException {
	private static final long serialVersionUID = -6926624393265867590L;

	public InvalidCredentialException(String msg) {
		super(msg);
	}

}