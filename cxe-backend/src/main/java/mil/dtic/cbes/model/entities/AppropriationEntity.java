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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="APPROP_ACCOUNT")
public class AppropriationEntity implements IEntity, Serializable {
	private static final long serialVersionUID = -1094433604961461682L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BUDGES_APPROP_ACCT_ID")
	private Integer id;
	
	@NotNull
	@Column(name="BUDGES_APPROP_ACCT_CODE")
	private String code;
	
	@NotNull
	@Column(name="BUDGES_APPROP_ACCT_NAME")
	private String name;
		
    @ManyToMany
    @JoinTable(name="service_agency_acct", 
    joinColumns = @JoinColumn(name="aa_ID"), 
    inverseJoinColumns = @JoinColumn(name="sa_ID")) 
    private List<ServiceAgencyEntity> serviceAgencies;

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
		AppropriationEntity other = (AppropriationEntity) obj;
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

//	public List<ServiceAgencyEntity> getServiceAgencies() {
//		return serviceAgencies;
//	}
//
//	public void setServiceAgencies(List<ServiceAgencyEntity> serviceAgencies) {
//		this.serviceAgencies = serviceAgencies;
//	}
	
	

}
