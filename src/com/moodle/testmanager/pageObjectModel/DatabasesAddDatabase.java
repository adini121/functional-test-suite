package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for adding a Database activity to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class DatabasesAddDatabase extends FormAddEditSettings {
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/databasesAddDatabase.properties";
/**
 * Locator variables.
 */
	private String locCheckboxEnabled = "enabled";
	private String locAvailFromPrefix = "id_timeavailablefrom_";
	private String locAvailToPrefix = "id_timeavailableto_";
	private String locReadOnlyFromPrefix = "id_timeavailableto_";
	private String locReadOnlyToPrefix = "id_timeavailableto_";
	private String locReqEntries = "id_requiredentries";
	private String locEntriesReqView = "id_requiredentriestoview";
	private String locMaxEntries = "id_maxentries";
	private String locComments = "id_comments";
	private String locReqApproval = "id_approval";
	private String locGradeCategory ="id_gradecat";
	private String locAggregateType = "id_assessed";
	private String locScale = "id_assessed";
	private String locRatingRestrictCheckbox = "id_ratingtime";
	private String locRestrictRatingFromPrefix = "id_assesstimestart_";
	private String locRestrictRatingToPrefix = "id_assesstimefinish_";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public DatabasesAddDatabase(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData(langFile);
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties databaseAddData = new Properties();
		try {
			databaseAddData.load(new FileInputStream(langFile));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("showAdvanced", databaseAddData.getProperty("showAdvanced"));
		this.properties.put("saveAndReturn", databaseAddData.getProperty("saveAndReturn"));
		this.properties.put("saveAndDisplay", databaseAddData.getProperty("saveAndDisplay"));
		this.properties.put("cancel", databaseAddData.getProperty("cancel"));
		this.properties.put("cancel", databaseAddData.getProperty("cancel"));
	}
/**
 * Selects the Enable checkbox for Available from.
 */
	public void checkboxClickAvailableFrom() {
		driver.findElement(By .id(locAvailFromPrefix + locCheckboxEnabled)).click();
	}
/**
 * Selects a value for the date fore "Available from".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateAvailableFrom(String day, String month, String year) {
		formActions.selectShortDateByID(day, month, year, locAvailFromPrefix);
	}
/**
 * Selects the Enable checkbox for Available to.
 */
	public void checkboxClickAvailableTo() {
		driver.findElement(By .id(locAvailToPrefix + locCheckboxEnabled)).click();
	}
/**
 * Selects a value for the date fore "Available to".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateAvailableTo(String day, String month, String year) {
		formActions.selectShortDateByID(day, month, year, locAvailToPrefix);
	}
/**
 * Selects the Enable checkbox for Read only from.
 */
	public void checkboxClickReadOnlyFrom() {
		driver.findElement(By .id(locReadOnlyFromPrefix + locCheckboxEnabled)).click();
	}
/**
 * Selects a value for the date fore "Available to".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateReadOnlyFrom(String day, String month, String year) {
		formActions.selectShortDateByID(day, month, year, locReadOnlyFromPrefix);
	}
/**
 * Selects the Enable checkbox for Read only from.
 */
	public void checkboxClickReadOnlyTo() {
		driver.findElement(By .id(locReadOnlyToPrefix + locCheckboxEnabled)).click();
	}
/**
 * Selects a value for the date fore "Available to".
 * @param day The day to be selected from the day dropdown.
 * @param month The month to be selected from the month dropdown.
 * @param year The year to be selected from the year dropdown.
 */
	public void selectDateReadOnlyTo(String day, String month, String year) {
		formActions.selectShortDateByID(day, month, year, locReadOnlyToPrefix);
	}
/**
 * Selects a value for Required Entries.
 * @param requiredEntries The number of required entries to be selected.
 */
	public void selectRequiredEntries(String requiredEntries) {
		formActions.selectDropdownItemByID(locReqEntries, requiredEntries);
	}
/**
 * Selects a value for Entries required before viewing.
 * @param entriesBeforeViewing The number of entries to be selected.
 */
	public void selectEntriesBeforeViewing(String entriesBeforeViewing) {
		formActions.selectDropdownItemByID(locEntriesReqView, entriesBeforeViewing);
	}
/**
 * Selects the value for Maximum entries.
 * @param maximumEntries The maxium number of entries.
 */
	public void selectMaximumEntries(String maximumEntries) {
		formActions.selectDropdownItemByID(locMaxEntries, maximumEntries);
	}
/**
 * Selects the value for Comments.
 * @param comments The value for comments.
 */
	public void selectComments(String comments) {
		formActions.selectDropdownItemByID(locComments, comments);
	}
/**
 * Selects a value for Require approval.
 * @param approval The value for Require approval; Yes or No.
 */
	public void selectRequireApproval(String approval) {
		formActions.selectDropdownItemByID(locReqApproval, approval);
	}
/**
 * Selects a value for Grade category.
 * @param category The value for category.
 */
	public void selectGradeCategory(String category) {
		formActions.selectDropdownItemByID(locGradeCategory, category);
	}
/**
 * Selects a value for Aggregate type.
 * @param type The required value for Aggregate type.
 */
	public void selectAggregateType(String type) {
		formActions.selectDropdownItemByID(locAggregateType, type);
	}
/**
 * Selects a value for Scale.
 * @param scale The requires value for Scale.
 */
	public void selectScale(String scale) {
		formActions.selectDropdownItemByID(locScale, scale);
	}
/**
 * Selects or de-selects the REstric ratings to items with dates in this range checkbox.
 */
	public void checkboxClickRestrictRatings() {
		driver.findElement(By .id(locRatingRestrictCheckbox)).click();
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
		formActions.selectDateByID(dd, month, yyyy, hh, mm, locRestrictRatingFromPrefix);
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
		formActions.selectDateByID(dd, month, yyyy, hh, mm, locRestrictRatingToPrefix);
	}
}