/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.service.OwnerService;

/**
 * @author dcividin
 *
 */
@Service
public class OwnerServiceMap extends AbstractMapCrudService<Owner, Long> implements OwnerService {
	@Override
	public Optional<Owner> findByLastName(String lastName) {
		return map.entrySet()
				.stream().filter(entry -> entry.getValue().getLastName().equals(lastName))
				.map(Entry::getValue)
				.findFirst();
	}

}
