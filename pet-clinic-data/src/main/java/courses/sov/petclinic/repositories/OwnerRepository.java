/**
 * 
 */
package courses.sov.petclinic.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.Owner;

/**
 * @author dcividin
 *
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Optional<Owner> findByLastName(String lastName);
	
	List<Owner> findAllByLastNameContainingIgnoreCase(String lastName);
}
