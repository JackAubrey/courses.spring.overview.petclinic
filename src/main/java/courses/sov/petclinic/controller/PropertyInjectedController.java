/**
 * 
 */
package courses.sov.petclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import courses.sov.petclinic.service.GreetingService;

/**
 * @author dcividin
 *
 * Worst solution. absolutely don't do it
 */
@Controller
public class PropertyInjectedController {
	@Autowired
	public GreetingService service;
	
	public String sayHello() {
		return service.sayHello();
	}
}
