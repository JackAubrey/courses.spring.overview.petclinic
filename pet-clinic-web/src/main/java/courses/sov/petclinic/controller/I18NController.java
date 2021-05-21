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
 * NOTE: this example take the advantage of "profiles".
 */
@Controller
public class I18NController {
	private final GreetingService greetingService;
	
	/**
	 * 
	 * @param greetingService
	 * 
	 * NOTE: remember in this case, since we are using the constructor method, is not required the @Autorired annotation.
	 * NOTE: we are using the qualifier in order to use a GreetingService with name "i18nService". There are 3 with the same name.
	 * 		Which will be used? In order to wire Spring to take the right one, we have annotated those 3 with @Profile.
	 * 		Now:
	 * 		1 - to activate a profile you have to specify via application.properties or equivalent external parameter too.
	 * 		2 - If no active profile is not set, Spring does not want rise up. So For this reason we have provided the 
	 * 			I18NEnglishGreetingService with a @Profile with two values. "EN" and "default". The last one says to Spring
	 * 			to consider it as default when no active profile is set.
	 */
	public I18NController(@Qualifier("i18nService") GreetingService greetingService) {
		this.greetingService = greetingService;
	}
	
	public String sayHello() {
		return greetingService.sayHello();
	}
}
