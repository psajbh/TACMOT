package mil.dtic.cbes.utils.exceptions.rest;

public class BudgetFileProcessingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public static final String UPLOAD_NOT_FOUND = "Download file was not present in the database.";
	public static final String FILE_NOT_FOUND = "Download file was not found on system.";
	public static final String FILE_CREATION = "Unable to generate download file.";
	public static final String FILE_OPEN_ERROR = "Unable to get user uploaded file";
	public static final String SAVE_ERROR = "Error saving file to system.";
	
	public BudgetFileProcessingException(String message){
		super(message);
	}
	
	public BudgetFileProcessingException(String message, Throwable cause){
		super(message, cause);
	}
}
