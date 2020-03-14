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
	}

	/**
	 * Registro de usuarios con datos válidos
	 */
	@Test
	public void prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "josesfoEmail", "Josefo", "Perez", "77777", "77777"); // Comprobamos que
																								// entramos
																								// en la sección privada
		PO_View.checkElement(driver, "text", "Bienvenido");
	}

	/**
	 * Registro de usuarios con datos inválidos (vacíos)
	 */
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

	/**
	 * Registro de usuario con datos inválidos (contraseña inválida)
	 */
	@Test
	public void prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "JosefoEmail", "Josefo", "Perez", "77577", "77777"); // Comprobamos que
																								// entramos
		PO_View.checkElement(driver, "text", "Regístrate");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}

	/**
	 * Registro de usuario con datos inválidos (email existente)
	 */
	@Test
	public void prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "lucasnuñez@correo.com", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH()); // Rellenamos el

	}

	/**
	 * Inicio sesión con datos válidos (administrador)
	 */
	@Test
	public void prueba5() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");

	}

	/**
	 * Inicio sesión con datos válidos (estándar)
	 */
	@Test
	public void prueba6() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

	}

	/**
	 * Inicio de sesión con datos inválidos (vacíos)
	 */
	@Test
	public void prueba7() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "", "123456");
		PO_View.checkElement(driver, "text", "Contraseña");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "");
		PO_View.checkElement(driver, "text", "Contraseña");

	}

	/**
	 * Inicio de sesión con datos válidos pero contraña incorrecta
	 */
	@Test
	public void prueba8() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "cxzcz");
		PO_View.checkElement(driver, "text", "Contraseña");
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());

	}

	/**
	 * Salir de sesión y redirigir a login
	 */
	@Test
	public void prueba9() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

	}

	/**
	 * Comprobar que el desconectar solo sale cuando el usuario está en sesión
	 */
	@Test
	public void prueba10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
	}

	/**
	 * aceptar una solicitud que se ha recibido
	 */
	@Test
	public void prueba18() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[1]/td[4]/div/a");

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

		PO_LoginView.fillForm(driver, "mariarodriguez@correo.com", "123456");

		PO_PrivateView.acceptRequest(driver, "lucasnuñez@correo.com");

	}

	/**
	 * Mostrar el listado de amigos de un usuario. Comprobar que el listado contiene
	 * los amigos que deben ser.
	 */
	@Test
	public void prueba19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[2]/td[4]/div/a"); // martaalmonte

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[3]/td[4]/div/a"); // pelayovaldes

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[4]/td[4]/div/a"); // edwardnuñez

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

		PO_LoginView.fillForm(driver, "martaalmonte@correo.com", "123456");
		PO_PrivateView.acceptRequest(driver, "lucasnuñez@correo.com");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

		PO_LoginView.fillForm(driver, "pelayovaldes@correo.com", "123456");
		PO_PrivateView.acceptRequest(driver, "lucasnuñez@correo.com");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

		PO_LoginView.fillForm(driver, "edwardnuñez@correo.com", "123456");
		PO_PrivateView.acceptRequest(driver, "lucasnuñez@correo.com");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Contraseña");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'friends-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/friendRequest/listAccepted')]", 0);

		PO_PrivateView.checkListFriendsContains(driver, "martaalmonte@correo.com", "pelayovaldes@correo.com",
				"edwardnuñez@correo.com");

	}

	/**
	 * Visualizar al menos cuatro páginas en Español/Inglés/Español (comprobando que
	 * algunas de las etiquetas cambian al idioma correspondiente).
	 */
	@Test
	public void prueba20() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());

		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH()); //

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");

		PO_HomeView.changeIdiom(driver, "btnSpanish");

		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_View.checkElement(driver, "text", "Usuarios");

		PO_HomeView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Users");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_View.checkElement(driver, "text", "Users");

	}

	/**
	 * Comprobar que al entrar como usuario, se listan todos los usuarios existentes
	 * menos él mismo
	 */
	@Test
	public void prueba31() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_PrivateView.checkListFriendsContains(driver, "martaalmonte@correo.com", "pelayovaldes@correo.com",
				"lucasnuñez@correo.com");
		PO_NavView.goToPage(driver, "1");
		PO_PrivateView.checkListFriendsContains(driver, "sofi", "luci", "admin2@email.com");

	}

	/**
	 * Ir a la lista de usuarios, borrar el primer usuario de la lista, comprobar
	 * que la lista se actualiza y dicho usuario desaparece.
	 * 
	 */
	@Test
	public void prueba32() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_PrivateView.deleteUser(driver, "pedrodiaz@correo.com");
		SeleniumUtils.textoNoPresentePagina(driver, "pedrodiaz@correo.com");

	}

	/**
	 * Ir a la lista de usuarios, borrar el último usuario de la lista, comprobar
	 * que la lista se actualiza y dicho usuario desaparece.
	 * 
	 */
	@Test
	public void prueba33() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.goToPage(driver, "1");

		PO_PrivateView.deleteUser(driver, "admin3@email.com");
		SeleniumUtils.textoNoPresentePagina(driver, "admin3@email.com");

	}

	/**
	 * Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	 * actualiza y dichos usuarios desaparecen.
	 * 
	 */
	@Test
	public void prueba34() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_PrivateView.deleteUsers(driver, "pelayovaldes@correo.com", "martaalmonte@correo.com",
				"mariarodriguez@correo.com");
		PO_View.checkElement(driver, "text", "Usuarios");
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "pedrodiaz@correo.com", PO_View.getTimeout());
		SeleniumUtils.textoNoPresentePagina(driver, "pedrodiaz@correo.com");
		SeleniumUtils.textoNoPresentePagina(driver, "martaalmonte@correo.com");
		SeleniumUtils.textoNoPresentePagina(driver, "mariarodriguez@correo.com");

	}

}
