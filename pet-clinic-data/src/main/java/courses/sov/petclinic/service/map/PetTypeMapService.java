/**
 * 
 */
package courses.sov.petclinic.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.PetTypeService;

/**
 * @author dcividin
 *
 */
@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapCrudService<PetType, Long> implements PetTypeService {
}
