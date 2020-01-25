package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccountNotFoundException extends CxeException{
	private static final long serialVersionUID = -63657869658227679L;

	public AccountNotFoundException(String msg) {
		super(msg);
	}
}
