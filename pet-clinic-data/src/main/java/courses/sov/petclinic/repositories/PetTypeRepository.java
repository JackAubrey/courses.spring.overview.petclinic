/**
 * 
 */
package courses.sov.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.PetType;

/**
 * @author dcividin
 *
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
