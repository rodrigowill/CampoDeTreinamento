package br.ce.rodrigo.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void teste() {
	//	System.setProperty("webdriver.gecko.driver", "caminho para o driver");
//		System.setProperty("webdriver.chrome.driver", "caminho para o driver");
	//	WebDriver driver = new ChromeDriver();
	//	WebDriver driver = new InternetExplorerDriver();
	//	driver.manage().window().setPosition(new Point(100, 100));
	//	driver.manage().window().setSize(new Dimension(1200, 765));
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit(); // fecha o browser e finaliza o processo
	}

}
