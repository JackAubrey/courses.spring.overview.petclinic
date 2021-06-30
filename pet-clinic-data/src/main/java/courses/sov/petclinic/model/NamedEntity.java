/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.MappedSuperclass;

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
	
	private String name;

	/**
	 * 
	 * @param name
	 */
	protected NamedEntity(String name) {
		super();
		this.name = name;
	}

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
