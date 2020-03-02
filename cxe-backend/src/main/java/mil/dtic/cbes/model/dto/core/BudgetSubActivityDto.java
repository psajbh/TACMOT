package mil.dtic.cbes.model.dto.core;


import mil.dtic.cbes.model.dto.Dto;

public class BudgetSubActivityDto extends Dto {
	private Integer id;
	private Integer num;
	private String name;
	private BudgetActivityDto budgetActivityDto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public BudgetActivityDto getBudgetActivityDto() {
		return budgetActivityDto;
	}
	public void setBudgetActivityDto(BudgetActivityDto budgetActivityDto) {
		this.budgetActivityDto = budgetActivityDto;
	}

}
