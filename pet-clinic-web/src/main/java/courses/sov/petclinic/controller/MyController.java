/**
 * 
 */
package courses.sov.petclinic.controller;

import org.springframework.stereotype.Controller;

/**
 * @author dcividin
 *
 */
@Controller
public class MyController {
	public String sayHello() {
		System.out.println("hello World");;
		
		return "Hi Folks!!!";
	}
}
