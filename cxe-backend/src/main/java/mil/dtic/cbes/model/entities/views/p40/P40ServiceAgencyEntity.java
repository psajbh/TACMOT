package mil.dtic.cbes.model.entities.views.p40;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;

@Entity
@Table(name="v_p40_service_agencies")
public class P40ServiceAgencyEntity implements IEntity, Serializable {
	private static final long serialVersionUID = 5527383780450543314L;
	
	@Id
	@Column(name="SA_ID")
	private Integer serviceAgencyId;
	@Column(name="SA_CODE")
	private String code;
	@Column(name="SA_NAME")
	private String name;
	public Integer getServiceAgencyId() {
		return serviceAgencyId;
	}
	public void setServiceAgencyId(Integer serviceAgencyId) {
		this.serviceAgencyId = serviceAgencyId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((serviceAgencyId == null) ? 0 : serviceAgencyId.hashCode());
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
		P40ServiceAgencyEntity other = (P40ServiceAgencyEntity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (serviceAgencyId == null) {
			if (other.serviceAgencyId != null)
				return false;
		} else if (!serviceAgencyId.equals(other.serviceAgencyId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "P40ServiceAgencyEntity [serviceAgencyId=" + serviceAgencyId + ", code=" + code + ", name=" + name + "]";
	}
	
	


}
