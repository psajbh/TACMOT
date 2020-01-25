package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class LdapRetrievalFailureException extends CxeException {
	private static final long serialVersionUID = -5890382259487313518L;

	public LdapRetrievalFailureException(String msg) {
		super(msg);
	}
}
