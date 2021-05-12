/**
 * 
 */
package courses.sov.petclinic.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author dcividin
 *
 * NOTE this service will brought up only when the active profile is "EN".
 * We can activate the profile via application.properties 
 * 
 * NOTE in this case the @Profile annotation specify two values.
 * "default" value says to Spring "hey if no profile set, use this as default".
 */
@Profile({"EN", "default"})
@Service("i18nService")
public class I18NEnglishGreetingService implements GreetingService {

	@Override
	public String sayHello() {
		return "I18N - Hello World!!";
	}

}
