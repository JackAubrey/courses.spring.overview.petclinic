/**
 * 
 */
package courses.sov.petclinic.model;

/**
 * @author dcividin
 *
 */
public class Specialty extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6025830140856266556L;
	
	private String description;

	/**
	 * 
	 */
	public Specialty() {
		super();
	}
	
	/**
	 * @param description
	 */
	public Specialty(String description) {
		super();
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
