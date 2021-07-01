/**
 * 
 */
package courses.sov.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.Specialty;

/**
 * @author dcividin
 *
 */
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

}
