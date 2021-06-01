/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Vet;
import courses.sov.petclinic.service.VetService;

/**
 * @author dcividin
 *
 */
@Service
public class VetServiceMap extends AbstractMapCrudService<Vet, Long> implements VetService {
	@Override
	public Optional<Vet> findByLastName(String lastName) {
		return map.entrySet()
				.stream().filter(entry -> entry.getValue().getLastName().equals(lastName))
				.map(Entry::getValue)
				.findFirst();
	}
}
