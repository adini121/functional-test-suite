package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the editing course settings.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class CoursesEditCourseSettings {
	//Internationalization file location
	public static String activitiesData = "properties/data/static/coursesEditCourseSettings.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public CoursesEditCourseSettings(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadTestData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/coursesEditCourseSettings.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadTestData() {
		Properties coursesEditCourseSettings = new Properties();
		try {
			coursesEditCourseSettings.load(new FileInputStream(activitiesData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("saveChangesButton", coursesEditCourseSettings.getProperty("saveChangesButton"));	
		this.properties.put("dropdownOptionEnableCompletionTracking", coursesEditCourseSettings.getProperty("dropdownOptionEnableCompletionTracking"));	
	}
/**
 * Selects a given value for the number of items to show from the News items to show dropdown.
 * @param numberOfEntries The value to select from the dropdown. The value is passed fromthe test.
 */
	public void selectNewsItemsToShow(String numberOfEntries) {
		Select itemsToShowDropDown = new Select(driver.findElement(By .xpath(".//*[@id='id_newsitems']")));
		itemsToShowDropDown.selectByVisibleText(numberOfEntries);
	}
/**
 * Clicks the save changes button.
 */
	public void clickSaveChanges() {
		WebElement saveChanges = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("saveChangesButton") +
				"']"));
		saveChanges.click();
	}
/**
 * Turns completion tracking on.
 */
	public void selectCompletionTrackingEnabled() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_enablecompletion", this.properties.get("dropdownOptionEnableCompletionTracking"));	
	}
}