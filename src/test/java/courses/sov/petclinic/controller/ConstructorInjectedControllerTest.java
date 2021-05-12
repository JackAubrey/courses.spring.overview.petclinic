package courses.sov.petclinic.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.sov.petclinic.service.GreetingServiceImpl;

class ConstructorInjectedControllerTest {
	ConstructorInjectedController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new ConstructorInjectedController(new GreetingServiceImpl());
	}

	@Test
	void testSayHello() {
		assertThat(controller.sayHello()).isNotEmpty();
	}

}
