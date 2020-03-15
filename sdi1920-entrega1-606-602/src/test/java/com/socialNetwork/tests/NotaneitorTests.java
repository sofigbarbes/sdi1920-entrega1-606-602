package com.socialNetwork.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public void _pruebaInicio() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}
 
	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta
	// Español

	@Test
	public void _pruebaIdioma() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH()); 
	}

	/**
	 * Registro de usuarios con datos válidos
	 */
	@Test
	public void prueba01() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "josesfoEmail", "Josefo", "Perez", "77777", "77777"); 
		PO_View.checkElement(driver, "text", "Bienvenido");
	}

	/**
	 * Registro de usuarios con datos inválidos (vacíos)
	 */
	@Test
	public void prueba02() {
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
	public void prueba03() {
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
	public void prueba04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "lucasnuñez@correo.com", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH()); // Rellenamos el

	}

	/**
	 * Inicio sesión con datos válidos (administrador)
	 */
	@Test
	public void prueba05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Bienvenido");

	}

	/**
	 * Inicio sesión con datos válidos (estándar)
	 */
	@Test
	public void prueba06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

	}

	/**
	 * Inicio de sesión con datos inválidos (vacíos)
	 */
	@Test
	public void prueba07() {
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
	public void prueba08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "cxzcz");
		PO_View.checkElement(driver, "text", "Contraseña");
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());

	}

	/**
	 * Salir de sesión y redirigir a login
	 */
	@Test
	public void prueba09() {
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
	 * Mostrar el listado de usuarios
	 */
	@Test
	public void prueba11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();
		
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
	}
	
	/**
	 * Busqueda vacia
	 */
	@Test
	public void prueba12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();
		
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
	}
	
	/**
	 * Busqueda sin resultados
	 */
	@Test
	public void prueba13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();
		
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		search.sendKeys("nadie");
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	/**
	 * Busqueda con resultados
	 */
	@Test
	public void prueba14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();
		
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		search.sendKeys("luci");
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}
	
	/**
	 * Enviar una peticion de amistad
	 */
	@Test
	public void prueba15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();
		
		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[1]/td[4]/div/a");
		PO_View.checkElement(driver, "text", "Solicitud enviada");
	}
	
	/**
	 * Enviar peticion de amistad a la misma persona dos veces
	 */
	@Test
	public void prueba16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "sofi", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();
		
		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[2]/td[4]/div/a");
		PO_View.checkElement(driver, "text", "Solicitud enviada");
	}

	/**
	 * Mostrar el listado de solicitudes de amistad
	 */
	@Test
	public void prueba17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");
		// Contamos el número de filas de notas
		// Pinchamos en la opción de menu de Usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'listUsers')]/a");
		elementos.get(0).click();

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[2]/td[4]/div/a");

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "martaalmonte@correo.com", "123456");

		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'friends-menu')]/a");
		elementos.get(0).click();

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/request/list')]");
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
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

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[3]/td[4]/div/a"); // pelayovaldes

		PO_PrivateView.sendFriendRequest(driver, "/html/body/div/div[1]/table/tbody/tr[4]/td[4]/div/a"); // edwardnuñez

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

		PO_PrivateView.checkListFriendsContains(driver, "pelayovaldes@correo.com",
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
	 * No poder acceder al listado de usuarios sin autenticarse
	 */
	@Test
	public void prueba21() {
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Usuarios", PO_View.getTimeout());
	}
	
	/**
	 * No poder acceder al listado de publicaciones sin autenticarse
	 */
	@Test
	public void prueba22() {
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Menú de Publicaciones", PO_View.getTimeout());
	}
	
	/**
	 * No poder ver cosas de administrador siendo usuario normal
	 */
	@Test
	public void prueba23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Administrador", PO_View.getTimeout());
		
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		SeleniumUtils.textoPresentePagina(driver, "Administrador");
	}
	
	/**
	 * Buena publicación
	 */
	@Test
	public void prueba24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'posts-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/post/add')]", 0);
		PO_PrivateView.añadirPublicacionSinFoto(driver, "P1", "La publicacion");
		PO_View.checkElement(driver, "text", "publicaciones");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "P1", PO_View.getTimeout());
	}
	
	/**
	 * Mala publicación
	 */
	@Test
	public void prueba25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");
		
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'posts-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/post/add')]", 0);
		PO_PrivateView.añadirPublicacionSinFoto(driver, "", "La publicacion2");
		
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'posts-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/post/list')]", 0);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "La publicacion2", PO_View.getTimeout());
	}
	
	
	/**
	 * Listar publicaciones
	 */
	@Test
	public void prueba26() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "luci", "123456");
		
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'posts-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/post/list')]", 0);
		
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}
	
	/**
	 * Listar publicaciones de amigo
	 */
	@Test
	public void prueba27() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "luci", "123456");
		
		driver.get("http://localhost:8070/friendRequest/sofi/posts");
		
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 2);
	}
	
	/**
	 * Error al listar publicaciones de un no amigo
	 */
	@Test
	public void prueba28() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "luci", "123456");
		
		driver.get("http://localhost:8070/friendRequest/admin@email.com/posts");
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Error", PO_View.getTimeout());
	}

	/**
	 * Probar publicación con foto
	 */
	@Test
	public void prueba29() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "lucasnuñez@correo.com", "123456");
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'posts-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/post/add')]", 0);
		PO_PrivateView.añadirPublicacionConFoto(driver, "hola", "Sofia", "D:\\Usuarios\\Sofia\\fondo.jpg");
		PO_View.checkElement(driver, "text", "publicaciones");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Multimedia", PO_View.getTimeout());

	}

	/**
	 * Probar publicación sin foto
	 */
	@Test
	public void prueba30() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "sofi", "123456");
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'posts-menu')]/a", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, '/post/add')]", 0);

		PO_PrivateView.añadirPublicacionSinFoto(driver, "hola", "Sofia");
		PO_View.checkElement(driver, "text", "publicaciones");

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
