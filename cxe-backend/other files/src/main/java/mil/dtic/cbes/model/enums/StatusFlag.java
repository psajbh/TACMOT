package mil.dtic.cbes.model.enums;

public enum StatusFlag {

    A, I, D, N;

    public static final StatusFlag ACTIVE = A;
    public static final StatusFlag INACTIVE = I;
    public static final StatusFlag DELETED = D;
    public static final StatusFlag NEW = N;

    public boolean isActive() {
        return this == A;
    }
    public boolean isInactive() {
        return this == I;
    }
    public boolean isDeleted() {
        return this == D;
    }
    public boolean isNew() {
        return this == N;
    }

}
