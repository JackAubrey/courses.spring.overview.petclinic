/**
 * 
 */
package courses.sov.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author dcividin
 *
 */
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848317630792706665L;

	@ManyToOne
	@JoinColumn(name = "pet_type_id")
	private PetType type;
	
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private LocalDate birthDate;
	
	// the foreign key goes her because "the owner of the relationship" between parent and child
	// delegate this responsibility to the child.
	// this is the reason why we use @JoinColumn here an not in "owner" class
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	/**
	 * 
	 */
	public Pet() {
		super();
	}
	
	/**
	 * 
	 * @param name
	 */
	public Pet(String name) {
		this.setName(name);
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
