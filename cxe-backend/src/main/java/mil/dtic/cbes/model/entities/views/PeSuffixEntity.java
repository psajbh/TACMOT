package mil.dtic.cbes.model.entities.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.entities.IEntity;

@Entity
@Table(name="v_pe_suffix")
public class PeSuffixEntity implements IEntity, Serializable{
	private static final long serialVersionUID = 2519060245765523065L;

	@Id
	@Column(name="PS_SUFFIX")
	private String peSuffix;
	
	@Column(name="PS_SERV_AGY_ID")
	private Integer serviceAgencyId;

	public String getPeSuffix() {
		return peSuffix;
	}

	public void setPeSuffix(String peSuffix) {
		this.peSuffix = peSuffix;
	}

	public Integer getServiceAgencyId() {
		return serviceAgencyId;
	}

	public void setServiceAgencyId(Integer serviceAgencyId) {
		this.serviceAgencyId = serviceAgencyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((peSuffix == null) ? 0 : peSuffix.hashCode());
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
		PeSuffixEntity other = (PeSuffixEntity) obj;
		if (peSuffix == null) {
			if (other.peSuffix != null)
				return false;
		} else if (!peSuffix.equals(other.peSuffix))
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
		return "PeSuffixEntity [peSuffix=" + peSuffix + ", serviceAgencyId=" + serviceAgencyId + "]";
	}
	

}
