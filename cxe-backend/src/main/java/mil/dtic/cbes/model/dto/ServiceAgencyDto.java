package mil.dtic.cbes.model.dto;

import java.util.Set;

public class ServiceAgencyDto extends Dto {
    
    private Integer id;
    private String code;
    private String name;
    private Set<UserDto> users;
    
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
    public Set<UserDto> getUsers() {
        return users;
    }
    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
