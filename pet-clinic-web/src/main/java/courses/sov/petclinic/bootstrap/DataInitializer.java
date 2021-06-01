/**
 * 
 */
package courses.sov.petclinic.bootstrap;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Vet;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.VetService;
import courses.sov.petclinic.service.map.OwnerServiceMap;
import courses.sov.petclinic.service.map.VetServiceMap;

/**
 * @author dcividin
 *
 */
@Component
public class DataInitializer implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

	private final OwnerService ownerService;
	private final VetService vetService;
	
	public DataInitializer(OwnerService ownerService, VetService vetService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {
		AtomicLong seqOwner = new AtomicLong();
		AtomicLong seqVet = new AtomicLong();
		
		Owner owner1 = new Owner();
		owner1.setId(seqOwner.incrementAndGet());
		owner1.setFirstName("Danilo");
		owner1.setLastName("Stoner");
		
		Owner owner2 = new Owner();
		owner2.setId(seqOwner.incrementAndGet());
		owner2.setFirstName("Andrea");
		owner2.setLastName("Marquez");
		
		Owner owner3 = new Owner();
		owner3.setId(seqOwner.incrementAndGet());
		owner3.setFirstName("Alberto");
		owner3.setLastName("Rossi");
		
		ownerService.save(owner1);
		ownerService.save(owner2);
		ownerService.save(owner3);

		log.info("Loaded Owners.......");
		
		Vet vet1 = new Vet();
		vet1.setId(seqVet.incrementAndGet());
		vet1.setFirstName("Paolo");
		vet1.setLastName("Amedei");
		
		Vet vet2 = new Vet();
		vet2.setId(seqVet.incrementAndGet());
		vet2.setFirstName("Luca");
		vet2.setLastName("Antonelli");
		
		Vet vet3 = new Vet();
		vet3.setId(seqVet.incrementAndGet());
		vet3.setFirstName("Mirco");
		vet3.setLastName("Sinatra");
		
		vetService.save(vet1);
		vetService.save(vet2);
		vetService.save(vet3);
		
		log.info("Loaded Vets.......");
	}
}
