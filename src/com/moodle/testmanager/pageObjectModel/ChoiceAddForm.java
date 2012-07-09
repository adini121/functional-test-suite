package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the add choice form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ChoiceAddForm extends FormAddEditSettings{
/**
 * Locator variables.
 */
	private String locLimitAnswers = "id_limitanswers";
	public String locOptions = "id_option_";
	private String locLimit = "id_limit_";
	private String loc3Fields = "id_option_add_fields";
	private String locRestrictAnsPeriod = "id_timerestrict";
	private String locTimeOpen = "id_timeopen_";
	private String locTimeUntil = "id_timeclose_";
	private String locDisplayMode = "id_display";
	private String locResults = "id_showresults";
	private String locPrivacy = "id_publish";
	private String locAllowUpd = "id_allowupdate";
	private String locColUnanswered = "id_showunanswered";
	private FormActions formActions = new FormActions(driver);
/**
 * Constructor for the page object.	
 */
	public ChoiceAddForm(RemoteWebDriver driver) {
		super(driver);
	}
/**
 * Loads data for the page object from the internationalization layer
 * where a locator requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/ChoiceAddForm.properties"));
		} 
		catch (Exception e) {}
	}
/**
 * Selects a value from the "Limit the number of responses allowed". 
 * @param disableOrEnable The value that you would like to select, valid values are:
 * <br>a) Disable
 * <br>b) Enable
 */
	public void selectLimitNoResponses(String disableOrEnable) {
		formActions.selectDropdownItemByID(locLimitAnswers, disableOrEnable);
	}
/**
 * Enters a value in a given option field.
 * @param optionNumber The option number that you want to enter text into as displayed on the form.
 * @param optionText The text that you want to enter.
 */
	public void enterOptionField(String optionNumber, String optionText) {
		driver.findElementById(locOptions + optionNumber).sendKeys(optionText);
	}
/**
 * Enters a value for limit in a given limit field.
 * @param optionNumber The option number that you want to enter value for limit into as displayed on the form.
 * @param limit The numeric value you want to enter int he limit field.
 */
	public void enterLimitField(String optionNumber, String limit) {
		formActions.enterTextByIDRepeatingElement(locLimit, optionNumber, limit);
	}
/**
 * Clicks the button to add more fields.
 */
	public void buttonAdd3Fields() {
		driver.findElementById(loc3Fields).click();
	}
/**
 * Clicks the to select or deselect the "Restrict answering to this time period" checkbox. 
 */
	public void checkboxRestrictAnsweringPeriod() {
		driver.findElementById(locRestrictAnsPeriod).click();
	}
/**
 * Selects all of the values for the "Open" field.
 * @param day The value for day.
 * @param month The value for month.
 * @param year The value for year.
 * @param hour The value for hour.
 * @param minute The value for minute.
 */
	public void selectOpenDate(String day, String month, String year, String hour, String minute) {
		formActions.selectDateByID(day, month, year, hour, minute, locTimeOpen);
	}
/**
 * Selects a value for day from the "Open" field.
 * @param day The value for day.
 */
	public void selectOpenDay(String day) {
		formActions.selectDay(day, locTimeOpen);
	}
/**
 * Selects a value for month from the "Open" field.
 * @param month The value for month.
 */
	public void selectOpenMonth(String month) {
		formActions.selectMonth(month, locTimeOpen);
	}
/**
 * Selects a value for year from the "Open" field.
 * @param year The value for year.
 */
	public void selectOpenYear(String year) {
		formActions.selectYear(year, locTimeOpen);
	}
/**
 * Selects a value for hour from the "Open" field.
 * @param hour The value for hour.
 */
	public void selectOpenHour(String hour) {
		formActions.selectHour(hour, locTimeOpen);
	}
/**
 * Selects a value for minute from the "Open" field.
 * @param minute The vale for minute.
 */
	public void selectOpenMinute(String minute) {
		formActions.selectMin(minute, locTimeOpen);
	}
/**
 * Selects all of the values for the "Until" field.
 * @param day The value for day.
 * @param month The value for month.
 * @param year The value for year.
 * @param hour The value for hour.
 * @param minute The value for minute.
 */
	public void selectUntilDate(String day, String month, String year, String hour, String minute) {
		formActions.selectDateByID(day, month, year, hour, minute, locTimeUntil);
	}
/**
 * Selects a value for day from the "Until" field.
 * @param day The value for day.
 */
	public void selectUntilDay(String day) {
		formActions.selectDay(day, locTimeUntil);
	}
/**
 * Selects a value for month from the "Until" field.
 * @param month The value for month.
 */
	public void selectUntilMonth(String month) {
		formActions.selectMonth(month, locTimeUntil);
	}
/**
 * Selects a value for year from the "Until" field.
 * @param year The value for year.
 */
	public void selectUntilYear(String year) {
		formActions.selectYear(year, locTimeUntil);
	}
/**
 * Selects a value for hour from the "Until" field.
 * @param hour The value for hour.
 */
	public void selectUntilHour(String hour) {
		formActions.selectHour(hour, locTimeUntil);
	}
/**
 * Selects a value for minute from the "Until" field.
 * @param minute The vale for minute.
 */
	public void selectUntilMinute(String minute) {
		formActions.selectMin(minute, locTimeUntil);
	}
/**
 * Selects a value for display mode from the dropdown.
 * @param mode The desired value for mode. Valid values are Display horizontally and Display vertically.
 */
	public void selectDisplayMode(String mode) {
		formActions.selectDropdownItemByID(locDisplayMode, mode);
	}
/**
 * Selects a value for Publish results from the dropdown.
 * @param results The desired value to be selected from the Publish results dropdown.
 */
	public void selectPublishResults(String results) {
		formActions.selectDropdownItemByID(locResults, results);
	}
/**
 * Selects a value from the "Privacy of results" field.
 * @param privacy The desired value to be selected from the "Privacy of results" dropdown.
 */
	public void selectPrivacyOfResults(String privacy) {
		formActions.selectDropdownItemByID(locPrivacy, privacy);
	}
/**
 * Selects a value from the "Allow choice to be updated".
 * @param updatesAllowed The desired value to be selected form the "Allow choice to be updated" dropdown.
 */
	public void selectAllowChoiceUpd(String updatesAllowed) {
		formActions.selectDropdownItemByID(locAllowUpd, updatesAllowed);
	}
/**
 * Selects a value from the "Show column for unanswered".
 * @param columnUnanswered The desired value to be selected form the "Show column for unanswered" dropdown.
 */
	public void showColumnUnanswered(String columnUnanswered) {
		formActions.selectDropdownItemByID(locColUnanswered, columnUnanswered);
	}
}