package mil.dtic.cbes.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//

@Entity
@Table(name="V_USER_CREDENTIALS" )
public class UserCredentialsEntity implements Serializable{
    private static final long serialVersionUID = 2373823681907374399L;
    
    @Id
    @Column(name="BUDGES_USER_ID")
    private Integer userId;
    
    @Column(name="USER_LDAP_ID")
    private String ldapId;
    
    @Column(name="USER_ROLE")
    private String userRole;
    
    @Column(name="CREATE_PE_PRIV")
    private String create_pe_priv;
    
    @Column(name="CREATE_LI_PRIV")
    private String create_li_priv;
    
    @Column(name="AGENCIES")
    private String agency;
    
    @Column(name="ROLE_ID")
    private String role_id;
    
    @Column(name="ROLE_NAME")
    private String role_name;

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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
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

}
