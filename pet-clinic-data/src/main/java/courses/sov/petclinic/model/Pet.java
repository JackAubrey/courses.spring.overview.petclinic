/**
 * 
 */
package courses.sov.petclinic.model;

import java.time.LocalDate;

/**
 * @author dcividin
 *
 */
public class Pet extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848317630792706665L;

	private LocalDate birthDate;
	
	private PetType type;
	
	private Owner owner;
	
	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the type
	 */
	public PetType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(PetType type) {
		this.type = type;
	}
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
