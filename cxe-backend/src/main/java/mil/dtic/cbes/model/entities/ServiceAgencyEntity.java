package mil.dtic.cbes.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="SERVICE_AGENCY" )
public class ServiceAgencyEntity implements IEntity, Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BUDGES_SERV_AGY_ID")
    private Integer id;
    
    @Column(name="BUDGES_SERV_AGY_CODE")
    private String code;
    
    @Column(name="SERV_AGY_NAME")
    private String name;
    
    @ManyToMany(mappedBy = "serviceAgencies")
    private List<UserEntity> users;
    
    @ManyToMany(mappedBy = "serviceAgencies")
    private List<AppropriationEntity> appropriations;
    
    public List<AppropriationEntity> getAppropriations() {
		return appropriations;
	}

	public void setAppropriations(List<AppropriationEntity> appropriations) {
		this.appropriations = appropriations;
	}

	public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        ServiceAgencyEntity other = (ServiceAgencyEntity) obj;
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
        return "ServiceAgencyEntity [id=" + id + ", code=" + code + ", name=" + name + "]";
    }
    
    
}
