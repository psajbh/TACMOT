package mil.dtic.cbes.model.dto.user;

import java.security.Principal;
import javax.security.auth.Subject;

import mil.dtic.cbes.model.dto.Dto;

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
        return false;  //not implementing this yet, if ever.
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((ldapId == null) ? 0 : ldapId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((strAgencies == null) ? 0 : strAgencies.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + (valid ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserCredentialDto other = (UserCredentialDto) obj;
        if (ldapId == null) {
            if (other.ldapId != null)
                return false;
        } else if (!ldapId.equals(other.ldapId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (roleName == null) {
            if (other.roleName != null)
                return false;
        } else if (!roleName.equals(other.roleName))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (strAgencies == null) {
            if (other.strAgencies != null)
                return false;
        } else if (!strAgencies.equals(other.strAgencies))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (userRole == null) {
            if (other.userRole != null)
                return false;
        } else if (!userRole.equals(other.userRole))
            return false;
        if (valid != other.valid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserCredentialDto [name=" + name + ", userId=" + userId + ", ldapId=" + ldapId + ", userRole=" + userRole + ", strAgencies="
                + strAgencies + ", roleId=" + roleId + ", roleName=" + roleName + ", status=" + status + ", valid=" + valid + "]";
    }

    
    
}
