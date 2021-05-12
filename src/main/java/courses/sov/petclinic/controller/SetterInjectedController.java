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
	 * 
	 * NOTE in this exercise evolution, since we have two implementation of the GreetingService interface
	 * 		we use the @Qualifier annotation to says which one we want to use.
	 * 		without this, Spring fails during startup because it does not knows which implementation you want.
	 */
	@Autowired
	@Qualifier("esGreetingServiceImpl")
	public void setService(GreetingService service) {
		this.service = service;
	}
}
