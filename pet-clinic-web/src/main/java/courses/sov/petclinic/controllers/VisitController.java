/**
 * 
 */
package courses.sov.petclinic.controllers;

import javax.validation.Valid;

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
import courses.sov.petclinic.model.Visit;
import courses.sov.petclinic.service.PetService;
import courses.sov.petclinic.service.VisitService;

/**
 * @author dcividin
 *
 */
@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {
	public static final String VIEW_CREATE_OR_UPDATE_PET = "pets/createOrUpdateVisitForm";
	
	private final PetService petService;
	private final VisitService visitService;
	
	public VisitController(PetService petService, VisitService visitService) {
		this.petService = petService;
		this.visitService = visitService;
	}
	
	@InitBinder
	public void initOwnerDataBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("visit")
	public Visit loadPetWithVisitPetTypes(@PathVariable("petId") Long petId, Model model) {
		var pet = petService.findById(petId).orElseThrow( () -> new RuntimeException("No visit found") );
		var visit = new Visit();
		
		model.addAttribute("pet", pet);
		pet.addVisit(visit);
		visit.setPet(pet);
		
		return visit;
	}
	
	@GetMapping("/visits/new")
	public String initCreatePetForm(Owner owner, Model model) {
		return VIEW_CREATE_OR_UPDATE_PET;
	}
	
	@PostMapping("/visits/new")
	public String handelCreatePetForm(@Valid Visit visit, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return VIEW_CREATE_OR_UPDATE_PET;
		} else {
			visitService.save(visit);
			
			return "redirect:/owners/{ownerId}";
		}
	}
}
