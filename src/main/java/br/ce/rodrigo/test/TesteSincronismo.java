package br.ce.rodrigo.test;
import static br.ce.rodrigo.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.rodrigo.core.DSL;
import br.ce.rodrigo.core.DriverFactory;

public class TesteSincronismo {
	
	private DSL dsl;
	
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // usado geralmente no @before para que todos os testes utilizem essa administração de espera implicita
		dsl.clicarBotao("buttonDelay");		
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {		
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");
	}

}
