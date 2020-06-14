package br.ce.rodrigo.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.rodrigo.core.DSL;
import br.ce.rodrigo.core.DriverFactory;



public class TestePrime {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		
		// id dinâmico na tela. nesse caso é melhor pedir ao desenvolvedor criar um id.		
		dsl.clicarRadio(By.xpath("//input[@id='j_idt722:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt722:console:0"));
		// se não for possível, pode-se buscar por texto (caso haja apenas uma ocorrência na tela
		dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt722:console:1"));
	}

}
