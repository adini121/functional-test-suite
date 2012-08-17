package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
/**
 * This is the page object model for the the add new user form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class UsersAddNewUser {
	public static String newUserData = "properties/data/static/usersAddNewUser.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public UsersAddNewUser(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
	public void loadObjectData() {
		Properties newUser = new Properties();
		try {
			newUser.load(new FileInputStream(newUserData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("updateProfile", newUser.getProperty("updateProfile"));;
	}
/**
 * Enter a value in the username field where the value is specified in the test.
 * @param username The desired username, this value is passed from the test.
 */
	public void enterUsername(String username) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement usernameField = driver.findElementById("id_username");
		usernameField.sendKeys(username);
	}
/**
 * Enter a value in the password field where the value is specified in the test.
 * @param newPassword The desired password, this value is passed from the test.
 */
	public void enterPassword(String newPassword) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement newPasswordField = driver.findElement(By .cssSelector("#id_newpassword"));
		newPasswordField.sendKeys(newPassword);
	}
/**
 * Enter a value in the first name field where the value is specified in the test.
 * @param firstName The desired firstname, this value is passed from the test.
 */
	public void enterFirstName(String firstName) {
		WebElement firstNameField = driver.findElement(By .cssSelector("#id_firstname"));
		firstNameField.sendKeys(firstName);
	}
/**
 * Enter a value in the surname field where the value is specified in the test.
 * @param surname The desired surname, this value is passed from the test.
 */
	public void enterSurname(String surname) {
		WebElement surnameField = driver.findElement(By .cssSelector("#id_lastname"));
		surnameField.sendKeys(surname);
	}
/**
 * Enter a value in the email field where the value is specified in the test.
 * @param email The desired email address, this value is passed from the test.
 */
	public void enterEmail(String email) {
		WebElement emailField = driver.findElement(By .cssSelector("#id_email"));
		emailField.sendKeys(email);
	}
/**
 * Enter a value in the town/city field where the value is specified in the test.
 * @param city The desired town/city, this value is passed from the test.
 */
	public void enterCity(String city) {
		WebElement cityTownCountry = driver.findElement(By .cssSelector("#id_city"));
		cityTownCountry.sendKeys(city);
	}
/**
 * Enter a value in the country field where the value is specified in the test.
 * @param country The desired country, this value is passed from the test.
 */
	public void enterCountry(String country) {
		Select countryOption = new Select(driver.findElement(By .id("id_country")));
		countryOption.selectByVisibleText(country);
	}
/**
 * Clicks the Update profile button.
 */
public void clickUpdateProfile() {
	WebElement updateProfileButton = driver.findElement(By .cssSelector("input[value='" +
			this.properties.get("updateProfile") +
			"']"));
	updateProfileButton.click();
	}
}