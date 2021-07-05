/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dcividin
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8113001019059163865L;
	
	
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Person(Long id, @NotBlank(message = "First Name may not be blank") String firstName,
			@NotBlank(message = "Last Name may not be blank") String lastName) {
		super();
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Column(name = "first_name")
	@NotBlank(message = "First Name may not be blank")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "Last Name may not be blank")
	private String lastName;
}
