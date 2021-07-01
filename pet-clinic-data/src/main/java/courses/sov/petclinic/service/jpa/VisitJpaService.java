/**
 * 
 */
package courses.sov.petclinic.service.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Visit;
import courses.sov.petclinic.repositories.VisitRepository;
import courses.sov.petclinic.service.VisitService;

/**
 * @author dcividin
 *
 */
@Service
@Profile("jpa")
public class VisitJpaService implements VisitService {
	private final VisitRepository visitRepository;
	
	public VisitJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Optional<Visit> findById(Long id) {
		return visitRepository.findById(id);
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		
		visitRepository.findAll().forEach(visits::add);
		
		return visits;
	}

	@Override
	public Visit save(Visit object) {
		return visitRepository.save(object);
	}

	@Override
	public void delete(Visit object) {
		visitRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

	@Override
	public long count() {
		return visitRepository.count();
	}
}
