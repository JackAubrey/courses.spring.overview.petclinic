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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dcividin
 *
 */
@Getter
@Setter
@NoArgsConstructor
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
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param telephone
	 * @param pets
	 */
	@Builder
	public Owner(Long id, @NotBlank(message = "First Name may not be blank") String firstName,
			@NotBlank(message = "Last Name may not be blank") String lastName, String address, String city,
			@NotBlank(message = "Telephone may not be blank") String telephone, Set<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		this.pets = pets;
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
		if(e != null) {
			e.setOwner(this);
			this.pets.add(e);
		}
	}
}
