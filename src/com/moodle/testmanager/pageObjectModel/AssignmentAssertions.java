package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import bsh.This;

public class AssignmentAssertions extends PassFailAssertions{
public static String assignmentData = "properties/data/static/assignment.properties";
public static String assignmentAddSubmissionData = "properties/data/static/assignmentAddSubmission.properties";
public static String assignmentGradingData = "properties/data/static/assignmentGrading.properties";
public static String assignmentSubmissionCommentsData = "properties/data/static/assignmentSubmissionComments.properties";
private Map<String, String> properties = new HashMap<String, String>();
public AssignmentAssertions(RemoteWebDriver driver) {
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
		dataLoad.load(new FileInputStream(assignmentData));
		dataLoad.load(new FileInputStream(assignmentAddSubmissionData));
		dataLoad.load(new FileInputStream(assignmentGradingData));
		dataLoad.load(new FileInputStream(assignmentSubmissionCommentsData));
    } catch (Exception e) {}
    //put values from the properties file into hashmap
    //Element locator variables
    this.properties.put("buttonEditMySubmission", dataLoad.getProperty("buttonEditMySubmission"));
    this.properties.put("submissionTableOnlineText", dataLoad.getProperty("submissionTableOnlineText"));
    this.properties.put("submissionStatusField", dataLoad.getProperty("submissionStatusField"));
    this.properties.put("gradingSummarySubmittedField", dataLoad.getProperty("gradingSummarySubmittedField"));
    //Exception messages
    this.properties.put("exceptionSubmissionEnabled", dataLoad.getProperty("exceptionSubmissionEnabled"));
    this.properties.put("errorMessageSubmissionNotSaved", dataLoad.getProperty("errorMessageSubmissionNotSaved"));
    this.properties.put("errorAssignmentName", dataLoad.getProperty("errorAssignmentName"));
    this.properties.put("errorSubmissionStatus", dataLoad.getProperty("errorSubmissionStatus"));
    this.properties.put("errorSubmitted", dataLoad.getProperty("errorSubmitted"));
    this.properties.put("errorFeedbackComments", dataLoad.getProperty("errorFeedbackComments"));
    this.properties.put("errorGrade", dataLoad.getProperty("errorGrade"));
    this.properties.put("errorSortOrderFirstName", dataLoad.getProperty("errorSortOrderFirstName"));
	this.properties.put("errorNumberOfPagesLink", dataLoad.getProperty("errorNumberOfPagesLink"));
	this.properties.put("errorNextMissing", dataLoad.getProperty("errorNextMissing"));
	this.properties.put("errorPreviousMissing", dataLoad.getProperty("errorPreviousMissing"));
	this.properties.put("errorPaginationStillOn", dataLoad.getProperty("errorPaginationStillOn"));
	this.properties.put("errorNameColumnNotHidden", dataLoad.getProperty("errorNameColumnNotHidden"));
	this.properties.put("exceptionMessageCommentPresentShouldNotBe", dataLoad.getProperty("exceptionMessageCommentPresentShouldNotBe"));
	this.properties.put("exceptionMessageCommentNotPresent", dataLoad.getProperty("exceptionMessageCommentNotPresent"));
	//this.properties.put("PROPERTY", dataLoad.getProperty("PROPERTY"));		
}
//Get properties and put them into a field.
//Element locator variables
protected String buttonEditMySubmission = this.properties.get("buttonEditMySubmission");
protected String submissionTableOnlineText = this.properties.get("submissionTableOnlineText");
protected String submissionStatusField = this.properties.get("submissionStatusField");
protected String gradingSummarySubmittedField = this.properties.get("gradingSummarySubmittedField");
//Exception messages
protected String exceptionSubmissionEnabled = this.properties.get("exceptionSubmissionEnabled");
protected String errorMessageSubmissionNotSaved = this.properties.get("errorMessageSubmissionNotSaved");
protected String errorAssignmentName = this.properties.get("errorAssignmentName");
protected String errorSubmissionStatus = this.properties.get("errorSubmissionStatus");
protected String errorSubmitted = this.properties.get("errorSubmitted");
protected String errorFeedbackComments = this.properties.get("errorFeedbackComments");
protected String errorGrade = this.properties.get("errorGrade");
protected String errorSortOrderFirstName = this.properties.get("errorSortOrderFirstName");
/**
 * 
 * @throws Exception
 */
public void assertFileSubmissionDisabled() throws Exception {
	assertItemNotOnscreenByCSSSelector("input[value='" + this.properties.get("buttonEditMySubmission") + "']", this.properties.get("exceptionSubmissionEnabled"), 2);
}
/**
 * Makes the test fail if mod/assign/view.php does not contain the assignment title. Useful for verifying that the page has been loaded.
 * @param assignmentName The Assignment name, it should appear as a heading on the submission page. Pass the assignment name form the test.
 */
public void assertSubmissionPage(String assignmentName) {
	assertTextPresentByXpath("//h2[contains(.,'" + assignmentName + "')]", 
		"The assignment name should appear onscreen.", assignmentName);
}
/**
 * Makes the test fail if the submission text doesn't appear on mod/assign/view.php. This verifies that the assignment has been saved.
 * @param submissionText The submission text that the student has entered. The value for this is passed from the test.
 * @throws Exception Throws an exception if the specified element ins not present onscreen.
 */
public void assertSubmissionOnlineText(String submissionText) throws Exception {
	assertElementIsPresentByXpath("//tr[contains(.,'" + this.properties.get("submissionTableOnlineText") + "')][contains(.,'" + submissionText + "')]",
		this.properties.get("errorMessageSubmissionNotSaved"), 2);		
}
/**
 * Makes the test fail if mod/assign/view.php does not contain the assignment title. Useful for verifying that the page has been loaded.
 * @param assignmentName The Assignment name, it should appear as a heading on the grading summary page. Pass the assignment name form the test.
 */
public void assertGradingSummaryPage(String assignmentName) {
	assertTextPresentByXpath("//h2[contains(.,'" + assignmentName +	"')]", this.properties.get("errorAssignmentName"), assignmentName);
}
/**
 * Makes the test fail if the submission status is not displayed on the grading form when grading an assignment.
 * @param submissionStatus The desired submission status. The value of which is passed from the test.
 * @throws Exception Throws exception when the status given is not present onscreen.
 */
public void assertSubmissionStatusGradingForm(String submissionStatus) throws Exception {
	assertElementIsPresentByXpath("//tr[contains(.,'" +	this.properties.get("submissionStatusField") +	"')][contains(.,'" + submissionStatus +	"')]", 
		this.properties.get("errorSubmissionStatus") + " " + submissionStatus, 2);
}
/**
 * Makes the test fail if the corrent number of submissions are not displayed in the grading summary table.
 * @param numberOfSubmissions The number of submissions that should be displayed, this value is passed from the test.
 * @throws Exception Throws a custom exception if the element is not present onscreen.
 */
public void assertNumberOfSubmissions(String numberOfSubmissions) throws Exception {
	//passFail.assertTextPresentByXpath(".//tr[contains(.,'" + this.properties.get("gradingSummarySubmittedField") + "')][contains(.,'" + numberOfSubmissions + "')]", 
	//		numberOfSubmissions + this.properties.get("errorSubmitted"), numberOfSubmissions);
	assertElementIsPresentByXpath(".//tr[contains(.,'" + this.properties.get("gradingSummarySubmittedField") + "')][contains(.,'" + numberOfSubmissions + "')]", 
		this.properties.get("errorSubmitted"), 2);
}
/**
 * Makes the test fail if a given feedback comment doesn't appear in the grading table. 
 * @param feedbackComments The feedback comments that have been entered and should appear in the grading table.
 * @param studentFirstName The Student's first name
 * @param studentSurname The Student's surname
 * @throws Exception Throws an exception if the given text is not displayed int the feedback comments field.
 */
public void assertFeedbackComments(String feedbackComments, String studentFirstName, String studentSurname) throws Exception {
	assertElementIsPresentByXpath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')][contains(.,'" + feedbackComments + "')]", 
		this.properties.get("errorFeedbackComments") + feedbackComments, 2);
}
/**
 * Makes the test fail if the students grade doesn't appear in the grading table.
 * @param grade The grade that has been given to the student and you are expecting to appear in the grading table.
 * @param studentFirstName The student's first name.
 * @param studentSurname The student's surname.
 * @throws Exception Throws an exception if the grade given doesn't appear in the field for grade.
 */
public void assertFinalGradeStandard(String grade, String studentFirstName, String studentSurname) throws Exception {
	assertElementIsPresentByXpath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')][contains(.,'" + grade + "')]", 
		this.properties.get("errorGrade"), 1);
}
/**
 * Makes the test fail if the submission status doesn't appear in the grading table.
 * @param status The submission status that you are expecting to see in the grading table. 
 * @param studentFirstName The student's first name.
 * @param studentSurname The student's surname.
 * @throws Exception Throws an exception if the submission status doesn't appear in the grading table.
 */
public void assertSubmissionStatusGradingTable(String status, String studentFirstName, String studentSurname) throws Exception {
	assertElementIsPresentByXpath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')][contains(.,'" + status + "')]", 
			this.properties.get("errorSubmissionStatus"), 1);
}
/**
 * Makes the test fail if the student's name does not appear in the correct place onscreen
 * @param rowClass The class of the row in the grading table that you are expecting the students name to appear in. e.g."r1" is the first row "r2" is the second row etc.
 * @param studentFirstName The first name of the student.
 * @param studentSurname The surname of the student.
 * @throws Exception Throws an exception if the name is not present in the specified table row.
 */
public void assertSortOrderStudentName(String studentFirstName, String studentSurname) throws Exception {
	assertElementIsPresentByXpath(".//tr/td[@class='cell c3'][contains(.,'" + studentFirstName + " " + studentSurname + "')]",
			studentFirstName + " " + studentSurname + " " + this.properties.get("errorSortOrderFirstName"), 2);
}
/**
 * Makes the test fail if a link to the specified page does not appear onscreen.
 * @param pageNumber The page number for the link you are looking for.
 * @throws Exception Throws an exception if a link to the page does not appear onscreen.
 */
public void assertNumberOfGradingTablePages(String pageNumber) throws Exception {
	//TODO
	assertElementIsPresentByXpath("//div[@class='paging']/a[contains(.,'" + pageNumber + "')]", pageNumber + this.properties.get("errorNumberOfPagesLink"), 2);
}
/**
 * Makes the test fail if a link to the "next" page does not appear onscreen.
 * @throws Exception Throws an exception if the link does not appear.
 */
public void assertNextLink() throws Exception {
	//TODO
	assertElementIsPresentByXpath("//a[@class='next']" , this.properties.get("errorNextMissing"), 2);
}
/**
 * Makes the test fail if a link to the "previous" page does not appear onscreen.
 * @throws Exception Throws an exception if the link does not appear.
 */
public void assertPreviousLink() throws Exception {
	//TODO
	assertElementIsPresentByXpath("//a[@class='previous']" , this.properties.get("errorPreviousMissing"), 2);
}
/**
 * Makes the test fail if pagination is still turned on after it has been turned off by selecting a number higher than the number of assignments.
 * @param pageNumber Any page number that would appear if pagination was turned on.
 * @throws Exception Throws an exception if there is a link to the given page.
 */
public void assertNoLinkGradingTablePageNumber(String pageNumber) throws Exception {
	//TODO
	assertElementIsNotPresentByXpath("//div[@class='paging']/a[contains(.,'" + pageNumber + "')]", this.properties.get("errorPaginationStillOn"), 2);
}
/**
 * Makes the test fail if the students name appears in the first name/surname column if it's hidden.
 * @param studentFirstName The student's first name.
 * @param studentSurname The student's surname.
 * @throws Exception An exception is thrown if the student's name appears in the table indicating that the column is not hidden.
 */
public void assertFirstAndSurnameHidden(String studentFirstName, String studentSurname) throws Exception {
	//TODO
	assertElementIsNotPresentByXpath("//tr/td[@class='cell c1'][contains(.,'" + studentFirstName + " " + studentSurname + "')]", studentFirstName + " " + studentSurname + " " + this.properties.get("errorNameColumnNotHidden"), 0);
}
/**
 * Makes the test fail if a given submission comment has been saved.	
 * @param commentText The text that was entered when the user entered the comment. This is the text that you are verifying is not there.
 * @throws Exception Throws an exception if the comment was saved.
 */
public void assertCommentNotSaved(String commentText) throws Exception {
	//TODO Move properties.get to top
	assertElementIsNotPresentByXpath("//div[contains(.,'" + commentText + "')][@class='text_to_html']", this.properties.get("exceptionMessageCommentPresentShouldNotBe"), 2);
}
/**
 * Makes the test fail if a given submission comment has not been saved.
 * @param commentText The text that was entered when the user entered the comment. This is the text that you are verifying is there.
 * @throws Exception Throws an exception if the comment was not saved.
 */
public void assertCommentSaved(String commentText) throws Exception {
	//TODO Move properties.get to top
	assertElementIsPresentByXpath("//div[@class='no-overflow'][contains(.,'" + commentText + "')]", this.properties.get("exceptionMessageCommentNotPresent"), 2);
}	
}