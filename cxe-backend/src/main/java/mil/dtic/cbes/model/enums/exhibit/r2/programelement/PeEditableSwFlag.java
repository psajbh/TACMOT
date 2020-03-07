package mil.dtic.cbes.model.enums.exhibit.r2.programelement;

public enum PeEditableSwFlag {
	
	T, F;
	
	public static final PeEditableSwFlag TRUE = T;
	public static final PeEditableSwFlag FALSE = F;
	
	public boolean isTrue() {
		return this==T;
	}
	
	public boolean isNo() {
		return this==F;
	}

}
