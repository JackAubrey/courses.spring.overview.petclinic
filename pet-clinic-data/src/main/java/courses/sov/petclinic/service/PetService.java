/**
 * 
 */
package courses.sov.petclinic.service;

import java.util.Set;

import courses.sov.petclinic.model.Pet;

/**
 * @author dcividin
 *
 */
public interface PetService {
	Pet findById(Long id);
	
	Set<Pet> findAll();
	
	Pet save(Pet pet);
}
