/**
 * 
 */
package courses.sov.petclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import courses.sov.petclinic.service.GreetingService;

/**
 * @author dcividin
 *
 * Worst solution. absolutely don't do it
 */
@Controller
public class PropertyInjectedController {
	/**
	 * NOTE in this exercise evolution, since we have two implementation of the GreetingService interface
	 * 		we use the @Qualifier annotation to says which one we want to use.
	 * 		without this, Spring fails during startup because it does not knows which implementation you want.
	 */
	@Autowired
	@Qualifier("greetingServiceImpl")
	public GreetingService service;
	
	public String sayHello() {
		return service.sayHello();
	}
}
