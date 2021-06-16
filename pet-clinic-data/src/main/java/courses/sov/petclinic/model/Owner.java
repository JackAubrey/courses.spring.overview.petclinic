/**
 * 
 */
package courses.sov.petclinic.model;

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

	private Set<Pet> pets;

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
}
