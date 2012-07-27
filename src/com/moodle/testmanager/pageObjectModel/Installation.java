package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for installation process.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Installation {
	//Internationalization file location
	public static String installationData = "properties/data/static/installation.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public Installation(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties installation = new Properties();
		try {
			installation.load(new FileInputStream(installationData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("continueButton", installation.getProperty("continueButton"));
		this.properties.put("cancelButton", installation.getProperty("cancelButton"));
		this.properties.put("saveChangesButton", installation.getProperty("saveChangesButton"));
	}
	public void clickContinue() {
		WebElement continueButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("continueButton") +
				"']"));
		continueButton.click();
	}
	public void clickCancel() {
		WebElement continueButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("cancelButton") +
				"']"));
		continueButton.click();
	}
	public void enterFullSiteName(String siteFullName) {
		WebElement fullName = driver.findElement(By .id("id_s__fullname"));
		fullName.sendKeys(siteFullName);		
	}
	public void enterShortSiteName(String shortname) {
		WebElement shortName = driver.findElement(By .id("id_s__shortname"));
		shortName.sendKeys(shortname);
	}
	public void clickSaveChanges() {
		WebElement saveChanges = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("saveChangesButton") +
				"']"));
		saveChanges.click();
	}
	public void clickNext() {
		WebElement nextButton = driver.findElement(By.id("nextbutton"));
		nextButton.click();
	}
}