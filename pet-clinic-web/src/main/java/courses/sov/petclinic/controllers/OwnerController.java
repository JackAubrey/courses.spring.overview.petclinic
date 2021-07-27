/**
 * 
 */
package courses.sov.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import courses.sov.petclinic.service.OwnerService;

/**
 * @author dcividin
 *
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {
	private final OwnerService service;
	
	public OwnerController(OwnerService service) {
		this.service = service;
	}
	
	@GetMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model) {
		model.addAttribute("owners", service.findAll());
		
		return "owners/index";
	}
	
	@GetMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owners", service.findAll());
		
		return "owners/ownersList";
	}
	
	@GetMapping("/{id}")
	public ModelAndView getOwner(@PathVariable Long id, Model model) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject("owner", service.findById(id).orElseThrow( () -> new RuntimeException("not found")));
		
		return mav;
	}
}
