package mil.dtic.cbes.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceAgencyDto implements IDto, Serializable {
    
	private static final long serialVersionUID = 1915736156650399450L;
	
	private Integer id;
    private String code;
    private String name;
    private Set<UserDto> users;
    private String message;
    private String httpSessionId;
    
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
