package mil.dtic.cbes.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    GROUP_R2_APP_ADMIN("R2AppMgr"),
    GROUP_R2_SITEADMIN("R2SiteAdmin"),
    GROUP_R2_LOCALSITEADMIN("R2LocalSiteAdmin"),
    GROUP_R2_USER("R2User"),
    GROUP_NONE("No R2 group in LDAP"),
    GROUP_R2_ANALYST("R2Analyst");
	
	private String name;
	
	private static Map<String, Role> nameMap = new HashMap<>(); 
	
	static {
		for(Role r : values()) {
			nameMap.put(r.getName(), r);
		}
	}
	
	private Role(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Role getByName(String name) {
		return nameMap.get(name);
	}
}
