/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.Optional;

import courses.sov.petclinic.model.Vet;

/**
 * @author dcividin
 *
 */
public interface VetService extends CrudService<Vet, Long> {
	Optional<Vet> findByLastName(String lastName);
}
