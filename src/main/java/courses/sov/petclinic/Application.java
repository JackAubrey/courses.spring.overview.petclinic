package courses.sov.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import courses.sov.petclinic.controller.ConstructorInjectedController;
import courses.sov.petclinic.controller.MyController;
import courses.sov.petclinic.controller.PropertyInjectedController;
import courses.sov.petclinic.controller.SetterInjectedController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		MyController myController = (MyController)ctx.getBean("myController");
		
		String greeting = myController.sayHello();
		
		System.out.println("----- Controller Greeting");
		System.out.println(greeting);
		
		System.out.println("----- Property Service Greeting");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController)ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.sayHello());
		
		System.out.println("----- Setter Service Greeting");
		SetterInjectedController setterInjectedController = (SetterInjectedController)ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.sayHello());
		
		System.out.println("----- Constructor Service Greeting");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController)ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.sayHello());
	}

}
