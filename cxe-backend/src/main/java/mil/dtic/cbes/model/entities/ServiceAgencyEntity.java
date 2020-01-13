package mil.dtic.cbes.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//@Setter
//@Getter
//@NoArgsConstructor
@Entity
@Table(name="SERVICE_AGENCY" )
public class ServiceAgencyEntity implements IEntity, Serializable{
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
    private Set<UserEntity> users;
    
//    public Object clone() throws CloneNotSupportedException{
//        return super.clone();
//    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
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
    

    
    
    

}
