package courses.sov.petclinic.service.jpa;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.repositories.OwnerRepository;
import courses.sov.petclinic.service.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {
	@Mock OwnerRepository ownerRepository;
	
	OwnerService ownerService;
	
	final Long defaultOwnerId = 1L;
	final String defaultOwnerName = "Default Name";
	final String defaultOwnerLastname = "Default Lastname";
	
	Owner defaultOwner;

	@BeforeEach
	void setUp() throws Exception {
		ownerService = new OwnerJpaService(ownerRepository);
		
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Fuffy")
				.type(new PetType("Cat"))
				.build();
		
		defaultOwner = Owner.builder()
				.id(defaultOwnerId)
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build();
	}

	@Test
	void testFindById() {
		// given
		given(ownerRepository.findById(defaultOwnerId)).willReturn(Optional.of(defaultOwner));
		
		// when
		Optional<Owner> owner = ownerService.findById(defaultOwnerId);
		
		// then
		assertTrue(owner.isPresent());
		assertEquals(defaultOwnerLastname, owner.get().getLastName());
		
		// double check
		verify(ownerRepository).findById(defaultOwnerId);
		verifyNoMoreInteractions(ownerRepository);
	}
	
	@Test
	void testFindByIdNotFound() {
		// given
		given(ownerRepository.findById(defaultOwnerId)).willReturn(Optional.empty());
		
		// when
		Optional<Owner> owner = ownerService.findById(defaultOwnerId);
		
		// then
		assertFalse(owner.isPresent());
		
		// double check
		verify(ownerRepository).findById(defaultOwnerId);
		verifyNoMoreInteractions(ownerRepository);
	}

	@Test
	void testFindAll() {
		// given
		Owner owner1 = Owner.builder()
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.build();
		
		Owner owner2 = Owner.builder()
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.build();
		
		Set<Owner> returnList = new HashSet<Owner>();
		returnList.add(owner1);
		returnList.add(owner2);
		given(ownerRepository.findAll()).willReturn(returnList);
		
		// when
		Set<Owner> owners = ownerService.findAll();
		
		// then
		assertFalse(owners.isEmpty());
		assertEquals(returnList.size(), owners.size());
		assertEquals(returnList, owners);
		
		// double check
		verify(ownerRepository).findAll();
		verifyNoMoreInteractions(ownerRepository);
	}

	@Test
	void testSave() {
		// given
		Pet pet = Pet.builder()
				.birthDate(LocalDate.now())
				.name("Dino")
				.type(new PetType("Dog"))
				.build();
		
		Owner newOwner = Owner.builder()
				.id(3L)
				.firstName(defaultOwnerName)
				.lastName(defaultOwnerLastname)
				.pets(new HashSet<Pet>(Arrays.asList(pet)))
				.build();
		
		given(ownerRepository.save(newOwner)).willReturn(newOwner);
		
		// when
		Owner savedOwner = assertDoesNotThrow( () -> ownerService.save(newOwner));
		
		// then
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
		assertTrue(savedOwner.getId() > 0);
		
		verify(ownerRepository).save(newOwner);
		verifyNoMoreInteractions(ownerRepository);
	}

	@Test
	void testDelete() {
		// when
		ownerService.delete(defaultOwner);
		
		// then
		verify(ownerRepository).delete(any());
		verifyNoMoreInteractions(ownerRepository);
	}

	@Test
	void testDeleteById() {
		// when
		ownerService.deleteById(1L);
		
		// then
		verify(ownerRepository).deleteById(anyLong());
		verifyNoMoreInteractions(ownerRepository);
	}

	@Test
	void testCount() {
		// given
		Long count = 3L;
		given(ownerRepository.count()).willReturn(count);
		
		// when
		Long counted = ownerService.count();
		
		// then
		assertEquals(count, counted);
		
		// double check
		verify(ownerRepository).count();
		verifyNoMoreInteractions(ownerRepository);
	}

	@Test
	void testFindByLastName() {
		// given
		given(ownerRepository.findByLastName(defaultOwnerLastname)).willReturn(Optional.of(defaultOwner));
		
		// when
		Optional<Owner> owner = ownerService.findByLastName(defaultOwnerLastname);
		
		// then
		assertTrue(owner.isPresent());
		assertEquals(defaultOwnerLastname, owner.get().getLastName());
		
		// double check
		verify(ownerRepository).findByLastName(defaultOwnerLastname);
		verifyNoMoreInteractions(ownerRepository);
	}

}
