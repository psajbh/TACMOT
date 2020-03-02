package mil.dtic.cbes.model.dto.core;

import mil.dtic.cbes.model.dto.Dto;
import mil.dtic.cbes.model.dto.exhibit.r2.R2AppropriationDto;
import mil.dtic.cbes.model.enums.StatusFlag;

public class BudgetActivityDto extends Dto {
	private Integer id;
	private AppropriationDto appropriationDto;
	private Integer num;
	private String name;
	private StatusFlag status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AppropriationDto getAppropriationDto() {
		return appropriationDto;
	}
	public void setAppropriationDto(R2AppropriationDto appropriationDto) {
		this.appropriationDto = appropriationDto;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StatusFlag getStatus() {
		return status;
	}
	public void setStatus(StatusFlag status) {
		this.status = status;
	}

}
