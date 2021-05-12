/**
 * 
 */
package courses.sov.petclinic.controller;

import courses.sov.petclinic.service.GreetingService;

/**
 * @author dcividin
 *
 * This is the best practice.
 */
public class ConstructorInjectedController {
	private final GreetingService service;
	
	/**
	 * @param service
	 */
	public ConstructorInjectedController(GreetingService service) {
		super();
		this.service = service;
	}

	public String sayHello() {
		return service.sayHello();
	}
}
