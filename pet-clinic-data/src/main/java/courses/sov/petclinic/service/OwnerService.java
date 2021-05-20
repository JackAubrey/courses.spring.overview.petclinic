/**
 * 
 */
package courses.sov.petclinic.service;

import courses.sov.petclinic.model.Owner;

/**
 * @author dcividin
 *
 */
public interface OwnerService extends CrudService<Owner, Long>{
	Owner findByLastName(String lastName);
}
