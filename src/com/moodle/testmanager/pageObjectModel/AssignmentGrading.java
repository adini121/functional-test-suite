package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.PassFailCriteria;
import com.moodle.testmanager.FormActions;
/**
 * The page object model for the Assignment Grading.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class AssignmentGrading {
	//Internationalization file location
	public static String data = "properties/data/static/assignmentGrading.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public AssignmentGrading(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/assignmentGrading.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(data));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("submissionStatusField", dataLoad.getProperty("submissionStatusField"));
		this.properties.put("errorSubmissionStatus", dataLoad.getProperty("errorSubmissionStatus"));
		this.properties.put("errorAssignmentName", dataLoad.getProperty("errorAssignmentName"));
		this.properties.put("gradingSummarySubmittedField", dataLoad.getProperty("gradingSummarySubmittedField"));
		this.properties.put("errorSubmitted", dataLoad.getProperty("errorSubmitted"));
		this.properties.put("errorFeedbackComments", dataLoad.getProperty("errorFeedbackComments"));
		this.properties.put("errorGrade", dataLoad.getProperty("errorGrade"));
		this.properties.put("errorNumberOfPagesLink", dataLoad.getProperty("errorNumberOfPagesLink"));
		this.properties.put("errorNextMissing", dataLoad.getProperty("errorNextMissing"));
		this.properties.put("errorPreviousMissing", dataLoad.getProperty("errorPreviousMissing"));
		this.properties.put("linkTextFirstName", dataLoad.getProperty("linkTextFirstName"));
		this.properties.put("errorSortOrderFirstName", dataLoad.getProperty("errorSortOrderFirstName"));
		this.properties.put("errorPaginationStillOn", dataLoad.getProperty("errorPaginationStillOn"));
		this.properties.put("fieldHeadingFirstName", dataLoad.getProperty("fieldHeadingFirstName"));
		this.properties.put("errorNameColumnNotHidden", dataLoad.getProperty("errorNameColumnNotHidden"));
		//this.properties.put("PROPERTY", dataLoad.getProperty("PROPERTY"));
	}
/**
 * Clicks the Submitted for grading link.
 */
	public void clickLinkSubmittedForGrading(String studentFirstname, String studentSurname) {
		WebElement link = driver.findElementByXPath("//tr[contains(.,'" + studentFirstname + " " + studentSurname +	"')]/td/a[@class='submissionstatussubmitted']");
		link.click();
	}
/**
 * Clicks the Grade link
 */
	public void clickLinkGrade(String studentFirstName, String studentSurname) {
		WebElement link = driver.findElementByXPath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')]/td/a/*[@title='Grade']");
		link.click();
	}
/**
 * Downloads a file with a given filename.
 * @param filename The filename that you would like to download.
 */
	public void clickLinkFileSubmission(String filename) {
		WebElement link = driver.findElementByLinkText(filename);
		link.click();
	}
/**
 * Enters a value for desired grade if the grade is a standard grade i.e. not a rubric.
 * @param desiredGrade The grade that you want to enter. Pass this from the test.
 */
	public void enterTextStandardGrade(String desiredGrade) {
		WebElement text = driver.findElementById("id_grade");
		text.sendKeys(desiredGrade);
	}
/**
 * Enters a value in the Feedback comments rich text editor field.
 * @param textToBeEntered The text to be entered. Pass this value from the test.
 */
	public void enterFeedbackComments(String textToBeEntered) {
		FormActions textAreaEntry = new FormActions(driver);
		textAreaEntry.enterValueInTinyMCE(textToBeEntered);
	}
/**
 * Clicks the Create folder button. Re-uses objects from AssignmentAddSubmission.java
 */
	public void clickButtonCreateFolder() {
		AssignmentAddSubmission clickButton = new AssignmentAddSubmission(driver);
		clickButton.clickButtonCreateFolder();
	}
/**
 * Enters a value for folder name. Re-uses objects from AssignmentAddSubmission.java
 * @param folderName The desired name of the folder. Pass from the test.
 */
	public void enterTextFolderName(String folderName) {
		AssignmentAddSubmission enterText = new AssignmentAddSubmission(driver);
		enterText.enterTextFolderName(folderName);
	}
/**
 * Clicks the OK button when entering a folder name. Re-uses objects from AssignmentAddSubmission.java
 */
	public void clickButtonOKFolderName() {
		AssignmentAddSubmission clickButton = new AssignmentAddSubmission(driver);
		clickButton.clickButtonOKFolderName();
	}
/**
 * Clicks the Cancel button when entering a folder name. Re-uses objects from AssignmentAddSubmission.java
 */
	public void clickButtonCancelFolderName() {
		AssignmentAddSubmission clickButton = new AssignmentAddSubmission(driver);
		clickButton.clickButtonCancelFolderName();
	}
/**
 * Clicks the Save changes button. Re-uses objects from AssignmentAddSubmission.java
 */
	public void clickButtonSaveChanges() {
		AssignmentAddSubmission clickButton = new AssignmentAddSubmission(driver);
		clickButton.clickButtonSaveChanges();
	}
/**
 * Clicks the Cancel button. Re-uses objects from AssignmentAddSubmission.java
 */
	public void clickButtonCancel() {
		AssignmentAddSubmission clickButton = new AssignmentAddSubmission(driver);
		clickButton.clickButtonCancel();
	}
/**
 * Makes the test fail if mod/assign/view.php does not contain the assignment title. Useful for verifying that the page has been loaded.
 * @param assignmentName The Assignment name, it should appear as a heading on the grading summary page. Pass the assignment name form the test.
 */
	public void assertGradingSummaryPage(String assignmentName) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//h2[contains(.,'" +
				assignmentName +
				"')]", this.properties.get("errorAssignmentName"), assignmentName);
	}
/**
 * Makes the test fail if the submission status is not displayed on the grading form when grading an assignment.
 * @param submissionStatus The desired submission status. The value of which is passed from the test.
 * @throws Exception Throws exception when the status given is not present onscreen.
 */
	public void assertSubmissionStatusGradingForm(String submissionStatus) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//tr[contains(.,'" +
				this.properties.get("submissionStatusField") +
				"')][contains(.,'" +
				submissionStatus +
				"')]", this.properties.get("errorSubmissionStatus") + " " + submissionStatus, 2);
	}
/**
 * Makes the test fail if the corrent number of submissions are not displayed in the grading summary table.
 * @param numberOfSubmissions The number of submissions that should be displayed, this value is passed from the test.
 * @throws Exception Throws a custom exception if the element is not present onscreen.
 */
	public void assertNumberOfSubmissions(String numberOfSubmissions) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		//passFail.assertTextPresentByXpath(".//tr[contains(.,'" + this.properties.get("gradingSummarySubmittedField") + "')][contains(.,'" + numberOfSubmissions + "')]", 
		//		numberOfSubmissions + this.properties.get("errorSubmitted"), numberOfSubmissions);
		passFail.assertElementIsPresentByXpath(".//tr[contains(.,'" + this.properties.get("gradingSummarySubmittedField") + "')][contains(.,'" + numberOfSubmissions + "')]", this.properties.get("errorSubmitted"), 2);
	}
/**
 * Makes the test fail if a given feedback comment doesn't appear in the grading table. 
 * @param feedbackComments The feedback comments that have been entered and should appear in the grading table.
 * @param studentFirstName The Student's first name
 * @param studentSurname The Student's surname
 * @throws Exception Throws an exception if the given text is not displayed int the feedback comments field.
 */
	public void assertFeedbackComments(String feedbackComments, String studentFirstName, String studentSurname) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')][contains(.,'" + feedbackComments + "')]", this.properties.get("errorFeedbackComments") + feedbackComments, 2);
	}
/**
 * Makes the test fail if the students grade doesn't appear in the grading table.
 * @param grade The grade that has been given to the student and you are expecting to appear in the grading table.
 * @param studentFirstName The student's first name.
 * @param studentSurname The student's surname.
 * @throws Exception Throws an exception if the grade given doesn't appear in the field for grade.
 */
	public void assertFinalGradeStandard(String grade, String studentFirstName, String studentSurname) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')][contains(.,'" + grade + "')]", this.properties.get("errorGrade"), 1);
	}
/**
 * Makes the test fail if the submission status doesn't appear in the grading table.
 * @param status The submission status that you are expecting to see in the grading table. 
 * @param studentFirstName The student's first name.
 * @param studentSurname The student's surname.
 * @throws Exception Throws an exception if the submission status doesn't appear in the grading table.
 */
	public void assertSubmissionStatusGradingTable(String status, String studentFirstName, String studentSurname) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')][contains(.,'" + status + "')]", this.properties.get("errorSubmissionStatus"), 1);
	}
/**
 * Clicks the link to sort the grading table by First Name.	
 */
	public void clickLinkSortFirstName() {
		WebElement link = driver.findElement(By .xpath("//a[contains(.,'" + (this.properties.get("linkTextFirstName") + "')]")));
		link.click();
	}
/**
 * Makes the test fail if the student's name does not appear in the correct place onscreen
 * @param rowClass The class of the row in the grading table that you are expecting the students name to appear in. e.g."r1" is the first row "r2" is the second row etc.
 * @param studentFirstName The first name of the student.
 * @param studentSurname The surname of the student.
 * @throws Exception Throws an exception if the name is not present in the specified table row.
 */
	public void assertSortOrderStudentName(String rowClass, String studentFirstName, String studentSurname) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath(".//tr[@class='" + rowClass + "']/td[@class='cell c1'][contains(.,'" + studentFirstName + " " + studentSurname + "')]",
				studentFirstName + " " + studentSurname + " " + this.properties.get("errorSortOrderFirstName"), 2);
	}
/**
 * Makes the test fail if a link to the specified page does not appear onscreen.
 * @param pageNumber The page number for the link you are looking for.
 * @throws Exception Throws an exception if a link to the page does not appear onscreen.
 */
	public void assertNumberOfGradingTablePages(String pageNumber) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//div[@class='paging']/a[contains(.,'" + pageNumber + "')]", pageNumber + this.properties.get("errorNumberOfPagesLink"), 2);
		}
/**
 * Makes the test fail if a link to the "next" page does not appear onscreen.
 * @throws Exception Throws an exception if the link does not appear.
 */
	public void assertNextLink() throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//a[@class='next']" , this.properties.get("errorNextMissing"), 2);
	}
/**
 * Makes the test fail if a link to the "previous" page does not appear onscreen.
 * @throws Exception Throws an exception if the link does not appear.
 */
	public void assertPreviousLink() throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath("//a[@class='previous']" , this.properties.get("errorPreviousMissing"), 2);
	}
/**
 * Clicks a link to a given page in the grading table.
 * @param pageNumber The page number that you want to navigate to.
 */
	public void clickLinkGradingTablePageNumber(String pageNumber) {
		WebElement link = driver.findElement(By .xpath("//div[@class='paging']/a[contains(.,'" + pageNumber + "')]"));
		link.click();		
	}
/**
 * Selects a value from the Assignments per page dropdown on the grading table page.
 * @param itemToSelect The item to select from the dropdown. Pass this value from the test.
 */
	public void selectValueAssignmentsPerPage(String itemToSelect) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByIDHandlesJS("id_perpage", itemToSelect, "id_submitbutton", 1);
	}
/**
 * Makes the test fail if pagination is still turned on after it has been turned off by selecting a number higher than the number of assignments.
 * @param pageNumber Any page number that would appear if pagination was turned on.
 * @throws Exception Throws an exception if there is a link to the given page.
 */
	public void assertNoLinkGradingTablePageNumber(String pageNumber) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsNotPresentByXpath("//div[@class='paging']/a[contains(.,'" + pageNumber + "')]", this.properties.get("errorPaginationStillOn"), 2);
	}
/**
 * Hides or un-hides the name field in the grader table.
 */
	public void clickHideName() {
		WebElement img = driver.findElement(By .xpath("//th[contains(.,'" + this.properties.get("fieldHeadingFirstName") + "')]/div[@class='commands']/a"));
		img.click();
	}
/**
 * Makes the test fail if the students name appears in the first name/surname column if it's hidden.
 * @param studentFirstName The student's first name.
 * @param studentSurname The student's surname.
 * @throws Exception An exception is thrown if the student's name appears in the table indicating that the column is not hidden.
 */
	public void assertFirstAndSurnameHidden(String studentFirstName, String studentSurname) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsNotPresentByXpath("//tr/td[@class='cell c1'][contains(.,'" + studentFirstName + " " + studentSurname + "')]", studentFirstName + " " + studentSurname + " " + this.properties.get("errorNameColumnNotHidden"), 0);
	}
}