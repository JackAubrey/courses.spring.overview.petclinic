package courses.sov.petclinic.service.map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.OwnerService;

class OwnerMapServiceTest {
	OwnerService ownerService;
	
	AtomicLong sequence = new AtomicLong(1);
	final Long defaultOwnerId = sequence.getAndIncrement();
	final String defaultOwnerName = "Default Name";
	final String defaultOwnerLastname = "Default Lastname";

	@BeforeEach
	void setUp() throws Exception {
		ownerService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
		
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Fuffy")
				.type(new PetType("Cat"))
				.build();
		
		ownerService.save(Owner.builder()
				.id(defaultOwnerId)
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build());
		
		ownerService.save(Owner.builder()
				.id(sequence.getAndIncrement())
				.firstName("Luca")
				.lastName("Rossi")
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build());
		
		ownerService.save(Owner.builder()
				.id(sequence.getAndIncrement())
				.firstName("Maria")
				.lastName("Verdi")
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build());
		
		ownerService.save(Owner.builder()
				.id(sequence.getAndIncrement())
				.firstName("Elisa")
				.lastName("Neri")
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build());
		
		ownerService.save(Owner.builder()
				.id(sequence.getAndIncrement())
				.firstName("Elisa")
				.lastName("Rossini")
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build());
		
		ownerService.save(Owner.builder()
				.id(sequence.getAndIncrement())
				.firstName("Paolo")
				.lastName("Crossitti")
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build());
	}

	@Test
	void testFindByLastName() {
		// given
		
		// when
		Optional<Owner> owner = assertDoesNotThrow( () -> ownerService.findByLastName(defaultOwnerLastname) );
		
		// then
		assertTrue(owner.isPresent());
		assertEquals(defaultOwnerLastname, owner.get().getLastName());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		// given
		
		// when
		Optional<Owner> owner = assertDoesNotThrow( () -> ownerService.findByLastName("fooo") );
		
		// then
		assertTrue(owner.isEmpty());
	}

	@Test
	void testSaveOwner() {
		// given
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Dino")
				.type(new PetType("Dog"))
				.build();
		
		Owner newOwner = Owner.builder()
			.firstName(defaultOwnerName)
			.lastName(defaultOwnerLastname)
			.pets(new HashSet<Pet>(Arrays.asList(pet)))
			.build();
		
		// when
		Owner savedOwner = assertDoesNotThrow( () -> ownerService.save(newOwner));
		
		// then
		assertNotNull(savedOwner.getId());
		assertTrue(savedOwner.getId() > 0);
	}
	
	@Test
	void testSaveExistingOwner() {
		// given
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Dino")
				.type(new PetType("Dog"))
				.build();
		
		Owner newOwner = Owner.builder()
				.id(defaultOwnerId)
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build();
		
		// when
		long countBefore = assertDoesNotThrow( () -> ownerService.count());
		Owner savedOwner = assertDoesNotThrow( () -> ownerService.save(newOwner));
		long countAfter = assertDoesNotThrow( () -> ownerService.count());
		
		// then
		assertNotNull(savedOwner.getId());
		assertTrue(savedOwner.getId() > 0);
		assertEquals(countBefore, countAfter);
	}

	@Test
	void testFindAll() {
		// given
		
		// when
		Set<Owner> list = assertDoesNotThrow( () -> ownerService.findAll());
		
		// then
		assertEquals(6, list.size());
	}

	@Test
	void testFindById() {
		// given
		
		// when
		Optional<Owner> owner = assertDoesNotThrow( () -> ownerService.findById(defaultOwnerId));
		
		// then
		assertTrue(owner.isPresent());
		assertEquals(defaultOwnerLastname, owner.get().getLastName());
	}

	@Test
	void testDeleteById() {
		// given
		
		// when
		long countBefore = assertDoesNotThrow( () -> ownerService.count());
		assertDoesNotThrow( () -> ownerService.deleteById(defaultOwnerId));
		long countAfter = assertDoesNotThrow( () -> ownerService.count());
		
		// then
		assertTrue(countBefore > countAfter);
	}

	@Test
	void testDelete() {
		// given
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Rudy")
				.type(new PetType("Dog"))
				.build();
		
		Owner newOwner = Owner.builder()
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build();
				
		// when
		Owner savedOwner = assertDoesNotThrow( () -> ownerService.save(newOwner));
		
		long countBefore = assertDoesNotThrow( () -> ownerService.count());
		assertDoesNotThrow( () -> ownerService.delete(savedOwner));
		long countAfter = assertDoesNotThrow( () -> ownerService.count());
		
		// then
		assertTrue(countBefore > countAfter);
	}

	@Test
	void testCount() {
		long count = assertDoesNotThrow( () -> ownerService.count());
		
		assertEquals(6, count);
	}

	
	@Test
	void testAllFindByLastNameLike() {
		// given
		String nameToFindByLike = "Rossi";
		
		// when
		List<Owner> owners = assertDoesNotThrow( () -> ownerService.findAllByLastNameLike(nameToFindByLike) );
		
		// then
		assertTrue(!owners.isEmpty());
		assertEquals(3, owners.size());
	}
	
	@Test
	void testAllFindByLastNameLikeNotFound() {
		// given
		String nameToFindByLike = "Marroncino";
		
		// when
		List<Owner> owners = assertDoesNotThrow( () -> ownerService.findAllByLastNameLike(nameToFindByLike) );
		
		// then
		assertTrue(owners.isEmpty());
	}
}
