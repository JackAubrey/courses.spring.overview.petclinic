/**
 * 
 */
package courses.sov.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.Owner;

/**
 * @author dcividin
 *
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
