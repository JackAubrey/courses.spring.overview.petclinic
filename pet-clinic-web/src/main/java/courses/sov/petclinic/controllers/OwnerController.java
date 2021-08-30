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

import courses.sov.petclinic.exceptions.NotFoundException;
import courses.sov.petclinic.model.Owner;
import courses.sov.petclinic.service.OwnerService;

/**
 * @author dcividin
 *
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {
	private static final String ERR_MSG_NOT_FOUND_THE_OWNER_ID = "Not found the owner id: ";
	
	private static final String VIEW_OWNERS_OWNER_DETAILS = "owners/ownerDetails";
	private static final String VIEW_OWNERS_OWNERS_LIST = "owners/ownersList";
	private static final String VIEW_REDIRECT_OWNERS = "redirect:/owners/";
	private static final String VIEW_OWNERS_FIND_OWNERS = "owners/findOwners";
	public static final String VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
	
	private static final String MODEL_ATTR_OWNERS = "owners";
	private static final String MODEL_ATTR_OWNER = "owner";
	
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
		model.addAttribute(MODEL_ATTR_OWNERS, service.findAll());
		
		return "owners/index";
	}
	
	@GetMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute(MODEL_ATTR_OWNER, new Owner());
		
		return VIEW_OWNERS_FIND_OWNERS;
	}
	
	@GetMapping("/doFind")
	public String handleFindForm(Owner owner, BindingResult result, Model model) {
		String lastname = owner == null || owner.getLastName() == null ? "" : owner.getLastName();
		
		List<Owner> ownersFound = service.findAllByLastNameContainingIgnoreCase(lastname);
		
		if(ownersFound.isEmpty()) {
			result.rejectValue("lastName", "notFound", "not found");
			return VIEW_OWNERS_FIND_OWNERS;
		} else if(ownersFound.size() == 1) {
			return VIEW_REDIRECT_OWNERS+ownersFound.iterator().next().getId();
		} else {
			model.addAttribute(MODEL_ATTR_OWNERS, ownersFound);
			return VIEW_OWNERS_OWNERS_LIST;
		}
	}
	
	@GetMapping("/{id}")
	public ModelAndView getOwner(@PathVariable String id, Model model) {
		var mav = new ModelAndView(VIEW_OWNERS_OWNER_DETAILS);
		mav.addObject(MODEL_ATTR_OWNER, service.findById(Utils.toLong(id)).orElseThrow( () -> new NotFoundException(ERR_MSG_NOT_FOUND_THE_OWNER_ID+id)));
		
		return mav;
	}
	
	@GetMapping("/new")
	public String initCreatOwnerForm(Model model) {
		model.addAttribute(MODEL_ATTR_OWNER, new Owner());
		
		return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
	}
	
	@PostMapping("/new")
	public String processCreatOwnerForm(@Valid Owner owner, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
		}
		
		var savedOwner = service.save(owner);
		model.addAttribute(MODEL_ATTR_OWNER, savedOwner);
		
		return VIEW_REDIRECT_OWNERS+savedOwner.getId();
	}
	
	@GetMapping("/{id}/update")
	public String initUpdateOwnerForm(@PathVariable String id, Model model) {
		Optional<Owner> owner = service.findById(Utils.toLong(id));
		
		model.addAttribute(MODEL_ATTR_OWNER, owner.orElseThrow( () -> new NotFoundException(ERR_MSG_NOT_FOUND_THE_OWNER_ID+id)));
		
		return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
	}
	
	@PostMapping("/{id}/update")
	public String processUpdateOwnerForm(@PathVariable String id, @Valid Owner owner, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return VIEW_NAME_OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
		}
		
		var ownerToUpdate = service.findById(Utils.toLong(id)).orElseThrow( () -> new NotFoundException(ERR_MSG_NOT_FOUND_THE_OWNER_ID+id));
		
		ownerToUpdate.setAddress(owner.getAddress());
		ownerToUpdate.setCity(owner.getCity());
		ownerToUpdate.setFirstName(owner.getFirstName());
		ownerToUpdate.setLastName(owner.getLastName());
		ownerToUpdate.setTelephone(owner.getTelephone());
		
		var savedOwner = service.save(ownerToUpdate);
		model.addAttribute(MODEL_ATTR_OWNER, savedOwner);
		
		return VIEW_REDIRECT_OWNERS+savedOwner.getId();
	}
}
