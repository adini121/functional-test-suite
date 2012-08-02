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
@SuppressWarnings("unused")
public class ReportActivityCompletionAssertions extends PassFailAssertions{
	//Internationalization file location
public static String data = "properties/data/static/ReportActivityCompletion.properties";
RemoteWebDriver driver;
private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
public ReportActivityCompletionAssertions(RemoteWebDriver driver) {
	super(driver);
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
	this.properties.put("errorCompletedTrackingAssignment", dataLoad.getProperty("errorCompletedTrackingAssignment"));
	this.properties.put("errorNotCompletedTrackingAssignment", dataLoad.getProperty("errorNotCompletedTrackingAssignment"));
}
/**
 * Makes the test fail if a student who has completed an assignment is not marked as doing so in the Activity completion report.
 * @param studentFirstname The first name of the student. Pass this parameter from the test.
 * @param studentSurname The surname of the student. Pass this parameter from the test.
 * @throws Exception Throws an exception if the student is not shown as completing the assignment in the report.
 */
public void assertCompleted(String studentFirstname, String studentSurname) throws Exception {
	assertElementIsPresentByXpath(".//tr[contains(.,'" + studentFirstname + " " + studentSurname + "')]/td/img[@alt='Completed']", 
		studentFirstname + " " + studentSurname + this.properties.get("errorCompletedTrackingAssignment"), 2);
}
/**
 * Makes the test fail if a student who has not completed an assignment is marked as doing so in the Activity completion report.
 * @param studentFirstname The first name of the student. Pass this parameter from the test.
 * @param studentSurname The surname of the student. Pass this parameter from the test.
 * @throws Exception Throws an exception if the student is shown as completing the assignment in the report.
 */
public void assertNotCompleted(String studentFirstname, String studentSurname) throws Exception {
	assertElementIsPresentByXpath(".//tr[contains(.,'" + studentFirstname + " " + studentSurname + "')]/td/img[@alt='Not completed']", 
		studentFirstname + " " + studentSurname + this.properties.get("errorNotCompletedTrackingAssignment"), 2);
}
}