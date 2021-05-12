/**
 * 
 */
package courses.sov.petclinic.controller;

import courses.sov.petclinic.service.GreetingService;

/**
 * @author dcividin
 *
 * Nice but not so best solution
 */
public class SetterInjectedController {
	private GreetingService service;
	
	public String sayHello() {
		return service.sayHello();
	}

	/**
	 * @param service the service to set
	 */
	public void setService(GreetingService service) {
		this.service = service;
	}
}
