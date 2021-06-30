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
public class Person extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8113001019059163865L;
	
	@Column(name = "first_name")
	@NotBlank(message = "First Name may not be blank")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "Last Name may not be blank")
	private String lastName;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
