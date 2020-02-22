package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidHeadersException extends CxeException implements SecurityExceptionMessageHolder  {
	private static final long serialVersionUID = 6047361523641775569L;

	public InvalidHeadersException(String msg) {
		super(msg);
	}

}
