package mil.dtic.cbes.model.dto.core;

import mil.dtic.cbes.model.dto.Dto;

public class PeSuffixDto extends Dto {
	private String peSuffix;
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
	
}
