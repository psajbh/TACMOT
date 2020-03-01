package mil.dtic.cbes.model.entities.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.enums.BooleanFlag;
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
    @JoinTable(name="USER_SERVICE_AGENCY", //join table
    joinColumns = @JoinColumn(name="BUDGES_USER_ID"), //join table column
    inverseJoinColumns = @JoinColumn(name = "BUDGES_SERV_AGY_ID")) //SERVICE_AGENCY PK
    private List<ServiceAgencyEntity> serviceAgencies;
    
    @Enumerated(EnumType.STRING)
    @Column(name="CREATE_PE_PRIV")
    private BooleanFlag createPeAllowed;
    
    @Enumerated(EnumType.STRING)
    @Column(name="CREATE_LI_PRIV")
    private BooleanFlag createLiAllowed;
    
    @Enumerated(EnumType.STRING)
    @Column(name="STATUS_FLAG", length=1)
    private StatusFlag statusFlag;   

    //TODO: determine if these are necessary, then implement
    private transient Date lastVisitDate;
    private transient Date lastAccessedTime;
    private transient Date savedLastVisitDate;
    private transient String sessionId; // only used in AdminToolsPage
    private transient String csrfToken;
    
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

    public List<ServiceAgencyEntity> getServiceAgencies() {
        return serviceAgencies;
    }

    public void setServiceAgencies(List<ServiceAgencyEntity> serviceAgencies) {
        this.serviceAgencies = serviceAgencies;
    }

    public BooleanFlag isCreatePeAllowed() {
        return createPeAllowed;
    }

    public void setCreatePeAllowed(BooleanFlag createPeAllowed) {
        this.createPeAllowed = createPeAllowed;
    }

    public BooleanFlag isCreateLiAllowed() {
        return createLiAllowed;
    }

    public void setCreateLiAllowed(BooleanFlag createLiAllowed) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createLiAllowed == null) ? 0 : createLiAllowed.hashCode());
        result = prime * result + ((createPeAllowed == null) ? 0 : createPeAllowed.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
        result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((statusFlag == null) ? 0 : statusFlag.hashCode());
        result = prime * result + ((userLdapId == null) ? 0 : userLdapId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserEntity other = (UserEntity) obj;
        if (createLiAllowed != other.createLiAllowed)
            return false;
        if (createPeAllowed != other.createPeAllowed)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (middleInitial == null) {
            if (other.middleInitial != null)
                return false;
        } else if (!middleInitial.equals(other.middleInitial))
            return false;
        if (phoneNum == null) {
            if (other.phoneNum != null)
                return false;
        } else if (!phoneNum.equals(other.phoneNum))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (statusFlag != other.statusFlag)
            return false;
        if (userLdapId == null) {
            if (other.userLdapId != null)
                return false;
        } else if (!userLdapId.equals(other.userLdapId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", userLdapId=" + userLdapId + ", fullName=" + fullName + ", firstName=" + firstName + ", middleInitial="
                + middleInitial + ", lastName=" + lastName + ", phoneNum=" + phoneNum + ", email=" + email + ", role=" + role + ", "
                + "createPeAllowed=" + createPeAllowed + ", createLiAllowed=" + createLiAllowed + ", statusFlag=" + statusFlag + "]";
    }

    
    
    
}
