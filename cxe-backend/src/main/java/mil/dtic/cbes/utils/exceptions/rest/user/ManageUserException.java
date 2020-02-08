package mil.dtic.cbes.utils.exceptions.rest.user;

import mil.dtic.cbes.utils.exceptions.CxeException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ManageUserException extends CxeException {
	private static final long serialVersionUID = -5115760793212635821L;
	
	public static final String UPDATE_USER_DTO_NULL = "dto object is null";
	public static final String UPDATE_USER_FAILED_TO_PROCESS = "update processing failure";
	public static final String UPDATE_USER_INVALID_LDAPID = "dto object ldapId is invalid";
	public static final String DELETE_USER_FAILURE = "failed to delete user";
	

	public ManageUserException(String msg) {
		super(msg);
	}

}
