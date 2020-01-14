package mil.dtic.cbes.model.dto;

import java.io.Serializable;
import java.util.Set;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;

//@ToString
//@NoArgsConstructor
//@Setter
//@Getter
public class ServiceAgencyDto implements IDto, Serializable, Cloneable{
    
    private Integer id;
    private String code;
    private String name;
    private Set<UserDto> users;
    
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    
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
    
    //--------------------
    

}
