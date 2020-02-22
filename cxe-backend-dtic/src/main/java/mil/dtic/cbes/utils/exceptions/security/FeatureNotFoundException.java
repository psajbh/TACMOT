package mil.dtic.cbes.utils.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mil.dtic.cbes.utils.exceptions.CxeException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FeatureNotFoundException extends CxeException {
	private static final long serialVersionUID = 2949141913285952146L;
	public static final String FEATURE_NOT_SUPPORTED = "Feature not supported";
	
	public FeatureNotFoundException(String msg) {
		super(msg);
	}

}
