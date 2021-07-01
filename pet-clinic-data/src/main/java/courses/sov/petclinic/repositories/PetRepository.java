/**
 * 
 */
package courses.sov.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.Pet;

/**
 * @author dcividin
 *
 */
public interface PetRepository extends CrudRepository<Pet, Long> {

}
