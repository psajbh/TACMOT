package mil.dtic.cbes.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum PRCPType {
	P1("P1", "_P1", 1),
	R1("R1", "_R1", 2);
	
	private String val;
	private String filter;
	
	private int type;
	
	private static final Map<Integer, PRCPType> typeLookupMap = new HashMap<Integer, PRCPType>();
	
	static {
		for(PRCPType p : PRCPType.values()) {
			typeLookupMap.put(p.getType(), p);
		}
	}
	
	private PRCPType(String val, String filter, int type) {
		this.val = val;
		this.filter = filter;
		this.type = type;
	}
	
	public String getVal() {
		return this.val;
	}
	
	public String getFilter() {
		return this.filter;
	}
	
	public int getType() {
		return this.type;
	}
	
	public static PRCPType getByType(int type) {
		return typeLookupMap.get(type);
	}
	
	public String toString() {
		return this.val;
	}
}
