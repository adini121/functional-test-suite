package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This is the page object model for interacting with the News Block.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class CourseAssertions extends PassFailAssertions{
	//Internationalization file location
	public static String coursesData = "properties/data/static/courses.properties";
	RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
public CourseAssertions(RemoteWebDriver driver) {
	super(driver);
	this.loadObjectData();
}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNews.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
public void loadObjectData() {
	Properties dataLoad = new Properties();
	try {
		dataLoad.load(new FileInputStream(coursesData));
	} catch (Exception e) {}
	//put values from the properties file into hashmap
	//Locators
	this.properties.put("turnEditingOn", dataLoad.getProperty("turnEditingOn"));
	//Exceptions
	this.properties.put("exceptionTurnEditingOn", dataLoad.getProperty("exceptionTurnEditingOn"));
	this.properties.put("exceptionTrackingEnabled", dataLoad.getProperty("exceptionTrackingEnabled"));
	this.properties.put("exceptionPostTracked", dataLoad.getProperty("exceptionPostTracked"));
}
/**
 * Asserts that the Turn Editing On button is not visible.
 * @throws Exception Passes silently if the button is disabled and throws an exception if it is enabled.
 */
public void assertTurnEditingOnIsDisabled() throws Exception {
	assertItemNotOnscreenByCSSSelector("input[value='" +
			this.properties.get("turnEditingOn") +
			"']",this.properties.get("exceptionTurnEditingOn"), 0);
}
/**
 * Asserts that tracking on a forum is disabled.
 * @param forumName The name of the forum where the value is passed from the test.
 * @throws Exception Passes quietly if the element does not exist and throws an exception if it does not.
 */
public void assertTrackingDisabled(String forumName) throws Exception {
	assertElementIsNotPresentByXpath(".//div[@class='mod-indent'][contains(.,'" +
			forumName +
			"')]/span[@class='unread']", "" +
					this.properties.get("exceptionPostTracked") +
					"", 2);
}
/**
 * Asserts that tracking is turned on for an item.
 * @param forumName The name of the forum where the value is passed from the test.
 * @throws Exception Throws and exception if tracking is turned off
 */
public void  assertTrackingEnabled(String forumName) throws Exception{
	assertElementIsPresentByXpath(".//div[@class='mod-indent'][contains(.,'" +
			forumName +
			"')]/span[@class='unread']",this.properties.get("exceptionTrackingEnabled"), 0);
}
}