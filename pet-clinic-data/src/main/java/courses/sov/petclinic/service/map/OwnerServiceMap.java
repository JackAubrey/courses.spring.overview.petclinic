/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.service.OwnerService;

/**
 * @author dcividin
 *
 */
public class OwnerServiceMap extends AbstractMapCrudService<Owner, Long> implements OwnerService {
	private AtomicLong sequence = new AtomicLong();
	
	@Override
	public Owner save(Owner object) {
		return super.save(sequence.getAndIncrement(), object);
	}

	@Override
	public Optional<Owner> findByLastName(String lastName) {
		return map.entrySet()
				.stream().filter(entry -> entry.getValue().getLastName().equals(lastName))
				.map(Entry::getValue)
				.findFirst();
	}

}
