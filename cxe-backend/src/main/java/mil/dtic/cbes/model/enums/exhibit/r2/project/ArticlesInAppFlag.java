package mil.dtic.cbes.model.enums.exhibit.r2.project;


public enum ArticlesInAppFlag {
	
	 Y, N;

    public static final ArticlesInAppFlag YES = Y;
    public static final ArticlesInAppFlag NO = N;

    public boolean isYes() {
        return this == Y;
    }
    public boolean isNo() {
        return this == N;
    }


}
