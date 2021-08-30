package courses.sov.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.OwnerService;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	@Mock OwnerService ownerService;
	@InjectMocks OwnerController ownerController;
	
	MockMvc mockMvc;
	Set<Owner> owners;
	List<Owner> ownersWithSimilarlastName = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<Owner>();
		
		owners.add(Owner.builder()
				.id(1L)
				.lastName("Smith")
				.telephone("333-456789")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(11L)
						.type(new PetType("dog"))
						.build())) )
				.build());
		
		owners.add(Owner.builder()
				.id(2L)
				.firstName("John")
				.lastName("McGuinness")
				.telephone("333-1234567")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(21L)
						.type(new PetType("dog"))
						.build())) )
				.build());
		
		ownersWithSimilarlastName.add(Owner.builder()
				.id(2L)
				.firstName("Joey")
				.lastName("Dunlop")
				.telephone("333-1234568")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(21L)
						.type(new PetType("dog"))
						.build())) )
				.build());
		
		ownersWithSimilarlastName.add(Owner.builder()
				.id(2L)
				.firstName("Michael")
				.lastName("Dunlops")
				.telephone("333-1234569")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(21L)
						.type(new PetType("dog"))
						.build())) )
				.build());
		
		ownersWithSimilarlastName.add(Owner.builder()
				.id(2L)
				.firstName("Michael")
				.lastName("Todunloping")
				.telephone("333-1234570")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(21L)
						.type(new PetType("dog"))
						.build())) )
				.build());
		
		owners.addAll(ownersWithSimilarlastName);
		
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController)
				.setControllerAdvice(ControllerExceptionHandler.class)
				.build();
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "/", "/index", "/index.html"})
	void testListOwners(String value) {
		// given
		given(ownerService.findAll()).willReturn(owners);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners"+value))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owners"))
			.andExpect(model().attribute("owners", hasSize(owners.size())))
			.andExpect(view().name("owners/index")) 
		);
		
		// then
		verify(ownerService).findAll();
		verifyNoMoreInteractions(ownerService);
	}

	@Test
	void testFindOwners() {
		// given
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/findOwners")) 
		);
		
		// then
		verifyNoInteractions(ownerService);
	}
	
	@Test
	void testGetOwner() {
		// given
		Optional<Owner> owner = owners.stream().findFirst();
		given(ownerService.findById(anyLong())).willReturn(owner);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(model().attribute("owner", hasProperty("id", is(owner.get().getId()))))
			.andExpect(view().name("owners/ownerDetails")) 
		);
		
		// then
		verify(ownerService).findById(anyLong());
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testGetOwnerNotFound() {
		// given
		given(ownerService.findById(anyLong())).willReturn(Optional.empty());
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/1"))
			.andExpect(status().isNotFound()) 
		);
		
		// then
		verify(ownerService).findById(anyLong());
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testGetOwnerNumberFormatException() {
		// given
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/abc"))
			.andExpect(status().isBadRequest()) 
		);
		
		// then
		verifyNoInteractions(ownerService);
	}
	
	@Test
	void testHandleFindFormNotFound() {
		// given
		given(ownerService.findAllByLastNameContainingIgnoreCase(anyString())).willReturn(new ArrayList<>());
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/doFind"))
			.andExpect(status().isOk())
			.andExpect(model().attributeDoesNotExist("owners"))
			.andExpect(view().name("owners/findOwners")) 
		);
		
		// then
		verify(ownerService).findAllByLastNameContainingIgnoreCase(anyString());
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testHandleFindFormOneResult() {
		// given
		Owner example = Owner.builder()
				.id(2L)
				.firstName("Michael")
				.lastName("Todunloping")
				.telephone("333-1234570")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(21L)
						.type(new PetType("dog"))
						.build())) )
				.build();
		
		given(ownerService.findAllByLastNameContainingIgnoreCase(anyString())).willReturn(Arrays.asList(example));
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/doFind"))
			.andExpect(status().is3xxRedirection())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("redirect:/owners/"+example.getId())) 
		);
		
		// then
		verify(ownerService).findAllByLastNameContainingIgnoreCase(anyString());
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testHandleFindFormManyResults() {
		// given
		given(ownerService.findAllByLastNameContainingIgnoreCase(anyString())).willReturn(ownersWithSimilarlastName);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/doFind"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owners"))
			.andExpect(view().name("owners/ownersList")) 
		);
		
		// then
		verify(ownerService).findAllByLastNameContainingIgnoreCase(anyString());
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testInitCreatOwnerForm() {
		// given
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/new"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(view().name("owners/createOrUpdateOwnerForm")) 
			);
		
		// then
		verifyNoInteractions(ownerService);
	}
	
	@Test
	void testProcessCreatOwnerForm() {
		// given
		Long ownerId = 1L;
		Owner owner = Owner.builder()
				.id(ownerId)
				.firstName("Michael")
				.lastName("Todunloping")
				.telephone("333-1234570")
				.build();
		
		given(ownerService.save(any(Owner.class))).willReturn(owner);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( MockMvcRequestBuilderUtils.postForm("/owners/new", owner) )
				.andExpect(status().is3xxRedirection())
				.andExpect(model().attributeExists("owner"))
				.andExpect(view().name("redirect:/owners/"+ownerId)) 
			);
		
		// then
		verify(ownerService).save(any(Owner.class));
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testInitUpdateOwnerForm() {
		// given
		Long ownerId = 3L;
		Optional<Owner> owner = Optional.of(Owner.builder()
				.id(2L)
				.firstName("Michael")
				.lastName("Todunloping")
				.telephone("333-1234570")
				.build());
		
		given(ownerService.findById(anyLong())).willReturn(owner);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( get("/owners/"+ownerId+"/update") )
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(view().name("owners/createOrUpdateOwnerForm")) 
			);
		
		// then
		verify(ownerService).findById(anyLong());
		verifyNoMoreInteractions(ownerService);
	}
	
	@Test
	void testProcessUpdateOwnerForm() {
		// given
		Long ownerId = 6L;
		
		Owner ownerSaved = Owner.builder()
				.id(ownerId)
				.firstName("Michael")
				.lastName("Dunlopp")
				.telephone("333-666666666")
				.build();
		
		Optional<Owner> owner = Optional.of(Owner.builder()
				.id(ownerId)
				.firstName("Michael")
				.lastName("Todunloping")
				.telephone("333-1234570")
				.build());
		
		given(ownerService.findById(anyLong())).willReturn(owner);
		given(ownerService.save(any(Owner.class))).willReturn(ownerSaved);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( MockMvcRequestBuilderUtils.postForm("/owners/"+owner.get().getId()+"/update", owner.get()) )
				.andExpect(status().is3xxRedirection())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("id", is(ownerSaved.getId()))))
				.andExpect(model().attribute("owner", hasProperty("lastName", is(ownerSaved.getLastName()))))
				.andExpect(view().name("redirect:/owners/"+owner.get().getId()))  
			);
		
		// then
		verify(ownerService).findById(anyLong());
		verify(ownerService).save(any(Owner.class));
		verifyNoMoreInteractions(ownerService);
	}
}
