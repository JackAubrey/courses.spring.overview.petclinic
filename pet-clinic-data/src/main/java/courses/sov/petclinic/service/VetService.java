/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.Set;

import courses.sov.petclinic.model.Vet;

/**
 * @author dcividin
 *
 */
public interface VetService {
	Vet findByLastName(String lastName);
	
	Vet findById(Long id);
	
	Set<Vet> findAll();
	
	Vet save(Vet vet);
}
