package br.ce.rodrigo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.rodrigo.core.DSL;
import br.ce.rodrigo.core.DriverFactory;

public class TestePrimeCombo {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		
		dsl.selecionarComboPrime("j_idt722:console", "PS4");
		Assert.assertEquals("PS4", dsl.obterTexto("j_idt722:console_label"));

	}

}
