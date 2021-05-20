/**
 * 
 */
package courses.sov.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import courses.sov.petclinic.service.OwnerService;

/**
 * @author dcividin
 *
 */
@Controller
public class OwnerController {
//	private final OwnerService service;
//	
//	public OwnerController(OwnerService service) {
//		this.service = service;
//	}
	
	@RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
	public String list(Model model) {
//		model.addAttribute("list", service.findAll());
		
		return "owners/index";
	}
}
