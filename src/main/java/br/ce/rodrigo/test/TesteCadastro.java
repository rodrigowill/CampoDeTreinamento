package br.ce.rodrigo.test;
import static br.ce.rodrigo.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.rodrigo.core.BaseTest;
import br.ce.rodrigo.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest{
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	
	@Test
	public void deveRealizarCadastroComSucesso() {
		page.setNome("Rodrigo");
		page.setSobrenome("Soares");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Natacao");
		page.setEsporte("Corrida");		
		page.cadastrar();
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Rodrigo", page.obterNomeCadastro());
		Assert.assertEquals("Soares", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao Corrida", page.obterEsporteCadastro());
	}
}