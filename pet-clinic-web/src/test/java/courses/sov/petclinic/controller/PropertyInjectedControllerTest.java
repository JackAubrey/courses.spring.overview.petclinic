package courses.sov.petclinic.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.sov.petclinic.service.GreetingServiceImpl;

class PropertyInjectedControllerTest {
	PropertyInjectedController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new PropertyInjectedController();
		controller.service = new GreetingServiceImpl();
	}

	@Test
	void testSayHello() {
		assertThat(controller.sayHello()).isNotEmpty();
	}

}
