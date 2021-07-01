/**
 * 
 */
package courses.sov.petclinic.bootstrap;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.model.Specialty;
import courses.sov.petclinic.model.Vet;
import courses.sov.petclinic.model.Visit;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.PetTypeService;
import courses.sov.petclinic.service.SpecialtyService;
import courses.sov.petclinic.service.VetService;
import courses.sov.petclinic.service.VisitService;

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
	private final SpecialtyService specialtyService;
	private final VisitService visitService;
	
	public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		long count = petTypeService.count();
		
		if(count == 0) {
			loadData();
		}
	}

	private void loadData() {
		var dog = petTypeService.save( new PetType("Dog") );
		var cat = petTypeService.save( new PetType("Cat") );
		var horse = petTypeService.save( new PetType("Horse") );
		log.info("Loaded PetTypes.......");
		
		var radiology = specialtyService.save(new Specialty("Radiology"));
		var surgery = specialtyService.save(new Specialty("Surgery"));
		var dentistry = specialtyService.save(new Specialty("Dentistry"));
		log.info("Loaded Specialties.......");
		
		var owner1 = new Owner();
		owner1.setFirstName("Danilo");
		owner1.setLastName("Stoner");
		owner1.setAddress("Viale della libertà, 1");
		owner1.setCity("Roma");
		owner1.setTelephone("063090570");
		
		var owner1Pet = new Pet("Poldo")
				.setBirthDate(LocalDate.now())
				.setOwner(owner1)
				.setType(dog);
		
		owner1.addPet(owner1Pet);
		
		var owner2 = new Owner();
		owner2.setFirstName("Andrea");
		owner2.setLastName("Marquez");
		owner2.setAddress("Piazza dei Sanniti, 123");
		owner2.setCity("Roma");
		owner2.setTelephone("065790871");
		
		var owner2Pet = new Pet("Melody")
				.setBirthDate(LocalDate.now())
				.setOwner(owner2)
				.setType(cat);
		
		owner2.addPet(owner2Pet);
		
		var owner3 = new Owner();
		owner3.setFirstName("Alberto");
		owner3.setLastName("Rossi");
		owner3.setAddress("Viale 5 maggio, 41");
		owner3.setCity("Milano");
		owner3.setTelephone("024091560");
		
		var owner3Pet = new Pet("Furia")
				.setBirthDate(LocalDate.now())
				.setOwner(owner3)
				.setType(horse);
		
		owner3.addPet(owner3Pet);
		
		ownerService.save(owner1);
		ownerService.save(owner2);
		ownerService.save(owner3);

		log.info("Loaded Owners.......");
		
		var visitPet1 = new Visit()
				.setDate(LocalDate.now())
				.setDescription("Checkup")
				.setPet(owner1Pet);
		
		var visitPet2 = new Visit()
				.setDate(LocalDate.now())
				.setDescription("Taglio unghie")
				.setPet(owner2Pet);
		
		var visitPet3 = new Visit()
				.setDate(LocalDate.now())
				.setDescription("Controllo Generale")
				.setPet(owner3Pet);

		visitService.save(visitPet1);
		visitService.save(visitPet2);
		visitService.save(visitPet3);
		
		log.info("Loaded Visits.......");
		
		var vet1 = new Vet();
		vet1.setFirstName("Paolo");
		vet1.setLastName("Amedei");
		vet1.addSpecialty(dentistry);
		
		var vet2 = new Vet();
		vet2.setFirstName("Luca");
		vet2.setLastName("Antonelli");
		vet2.addSpecialty(surgery);
		vet2.addSpecialty(radiology);
		
		var vet3 = new Vet();
		vet3.setFirstName("Mirco");
		vet3.setLastName("Sinatra");
		vet3.addSpecialty(radiology);
		
		vetService.save(vet1);
		vetService.save(vet2);
		vetService.save(vet3);
		
		log.info("Loaded Vets.......");
	}
}
