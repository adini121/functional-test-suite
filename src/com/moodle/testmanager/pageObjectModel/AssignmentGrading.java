package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

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
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean studentVisible = false;
		try {
		WebElement link = driver.findElementByXPath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')]/td/a/*[@title='Grade']");
		studentVisible = link.isDisplayed();
		}
		catch (NoSuchElementException ex){};
		if (studentVisible) {
			WebElement link = driver.findElementByXPath("//tr[contains(.,'" + studentFirstName + " " + studentSurname + "')]/td/a/*[@title='Grade']");
			link.click();
		}
		else {
			WebElement next = driver.findElementByLinkText("Next");
			next.click();
			clickLinkGrade(studentFirstName, studentSurname);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
 * @throws Exception 
 */
	public void enterFeedbackComments(String textToBeEntered) throws Exception {
		FormActions textAreaEntry = new FormActions(driver);
		textAreaEntry.enterValueInTextArea(textToBeEntered);
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
 * Clicks the link to sort the grading table by First Name.	
 */
	public void clickLinkSortFirstName() {
		WebElement link = driver.findElement(By .xpath("//a[contains(.,'" + (this.properties.get("linkTextFirstName") + "')]")));
		link.click();
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
 * Hides or un-hides the name field in the grader table.
 */
	public void clickHideName() {
		WebElement img = driver.findElement(By .xpath("//th[contains(.,'" + this.properties.get("fieldHeadingFirstName") + "')]/div[@class='commands']/a"));
		img.click();
	}
}