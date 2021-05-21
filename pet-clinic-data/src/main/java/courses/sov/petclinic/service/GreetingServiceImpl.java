/**
 * 
 */
package courses.sov.petclinic.service;

import org.springframework.stereotype.Service;

/**
 * @author dcividin
 *
 */
@Service
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String sayHello() {
		return "Hello Folks!!";
	}

}
