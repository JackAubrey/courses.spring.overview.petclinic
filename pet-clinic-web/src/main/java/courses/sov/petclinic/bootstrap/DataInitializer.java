/**
 * 
 */
package courses.sov.petclinic.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.model.Vet;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.PetTypeService;
import courses.sov.petclinic.service.VetService;

/**
 * @author dcividin
 *
 */
@Component
public class DataInitializer implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	
	public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {
		var dog = petTypeService.save( new PetType("Dog") );
		var cat = petTypeService.save( new PetType("Cat") );
		var bird = petTypeService.save( new PetType("Bird") );
		var horse = petTypeService.save( new PetType("Horse") );
		var caw = petTypeService.save( new PetType("Caw") );
		var mule = petTypeService.save( new PetType("Mule") );
		
		log.info("Loaded PetTypes.......");
		
		var owner1 = new Owner();
		owner1.setFirstName("Danilo");
		owner1.setLastName("Stoner");
		
		var owner2 = new Owner();
		owner2.setFirstName("Andrea");
		owner2.setLastName("Marquez");
		
		var owner3 = new Owner();
		owner3.setFirstName("Alberto");
		owner3.setLastName("Rossi");
		
		ownerService.save(owner1);
		ownerService.save(owner2);
		ownerService.save(owner3);

		log.info("Loaded Owners.......");
		
		var vet1 = new Vet();
		vet1.setFirstName("Paolo");
		vet1.setLastName("Amedei");
		
		var vet2 = new Vet();
		vet2.setFirstName("Luca");
		vet2.setLastName("Antonelli");
		
		var vet3 = new Vet();
		vet3.setFirstName("Mirco");
		vet3.setLastName("Sinatra");
		
		vetService.save(vet1);
		vetService.save(vet2);
		vetService.save(vet3);
		
		log.info("Loaded Vets.......");
	}
}
