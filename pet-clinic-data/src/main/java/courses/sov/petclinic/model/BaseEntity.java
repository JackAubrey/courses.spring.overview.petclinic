/**
 * 
 */
package courses.sov.petclinic.model;

import java.io.Serializable;

/**
 * @author dcividin
 *
 */
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7900127341086472018L;
	
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
