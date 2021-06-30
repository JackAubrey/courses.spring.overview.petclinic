/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dcividin
 *
 */
@Entity
@Table(name = "pet_types")
public class PetType extends NamedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6792844611408658554L;
	
	public PetType() {
		super();
	}
	
	/**
	 * 
	 * @param name
	 */
	public PetType(String name) {
		this.setName(name);
	}
}
