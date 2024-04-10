package br.ce.wcaquino.taskbackend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RootControllerTest {

	@InjectMocks
	private RootController controller;
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		Assertions.assertEquals("Hello World!", controller.hello());
	}
}
