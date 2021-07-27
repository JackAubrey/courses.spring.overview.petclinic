/**
 * 
 */
package courses.sov.petclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.model.Pet;
import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.OwnerService;
import courses.sov.petclinic.service.PetService;
import courses.sov.petclinic.service.PetTypeService;

/**
 * @author dcividin
 *
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	public static final String VIEW_CREATE_OR_UPDATE_PET = "pets/createOrUpdatePetForm";
	
	private final OwnerService ownerService;
	private final PetService petService;
	private final PetTypeService petTypeService;
	
	public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.petService = petService;
		this.petTypeService = petTypeService;
	}
	
	@InitBinder("owner")
	public void initOwnerDataBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("types")
	public Collection<PetType> allPetTypes() {
		return  petTypeService.findAll();
	}
	
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return ownerService.findById(ownerId).orElseThrow( () -> new RuntimeException("Owner not Found"));
	}
	
	@GetMapping("/pets/new")
	public String initCreatePetForm(Owner owner, Model model) {
		Pet pet = new Pet();
		owner.addPet(pet);
		model.addAttribute("pet", pet);
		
		return VIEW_CREATE_OR_UPDATE_PET;
	}
	
	@PostMapping("/pets/new")
	public String handelCreatePetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if (StringUtils.isNotBlank(pet.getName()) && pet.isNew() && owner.contains(pet)){
            result.rejectValue("name", "duplicate", "already exists");
        }
		
		if(result.hasErrors()) {
			model.addAttribute("pet", pet);
			return VIEW_CREATE_OR_UPDATE_PET;
		} else {
			owner.addPet(pet);
			petService.save(pet);
			
			return "redirect:/owners/"+owner.getId();
		}
	}
	
	@GetMapping("/pets/{id}/update")
	public String initUpdatePetForm(@PathVariable Long id, Model model) {
		var pet = petService.findById(id).orElseThrow( () -> new RuntimeException("Pet not found"));
		model.addAttribute("pet", pet);
		
		return VIEW_CREATE_OR_UPDATE_PET;
	}
	
	@PostMapping("/pets/{id}/update")
	public String handelUpdatePetForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
		if(result.hasErrors()) {
			pet.setOwner(owner);
			model.addAttribute("pet", pet);
			return VIEW_CREATE_OR_UPDATE_PET;
		} else {
			var savedPet = petService.findById(pet.getId()).orElseThrow( () -> new RuntimeException("Pet not found"));
			savedPet.setBirthDate(pet.getBirthDate());
			savedPet.setName(pet.getName());
			savedPet.setType(pet.getType());
			owner.addPet(savedPet);
			petService.save(savedPet);
			
			return "redirect:/owners/"+owner.getId();
		}
	}
}
