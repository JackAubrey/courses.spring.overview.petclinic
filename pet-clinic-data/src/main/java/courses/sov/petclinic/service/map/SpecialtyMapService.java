/**
 * 
 */
package courses.sov.petclinic.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Specialty;
import courses.sov.petclinic.service.SpecialtyService;

/**
 * @author dcividin
 *
 */
@Service
@Profile({"default", "map"})
public class SpecialtyMapService extends AbstractMapCrudService<Specialty, Long> implements SpecialtyService {
}
