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
	
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
