/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

/**
 * @author dcividin
 *
 */
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3115844759016890413L;
	
	@Column(name = "name")
	@NotBlank(message = "Name may not be blank")
	private String name;


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
