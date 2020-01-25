package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class AccountNotFoundException extends CxeException{
	private static final long serialVersionUID = -63657869658227679L;

	public AccountNotFoundException(String msg) {
		super(msg);
	}
}
