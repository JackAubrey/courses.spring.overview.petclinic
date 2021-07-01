/**
 * 
 */
package courses.sov.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import courses.sov.petclinic.model.Vet;

/**
 * @author dcividin
 *
 */
public interface VetRepository extends CrudRepository<Vet, Long> {

}
