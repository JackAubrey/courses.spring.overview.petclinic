package courses.sov.petclinic.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import courses.sov.petclinic.service.GreetingServiceImpl;

class SetterInjectedControllerTest {
	SetterInjectedController controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new SetterInjectedController();
		controller.setService(new GreetingServiceImpl());
	}

	@Test
	void testSayHello() {
		assertThat(controller.sayHello()).isNotEmpty();
	}

}
