package br.ce.rodrigo.core;

import static br.ce.rodrigo.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	/******** TextField e TextArea *********/
	
	public void escrever(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escrever(String id_campo, String texto) {
		escrever(By.id(id_campo), texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/******** Radio e Check *********/
	
	public void clicarRadio (By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio (String id) {
		clicarRadio(By.id(id));
	}	

	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck (String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	/******** Combo *********/
	
	public void selecionarCombo(String id, String valor) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao : allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor) {
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
	}
	

	/******** Botão *********/
	
	public void clicarBotao(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	
	/******** Link *********/
	
	public void clicarLink (String link) {
		getDriver().findElement(By.id(link));
	}
	
	/******** Texto *********/
	
	public String obterTexto (By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto (String id) {
		return obterTexto(By.id(id));
	}
	
	/******** Alerts *********/

	public String alertaObterTexto() {
		Alert alerta = getDriver().switchTo().alert();
		return alerta.getText();
	}
	
	public String alertaObterTextoEAceita() {
		Alert alerta = getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.accept();
		return valor;
	}
	
	public String alertaObterTextoENega() {
		Alert alerta = getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.dismiss();
		return valor;
	}
	
	public void alertaEscrever(String valor) {
		Alert alerta = getDriver().switchTo().alert();
		alerta.sendKeys(valor);
		alerta.accept();
	}
	
	/******** Frames e Janelas *********/
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}
	
	/******** JS *********/
	
	public Object executaJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	/******** Tabela *********/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// Achar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
				
		// Achar linha
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
				
		// Achar coluna do botão
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		// Clicar botao
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}	
	
}
