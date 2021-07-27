/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.List;
import java.util.Optional;

import courses.sov.petclinic.model.Owner;

/**
 * @author dcividin
 *
 */
public interface OwnerService extends CrudService<Owner, Long>{
	Optional<Owner> findByLastName(String lastName);
	
	List<Owner> findAllByLastNameContainingIgnoreCase(String lastName);
}
