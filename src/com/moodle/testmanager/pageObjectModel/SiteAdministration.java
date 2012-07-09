package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * The page object model for the Assignment module.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class SiteAdministration {
	//Internationalization file location
	public static String data = "properties/data/static/SiteAdministration.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public SiteAdministration(RemoteWebDriver driver) {
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
		this.properties.put("<YOUR_PROPERTY>", dataLoad.getProperty("<YOUR_PROPERTY>"));
	}
/**
 * Turns completion tracking on or ignores it if it already is turned on.
 */
	public void turnCompletionTrackingOn() {
		FormActions checkbox = new FormActions(driver);
		checkbox.handleCheckboxStateAndEnterTick("id_s__enablecompletion");
	}
/**
 * Clicks Save changes button.
 */
	public void clickSaveChanges() {
		WebElement button = driver.findElement(By .className("form-submit"));
		button.click();
	}
}
