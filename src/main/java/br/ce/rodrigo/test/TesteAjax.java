package br.ce.rodrigo.test;
import static br.ce.rodrigo.core.DriverFactory.getDriver;
import static br.ce.rodrigo.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.rodrigo.core.DSL;

public class TesteAjax {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("http://www.primefaces.org;showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void testarAjax() {
		dsl.escrever("j_idt85:nome", "Teste");
		dsl.clicarBotao("j_idt85:j_idt88");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt85:display"));
	}

}
