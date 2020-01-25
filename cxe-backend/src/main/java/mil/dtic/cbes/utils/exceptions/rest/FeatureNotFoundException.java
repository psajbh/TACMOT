package mil.dtic.cbes.utils.exceptions.rest;

import mil.dtic.cbes.utils.exceptions.CxeException;

public class FeatureNotFoundException extends CxeException {
	private static final long serialVersionUID = 2949141913285952146L;
	
	public FeatureNotFoundException(String msg) {
		super(msg);
	}

}
