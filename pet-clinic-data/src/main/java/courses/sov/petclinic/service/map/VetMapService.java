/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Vet;
import courses.sov.petclinic.service.SpecialtyService;
import courses.sov.petclinic.service.VetService;

/**
 * @author dcividin
 *
 */
@Service
public class VetMapService extends AbstractMapCrudService<Vet, Long> implements VetService {
	private final SpecialtyService specialtyService;
	
	/**
	 * @param specialtyService
	 */
	public VetMapService(SpecialtyService specialtyService) {
		super();
		this.specialtyService = specialtyService;
	}
	
	@Override
	public Optional<Vet> findByLastName(String lastName) {
		return map.entrySet()
				.stream().filter(entry -> entry.getValue().getLastName().equals(lastName))
				.map(Entry::getValue)
				.findFirst();
	}
	
	@Override
	public Vet save(Vet object) {
		if(object != null) {
			if(object.getSpecialties() != null && !object.getSpecialties().isEmpty()) {
				object.getSpecialties().forEach(specialty -> {
					if(specialty.getId() == null || specialty.getId() == 0) {
						var savedSpecialty = specialtyService.save(specialty);
						object.addSpecialty(savedSpecialty);
					}
				});
			}

			return super.save(object);
		} else {
			return null;
		}
	}
}
