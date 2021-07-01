/**
 * 
 */
package courses.sov.petclinic.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.repositories.PetTypeRepository;
import courses.sov.petclinic.service.PetTypeService;

/**
 * @author dcividin
 *
 */
@Service
@Profile("jpa")
public class PetTypeJpaService implements PetTypeService {
	private final PetTypeRepository petTypeRepository;
	
	public PetTypeJpaService(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Optional<PetType> findById(Long id) {
		return petTypeRepository.findById(id);
	}

	@Override
	public Set<PetType> findAll() {
		Set<PetType> petTypes = new HashSet<>();
		
		petTypeRepository.findAll().forEach(petTypes::add);
		
		return petTypes;
	}

	@Override
	public PetType save(PetType object) {
		return petTypeRepository.save(object);
	}

	@Override
	public void delete(PetType object) {
		petTypeRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}

	@Override
	public long count() {
		return petTypeRepository.count();
	}
}
