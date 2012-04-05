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
 * This is the page object model for adding a Database activity to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class DatabasesAddDatabase {
	//Internationalization file location
	public static String databasesAddData = "properties/data/static/databasesAddDatabase.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public DatabasesAddDatabase(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties databaseAddData = new Properties();
		try {
			databaseAddData.load(new FileInputStream(databasesAddData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("showAdvanced", databaseAddData.getProperty("showAdvanced"));
		this.properties.put("saveAndReturn", databaseAddData.getProperty("saveAndReturn"));
		this.properties.put("saveAndDisplay", databaseAddData.getProperty("saveAndDisplay"));
		this.properties.put("cancel", databaseAddData.getProperty("cancel"));
		this.properties.put("cancel", databaseAddData.getProperty("cancel"));
	}
/**
 * Enters a value for database name in the field.
 * @param name The database name required for testing. The data is passed from the test.
 */
	public void fieldDatabaseName(String name) {
		WebElement dbName = driver.findElement(By .id("id_name"));
		dbName.sendKeys(name);
	}
/**
 * Enters a value in the Introduction field.
 * @param introText The text to be entered in the introduction field.
 */
	public void fieldIntroduction(String introText) {
		FormActions intro = new FormActions(driver);
		intro.enterValueInTinyMCE(introText);
	}
/**
 * Selects the Display description on course page checkbox.
 */
	public void checkboxDisplayDescription() {
		WebElement checkbox = driver.findElement(By .id("id_showdescription"));
		checkbox.click();
	}
/**
 * Selects the Enable checkbox for Available from.
 */
	public void checkboxClickAvailableFrom() {
		WebElement checkbox = driver.findElement(By .id("id_timeavailablefrom_enabled"));
		checkbox.click();
	}
/**
 * Selects a value for the date fore "Available from".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateAvailableFrom(String day, String month, String year) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_timeavailablefrom_day", day);
		dropdown.selectDropdownItemByID("id_timeavailablefrom_month", month);
		dropdown.selectDropdownItemByID("id_timeavailablefrom_year", year);
	}
/**
 * Selects the Enable checkbox for Available to.
 */
	public void checkboxClickAvailableTo() {
		WebElement checkbox = driver.findElement(By .id("id_timeavailableto_enabled"));
		checkbox.click();
	}
/**
 * Selects a value for the date fore "Available to".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateAvailableTo(String day, String month, String year) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_timeavailableto_day", day);
		dropdown.selectDropdownItemByID("id_timeavailableto_month", month);
		dropdown.selectDropdownItemByID("id_timeavailableto_year", year);
	}
/**
 * Selects the Enable checkbox for Read only from.
 */
	public void checkboxClickReadOnlyFrom() {
		WebElement checkbox = driver.findElement(By .id("id_timeavailableto_enabled"));
		checkbox.click();
	}
/**
 * Selects a value for the date fore "Available to".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateReadOnlyFrom(String day, String month, String year) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_timeviewfrom_day", day);
		dropdown.selectDropdownItemByID("id_timeviewfrom_month", month);
		dropdown.selectDropdownItemByID("id_timeviewfrom_year", year);
	}
/**
 * Selects the Enable checkbox for Read only from.
 */
	public void checkboxClickReadOnlyTo() {
		WebElement checkbox = driver.findElement(By .id("id_timeavailableto_enabled"));
		checkbox.click();
	}
/**
 * Selects a value for the date fore "Available to".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateReadOnlyTo(String day, String month, String year) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_timeviewto_day", day);
		dropdown.selectDropdownItemByID("id_timeviewto_month", month);
		dropdown.selectDropdownItemByID("id_timeviewto_year", year);
	}
/**
 * Selects a value for Required Entries.
 * @param requiredEntries The number of required entries to be selected.
 */
	public void selectRequiredEntries(String requiredEntries) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_requiredentries", requiredEntries);
	}
/**
 * Selects a value for Entries required before viewing.
 * @param entriesBeforeViewing The number of entries to be selected.
 */
	public void selectEntriesBeforeViewing(String entriesBeforeViewing) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_requiredentriestoview", entriesBeforeViewing);
	}
/**
 * Selects the value for Maximum entries.
 * @param maximumEntries The maxium number of entries.
 */
	public void selectMaximumEntries(String maximumEntries) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_maxentries", maximumEntries);
	}
/**
 * Selects the value for Comments.
 * @param comments The value for comments.
 */
	public void selectComments(String comments) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_comments", comments);
	}
/**
 * Selects a value for Require approval.
 * @param approval The value for Require approval; Yes or No.
 */
	public void selectRequireApproval(String approval) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_approval", approval);
	}
/**
 * Selects a value for Grade category.
 * @param category The value for category.
 */
	public void selectGradeCategory(String category) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_gradecat", category);
	}
/**
 * Selects a value for Aggregate type.
 * @param type The required value for Aggregate type.
 */
	public void selectAggregateType(String type) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed", type);
	}
/**
 * Selects a value for Scale.
 * @param scale The requires value for Scale.
 */
	public void selectScale(String scale) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed", scale);
	}
/**
 * Selects or de-selects the REstric ratings to items with dates in this range checkbox.
 */
	public void checkboxClickRestrictRatings() {
		WebElement checkbox = driver.findElement(By .id("id_ratingtime"));
		checkbox.click();
	}
/**
 * Selects the from date for restricting ratings.
 * @param dd The value for day.
 * @param month The value for month.
 * @param yyyy The value for year.
 * @param hh The value for hour.
 * @param mm The value for minutes.
 */
	public void selectRestrictDateRangeFrom(String dd, String month, String yyyy, String hh, String mm) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assesstimestart_day", dd);
		dropdown.selectDropdownItemByID("id_assesstimestart_month", month);
		dropdown.selectDropdownItemByID("id_assesstimestart_year", yyyy);
		dropdown.selectDropdownItemByID("id_assesstimestart_hour", hh);
		dropdown.selectDropdownItemByID("id_assesstimestart_minute", mm);
	}
/**
 * Selects the to date for restricting ratings.
 * @param dd The value for day.
 * @param month The value for month.
 * @param yyyy The value for year.
 * @param hh The value for hour.
 * @param mm The value for minutes.
 */
	public void selectRestrictDateRangeTo(String dd, String month, String yyyy, String hh, String mm) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assesstimefinish_day", dd);
		dropdown.selectDropdownItemByID("id_assesstimefinish_month", month);
		dropdown.selectDropdownItemByID("id_assesstimefinish_year", yyyy);
		dropdown.selectDropdownItemByID("id_assesstimefinish_hour", hh);
		dropdown.selectDropdownItemByID("id_assesstimefinish_minute", mm);
	}
/**
 * Selects the value for Group mode.
 * @param groupMode The required value for Group mode.
 */
	public void selectGroupMode(String groupMode) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_groupmode", groupMode);
	}
/**
 * Selects the value for Visible.
 * @param visible The required value for Visible.
 */
	public void selectVisible(String visible) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_visible", visible);
	}
/**
 * Enters a value for ID in the ID number field.
 * @param ID The value to be entered for ID.
 */
	public void enterIDNumber(String ID) {
		WebElement idNumber = driver.findElement(By .id("id_cmidnumber"));
		idNumber.sendKeys(ID);
	}
/**
 * Clicks the show advanced button.
 */
	public void buttonClickShowAdvanced() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("showAdvanced") +
				"']"));
		button.click();
	}
/**
 * Clicks the Save and return to course button.
 */
	public void buttonSaveAndReturn() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("saveAndReturn") +
				"']"));
		button.click();
	}
/**
 * Clicks the Save and display to course button.
 */
	public void buttonSaveAndDisplay() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("saveAndDisplay") +
				"']"));
		button.click();
	}
/**
 * Clicks the cancel button.
 */
	public void buttonCancel() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("cancel") +
				"']"));
		button.click();
	}

}