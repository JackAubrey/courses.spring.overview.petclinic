/**
 * 
 */
package courses.sov.petclinic.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import courses.sov.petclinic.service.GreetingService;

/**
 * @author dcividin
 *
 * This is the best practice.
 * 
 * NOTE: when we use constructor method the @Autowire annotation is optional since Spring 4.2
 */
@Controller
public class ConstructorInjectedController {
	private final GreetingService service;
	
	/**
	 * @param service
	 * 
	 * NOTE when we use constructor method the @Autowire annotation is optional since Spring 4.2
	 * 
	 * NOTE in this exercise evolution, since we have two implementation of the GreetingService interface
	 * 		we use the @Qualifier annotation to says which one we want to use.
	 * 		without this, Spring fails during startup because it does not knows which implementation you want.
	 */
	public ConstructorInjectedController(@Qualifier("itGreetingServiceImpl") GreetingService service) {
		super();
		this.service = service;
	}

	public String sayHello() {
		return service.sayHello();
	}
}
