/**
 * 
 */
package courses.sov.petclinic.model;

import java.util.Set;

/**
 * @author dcividin
 *
 */
public class Vet extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6155651241409836039L;

	private Set<Specialty> specialties;

	/**
	 * @return the specialties
	 */
	public Set<Specialty> getSpecialties() {
		return specialties;
	}

	/**
	 * @param specialties the specialties to set
	 */
	public void setSpecialties(Set<Specialty> specialties) {
		this.specialties = specialties;
	}
}
