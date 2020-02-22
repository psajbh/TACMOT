package mil.dtic.cbes.model.dto.user;

import java.util.ArrayList;
import java.util.List;

import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.serviceagency.ServiceAgencyDto;
import mil.dtic.cbes.model.enums.StatusFlag;

public class UserDto extends Dto {
    
    private Integer id;
    private String userLdapId;
    private String fullName;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String phoneNum;
    private String email;
    private String role;
    private List<ServiceAgencyDto> serviceAgencies;
    private boolean createPeAllowed;
    private boolean createLiAllowed;
    private StatusFlag statusFlag;

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
    
    public List<ServiceAgencyDto> getServiceAgencies(){
        if(null == serviceAgencies) {
            serviceAgencies = new ArrayList<ServiceAgencyDto>();
        }
        return serviceAgencies;
    }

    public void setServiceAgencies(List<ServiceAgencyDto> serviceAgencies) {
        this.serviceAgencies = serviceAgencies;
    }
    
    @Override
    public String toString() {
        return "UserDto [id=" + id + ", userLdapId=" + userLdapId + ", fullName=" + fullName + ", firstName=" + firstName + ", middleInitial="
                + middleInitial + ", lastName=" + lastName + ", phoneNum=" + phoneNum + ", email=" + email + ", role=" + role + ", serviceAgencies="
                + serviceAgencies + ", createPeAllowed=" + createPeAllowed + ", createLiAllowed=" + createLiAllowed + ", statusFlag=" + statusFlag
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (createLiAllowed ? 1231 : 1237);
        result = prime * result + (createPeAllowed ? 1231 : 1237);
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDto other = (UserDto) obj;
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
    

}
