/**
 * 
 */
package courses.sov.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.PetService;
import courses.sov.petclinic.service.PetTypeService;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

/**
 * @author dcividin
 *
 */
@ExtendWith(MockitoExtension.class)
class PetControllerTest {
	@Mock OwnerService ownerService;
	@Mock PetService petService;
	@Mock PetTypeService petTypeService;
	
	PetController petController;
	MockMvc mockMvc;
	
	Owner owner;
	Set<PetType> petTypes;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		petController = new PetController(ownerService, petService, petTypeService);
		
		owner = Owner.builder().id(321L).build();
		petTypes = new HashSet<>( Arrays.asList(new PetType(1L, "Dog"), new PetType(3L, "Cat"), new PetType(5L, "Cow")) );
		
		mockMvc = MockMvcBuilders
				.standaloneSetup(petController)
				.build();
	}

	/**
	 * Test method for {@link courses.sov.petclinic.controllers.PetController#initCreatePetForm(org.springframework.ui.Model)}.
	 */
	@Test
	void testInitCreatePetForm() {
		// given
		given(ownerService.findById(anyLong())).willReturn(Optional.of(owner));
		given(petTypeService.findAll()).willReturn(petTypes);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( get("/owners/"+owner.getId()+"/pets/new") )
				.andExpect(status().isOk())
				.andExpect(view().name(PetController.VIEW_CREATE_OR_UPDATE_PET))
				.andExpect(model().attributeExists("pet")));
		
		// then
		verify(ownerService).findById(anyLong());
		verify(petTypeService).findAll();
		
		verifyNoMoreInteractions(ownerService);
		verifyNoMoreInteractions(petTypeService);
		
		verifyNoInteractions(petService);
	}

	/**
	 * Test method for {@link courses.sov.petclinic.controllers.PetController#initUpdatePetForm(java.lang.Long, java.lang.Long, org.springframework.ui.Model)}.
	 */
	@Test
	void testInitUpdatePetForm() {
		// given
		Long petId = 1L;
		given(ownerService.findById(anyLong())).willReturn(Optional.of(owner));
		given(petTypeService.findAll()).willReturn(petTypes);
		given(petService.findById(anyLong())).willReturn( Optional.of(Pet.builder().id(petId).build()) );
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( get("/owners/"+owner.getId()+"/pets/"+petId+"/update") )
				.andExpect(status().isOk())
				.andExpect(view().name(PetController.VIEW_CREATE_OR_UPDATE_PET))
				.andExpect(model().attributeExists("pet"))
				.andExpect(model().attribute("pet", hasProperty("id", is(petId)))));
		
		// then
		verify(ownerService).findById(anyLong());
		verify(petTypeService).findAll();
		verify(petService).findById(anyLong());
		
		verifyNoMoreInteractions(ownerService);
		verifyNoMoreInteractions(petTypeService);
		verifyNoMoreInteractions(petService);
	}

	/**
	 * Test method for {@link courses.sov.petclinic.controllers.PetController#handelCreatePetForm(java.lang.Long, courses.sov.petclinic.model.Pet, org.springframework.validation.BindingResult, org.springframework.ui.Model)}.
	 */
	@Test
	void testHandelCreatePetForm() {
		// given
		Long petId = 1L;
		Pet pet = Pet.builder().id(petId).name("foo").build();
		given(ownerService.findById(anyLong())).willReturn(Optional.of(owner));
		given(petTypeService.findAll()).willReturn(petTypes);
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( MockMvcRequestBuilderUtils.postForm("/owners/"+owner.getId()+"/pets/new", pet) )
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/"+owner.getId())));
		
		// then
		verify(ownerService).findById(anyLong());
		verify(petTypeService).findAll();
		verify(petService).save(any(Pet.class));
		
		verifyNoMoreInteractions(ownerService);
		verifyNoMoreInteractions(petTypeService);
		verifyNoMoreInteractions(petService);
	}

	/**
	 * Test method for {@link courses.sov.petclinic.controllers.PetController#handelUpdatePetForm(java.lang.Long, java.lang.Long, courses.sov.petclinic.model.Pet, org.springframework.validation.BindingResult, org.springframework.ui.Model)}.
	 */
	@Test
	void testHandelUpdatePetForm() {
		// given
		Long petId = 1L;
		Pet pet = Pet.builder().id(petId).name("foo2").build();
		given(ownerService.findById(anyLong())).willReturn(Optional.of(owner));
		given(petTypeService.findAll()).willReturn(petTypes);
		given(petService.findById(anyLong())).willReturn( Optional.of( Pet.builder().id(petId).build() ));
		
		// when
		assertDoesNotThrow( () -> mockMvc.perform( MockMvcRequestBuilderUtils.postForm("/owners/"+owner.getId()+"/pets/"+petId+"/update", pet) )
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/"+owner.getId())));
		
		// then
		verify(ownerService).findById(anyLong());
		verify(petTypeService).findAll();
		verify(petService).findById(anyLong());
		verify(petService).save(any(Pet.class));
		
		verifyNoMoreInteractions(ownerService);
		verifyNoMoreInteractions(petTypeService);
		verifyNoMoreInteractions(petService);
	}

}
