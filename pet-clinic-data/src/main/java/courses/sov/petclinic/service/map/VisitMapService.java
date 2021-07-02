/**
 * 
 */
package courses.sov.petclinic.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Visit;
import courses.sov.petclinic.service.VisitService;

/**
 * @author dcividin
 *
 */
@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapCrudService<Visit, Long> implements VisitService {

	@Override
	public Visit save(Visit visit) {
		if(visit == null || visit.getPet() == null || visit.getPet().getOwner() == null
				|| visit.getPet().getId() == null || visit.getPet().getId() < 1
				|| visit.getPet().getOwner().getId() == null || visit.getPet().getOwner().getId() < 1) {
			throw new IllegalArgumentException("Visit has no pet or owner");
		}
		
		return super.save(visit);
	}
}
