package mil.dtic.cbes.model.enums.core;

public enum BudgetActivityStatusFlag {
	A, I;
	
    public static final BudgetActivityStatusFlag ACTIVE = A;
    public static final BudgetActivityStatusFlag INACTIVE = I;

    public boolean isActive() {
        return this == A;
    }
    public boolean isInactive() {
        return this == I;
    }


}
