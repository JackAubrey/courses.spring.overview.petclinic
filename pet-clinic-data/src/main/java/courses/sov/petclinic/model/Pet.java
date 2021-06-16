/**
 * 
 */
package courses.sov.petclinic.model;

import java.time.LocalDate;

/**
 * @author dcividin
 *
 */
public class Pet extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848317630792706665L;
	
	private LocalDate birthDate;
	private PetType type;
	private Owner owner;
	
	/**
	 * 
	 * @param name
	 */
	public Pet(String name) {
		super(name);
	}
	
	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public Pet setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
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
	public Pet setType(PetType type) {
		this.type = type;
		return this;
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
	public Pet setOwner(Owner owner) {
		this.owner = owner;
		return this;
	}
}
