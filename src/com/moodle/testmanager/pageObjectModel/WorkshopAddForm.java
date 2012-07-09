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
 * This is the page object model for adding a Workshop activity to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class WorkshopAddForm extends FormAddEditSettings {
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/WikiAddForm.properties";
/**
 * Locator variables.
 */
//Generic locators
	private String locGenXpathPrefix=".//table[@id='";
	private String locGenXpathSuffix="']/*/*/*/iframe";
//Non-generic
	private String locCBExamples="id_useexamples";
	private String locCBPeerAssess="id_usepeerassessment";
	private String locCBSelfAssess="id_useselfassessment";
	private String locSubGradeVal="id_grade";
	private String locSubGradeCat="id_gradecategory";
	private String locAssGradeVal="id_gradinggrade";
	private String locAssGradeCat="id_gradinggradecategory";
	private String locGradeStrategy="id_strategy";
	private String locDecPlaces="id_gradedecimals";
	private String locInstForSub="id_instructauthorseditor_tbl";
	private String locMaxAttachments="id_nattachments";
	private String locMaxFileSize="id_maxbytes";
	private String locLateSubCheckbox="id_latesubmissions";
	private String locInstructAssess="id_instructreviewerseditor_tbl";
	private String locOpenSubEnable="id_submissionstart_enabled";
	private String locOpenSubFromPrefix="id_submissionstart_";
	private String locSubDeadlineEnable="id_submissionend_enabled";
	private String locSubDeadlinePrefix="id_submissionend_";
	private String locSwitchPhaseCB="id_phaseswitchassessment";
	private String locOpenAssessEnable="id_assessmentstart_enabled";
	private String locOpenAssessFromPrefix="id_assessmentstart_";
	private String locAssessDeadlineEnable="id_assessmentend_enabled";
	private String locAssessDeadlinePrefix="id_assessmentend_";

/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public WorkshopAddForm(RemoteWebDriver driver) {
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
 * Override unused methods from super.
 */
/**
 * selectGrade is not used.
 */
	@Override
	public void selectGrade(String grade) {
		grade = null;
	}
/**
 * selectGradeCategory is not used.
 */
	@Override
	public void selectGradeCategory(String gradeCategory) {
		gradeCategory = null;
	}
/*
 * Specific object methods.
 */
/**
 * Generic checkbox click method for re-use in this class.
 * @param cboxLocator The locator of the checkbox, using ID only.
 */
	private void checkboxWorkshop(String cboxLocator){
		WebElement checkbox = driver.findElementById(cboxLocator);
		checkbox.click();
	}
/**
 * Generic dropdown method for re-use in this class.
 * @param dropdownLocator The locator of the dropdown. With everything here is an id.
 * @param itemToSelect The item that is to be selected.
 */
	private void dropdownWorkshop(String dropdownLocator, String itemToSelect){
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID(dropdownLocator, itemToSelect);
	}
/**
 * Generic datetime dropdown selection for this class.
 * @param dd The day in the format dd.
 * @param month The month in the format month.
 * @param yyyy The year in the format yyyy.
 * @param hh The hour in the format hh.
 * @param mm The minute in the format mm.
 * @param dateLocatorPrefix The locator of the date field. This is a prefix value for ID here.
 * <br> e.g. id_foo_ where hour is appended automatically in FormActions.selectDateByID to make a concatenated value of id_foo_hour.  
 */
	private void dropdownWorkshopDateTime(String dd, String month, String yyyy, String hh, String mm, String dateLocatorPrefix){
		FormActions dateTime = new FormActions(driver);
		dateTime.selectDateByID(dd, month, yyyy, hh, mm, dateLocatorPrefix);
	}
/**
 * This form contains multiple rich text editors so it is necessary to locate anything other than the first on the page specifically.
 * The Module description is located by the "iframe" tagname as this is the fastest location strategy. This will still work as WebDriver will just find the
 * First element on the page. XPath is programmable so that has been used to locate the other TinyMCE elements. In this method the
 * Xpath is formed by using the ID of the table containing the form element and concatenating a prefix containing the XPath either side of that. 
 * @param message The character sequence that you would like to enter. 
 * @param tableID The ID of the table contining the iframe form element that locates the editor that you want to use. 
 */
	private void richTextFieldWorkshop(CharSequence message, String tableID){
		WebElement messagebox = driver.findElement(By.xpath(locGenXpathPrefix + tableID + locGenXpathSuffix));
		driver.switchTo().frame(messagebox);
		WebElement richTextBox = driver.findElement(By.id("tinymce"));
		richTextBox.click();
		richTextBox.sendKeys(message);
		driver.switchTo().window(driver.getWindowHandle());
	}
/*
 * Page objects
 */
/**
 * Selects/de-selects the "Use examples" checkbox.
 */
	public void checkboxUseExamples(){
		checkboxWorkshop(locCBExamples);
	}
/**
 * Selects/de-selects the "Use peer assessment" checkbox.
 */
	public void checkboxUsePeerAsses(){
		checkboxWorkshop(locCBPeerAssess);
	}
/**
 * Selects/de-selects the "Use self-assessment" checkbox.
 */
	public void checkboxUseSelfAssess(){
		checkboxWorkshop(locCBSelfAssess);
	}
/**
 * Selects a value for "Grade for submission" from the dropdown and a grade category.
 * @param gradeValue The grade that you want to select from the dropdown.
 * @param gradeCategory The category that you want to select from the dropdown.
 */
	public void selectGradeForSubmission(String gradeValue, String gradeCategory){
		dropdownWorkshop(locSubGradeVal, gradeValue);
		dropdownWorkshop(locSubGradeCat, gradeCategory);
	}
/**
 * Selects a value for "Grade for assessment" from the dropdown and a grade category.
 * @param gradeValue The grade that you want to select from the dropdown.
 * @param gradeCategory The category that you want to select from the dropdown.
 */
	public void selectGradeForAssessment(String gradeValue, String gradeCategory){
		dropdownWorkshop(locAssGradeVal, gradeValue);
		dropdownWorkshop(locAssGradeCat, gradeCategory);
	}
/**
 * Selects a value from the Grading strategy dropdown.
 * @param GradingStrategy The value that you want to select from the dropdown.
 */
	public void selectGradingStrategy(String GradingStrategy){
		dropdownWorkshop(locGradeStrategy, GradingStrategy);
	}
/**
 * Selects a value from the "Decimal places in grades" dropdown.
 * @param decimalPlaces The number of decimal places that you want to select.
 */
	public void selectDecimalPlacesInGrades(String decimalPlaces){
		dropdownWorkshop(locDecPlaces, decimalPlaces);
	}
/**
 * Enters a value in the "Instructions for submission field.
 * @param instructions The character sequence that you want to enter.
 */
	public void enterInstructionsForSubmission(CharSequence instructions){
		richTextFieldWorkshop(instructions, locInstForSub);
	}
/**
 * Selects a value from the "Maximum number of submission attachments" dropdown.
 * @param maxAttachments
 */
	public void selectMaxNoSubmissions(String maxAttachments){
		dropdownWorkshop(locMaxAttachments, maxAttachments);
	}
/**
 * Selects a value from the "Maximum file size" dropdown.
 * @param maxFileSize The maximum file size that you want to select.
 */
	public void selectMaxFileSize(String maxFileSize){
		dropdownWorkshop(locMaxFileSize, maxFileSize);
	}
/**
 * Selects/de-selects the "Late submissions" checkbox.
 */
	public void checkboxLateSubmissions(){
		checkboxWorkshop(locLateSubCheckbox);
	}
/**
 * Enters a value in the "Instructions for assessment" field.
 * @param instructions The value that you would like to enter.
 */
	public void enterInstructionsForAssessment(CharSequence instructions){
		richTextFieldWorkshop(instructions, locInstructAssess);
	}
/**
 * Clicks the checkbox to enable/disable the "Open for submissions from" field. 
 */
	public void checkboxOpenSubFromEnable(){
		checkboxWorkshop(locOpenSubEnable);
	}
/**
 * Selects a date-time value for the "Open for submissions from" dropdown.
 * @param dd
 * @param month
 * @param yyyy
 * @param hh
 * @param mm
 */
	public void selectDateTimeOpenSubFrom(String dd, String month, String yyyy, String hh, String mm){
		dropdownWorkshopDateTime(dd, month, yyyy, hh, mm, locOpenSubFromPrefix);
	}
/**
 * Clicks the checkbox to enable/disable the "Submissions deadline" field. 
 */
	public void checkboxSubDeadlineEnable(){
		checkboxWorkshop(locSubDeadlineEnable);
	}
/**
 * Selects a date-time value for the "Submissions deadline" dropdown.
 * @param dd
 * @param month
 * @param yyyy
 * @param hh
 * @param mm
 */
	public void selectDateTimeSubDeadline(String dd, String month, String yyyy, String hh, String mm){
		dropdownWorkshopDateTime(dd, month, yyyy, hh, mm, locSubDeadlinePrefix);
	}
/**
 * Selects/de-selects the "Switch to the next phase...." checkbox. 
 */
	public void checkboxSwitchPhaseDeadline(){
		checkboxWorkshop(locSwitchPhaseCB);
	}
/**
 * Clicks the checkbox to enable/disable the "Open for assessments from" field. 
 */
	public void checkboxOpenAssessFromEnable(){
		checkboxWorkshop(locOpenAssessEnable);
	}
/**
 * Selects a date-time value for the "Open for assessments from" dropdown.
 * @param dd
 * @param month
 * @param yyyy
 * @param hh
 * @param mm
 */
	public void selectDateTimeOpenAssessFrom(String dd, String month, String yyyy, String hh, String mm){
		dropdownWorkshopDateTime(dd, month, yyyy, hh, mm, locOpenAssessFromPrefix);
	}
/**
 * Clicks the checkbox to enable/disable the "Deadline for assessment" field. 
 */
	public void checkboxAssessDeadlineEnable(){
		checkboxWorkshop(locAssessDeadlineEnable);
	}
/**
 * Selects a date-time value for the "Deadline for assesment" dropdown.
 * @param dd
 * @param month
 * @param yyyy
 * @param hh
 * @param mm
 */
	public void selectDateTimeDeadlineAssess(String dd, String month, String yyyy, String hh, String mm){
		dropdownWorkshopDateTime(dd, month, yyyy, hh, mm, locAssessDeadlinePrefix);
	}
}