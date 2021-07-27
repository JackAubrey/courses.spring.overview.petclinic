/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dcividin
 *
 */
@Getter
@Setter
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
		super();
		this.setName(name);
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 */
	@Builder
	public PetType(Long id, String name) {
		super();
		setId(id);
		this.setName(name);
	}

	@Override
	public String toString() {
		return getName();
	}
}
