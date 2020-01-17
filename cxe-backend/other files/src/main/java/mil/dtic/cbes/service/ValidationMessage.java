package mil.dtic.cbes.service;

public interface ValidationMessage {
	public final static int SEVERITY_ERROR = 0;
	public final static int SEVERITY_WARNING = 1;
	public final static int NO_VALUE = -1;

	public String getErrorCode();

	public int getSeverity();

	public String getMessage();

	public int getLineNum();

	public int getColNum();

}
