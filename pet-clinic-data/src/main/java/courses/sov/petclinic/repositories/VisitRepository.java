/**
 * 
 */
package courses.sov.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.Visit;

/**
 * @author dcividin
 *
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {

}
