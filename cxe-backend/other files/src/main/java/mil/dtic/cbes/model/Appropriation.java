package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Appropriation")
public class Appropriation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String appropriationName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppropriationName() {
		return appropriationName;
	}

	public void setAppropriationName(String appropriationName) {
		this.appropriationName = appropriationName;
	}

	
}
