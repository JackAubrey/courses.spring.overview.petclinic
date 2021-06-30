/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author dcividin
 *
 */
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6025830140856266556L;
	
	@Column(name = "description")
	@NotBlank(message = "Description may not be blank")
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
