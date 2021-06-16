/**
 * 
 */
package courses.sov.petclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dcividin
 *
 */
public class Owner extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3102221271274397930L;
	
	private String address;
	private String city;
	private String telephone;
	private Set<Pet> pets = new HashSet<>();
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the pets
	 */
	public Set<Pet> getPets() {
		return pets;
	}
	/**
	 * @param pets the pets to set
	 */
	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
	/**
	 * 
	 * @param e
	 */
	public void addPet(Pet e) {
		if(this.pets == null) {
			this.pets = new HashSet<>();
		}
		
		this.pets.add(e);
	}
}
