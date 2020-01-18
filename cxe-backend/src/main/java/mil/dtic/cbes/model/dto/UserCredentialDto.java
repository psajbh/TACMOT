package mil.dtic.cbes.model.dto;

import java.security.Principal;
import javax.security.auth.Subject;


public class UserCredentialDto extends Dto implements Principal{
    
    public static final String GROUP_R2_APP_ADMIN = "R2AppMgr";
    public static final String GROUP_R2_SITEADMIN = "R2SiteAdmin";
    public static final String GROUP_R2_LOCALSITEADMIN = "R2LocalSiteAdmin";
    public static final String GROUP_R2_USER = "R2User";
    public static final String GROUP_NONE = "No R2 group in LDAP";
    public static final String GROUP_R2_ANALYST = "R2Analyst";
    
    private String name;
    private Integer userId;
    private String ldapId;
    private String userRole;
    private String strAgencies;
    private String roleId;
    private String roleName;
    private String status;
    private boolean valid;
    
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getLdapId() {
        return ldapId;
    }
    
    public void setLdapId(String ldapId) {
        this.ldapId = ldapId;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public String getRoleId() {
        return roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getStrAgencies() {
        return strAgencies;
    }
    
    public void setStrAgencies(String strAgencies) {
        this.strAgencies = strAgencies;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    @Override
    public boolean implies(Subject subject) {
        return false;  //where not implementing this yet, if ever.
    }
    
    
    


}
