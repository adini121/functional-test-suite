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
		this.properties.put("yes", dataLoad.getProperty("yes"));
		this.properties.put("no", dataLoad.getProperty("no"));
		this.properties.put("buttonSaveAndReturn", dataLoad.getProperty("buttonSaveAndReturn"));
		this.properties.put("buttonSaveAndDisplay", dataLoad.getProperty("buttonSaveAndDisplay"));
		this.properties.put("buttonCancel", dataLoad.getProperty("buttonCancel"));
	}
/*
 * 2.3 METHODS With some backwards compatability to 2.2
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
		FormActions textAreaEntry = new FormActions(driver);
		textAreaEntry.enterValueInTinyMCE(description);
	}
/**
 * Clicks the show description on course page checkbox. Should work for versions <2.3 and >=2.3.
 */
	public void checkboxDescOnCoursePage() {
		WebElement checkbox = driver.findElementById("id_showdescription");
		checkbox.click();
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
		WebElement checkbox = driver.findElementByName("allowsubmissionsfromdate[enabled]");
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
		WebElement checkbox = driver.findElementByName("duedate[enabled]");
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
 * Selects a value for grade from the Grade dropdown.
 * @param grade The value for grade to be passed from the test. Valid values in a default Moodle install are currently:
 * A range from 1 to 100
 * "No Grade"
 * "Scale: Competencies"
 * "Scale: Separate and Connected ways of knowing"
 */
	public void selectGrade(String grade) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_grade", grade);
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
 * Selects a value for Grade category from the Grade category dropdown.
 * @param gradeCategory The value for Grade category to be passed from the test. Valid values in a default Moodle install are currently:
 * "Uncategorised"
 */
	public void selectGradeCategory(String gradeCategory) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_gradecat", gradeCategory);
	}
/**
 * Selects a value for Group mode from the Group mode dropdown.
 * @param mode The value for Group mode to be passed from the test. Valid values in a default Moodle install are currently:
 * "No groups"
 * "Separate groups"
 * "Visible groups"
 */
	public void selectGroupMode(String mode) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_groupmode", mode);
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
		WebElement checkbox = driver.findElementById("id_groupmembersonly");
		checkbox.click();
	}
/**
 * Selects a value for Visible from the Visible dropdown.
 * @param showHide The value for Visible that is passed from the test. Valid values are:
 * "Show"
 * "Hide"
 */
	public void selectVisible(String showHide) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_visible", showHide);
	}
/**
 * Enters a string the is passed from the test in the ID number field.
 * @param ID The value for ID number, can be anything that will pass validation.
 */
	public void enterID(String ID) {
		WebElement text = driver.findElementById("id_cmidnumber");
		text.sendKeys(ID);
	}
/**
 * Clicks the Show or Hide advanced button in Common Module Settings. 
 */
	public void clickShowHideAdvanced() {
		WebElement button = driver.findElementByName("mform_showadvanced");
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
	public void selectAllowAccessFromEnable() {
		WebElement checkbox = driver.findElementByName("availablefrom[enabled]");
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
	public void selectAllowAccessFromUntil() {
		WebElement checkbox = driver.findElementByName("availableuntil[enabled]");
		checkbox.click();
	}	
/**
 * Clicks Save and return to course	
 */
	public void clickSaveAndRetToCourse() {
		WebElement button = driver.findElement(By .xpath(".//*[@value='" +
				this.properties.get("buttonSaveAndReturn") +
				"']"));
		button.click();
	}
/**
 * Clicks Save and display
 */
	public void clickSaveAndDisplay() {
		WebElement button = driver.findElement(By .xpath(".//*[@value='" +
				this.properties.get("buttonSaveAndDisplay") +
				"']"));
		button.click();
	}
/**
 * Clicks Cancel
 */
	public void clickCancel() {
		WebElement button = driver.findElement(By .xpath(".//*[@value='" +
				this.properties.get("buttonCancel") +
				"']"));
		button.click();
	}
}