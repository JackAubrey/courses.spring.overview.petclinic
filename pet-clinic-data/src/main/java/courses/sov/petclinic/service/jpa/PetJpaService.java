/**
 * 
 */
package courses.sov.petclinic.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.repositories.PetRepository;
import courses.sov.petclinic.service.PetService;

/**
 * @author dcividin
 *
 */
@Service
@Profile("jpa")
public class PetJpaService implements PetService {
	private final PetRepository petRepository;
	
	public PetJpaService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Optional<Pet> findById(Long id) {
		return petRepository.findById(id);
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<>();
		
		petRepository.findAll().forEach(pets::add);
		
		return pets;
	}

	@Override
	public Pet save(Pet object) {
		return petRepository.save(object);
	}

	@Override
	public void delete(Pet object) {
		petRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);
	}

	@Override
	public long count() {
		return petRepository.count();
	}
}
