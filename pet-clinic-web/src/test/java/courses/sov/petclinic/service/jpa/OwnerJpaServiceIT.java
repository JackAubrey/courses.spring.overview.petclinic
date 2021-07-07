package courses.sov.petclinic.service.jpa;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.PetTypeService;

@ActiveProfiles({"it-test","jpa"})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan("courses.sov.petclinic")
class OwnerJpaServiceIT {

	@Autowired
	OwnerService ownerService;
	
	@Autowired
	PetTypeService petTypeService;
	
	final String fuffyOwnerName = "John";
	final String fuffyOwnerLastname = "Smith";
	final String rudyOwnerName = "Arthur";
	final String rudyOwnerLastname = "Doe";
	
	PetType dog;
	PetType cat;
	Pet fuffy;
	Pet rudy;
	Owner fuffyOwner;
	Owner rudyOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		if(ownerService.count() > 0)  {
			Set<Owner> owners = ownerService.findAll();
			
			owners.forEach( ownerService::delete );
		}
		
		dog = petTypeService.save(PetType.builder().id(1L).name("Dog").build());
		cat = petTypeService.save(PetType.builder().id(2L).name("Cat").build());
		
		fuffy = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Fuffy")
				.type(cat)
				.build();
		
		rudy = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Rudy")
				.type(dog)
				.build();
		
		fuffyOwner = Owner.builder()
				.firstName(fuffyOwnerName)
				.lastName(fuffyOwnerLastname)
				.telephone("328-123456")
				.pets(new HashSet<Pet>(Arrays.asList(fuffy)))
				.build();
		
		rudyOwner = Owner.builder()
				.firstName(rudyOwnerName)
				.lastName(rudyOwnerLastname)
				.telephone("328-4567321")
				.pets(new HashSet<Pet>(Arrays.asList(rudy)))
				.build();
		
		fuffy.setOwner(fuffyOwner);
		rudy.setOwner(rudyOwner);
		
		fuffyOwner = ownerService.save( fuffyOwner );
		rudyOwner = ownerService.save( rudyOwner );
	}

	@Test
	void testFindById() {
		// given
		
		// when
		Optional<Owner> owner = ownerService.findById(rudyOwner.getId());
		
		// then
		assertTrue(owner.isPresent());
		assertEquals(rudyOwnerLastname, owner.get().getLastName());
	}
	
	@Test
	void testFindByIdNotFound() {
		// given
		
		// when
		Optional<Owner> owner = ownerService.findById(-1L);
		
		// then
		assertFalse(owner.isPresent());
	}

	@Test
	void testFindAll() {
		// given
		
		// when
		Set<Owner> owners = ownerService.findAll();
		
		// then
		assertFalse(owners.isEmpty());
		assertEquals(2, owners.size());
	}

	@Test
	@Rollback
	void testSave() {
		// given
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Dino")
				.type(dog)
				.build();
		
		Owner newOwner = Owner.builder()
				.firstName("Margaret")
				.lastName("Lee")
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.telephone("337-9087653")
				.build();
		
		// when
		Owner savedOwner = assertDoesNotThrow( () -> ownerService.save(newOwner));
		
		// then
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
		assertTrue(savedOwner.getId() > 0);
		assertTrue(savedOwner.getPets().containsAll(newOwner.getPets()));
	}

	@Test
	void testDelete() {
		// when
		long countBefore = ownerService.count();
		System.out.println("Count Before Delete = " + countBefore);
		ownerService.delete(fuffyOwner);
		long countAfter = ownerService.count();
		System.out.println("Count After Delete = " + countAfter);
		
		// then
		assertTrue(countBefore > countAfter);
	}

	@Test
	void testDeleteById() {
		// when
		long countBefore = ownerService.count();
		ownerService.deleteById(rudyOwner.getId());
		long countAfter = ownerService.count();
		
		// then
		assertTrue(countBefore > countAfter);
	}

	@Test
	void testCount() {
		// given
		
		// when
		Long counted = ownerService.count();
		
		// then
		assertEquals(2, counted);
	}

	@Test
	void testFindByLastName() {
		// given
		
		// when
		Optional<Owner> owner = ownerService.findByLastName(rudyOwnerLastname);
		
		// then
		assertTrue(owner.isPresent());
		assertEquals(rudyOwnerLastname, owner.get().getLastName());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		// given
		
		// when
		Optional<Owner> owner = ownerService.findByLastName("fooo");
		
		// then
		assertFalse(owner.isPresent());
	}

}
