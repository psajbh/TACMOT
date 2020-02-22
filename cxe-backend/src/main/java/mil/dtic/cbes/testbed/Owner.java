package mil.dtic.cbes.testbed;

import java.util.HashSet;
import java.util.Set;

public class Owner extends Person {
	
	private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets = new HashSet<>();
	
	public Owner(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
	
	

}
