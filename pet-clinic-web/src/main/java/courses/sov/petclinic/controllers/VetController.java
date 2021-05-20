/**
 * 
 */
package courses.sov.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import courses.sov.petclinic.service.VetService;

/**
 * @author dcividin
 *
 */
@RequestMapping("/vets")
@Controller
public class VetController {
//	private final VetService service;
//	
//	public VetController(VetService service) {
//		this.service = service;
//	}
	
	@RequestMapping({"", "/", "/index", "/index.html"})
	public String list(Model model) {
//		model.addAttribute("list", service.findAll());
		
		return "vets/index";
	}
}
