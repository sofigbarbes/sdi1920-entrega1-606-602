package com.socialNetwork.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.socialNetwork.tests.pageObjects.PO_HomeView;
import com.socialNetwork.tests.pageObjects.PO_LoginView;
import com.socialNetwork.tests.pageObjects.PO_NavView;
import com.socialNetwork.tests.pageObjects.PO_PrivateView;
import com.socialNetwork.tests.pageObjects.PO_Properties;
import com.socialNetwork.tests.pageObjects.PO_RegisterView;
import com.socialNetwork.tests.pageObjects.PO_View;

import socialNetwork.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {
	static String PathFirefox65 = "D:\\PROG\\FIREFOX\\FIREFOX\\firefox.exe";
	static String Geckdriver024 = "D:\\PROG\\FIREFOX\\MATERIAL\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8070";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home

	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
	// home

	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta
	// Español

	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH()); //
		SeleniumUtils.esperarSegundos(driver, 2);
	}

	@Test
	public void prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "josesfoEmail", "Josefo", "Perez", "77777", "77777"); // Comprobamos que
																								// entramos
																								// en la sección privada
		PO_View.checkElement(driver, "text", "Bienvenido");
	}

	@Test
	public void prueba2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777"); // Comprobamos que entramos
		// en la sección privada
		PO_View.checkElement(driver, "text", "Regístrate");
		PO_RegisterView.fillForm(driver, "JosefoEmail1", "", "Perez", "77777", "77777"); // Comprobamos que entramos
		PO_View.checkElement(driver, "text", "Regístrate");
		PO_RegisterView.fillForm(driver, "JosefoEmail2", "Josefo", "", "77777", "77777"); // Comprobamos que entramos
		PO_View.checkElement(driver, "text", "Regístrate");
		PO_RegisterView.fillForm(driver, "JosefoEmail2", "Josefo", "Perez", "", "77777"); // Comprobamos que entramos
		PO_View.checkElement(driver, "text", "Regístrate");

	}

	@Test
	public void prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "JosefoEmail", "Josefo", "Perez", "77577", "77777"); // Comprobamos que entramos
		PO_View.checkElement(driver, "text", "Regístrate");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}
	@Test
	public void prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "lucasnuñez@correo.com", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH()); // Rellenamos el
		
	}
	
	/*
	 * Inicio sesión con datos válidos (administrador)
	 */
	@Test
	public void prueba5() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com","admin");
		PO_View.checkElement(driver, "text", "Bienvenido");

	}
	
	/*
	 * Inicio sesión con datos válidos (estándar)
	 */
	@Test
	public void prueba6() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com","123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

	}

	/*
	 *Inicio de sesión con datos inválidos (vacíos)
	 */
	@Test
	public void prueba7() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "","123456");
		PO_View.checkElement(driver, "text", "Contraseña");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com","");
		PO_View.checkElement(driver, "text", "Contraseña");

	}
	
	
	/*
	 *Inicio de sesión con datos válidos pero contraña vacía
	 */
	@Test
	public void prueba8() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com","");
		PO_View.checkElement(driver, "text", "Contraseña");

	}

	@Test
	public void prueba9() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

	}	

	@Test
	public void prueba10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsers");

		PO_PrivateView.checkElementClickIndex(driver, "/html/body/div/div[1]/table/tbody/tr[1]/td[4]/div/a", 0); //XPATH COPIED FROM FIREFOX
		PO_View.checkElement(driver, "text", "Solicitud enviada");

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");



	}

}
