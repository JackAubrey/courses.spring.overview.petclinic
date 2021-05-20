/**
 * 
 */
package courses.sov.petclinic.service;

import courses.sov.petclinic.model.Vet;

/**
 * @author dcividin
 *
 */
public interface VetService extends CrudService<Vet, Long> {
	Vet findByLastName(String lastName);
}
