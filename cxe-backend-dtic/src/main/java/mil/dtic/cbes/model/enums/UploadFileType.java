package mil.dtic.cbes.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum UploadFileType {
	SYS("SYS", "SYS_", 1),
	PRCP("PRCP", "PRCP_", 2);

	private String val;
	private String filter;

	private int type;

	private static final Map<Integer, UploadFileType> typeLookupMap = new HashMap<Integer, UploadFileType>();

	static {
		for (UploadFileType p : UploadFileType.values()) {
			typeLookupMap.put(p.getType(), p);
		}
	}

	private UploadFileType(String val, String filter, int type) {
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

	public static UploadFileType getByType(int type) {
		return typeLookupMap.get(type);
	}

	public String toString() {
		return this.val;
	}
}
