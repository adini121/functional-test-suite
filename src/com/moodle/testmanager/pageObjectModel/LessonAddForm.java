package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for adding a Lesson activity to a course.
 * @author Tim Barker 
 * @param <hideIntroField>
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class LessonAddForm extends FormAddEditSettings {
/**
 * Constructors
 */
	private FormActions formActions = new FormActions(driver);
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/LessonAddForm.properties";
/**
 * Locator variables.
 */
	private String locTimeLimit = "id_maxtime";
	private String locEnableCheckbox = "id_timed";
	private String locAvailableFromPrefix = "id_available_";
	private String locAvailableFromEnable = "id_available_enabled";
	private String locDeadlinePrefix = "id_deadline_";
	private String locDeadlineEnable = "id_deadline_enabled";
	private String locMaxAnswers = "id_maxanswers";
	private String locPracticeLession = "id_practice";
	private String locCustomScoring = "id_custom";
	private String locRetakesAllowed = "id_retakes";
	private String locHandlingOfRetakes = "id_usemaxgrade";
	private String locOngoingScore = "id_ongoing";
	private String locAllowStudentRev = "id_modattempts";
	private String locOptTryAgain = "id_review";
	private String locMaxAttempts = "id_maxattempts";
	private String locDispDefFeedback = "id_feedback";
	private String locProgBar = "id_progressbar";
	private String locLeftMenu = "id_displayleft";
	private String locChooseAFile = "mediafilepickerchoose";
	private String locDepOn = "id_dependancy";
	private String locTimeSpent = "id_timespent";
	private String locCheckboxComp = "id_completed";
	private String locGradeBetter = "id_gradebetterthan";

/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public LessonAddForm(RemoteWebDriver driver) {
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
		this.properties.put("PROPERTY", databaseAddData.getProperty("PROPERTY"));
	}
/*
 * Overriding unused, inherited methods.
 */
/**
 * You cannot use the inherited method enterIntroField with the Add/Edit Lesson form. 
 */
	@Override
	public void enterDescriptionField(CharSequence message) {
		message = null;
	}
/**
 * You cannot use the inherited method checkboxDescOnCourse with the Add/Edit Lesson form. 
 */
	@Override
	public void checkboxDescOnCourse(){
	}
/**
 * You cannot use the inherited method enterGroupField with the Add/Edit Lesson form.
 */
	@Override
	public void selectGroupMode(String itemToSelect) {
		itemToSelect = null;
	}
/**
 * Enter the desired value in the "Time limit" field.
 * @param timeLimit The value for time limit that you require.
 */
	public void enterTimeLimitField(String timeLimit) {
		driver.findElementById(locTimeLimit).sendKeys(timeLimit);
	}
/**
 * Selects the checkbox to enable the "Time limit" field.
 */
	public void checkboxEnableTimeLimit() {
		driver.findElementById(locEnableCheckbox).click();
	}
/**
 * Selects the checkbox to enable "Available from" Field. 
 */
	public void checkboxEnableFromDate() {
		driver.findElementById(locAvailableFromEnable).click();
	}
/**
 * Selects the value for the "Available from" field.
 * @param dd The desired value for day.
 * @param month The desired value for month.
 * @param yyyy The desired value for year.
 * @param hh The desired value for hour.
 * @param mm The desired value for minutes.
 */
	public void selectAvailFromDate(String dd, String month, String yyyy, String hh, String mm) {
		formActions.selectDateByID(dd, month, yyyy, hh, mm, locAvailableFromPrefix);
	}
/**
 * Selects the checkbox to enable/diable the "Deadline" field.
 */
	public void checkboxEnableDeadline() {
		driver.findElementById(locDeadlineEnable).click();
	}
/**
 * Selects the value for the "Deadline" field.
 * @param dd The desired value for day.
 * @param month The desired value for month.
 * @param yyyy The desired value for year.
 * @param hh The desired value for hour.
 * @param mm The desired value for minutes.
 */
	public void selectDeadlineDate(String dd, String month, String yyyy, String hh, String mm) {
		formActions.selectDateByID(dd, month, yyyy, hh, mm, locDeadlinePrefix);
	}
/**
 * Selects a value from the "Maximum number of answers" dropdown field.
 * @param noOfAnswers The desired value for Maximum number of values. 
 */
	public void selectMaximumNoAnswers(String noOfAnswers) {
		formActions.selectDropdownItemByID(locMaxAnswers, noOfAnswers);
	}
/**
 * Selects a value from the "Practice lesson" dropdown field. 
 * @param yesNo Answer yes or no.
 */
	public void selectPracticeLesson(String yesNo) {
		formActions.selectDropdownItemByID(locPracticeLession, yesNo);
	}
/**
 * Selects a value from the "Custom scoring" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectCustomScoring(String yesNo) {
		formActions.selectDropdownItemByID(locCustomScoring, yesNo);
	}
/**
 * Selects a value from the "Re-takes allowed" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectRetakesAllowed(String yesNo) {
		formActions.selectDropdownItemByID(locRetakesAllowed, yesNo);
	}
/**
 * Selects a value from the "Handling of re-takes" dropdown field.
 * @param retakeValue The desired value.
 */
	public void selectHandlingOfRetakes(String retakeValue) {
		formActions.selectDropdownItemByID(locHandlingOfRetakes, retakeValue);
	}
/**
 * Selects a value from the "Display ongoing score" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectDisplayOngoingScore(String yesNo) {
		formActions.selectDropdownItemByID(locOngoingScore, yesNo);
	}
/**
 * Selects a value from the "Allow student review" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectAllowStudentReview(String yesNo) {
		formActions.selectDropdownItemByID(locAllowStudentRev, yesNo);
	}
/**
 * Selects a value from the "Provide an option to try again" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectProvideOptionTryAgain(String yesNo) {
		formActions.selectDropdownItemByID(locOptTryAgain, yesNo);
	}
/**
 * Selects a value from the "Maximum number of attampts" dropdown field.
 * @param maxAttempts The desired value.
 */
	public void selectMaxNOAttempts(String maxAttempts) {
		formActions.selectDropdownItemByID(locMaxAttempts, maxAttempts);
	}
/**
 * Selects a value from the "Display default feedback" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectDisplayDefaultFeedback(String yesNo) {
		formActions.selectDropdownItemByID(locDispDefFeedback, yesNo);
	}
/**
 * Selects a value from the "Progress bar" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectProgressBar(String yesNo) {
		formActions.selectDropdownItemByID(locProgBar, yesNo);
	}
/**
 * Selects a value from the "Display left menu" dropdown field.
 * @param yesNo Answer yes or no.
 */
	public void selectDisplayLeftMenu(String yesNo) {
		formActions.selectDropdownItemByID(locLeftMenu, yesNo);
	}
/**
 * Clicks the "Choose a file..." button.
 */
	public void clickChooseAFileButton() {
		driver.findElementByName(locChooseAFile).click();
	}
/**
 * Selects a value from the "Dependant on" dropdown field.
 * @param depOn The desired value.
 */
	public void selectDependantOn(String depOn) {
		formActions.selectDropdownItemByID(locDepOn, depOn);
	}
/**
 * Enters a value in the "Time spent" field.
 * @param timeSpent The desired value that you would like to enter.
 */
	public void enterTimeSpentField(String timeSpent) {
		driver.findElementById(locTimeSpent).sendKeys(timeSpent);
	}
/**
 * Clicks to select or deselect the "Completed" checkbox.
 */
	public void checkboxCompleted() {
		driver.findElementById(locCheckboxComp).click();
	}
/**
 * Enters a value in the "Grade beter than (%)" field.
 * @param gradeBetter The value that you want to enter.
 */
	public void enterGradeBetterThanField(CharSequence[] gradeBetter) {
		driver.findElementById(locGradeBetter).sendKeys(gradeBetter);
	}
}