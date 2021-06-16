/**
 * 
 */
package courses.sov.petclinic.model;

/**
 * @author dcividin
 *
 */
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
	public NamedEntity(String name) {
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
