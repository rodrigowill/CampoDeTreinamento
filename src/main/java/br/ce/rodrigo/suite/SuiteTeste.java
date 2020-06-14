package br.ce.rodrigo.suite;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.rodrigo.core.DriverFactory;
import br.ce.rodrigo.test.TesteCadastro;
import br.ce.rodrigo.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
})
public class SuiteTeste {

	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
	
}
