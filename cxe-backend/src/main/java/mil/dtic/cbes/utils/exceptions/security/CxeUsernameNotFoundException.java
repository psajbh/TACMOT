package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CxeUsernameNotFoundException extends UsernameNotFoundException {
	private static final long serialVersionUID = 549437740454616371L;

	public CxeUsernameNotFoundException(String message) {
		super(message);
	}

}
