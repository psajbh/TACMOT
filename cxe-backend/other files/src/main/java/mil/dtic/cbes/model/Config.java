package mil.dtic.cbes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.cbes.model.enums.ConfigTypeFlag;

@Entity
@Table(name="config")
public class Config {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cf_ID")
	private Integer id;
	
	@Column(name="cf_name")
	private String name;
	
	@Column(name="cf_value")
	private String value;
	
	@Enumerated(EnumType.STRING)
	@Column(name="cf_type")
	private ConfigTypeFlag valueType;
	
	@Column(name="cf_desc")
	private String description;
	
	@Column(name="cf_env")
	private String env;
	
	@Column(name="cf_cycle")
	private String budgetCycle;
	
	@Column(name="cf_year")
	private Integer budgetYear;
	
	@Column(name="cf_read_only")
	private boolean readOnly;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ConfigTypeFlag getValueType() {
		return valueType;
	}

	public void setValueType(ConfigTypeFlag valueType) {
		this.valueType = valueType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getBudgetCycle() {
		return budgetCycle;
	}

	public void setBudgetCycle(String budgetCycle) {
		this.budgetCycle = budgetCycle;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
