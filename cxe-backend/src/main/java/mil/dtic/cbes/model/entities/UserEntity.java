package mil.dtic.cbes.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import mil.dtic.cbes.model.enums.StatusFlag;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="USER" )
public class UserEntity implements IEntity, Serializable, UserDetails{
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BUDGES_USER_ID")
    private Integer id;
    
    @Column(name="USER_LDAP_ID")
    private String userLdapId;

    @Column(name="FULL_NAME")
    private String fullName;
    
    @Column(name="FIRST_NAME")
    private String firstName;
    
    @Column(name="MIDDLE_INITIAL")
    private String middleInitial;
    
    @Column(name="LAST_NAME")
    private String lastName;
    
    @Column(name="PHONE_NUM")
    private String phoneNum;
    
    @Column(name="EMAIL")
    private String email;
    
    @Column(name="ROLE")
    private String role;
    
    @ManyToMany
    @JoinTable(name="USER_SERVICE_AGENCY",
    joinColumns = @JoinColumn(name="BUDGES_USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "BUDGES_SERV_AGY_ID"))
    private Set<ServiceAgencyEntity> serviceAgencies;
    
    @Column(name="CREATE_PE_PRIV")
    private boolean createPeAllowed;
    
    @Column(name="CREATE_LI_PRIV")
    private boolean createLiAllowed;
    
    @Enumerated(EnumType.STRING)
    @Column(name="STATUS_FLAG", length=1)
    private StatusFlag statusFlag;   

    //TODO: determine if these are necessary, then implement
    private transient Date lastVisitDate;
    private transient Date lastAccessedTime;
    private transient Date savedLastVisitDate;
    private transient String sessionId; // only used in AdminToolsPage
    private transient String csrfToken;
    
    //TODO: determine if these are going to work.
    private transient List<GrantedAuthority> authorities;
    private transient String password;
    private transient String username = "bullshit";
    private transient boolean accountNonExpired;
    private transient boolean accountNonLocked;
    private transient boolean credentialsNonExpired;
    private transient boolean enabled;
    
    public void setUserLdapId(String userLdapId) {
        this.userLdapId = userLdapId;
        setUsername(userLdapId);
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = "bullshit";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
