package com.socialNetwork.tests.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import socialNetwork.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	static public void loginUserCheck(WebDriver driver, String user, String password, String condition) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, user, password);
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", condition);
	}

	static public List<WebElement> checkElementClickIndex(WebDriver driver, String xPath, int index) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", xPath);
		elementos.get(index).click();
		return elementos;

	}

	static public void fillFormAddProfessor(WebDriver driver, String dnip, String namep, String lastnamep,
			String categoria) {
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("nombre"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("apellido"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);

		WebElement category = driver.findElement(By.name("categoria"));
		category.click();
		category.clear();
		category.sendKeys(lastnamep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void sendFriendRequest(WebDriver driver, String xPathToSend) {
		PO_View.checkElement(driver, "text", "Usuarios");
		PO_NavView.clickOption(driver, "/user/list", "id", "listUsersTable");
		PO_PrivateView.checkElementClickIndex(driver, xPathToSend, 0); // XPATH COPIED FROM FIREFOX
		PO_View.checkElement(driver, "text", "Solicitud enviada");
	}

	public static void acceptRequest(WebDriver driver, String correoAceptar) {
		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'friends-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'request/list')]", 0);
		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'request/" + correoAceptar + "/accept')]",
				0);

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'listUsers')]/a", 0);
		PO_View.checkElement(driver, "text", "Ya agregado");
	}

	public static List<WebElement> getNumberElements(WebDriver driver, String path) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", path);
		return elementos;
	}

	public static void checkListFriendsContains(WebDriver driver, String... string) {
		for (String s : string) {
			checkElement(driver, "//*[@id='" + s + "']");
		}

	}

	private static void checkElement(WebDriver driver, String type) {
		PO_View.checkElement(driver, "free", type);
	}
	public static void deleteUser(WebDriver driver, String string) {
		clickOnHRef(driver, "/user/delete/"+string); //primero de la lista
	}
	public static void deleteUsers(WebDriver driver, String... emails) {
		for (String s : emails) {
			checkElementClickIndex(driver, "//*[@id='" + s + "']",0);
		}
		checkElementClickIndex(driver,"//*[@id=\"deleteAll\"]",0);
		
	}
	
	public static void añadirPublicacionSinFoto(WebDriver driver, String title, String text) {
		WebElement titlew = driver.findElement(By.name("title"));
		titlew.clear();
		titlew.sendKeys(title);
		WebElement score = driver.findElement(By.name("text"));
		score.click();
		score.clear();
		score.sendKeys(text);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void añadirPublicacionConFoto(WebDriver driver, String title, String text, String path) {
		WebElement elem = driver.findElement(By.xpath("//input[@type='file']"));
		elem.sendKeys(path);
		
		añadirPublicacionSinFoto(driver, title, text);
	}

}
