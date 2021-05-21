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
public class EsGreetingServiceImpl implements GreetingService {

	@Override
	public String sayHello() {
		return "Hola Mundo!!";
	}

}
