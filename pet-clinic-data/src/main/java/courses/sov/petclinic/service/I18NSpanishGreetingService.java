/**
 * 
 */
package courses.sov.petclinic.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author dcividin
 *
 * NOTE this service will brought up only when the active profile is "ES".
 * We can activate the profile via application.properties 
 */
@Profile("ES")
@Service("i18nService")
public class I18NSpanishGreetingService implements GreetingService {

	@Override
	public String sayHello() {
		return "I18N - Hello Mundo!!!";
	}

}
