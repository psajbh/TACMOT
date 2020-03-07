package mil.dtic.cbes.utils.exceptions.service.exhibit.r2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProgramElementServiceException extends CxeException {
	private static final long serialVersionUID = 6537713279392094615L;
	
	public static final String PG_ELEM_UN_BUDGES_PE_NUM_BUDGET_CYCLE = "UN_BUDGES_PE_NUM_BUDGET_CYCLE";
	public static final String PGM_ELEM_EXCEPTION_DUP_KEY = "duplicate key constraint failure";
	public static final String PGM_ELEM_EXCEPTION_DATA_INEGRITY = "data integrity failure";
	public static final String PGM_ELEM_EXCEPTION_GENERIC = "generic system failure";
	public static final String PGM_ELEM_PROCESS_FAILURE = "failed to create program element";
	public static final String PGM_ELEM_USER_VALIDATION_FAILURE = "failed to validate user";
	

	public ProgramElementServiceException(String msg) {
		super(msg);
	}

}
