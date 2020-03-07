package mil.dtic.cbes.model.enums.exhibit.r2.programelement;

public enum PeSubmissionStatusFlag {
	A, V, W, E;

	public static final PeSubmissionStatusFlag NONE = A;
	public static final PeSubmissionStatusFlag VALID = V;
	public static final PeSubmissionStatusFlag INVALID = W;
	public static final PeSubmissionStatusFlag ERRORS = E;

	public boolean isValid() {
		return this == VALID;
	}

	public boolean isInvalid() {
		return this == INVALID;
	}

	public boolean hasErrors() {
		return this == ERRORS;
	}
}
