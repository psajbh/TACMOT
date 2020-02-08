package mil.dtic.cbes.model.enums;

public enum BooleanFlag {

	 Y, N;

    public static final BooleanFlag YES = Y;
    public static final BooleanFlag NO = N;

    public boolean isYes() {
        return this == Y;
    }
    public boolean isNo() {
        return this == N;
    }
}
