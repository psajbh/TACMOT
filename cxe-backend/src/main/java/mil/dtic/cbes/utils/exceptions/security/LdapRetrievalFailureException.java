package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LdapRetrievalFailureException extends CxeException {
	private static final long serialVersionUID = -5890382259487313518L;

	public LdapRetrievalFailureException(String msg) {
		super(msg);
	}
}
