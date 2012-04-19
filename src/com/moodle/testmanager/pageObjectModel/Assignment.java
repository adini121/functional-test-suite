package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * The page object model for the Assignment module.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Assignment {
	//Internationalization file location
	public static String data = "properties/data/static/assignment.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public Assignment(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/assignment.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(data));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("buttonEditMySubmission", dataLoad.getProperty("buttonEditMySubmission"));
		this.properties.put("buttonGradeAssignment", dataLoad.getProperty("buttonGradeAssignment"));
		this.properties.put("buttonSubmitAssignment", dataLoad.getProperty("buttonSubmitAssignment"));
		//this.properties.put("PROPERTY", dataLoad.getProperty("PROPERTY"));
	}
/**
 * Clicks the Edit my submission button.
 */
	public void clickButtonEditMySubmission() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonEditMySubmission") +
				"']");
		button.click();
	}
/**
 * Clicks the Grade assignment button.
 */
	public void clickButtonGradeAssignment() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonGradeAssignment") +
				"']");
		button.click();
	}
/**
 * Clicks the Submit assignment button when it is enabled.
 */
	public void clickButtonSubmitAssignment() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonSubmitAssignment") +
				"']");
		button.click();
	}
}