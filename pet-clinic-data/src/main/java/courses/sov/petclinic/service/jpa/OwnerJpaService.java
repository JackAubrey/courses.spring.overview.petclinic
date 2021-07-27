/**
 * 
 */
package courses.sov.petclinic.service.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.repositories.OwnerRepository;
import courses.sov.petclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dcividin
 *
 */
@Slf4j
@Service
@Profile("jpa")
public class OwnerJpaService implements OwnerService {
	private final OwnerRepository ownerRepository;
	
	public OwnerJpaService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public Optional<Owner> findById(Long id) {
		log.info("....find by id {}", id);
		return ownerRepository.findById(id);
	}

	@Override
	public Set<Owner> findAll() {
		log.info("....find all");
		Set<Owner> owners = new HashSet<>();
		
		ownerRepository.findAll().forEach(owners::add);
		
		return owners;
	}

	@Override
	public Owner save(Owner object) {
		log.info("....save {}", object);
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		log.info("....delete {}", object);
		ownerRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		log.info("....delete by id {}", id);
		ownerRepository.deleteById(id);
	}

	@Override
	public long count() {
		log.info("....count");
		return ownerRepository.count();
	}

	@Override
	public Optional<Owner> findByLastName(String lastName) {
		log.info("....find by last name {}", lastName);
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public List<Owner> findAllByLastNameContainingIgnoreCase(String lastName) {
		return ownerRepository.findAllByLastNameContainingIgnoreCase(lastName);
	}
}
