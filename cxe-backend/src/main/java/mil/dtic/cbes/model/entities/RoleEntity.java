package mil.dtic.cbes.model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="role")
public class RoleEntity implements IEntity, Serializable{
    private static final long serialVersionUID = 1395299267622214202L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer id;
    
    @Column(name="role_name")
    private String roleName;

    @Column(name="role_desc")
    private String roleDesc;
    
    @Column(name="CREATED_BY_USER")
    private String createdByUser;
    
    @Column(name="DATE_CREATED")
    private Date dateCreated;
    
    @Column(name="MODIFIED_BY_USER")
    private String modifiedByUser;
    
    @Column(name="DATE_MODIFIED")
    private Date dateModified;
    
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<UserEntity> userEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public List<UserEntity> getUserEnties() {
        return userEntities;
    }

}
