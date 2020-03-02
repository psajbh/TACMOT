package mil.dtic.cbes.model.dto.core;

import java.util.List;

import mil.dtic.cbes.model.dto.Dto;

public class AppropriationDto extends Dto{
	
	private Integer id;
	private String code;
	private String name;
	private List<ServiceAgencyDto> serviceAgencies;
	
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
	public List<ServiceAgencyDto> getServiceAgencies() {
		return serviceAgencies;
	}
	public void setServiceAgencies(List<ServiceAgencyDto> serviceAgencies) {
		this.serviceAgencies = serviceAgencies;
	}
	
	


}
