/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.Set;

import courses.sov.petclinic.model.Owner;

/**
 * @author dcividin
 *
 */
public interface OwnerService {
	Owner findByLastName(String lastName);
	
	Owner findById(Long id);
	
	Set<Owner> findAll();
	
	Owner save(Owner owner);
}
