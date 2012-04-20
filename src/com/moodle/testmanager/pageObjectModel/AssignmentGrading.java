package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.FormActions;
import com.moodle.seleniumutils.PassFailCriteria;
/**
 * The page object model for the Assignment module.
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
		this.properties.put("linkComments", dataLoad.getProperty("linkComments"));
		this.properties.put("linkSaveComment", dataLoad.getProperty("linkSaveComment"));
		this.properties.put("linkCancelComment", dataLoad.getProperty("linkCancelComment"));
		this.properties.put("submissionStatusField", dataLoad.getProperty("submissionStatusField"));
		this.properties.put("errorSubmissionStatus", dataLoad.getProperty("errorSubmissionStatus"));
		this.properties.put("errorAssignmentName", dataLoad.getProperty("errorAssignmentName"));
		//this.properties.put("PROPERTY", dataLoad.getProperty("PROPERTY"));
	}
/**
 * Clicks the Submitted for grading link.
 */
	public void clickLinkSubmittedForGrading(String studentName) {
		WebElement link = driver.findElementByXPath("//tr[contains(.,'" +
				studentName +
				"')]/td/a[@class='submissionstatussubmitted']");
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
 * Clicks the comments link to display submission comments.
 */
	public void clickLinkSubmissionComments() {
		WebElement link = driver.findElementByPartialLinkText(this.properties.get("linkComments"));
		link.click();
	}
/**
 * Enters text in the submission comments text box.
 * @param desiredComment The text that you want to enter in the comments box. Pass from the test.
 */
	public void enterTextSubmissionComments(String desiredComment) {
		WebElement textArea = driver.findElementByXPath("//div[@class='comment-area']/div/textarea");
		textArea.sendKeys(desiredComment);
	}
/**
 * Clicks the link to save a submission comment.
 */
	public void clickLinkSaveComment() {
		WebElement link = driver.findElementByLinkText(this.properties.get("linkSaveComment"));
		link.click();
	}
/**
 * Clicks the link to cancel a submission comment.
 */
	public void clickLinkCancelComment() {
		WebElement link = driver.findElementByLinkText(this.properties.get("linkCancelComment"));
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
 * Makes the test fail if the submission status is not displayed onscreen when grading an assignment.
 * @param submissionStatus The desired submission status. The value of which is passed from the test.
 */
	public void assertSubmissionStatus(String submissionStatus) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//tr[contains(.,'" +
				this.properties.get("submissionStatusField") +
				"')][contains(.,'" +
				submissionStatus +
				"')]", this.properties.get("errorSubmissionStatus") + " " + submissionStatus, submissionStatus);
	}
}