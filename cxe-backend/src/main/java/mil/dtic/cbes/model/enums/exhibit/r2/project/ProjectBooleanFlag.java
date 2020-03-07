package mil.dtic.cbes.model.enums.exhibit.r2.project;


public enum ProjectBooleanFlag {
	
	 Y, N;

    public static final ProjectBooleanFlag YES = Y;
    public static final ProjectBooleanFlag NO = N;

    public boolean isYes() {
        return this == Y;
    }
    public boolean isNo() {
        return this == N;
    }


}
