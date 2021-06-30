/**
 * 
 */
package courses.sov.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author dcividin
 *
 */
@Entity
@Table(name = "owners")
public class Owner extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3102221271274397930L;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "telephone")
	@NotBlank(message = "Telephone may not be blank")
	private String telephone;
	
	// one owner may have many pets mapped by the pet's owner property
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
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
		this.pets = pets == null ? new HashSet<>() : new HashSet<>(pets);
	}
	/**
	 * 
	 * @param e
	 */
	public void addPet(Pet e) {
		if(this.pets == null) {
			this.pets = new HashSet<>();
		}
		
		if(e != null) {
			e.setOwner(this);
			this.pets.add(e);
		}
	}
}
