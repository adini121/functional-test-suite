package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.FormActions;
/**
 * The page object model for the Add Assignment form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class AssignmentAddAssignment {
	//Internationalization file location
	public static String data = "properties/data/static/assignmentAddAssignment.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public AssignmentAddAssignment(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/assignmentAddAssignment.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(data));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("PROPERTY", dataLoad.getProperty("PROPERTY"));
	}
/*
 * TODO Methods in this class for existing non-2.3 assigment
 * TODO Methods in this class for 2.3 assignment
 * Assignment is changing in 2.3, the subtypes are being removed so the form is changing.
 */
/**
 * Enters a value for assignment name. Should work for versions <2.3 and >=2.3.
 * @param assignmentName The value to be entered for Assignment Name.
 */
	public void enterAssignmentName(String assignmentName) {
		WebElement name = driver.findElementById("id_name");
		name.sendKeys(assignmentName);
	}
/**
 * Enters a value for assigment description. Should work for versions <2.3 and >=2.3.
 * @param description The value to be entered for Description.
 */
	public void enterAssignmentDescription(String description) {
		WebElement frame = driver.findElement(By .id("id_introeditor_ifr"));
		driver.switchTo().frame(frame);
		WebElement messagebox = driver.findElement(By.id("tinymce"));
		messagebox.click();
		messagebox.sendKeys(description);
		driver.switchTo().window(driver.getWindowHandle());
	}
/**
 * Clicks the show description on course page checkbox. Should work for versions <2.3 and >=2.3.
 */
	public void checkboxDescOnCoursePage() {
		WebElement checkbox = driver.findElementById("id_showdescription");
		checkbox.click();
	}
/**
 * Selects a value for the available from Field. May only work for versions <2.3 as the form has changed for 2.3.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectAvailableFrom(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_timeavailable_day", day);
		dropdown.selectDropdownItemByID("id_timeavailable_month", month);
		dropdown.selectDropdownItemByID("id_timeavailable_year", year);
		dropdown.selectDropdownItemByID("id_timeavailable_hour", hour);
		dropdown.selectDropdownItemByID("id_timeavailable_minute", mins);
	}
/**
 * Selects the checkbox to enable the available from field. May only work for versions <2.3 as the form has changed for 2.3.
 */
	public void selectAvailableFromDisplayed() {
		WebElement checkbox = driver.findElementById("id_timeavailable_enabled");
		checkbox.click();
	}
/**
 * Selects a value for the due date Field. Think this will work for 2.3 as the field is there it's just in a different section.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectDueDate(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_timedue_day", day);
		dropdown.selectDropdownItemByID("id_timedue_month", month);
		dropdown.selectDropdownItemByID("id_timedue_year", year);
		dropdown.selectDropdownItemByID("id_timedue_hour", hour);
		dropdown.selectDropdownItemByID("id_timedue_minute", mins);
	}
/**
 * Selects the checkbox to enable the available from field. Think this will work for 2.3 as the field is there it's just in a different section.
 */
	public void selectTimeDueDisplayed() {
		WebElement checkbox = driver.findElementById("id_timedue_enabled");
		checkbox.click();
	}
/*
 * In 2.3 the mockups the field labels for this have changed.
 */
	public void selectAllowSubmissionsFrom() {
		//TODO fond out if the Field ID's have changed and if so what the new ones are.
	}
/*
 * In 2.3 the mockups have changed the field labels for this.
 */
	public void selectAllowSubmissionsFromDisplayed() {
		//TODO find out if the Field ID's have changed and if so what the new ones are.
	}
/*
 * Always show description dropdown appears with 2.3.
 */
	public void selectAlwaysShowDescriptionYes() {
		//TODO find out what the id tag will be for this.
	}
	public void selectAlwaysShowDescriptionNo() {
		//TODO find out what the id tag will be for this.
	}

}