package mil.dtic.cbes.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import mil.dtic.cbes.model.enums.StatusFlag;

public class UserDto implements IDto , Serializable, Cloneable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String userLdapId;
    private String fullName;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String phoneNum;
    private String email;
    private String role;
    private Set<ServiceAgencyDto> serviceAgencies;
    private boolean createPeAllowed;
    private boolean createLiAllowed;
    private StatusFlag statusFlag;
    private String message;
    private String httpSessionId;
    
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
    public Set<ServiceAgencyDto> getServiceAgencies(){
        if(null == serviceAgencies) {
            return new HashSet<ServiceAgencyDto>();
        }
        return serviceAgencies;
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

    public void setServiceAgencies(Set<ServiceAgencyDto> serviceAgencies) {
        this.serviceAgencies = serviceAgencies;
    }


    @Override
	public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String getHttpSessionId() {
		return httpSessionId;
	}

    @Override
	public void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}
    
    
    
    

}
