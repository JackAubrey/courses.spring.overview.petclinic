/**
 * 
 */
package courses.sov.petclinic.service.map;

import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.service.PetService;

/**
 * @author dcividin
 *
 */
@Service
public class PetServiceMap extends AbstractMapCrudService<Pet, Long> implements PetService {
}
