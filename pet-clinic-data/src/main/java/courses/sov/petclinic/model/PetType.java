/**
 * 
 */
package courses.sov.petclinic.model;

/**
 * @author dcividin
 *
 */
public class PetType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6792844611408658554L;
	
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
