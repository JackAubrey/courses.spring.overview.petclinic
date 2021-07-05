/**
 * 
 */
package courses.sov.petclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dcividin
 *
 */
@Getter
@Setter
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3115844759016890413L;
	
	@Column(name = "name")
	@NotBlank(message = "Name may not be blank")
	private String name;
}
