package mil.dtic.cbes.service;

import java.util.List;

public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = -7660731869218621676L;
	
	private List<ValidationMessage> errors;
	
	public ValidationException(List<ValidationMessage> errors) {
		super();
		this.errors = errors;
	}
	
	public ValidationException(String message, List<ValidationMessage> errors) {
		super(message);
		this.errors = errors;
	}
	
	public ValidationException(String message, Throwable cause, List<ValidationMessage> errors) {
		super(message, cause);
		this.errors = errors;
	}
	
	public ValidationException(Throwable cause, List<ValidationMessage> errors) {
		super(cause);
		this.errors = errors;
	}
	
	public List<ValidationMessage> getErrors() {
		return errors;
	}

}
