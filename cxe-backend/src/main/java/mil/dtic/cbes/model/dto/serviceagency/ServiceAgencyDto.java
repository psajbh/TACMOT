package mil.dtic.cbes.model.dto.serviceagency;

import java.util.List;

import mil.dtic.cbes.model.dto.AppropriationDto;
import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.user.UserDto;

public class ServiceAgencyDto extends Dto {
    
    private Integer id;
    private String code;
    private String name;
    private List<UserDto> users;
    private List<AppropriationDto> appropriations;
    
    public ServiceAgencyDto() {}
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<UserDto> getUsers() {
        return users;
    }
    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
	public List<AppropriationDto> getAppropriations() {
		return appropriations;
	}
	public void setAppropriations(List<AppropriationDto> appropriations) {
		this.appropriations = appropriations;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ServiceAgencyDto other = (ServiceAgencyDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ServiceAgencyDto [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
    
    
}
