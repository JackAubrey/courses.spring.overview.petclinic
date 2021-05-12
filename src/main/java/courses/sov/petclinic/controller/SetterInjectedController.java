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
 * Nice but not so best solution
 */
@Controller
public class SetterInjectedController {
	private GreetingService service;
	
	public String sayHello() {
		return service.sayHello();
	}

	/**
	 * @param service the service to set
	 */
	@Autowired
	public void setService(GreetingService service) {
		this.service = service;
	}
}
