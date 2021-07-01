/**
 * 
 */
package courses.sov.petclinic.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Specialty;
import courses.sov.petclinic.repositories.SpecialtyRepository;
import courses.sov.petclinic.service.SpecialtyService;


/**
 * @author dcividin
 *
 */
@Service
@Profile("jpa")
public class SpecialtyJpaService implements SpecialtyService {
	private final SpecialtyRepository specialtyRepository;
	
	public SpecialtyJpaService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public Optional<Specialty> findById(Long id) {
		return specialtyRepository.findById(id);
	}

	@Override
	public Set<Specialty> findAll() {
		Set<Specialty> specialtys = new HashSet<>();
		
		specialtyRepository.findAll().forEach(specialtys::add);
		
		return specialtys;
	}

	@Override
	public Specialty save(Specialty object) {
		return specialtyRepository.save(object);
	}

	@Override
	public void delete(Specialty object) {
		specialtyRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		specialtyRepository.deleteById(id);
	}

	@Override
	public long count() {
		return specialtyRepository.count();
	}
}
