/**
 * 
 */
package courses.sov.petclinic.service.map;

import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Specialty;
import courses.sov.petclinic.service.SpecialtyService;

/**
 * @author dcividin
 *
 */
@Service
public class SpecialtyMapService extends AbstractMapCrudService<Specialty, Long> implements SpecialtyService {


}
