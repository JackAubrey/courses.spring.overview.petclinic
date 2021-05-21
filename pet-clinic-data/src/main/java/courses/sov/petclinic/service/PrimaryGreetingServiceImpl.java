/**
 * 
 */
package courses.sov.petclinic.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author dcividin
 * 
 * Since this GreetingService implementation is annotated with @Primary, when spring found multiple implementation of the same interface
 * will use that one annotated as Primary.
 * 
 * NOTE the @Primary annotation is used when multiple implementations of the same interface has been found by Spring and no @Qualifier 
 * was used by the component that want to be injected in it.
 */
@Service
@Primary
public class PrimaryGreetingServiceImpl implements GreetingService {

	@Override
	public String sayHello() {
		return "Hi!! I'm the primary bean delgated to say you an hello.";
	}

}
