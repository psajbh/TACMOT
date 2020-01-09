package mil.dtic.cbes.model.dto;

import java.util.ArrayList;
import java.util.List;


public class UserCredential {
    
    public static final String GROUP_R2_APP_ADMIN = "R2AppMgr";
    public static final String GROUP_R2_SITEADMIN = "R2SiteAdmin";
    public static final String GROUP_R2_LOCALSITEADMIN = "R2LocalSiteAdmin";
    public static final String GROUP_R2_USER = "R2User";
    public static final String GROUP_NONE = "No R2 group in LDAP";
    public static final String GROUP_R2_ANALYST = "R2Analyst";
    
    private Integer userId;
    private String ldapId;
    private String userRole;
    private String create_pe_priv;
    private String create_li_priv;
    private List<String> agencies;
    private String strAgencies;
    private String role_id;
    private String role_name;
    private String status;
    private boolean valid;
    
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
    public String getCreate_pe_priv() {
        return create_pe_priv;
    }
    public void setCreate_pe_priv(String create_pe_priv) {
        this.create_pe_priv = create_pe_priv;
    }
    public String getCreate_li_priv() {
        return create_li_priv;
    }
    public void setCreate_li_priv(String create_li_priv) {
        this.create_li_priv = create_li_priv;
    }
    
    public String getRole_id() {
        return role_id;
    }
    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
    public String getRole_name() {
        return role_name;
    }
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
    public List<String> getAgencies() {
        if (null == agencies) {
            return new ArrayList<>();
        }
        return agencies;
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
    
    


}
