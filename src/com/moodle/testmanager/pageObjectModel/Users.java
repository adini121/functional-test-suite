package com.moodle.testmanager.pageObjectModel;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for the high level interactions with the user such as login.
 *  All interaction with the navigation block is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Users {
	//Internationalization file location
	public static String userData = "properties/data/static/users.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public Users(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties user = new Properties();
		try {
			user.load(new FileInputStream(userData));
		} catch (Exception e) {}
		this.properties.put("loginLink", user.getProperty("loginLink"));
		this.properties.put("logoutLink", user.getProperty("logoutLink"));
	}
/**
 * Select the login link that is available when logged out
 */
	public void selectLoginLink() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement login;
		login = driver.findElement(By .linkText(this.properties.get("loginLink")));
		login.click();
	}
/**
 * Click the login button
 */
	public void clickLoginButton() {
		driver.findElement(By .id("loginbtn")).click();	
	}
/**
 * Select the Logout link
 */
	public void selectLogout() {
		driver.findElement(By .partialLinkText(this.properties.get("logoutLink")) ).click();
	}
/**
 * Enter a username
 * @param user The user username passed from the test.
 */
	public void enterUsername(String user) {
		WebElement username = driver.findElement(By .id("username"));
		username.sendKeys(user);
	}
/**
 * Enter a password
 * @param userPassword The password. This is passed from the test.
 */
	public void enterPassword(String userPassword) {
		WebElement password = driver.findElement(By .id("password"));
		password.sendKeys(userPassword);	
	}
/**
 * Logs a given user into the system. calling this method avoids repeating code.
 * @param uname The Username of the user you want to log into Moodle. Pass this value from the test.
 * @param userPassword The password of the user that you want to log into the system. Pass this value from the test.
 */
	public void loginToSystem(String uname, String userPassword) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement login;
		login = driver.findElement(By .partialLinkText(this.properties.get("loginLink")));
		login.click();
		WebElement username = driver.findElement(By .id("username"));
		username.sendKeys(uname);
		WebElement password = driver.findElement(By .id("password"));
		password.sendKeys(userPassword);	
		driver.findElement(By .id("loginbtn")).click();	
	}
}