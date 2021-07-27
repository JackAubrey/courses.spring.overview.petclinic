package courses.sov.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
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

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	@Mock OwnerService ownerService;
	@InjectMocks OwnerController ownerController;
	
	MockMvc mockMvc;
	Set<Owner> owners;

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
				.lastName("McGuinness")
				.telephone("333-1234567")
				.pets( new HashSet<Pet>(Arrays.asList(Pet.builder()
						.id(21L)
						.type(new PetType("dog"))
						.build())) )
				.build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
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
			.andExpect(model().attribute("owners", hasSize(2)))
			.andExpect(view().name("owners/index")) 
		);
		
		// then
		verify(ownerService).findAll();
		verifyNoMoreInteractions(ownerService);
	}

	@Test
	void testFindOwners() {
		// given
		given(ownerService.findAll()).willReturn(owners);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owners"))
			.andExpect(view().name("owners/ownersList")) 
		);
		
		// then
		verify(ownerService).findAll();
		verifyNoMoreInteractions(ownerService);
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
}
