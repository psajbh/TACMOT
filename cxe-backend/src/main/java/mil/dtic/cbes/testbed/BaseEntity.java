package mil.dtic.cbes.testbed;

public abstract class BaseEntity {
	
	public int id;
	
	public BaseEntity(Integer id) {
		this.id =  id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
