package mil.dtic.cbes.model.enums.exhibit.r2.programelement;

public enum PeTestFlag {

	 Y, N;

    public static final PeTestFlag YES = Y;
    public static final PeTestFlag NO = N;

    public boolean isYes() {
        return this == Y;
    }
    public boolean isNo() {
        return this == N;
    }
}
