/**
 * 
 */
package courses.sov.petclinic.controller;

import courses.sov.petclinic.service.GreetingService;

/**
 * @author dcividin
 *
 * Worst solution. absolutely don't do it
 */
public class PropertyInjectedController {
	public GreetingService service;
	
	public String sayHello() {
		return service.sayHello();
	}
}
