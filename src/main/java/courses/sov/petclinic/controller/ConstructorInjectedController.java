/**
 * 
 */
package courses.sov.petclinic.controller;

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
	 */
	public ConstructorInjectedController(GreetingService service) {
		super();
		this.service = service;
	}

	public String sayHello() {
		return service.sayHello();
	}
}
