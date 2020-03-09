package mil.dtic.cbes.model.enums.exhibit.r2.project;

public enum ProjectIncludeInPdfFlag {
	 Y, N;

    public static final ProjectIncludeInPdfFlag YES = Y;
    public static final ProjectIncludeInPdfFlag NO = N;

    public boolean isYes() {
        return this == Y;
    }
    public boolean isNo() {
        return this == N;
    }
	

}
