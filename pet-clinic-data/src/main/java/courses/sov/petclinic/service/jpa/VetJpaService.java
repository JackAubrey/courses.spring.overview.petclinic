/**
 * 
 */
package courses.sov.petclinic.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Vet;
import courses.sov.petclinic.repositories.VetRepository;
import courses.sov.petclinic.service.VetService;

/**
 * @author dcividin
 *
 */
@Service
@Profile("jpa")
public class VetJpaService implements VetService {
	private final VetRepository vetRepository;
	
	public VetJpaService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public Optional<Vet> findById(Long id) {
		return vetRepository.findById(id);
	}

	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<>();
		
		vetRepository.findAll().forEach(vets::add);
		
		return vets;
	}

	@Override
	public Vet save(Vet object) {
		return vetRepository.save(object);
	}

	@Override
	public void delete(Vet object) {
		vetRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);
	}

	@Override
	public long count() {
		return vetRepository.count();
	}

	@Override
	public Optional<Vet> findByLastName(String lastName) {
		return vetRepository.findByLastName(lastName);
	}

}
