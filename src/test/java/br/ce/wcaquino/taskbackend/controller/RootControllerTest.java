package br.ce.wcaquino.taskbackend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class RootControllerTest {

	@InjectMocks
	private RootController controller;
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		Assertions.assertEquals("Hello World!", controller.hello());
	}
}
