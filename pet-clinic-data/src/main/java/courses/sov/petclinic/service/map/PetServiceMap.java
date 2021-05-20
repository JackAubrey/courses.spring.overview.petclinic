/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.concurrent.atomic.AtomicLong;

import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.service.PetService;

/**
 * @author dcividin
 *
 */
public class PetServiceMap extends AbstractMapCrudService<Pet, Long> implements PetService {
	private AtomicLong sequence = new AtomicLong();
	
	@Override
	public Pet save(Pet object) {
		return super.save(sequence.getAndIncrement(), object);
	}
}
