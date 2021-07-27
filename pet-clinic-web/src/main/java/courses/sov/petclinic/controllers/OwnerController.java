/**
 * 
 */
package courses.sov.petclinic.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.service.OwnerService;

/**
 * @author dcividin
 *
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {
	public static final String VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
	private final OwnerService service;
	
	public OwnerController(OwnerService service) {
		this.service = service;
	}
	
	@InitBinder
	void setupBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}
	
	@GetMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model) {
		model.addAttribute("owners", service.findAll());
		
		return "owners/index";
	}
	
	@GetMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", new Owner());
		
		return "owners/findOwners";
	}
	
	@GetMapping("/doFind")
	public String handleFindForm(Owner owner, BindingResult result, Model model) {
		String lastname = owner == null || owner.getLastName() == null ? "" : owner.getLastName();
		
		List<Owner> ownersFound = service.findAllByLastNameContainingIgnoreCase(lastname);
		
		if(ownersFound.isEmpty()) {
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if(ownersFound.size() == 1) {
			return "redirect:/owners/"+ownersFound.iterator().next().getId();
		} else {
			model.addAttribute("owners", ownersFound);
			return "owners/ownersList";
		}
	}
	
	@GetMapping("/{id}")
	public ModelAndView getOwner(@PathVariable Long id, Model model) {
		var mav = new ModelAndView("owners/ownerDetails");
		mav.addObject("owner", service.findById(id).orElseThrow( () -> new RuntimeException("not found")));
		
		return mav;
	}
	
	@GetMapping("/new")
	public String initCreatOwnerForm(Model model) {
		model.addAttribute("owner", new Owner());
		
		return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
	}
	
	@PostMapping("/new")
	public String processCreatOwnerForm(@Valid Owner owner, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
		}
		
		var savedOwner = service.save(owner);
		model.addAttribute("owner", savedOwner);
		
		return "redirect:/owners/"+savedOwner.getId();
	}
	
	@GetMapping("/{id}/update")
	public String initUpdateOwnerForm(@PathVariable Long id, Model model) {
		Optional<Owner> owner = service.findById(id);
		
		model.addAttribute("owner", owner.orElseThrow( () -> new RuntimeException("Not found")));
		
		return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
	}
	
	@PostMapping("/{id}/update")
	public String processUpdateOwnerForm(@PathVariable Long id, @Valid Owner owner, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
		}
		
		var ownerToUpdate = service.findById(id).orElseThrow( () -> new RuntimeException("Not found"));
		
		ownerToUpdate.setAddress(owner.getAddress());
		ownerToUpdate.setCity(owner.getCity());
		ownerToUpdate.setFirstName(owner.getFirstName());
		ownerToUpdate.setLastName(owner.getLastName());
		ownerToUpdate.setTelephone(owner.getTelephone());
		
		var savedOwner = service.save(ownerToUpdate);
		model.addAttribute("owner", savedOwner);
		
		return "redirect:/owners/"+savedOwner.getId();
	}
}
