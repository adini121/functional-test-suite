package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * The page object model for the Add Assignment form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class AssignmentAddAssignmentForm extends FormAddEditSettings{
	protected Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public AssignmentAddAssignmentForm(RemoteWebDriver driver) {
		super(driver);
		//Internationalization file location
		this.loadObjectData("properties/data/static/assignmentAddAssignment.properties");
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/assignmentAddAssignment.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/assignmentAddAssignment.properties"));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("yes", dataLoad.getProperty("yes"));
		this.properties.put("no", dataLoad.getProperty("no"));
		this.properties.put("buttonSaveAndReturn", dataLoad.getProperty("buttonSaveAndReturn"));
		this.properties.put("buttonSaveAndDisplay", dataLoad.getProperty("buttonSaveAndDisplay"));
		this.properties.put("buttonCancel", dataLoad.getProperty("buttonCancel"));
		this.properties.put("dropdownactivityCompletionCondMet", dataLoad.getProperty("dropdownactivityCompletionCondMet"));
	}
/**
 * Selects a value for the Allow Submissions from Field. May only work for versions <2.3 as the form has changed for 2.3.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectAllowSubmissionsFrom(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_allowsubmissionsfromdate_day", day);
		dropdown.selectDropdownItemByID("id_allowsubmissionsfromdate_month", month);
		dropdown.selectDropdownItemByID("id_allowsubmissionsfromdate_year", year);
		dropdown.selectDropdownItemByID("id_allowsubmissionsfromdate_hour", hour);
		dropdown.selectDropdownItemByID("id_allowsubmissionsfromdate_minute", mins);
	}
/**
 * Selects the checkbox to enable the Allow Submissions from field.
 */
	public void selectAvailableFromDisplayed() {
		WebElement checkbox = this.driver.findElementByName("allowsubmissionsfromdate[enabled]");
		checkbox.click();
	}
/**
 * Selects a value for the due date Field.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectDueDate(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_duedate_day", day);
		dropdown.selectDropdownItemByID("id_duedate_month", month);
		dropdown.selectDropdownItemByID("id_duedate_year", year);
		dropdown.selectDropdownItemByID("id_duedate_hour", hour);
		dropdown.selectDropdownItemByID("id_duedate_minute", mins);
	}
/**
 * Selects the checkbox to enable the available from field. Think this will work for 2.3 as the field is there it's just in a different section.
 */
	public void selectTimeDueDisplayed() {
		WebElement checkbox = this.driver.findElementByName("duedate[enabled]");
		checkbox.click();
	}
/*
 * Final Date appears with 2.3
 */
/**
 * Selects a value for the Final date Field.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectFinalDate(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_finaldate_day", day);
		dropdown.selectDropdownItemByID("id_finaldate_month", month);
		dropdown.selectDropdownItemByID("id_finaldate_year", year);
		dropdown.selectDropdownItemByID("id_finaldate_hour", hour);
		dropdown.selectDropdownItemByID("id_finaldate_minute", mins);
	}
/*
 * Always show description dropdown appears with 2.3.
 */
/**
 * Selects the Yes option from the Always show description dropdown. 
 */
	public void selectAlwaysShowDescriptionYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_alwaysshowdescription", this.properties.get("yes"));
	}
/**
 * Selects the No option from the Always show description dropdown.
 */
	public void selectAlwaysShowDescriptionNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_alwaysshowdescription", this.properties.get("no"));
	}
/*
 * Prevent late submissions should be the same for 2.3
 */
/**
 * Selects the Yes option from the Prevent late submissions dropdown.
 */
	public void selectPreventLateSubmissionsYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_preventlatesubmissions", this.properties.get("yes"));
	}
/**
 * Selects the No option from the Prevent late submissions dropdown.
 */
	public void selectPreventLateSubmissionsNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_preventlatesubmissions", this.properties.get("no"));
	}
/*
 * Require students click submit button.
 */
/**
 * Selects the Yes option form the Require students click submit button field.
 */
	public void selectRequireStudentSubmitYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_submissiondrafts", this.properties.get("yes"));
	}
/**
 * Selects the No option form the Require students click submit button field.
 */
	public void selectRequireStudentSubmitNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_submissiondrafts", this.properties.get("no"));
	}
/*
 * Send notifications to graders appears in 2.3
 */
/**
 * Selects Yes from the Send notifications to graders dropdown.
 */
	public void selectSendNotificationsToGradersYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_sendlatenotifications", this.properties.get("yes"));
	}
/**
 * Selects No from the Send notifications to graders dropdown.
 */
	public void selectSendNotificationsToGradersNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_sendlatenotifications", this.properties.get("no"));
	}
/*
 * Blind Marking appears in 2.3 
 */
/**
 * Selects Yes from the Blind Marking dropdown.
 */
	public void selectBlindMarkingYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_blindmarking", this.properties.get("yes"));
	}
/**
 * Selects No from the Blind Marking dropdown.
 */
	public void selectBlindMarkingNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_blindmarking", this.properties.get("no"));
	}
/*
 * Student submit in teams is introduced in 2.3
 */
/**
 * Selects Yes from the Students submit in teams dropdown.
 */
	public void selectStudentsSubmitInTeamsYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_teamsubmission", this.properties.get("yes"));
	}
/**
 * Selects No from the Students submit in teams dropdown.
 */
	public void selectStudentsSubmitInTeamsNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_teamsubmission", this.properties.get("no"));
	}
/**
 * Selects Yes from the Require all team members to submit dropdown.
 */
	public void selectRequireAllTeamMembersSubmitYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_requireallteammemberssubmit", this.properties.get("yes"));
	}
/**
 * Selects No from the Require all team members to submit dropdown.
 */
	public void selectRequireAllTeamMembersSubmitNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_requireallteammemberssubmit", this.properties.get("no"));
	}
/**
 * Selects a value for the Grouping for student teams dropdown.
 * @param groupSelection A value for the Grouping for student teams dropdown that must be passed from the test. 
 */
	public void selectGroupingForStudentTeams(String groupSelection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_teamsubmissiongroupingid", groupSelection);
	}
/*
 * The actual refactoring of Assignments with the ability to set an online text assignment from the add assignment form.
 */
/**
 * Selects Yes to enable Online Text.
 */
	public void selectOnlineTextEnabledYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_onlinetext_enabled", this.properties.get("yes"));
	}
/**
 * Selects No to disable Online Text.
 */
	public void selectOnlineTextEnabledNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_onlinetext_enabled", this.properties.get("no"));
	}
/*
 * More of the new refactored subtypes feature with File Submissions   
 */
/**
 * Selects Yes to enable File Submissions
 */
	public void selectFileSubmissionsEnabledYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_file_enabled", this.properties.get("yes"));
	}
/**
 * Selects No to disable File Submissions.
 */
	public void selectFileSubmissionsEnabledNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_file_enabled", this.properties.get("no"));
	}
/**
 * Selects the maximum number of files that can be uploaded as a submission.
 * @param numberOfFiles The maximum nuber of files that will be permitted to be. Currently is a value from 1-20.
 */
	public void selectMaxNoUploadedFiles(String numberOfFiles) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_file_maxfiles", numberOfFiles);
	}
/**
 * Selects the maximum size for a file upload from the Maximum submission size field. 
 * @param fileSize The value to be selected for the maximum file size that can be uploaded. Valid values are:
 * 2MB
 * 1MB
 * 500KB
 * 100KB
 * 50KB
 * 10KB
 * Course upload limit (2MB)
 */
	public void selectMaxSizeFiles(String fileSize) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_file_maxsizebytes", fileSize);
}
/**
 * Selects Yes from the Submission comments dropdown to enable Submission comments.
 */
	public void selectSubmissionCommentsYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_comments_enabled", this.properties.get("yes"));
	}
/**
 * Selects No from the Submission comments dropdown to enable Submission comments.
 */
	public void selectSubmissionCommentsNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignsubmission_comments_enabled", this.properties.get("no"));
	}
/**
 * Selects Yes from the Feedback comments dropdown to enable Feedback comments.
 */
	public void selectFeedbackCommentsYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignfeedback_comments_enabled", this.properties.get("yes"));
	}
/**
 * Selects No from the Feedback comments dropdown to disable Feedback comments.
 */
	public void selectFeedbackCommentsNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignfeedback_comments_enabled", this.properties.get("no"));
	}
/**
 * Selects Yes from the Feedback files dropdown to enable Feedback files.
 */
	public void selectFeedbackFilesYes() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignfeedback_file_enabled", this.properties.get("yes"));
	}
/**
 * Selects No from the Feedback files dropdown to disable Feedback files.
 */
	public void selectFeedbackFilesNo() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assignfeedback_file_enabled", this.properties.get("no"));
	}
/**
 * Selects a value for grade from the Grading method dropdown.
 * @param gradingMethod The value for Grading method to be passed from the test. Valid values in a default Moodle install are currently:
 * "Simple direct grading"
 * "Rubric"  
 */
	public void selectGradingMethod(String gradingMethod) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_advancedgradingmethod_submissions", gradingMethod);
	}
/**
 * Selects a value from grouping from the Grouping dropdown.
 * @param grouping The value for Grouping to be selected from the Grouping dropdown. 
 */
	public void selectGrouping(String grouping) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_groupingid", grouping);
	}
/**
 * Clicks the Available for group members only checkbox to check or un-check it.
 */
	public void checkboxAvailableGroupOnly() {
		WebElement checkbox = this.driver.findElementById("id_groupmembersonly");
		checkbox.click();
	}
/**
 * Enters a string the is passed from the test in the ID number field.
 * @param ID The value for ID number, can be anything that will pass validation.
 */
	public void enterID(String ID) {
		WebElement text = this.driver.findElementById("id_cmidnumber");
		text.sendKeys(ID);
	}
/**
 * Clicks the Show or Hide advanced button in Common Module Settings. 
 */
	public void clickShowHideAdvanced() {
		WebElement button = this.driver.findElementByName("mform_showadvanced");
		button.click();
	}
/**
 * Selects a value for the Allow access from Field.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectAllowAccessFrom(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_availablefrom_day", day);
		dropdown.selectDropdownItemByID("id_availablefrom_month", month);
		dropdown.selectDropdownItemByID("id_availablefrom_year", year);
		dropdown.selectDropdownItemByID("id_availablefrom_hour", hour);
		dropdown.selectDropdownItemByID("id_availablefrom_minute", mins);
	}
/**
 * Selects the checkbox to enable or disable the Allow access from field.
 */
	public void checkboxAllowAccessFromEnable() {
		WebElement checkbox = this.driver.findElementByName("availablefrom[enabled]");
		checkbox.click();
	}
/**
 * Selects a value for the Allow access until Field.
 * @param day The day to be selected.
 * @param month The Month to be selected.
 * @param year The year to be selected.
 * @param hour The hour to be selected.
 * @param mins The minutes to be selected.
 */
	public void selectAllowAccessUntil(String day, String month, String year, String hour, String mins) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_availableuntil_day", day);
		dropdown.selectDropdownItemByID("id_availableuntil_month", month);
		dropdown.selectDropdownItemByID("id_availableuntil_year", year);
		dropdown.selectDropdownItemByID("id_availableuntil_hour", hour);
		dropdown.selectDropdownItemByID("id_availableuntil_minute", mins);
	}
/**
 * Selects the checkbox to enable or disable the Allow access until field.
 */
	public void checkboxAllowAccessUntilEnable() {
		WebElement checkbox = this.driver.findElementByName("availableuntil[enabled]");
		checkbox.click();
	}	
/**
 * Selects the option to show activity as complete when conditions are met.
 */
	public void selectCompletionTrackingConditionsMet() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_completion", this.properties.get("dropdownactivityCompletionCondMet"));
	}
/**
 * Clicks the "Student must receive a grade to complete this activity" checkbox (if it's unchecked).
 */
	public void clickCheckboxStudentReceiveGrade() {
		FormActions checkbox = new FormActions(driver);
		checkbox.handleCheckboxStateAndEnterTick("id_completionusegrade");
	}
}