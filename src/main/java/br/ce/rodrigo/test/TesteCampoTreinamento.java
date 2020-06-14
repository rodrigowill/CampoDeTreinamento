package br.ce.rodrigo.test;
import static br.ce.rodrigo.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.ce.rodrigo.core.DSL;
import br.ce.rodrigo.core.DriverFactory;

public class TesteCampoTreinamento {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testeTextField() {	
		dsl.escrever("elementosForm:nome", "Rodrigo");
		Assert.assertEquals("Rodrigo", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo() {
		dsl.escrever("elementosForm:nome","Rodrigo");
		Assert.assertEquals("Rodrigo", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome","Soares");
		Assert.assertEquals("Soares", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Teste\nTeste2");
		Assert.assertEquals("Teste\nTeste2", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteragirComComboBox() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
		
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		// deseleciona opção
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}	
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	public void deveInteragirComLink() {
		dsl.clicarLink("Voltar");
		// caso o teste não seja terminado, garantir q falhe para evitar falso positivo e esquecermos de termina-lo
		//Opção 1
		//Assert.fail();
		//Opção 2
		// @Ignore
		assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextoNaPagina() {

		// Primeira opção, mas não indicada
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		//Segunda opção
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3"))); // Como era a primeira ocorrencia, funcionou
		//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.tagName("span")).getText()); // Esse dá erro pq tem outro span antes desse q busco aqui
		
		//A melhor opção é tentar achar algo que garanta a unicidade da ocorrencia do elemento
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

	}
	
	@Test
	public void testaJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
}
