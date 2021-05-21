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
 * 
 */
@Controller
public class PrimaryBeanConstructorInjectedController {
	private final GreetingService service;
	
	/**
	 * @param service
	 * 
	 * NOTE when we use constructor method the @Autowire annotation is optional since Spring 4.2
	 * 
	 * NOTE in this exercise evolution, we have declared the service PrimaryGreetingServiceImpl as primary implementation of the GreatingService.
	 * This means Spring candidate that as default when multiple class implements a certain interface a no Qualifier has been used
	 */
	public PrimaryBeanConstructorInjectedController(GreetingService service) {
		super();
		this.service = service;
	}

	public String sayHello() {
		return service.sayHello();
	}
}
