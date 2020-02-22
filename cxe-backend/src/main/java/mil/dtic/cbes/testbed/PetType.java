package mil.dtic.cbes.testbed;

public class PetType extends BaseEntity {
	
	private String name;
	
	public PetType(int id, String name) {
		super(id);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public String toString() {
        return name;
    }

}
