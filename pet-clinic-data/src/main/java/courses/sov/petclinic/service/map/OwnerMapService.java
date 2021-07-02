/**
 * 
 */
package courses.sov.petclinic.service.map;

import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.PetService;
import courses.sov.petclinic.service.PetTypeService;

/**
 * @author dcividin
 *
 */
@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapCrudService<Owner, Long> implements OwnerService {
	private final PetService petService;
	private final PetTypeService petTypeService;
	
	public OwnerMapService(PetService petService, PetTypeService petTypeService) {
		this.petService = petService;
		this.petTypeService = petTypeService;
		
	}
	
	@Override
	public Optional<Owner> findByLastName(String lastName) {
		return map.entrySet()
				.stream().filter(entry -> entry.getValue().getLastName().equals(lastName))
				.map(Entry::getValue)
				.findFirst();
	}

	@Override
	public Owner save(Owner object) {
		if(object != null) {
			if(object.getPets() != null && !object.getPets().isEmpty()) {
				object.getPets().forEach(pet -> {
					if(pet.getType() != null) {
						if( pet.getType().getId() == null || pet.getType().getId() == 0) {
							var savedPetType = petTypeService.save(pet.getType());
							pet.setType(savedPetType);
						}
					} else {
						throw new RuntimeException("The Pet ["+pet.getName()+"] has not petType set!");
					}
					
					if(pet.getId() == null || pet.getId() == 0) {
						var savedPet = petService.save(pet);
						object.addPet(savedPet);
					}
				});
			} else {
				throw new RuntimeException("The Owner has no Pets set!");
			}

			return super.save(object);
		} else {
			return null;
		}
	}
}
