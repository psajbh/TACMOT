package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class CxeUsernameNotFoundException extends UsernameNotFoundException {
	private static final long serialVersionUID = 549437740454616371L;
	
	public static final String INVALID_USER_CREDENTIAL_MSG = "Invalid Credentials";
	public static final String USER_NO_ROLES_FAILURE = "No associated roles";
	public static final String USER_NOT_FOUND_FAILURE = "Failed to authenticate as a CXE user";


	public CxeUsernameNotFoundException(String msg) {
		super(msg);
	}

}
