package mil.dtic.cbes.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import mil.dtic.cbes.model.enums.StatusFlag;

@Entity
@Table(name="USER" )
public class UserEntity implements IEntity, Serializable {
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
    
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserLdapId() {
        return userLdapId;
    }

    public void setUserLdapId(String userLdapId) {
        this.userLdapId = userLdapId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<ServiceAgencyEntity> getServiceAgencies() {
        return serviceAgencies;
    }

    public void setServiceAgencies(Set<ServiceAgencyEntity> serviceAgencies) {
        this.serviceAgencies = serviceAgencies;
    }

    public boolean isCreatePeAllowed() {
        return createPeAllowed;
    }

    public void setCreatePeAllowed(boolean createPeAllowed) {
        this.createPeAllowed = createPeAllowed;
    }

    public boolean isCreateLiAllowed() {
        return createLiAllowed;
    }

    public void setCreateLiAllowed(boolean createLiAllowed) {
        this.createLiAllowed = createLiAllowed;
    }

    public StatusFlag getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(StatusFlag statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public Date getSavedLastVisitDate() {
        return savedLastVisitDate;
    }

    public void setSavedLastVisitDate(Date savedLastVisitDate) {
        this.savedLastVisitDate = savedLastVisitDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    
    
}
