package mil.dtic.cbes.model.dto.core;

import java.util.ArrayList;
import java.util.List;

import mil.dtic.cbes.model.dto.R2BudgetActivityDto;
import mil.dtic.cbes.model.dto.Dto;

public class AppropriationDto extends Dto {

	private Integer id;
	private String code;
	private String name;
	private List<ServiceAgencyDto> serviceAgencies;
	private List<R2BudgetActivityDto> r2BudgetActivities;
	
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
	public List<R2BudgetActivityDto> getBudgetActivities() {
		if (null == r2BudgetActivities) {
			r2BudgetActivities = new ArrayList<R2BudgetActivityDto>();
		}
		return r2BudgetActivities;
	}
	public void setBudgetActivities(List<R2BudgetActivityDto> budgetActivities) {
		this.r2BudgetActivities = budgetActivities;
	}
	
	
	
}
