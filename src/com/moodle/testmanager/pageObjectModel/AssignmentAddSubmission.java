package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * The page object model for the Add Submission.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class AssignmentAddSubmission {
	//Internationalization file location
	public static String data = "properties/data/static/assignmentAddSubmission.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public AssignmentAddSubmission(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/assignmentAddSubmission.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(data));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("buttonSaveChanges", dataLoad.getProperty("buttonSaveChanges"));
		this.properties.put("buttonCancel", dataLoad.getProperty("buttonCancel"));
		this.properties.put("buttonAdd", dataLoad.getProperty("buttonAdd"));
		this.properties.put("buttonCreateFolder", dataLoad.getProperty("buttonCreateFolder"));
		this.properties.put("buttonOK", dataLoad.getProperty("buttonOK"));
		this.properties.put("buttonSubmit", dataLoad.getProperty("buttonSubmit"));
		this.properties.put("submissionTableOnlineText", dataLoad.getProperty("submissionTableOnlineText"));
		this.properties.put("errorMessageSubmissionNotSaved", dataLoad.getProperty("errorMessageSubmissionNotSaved"));
		//this.properties.put("PROPERTY", dataLoad.getProperty("PROPERTY"));
	}
/*
 * Clicks the submission statement checkbox that appears on Netspot's website
 */
/**
 * Clicks the submission statement checkbox. Test will not fail with a No Such Element Exception but will continue to next step.
 */
	public void clickCheckboxSubmissionStatement() {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement checkbox = driver.findElementById("id_submissionstatement");
			itemVisible = checkbox.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			WebElement checkbox = driver.findElementById("id_submissionstatement");
			checkbox.click();
		}
		else{}
	}
/**
 * Enters the text for an online submission.
 * @param textToBeEntered The test to be entered as the online text submission.
 * @throws Exception 
 */
	public void enterOnlineText(String textToBeEntered) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		FormActions textAreaEntry = new FormActions(driver);
		textAreaEntry.enterValueInTextArea(textToBeEntered);
	}
/**
 * Clicks the Add button to launch the file picker.
 */
	public void clickButtonAdd() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonAdd") +
				"']");
		button.click();
	}
/**
 * Clicks the Create folder button.
 */
	public void clickButtonCreateFolder() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonCreateFolder") +
				"']");
		button.click();
	}
/**
 * Enters a value for folder name.
 * @param folderName The desired name of the folder. Pass from the test.
 */
	public void enterTextFolderName(String folderName) {
		WebElement yuiTextField = driver.findElementByCssSelector("input[type='text']");
		yuiTextField.sendKeys(folderName);
	}
/**
 * Clicks the OK button when entering a folder name.
 */
	public void clickButtonOKFolderName() {
		WebElement yuiButton = driver.findElementByXPath("//button[contains(.,'" +
				this.properties.get("buttonOK") +
				"')]");
		yuiButton.click();
	}
/**
 * Clicks the Cancel button when entering a folder name.
 */
	public void clickButtonCancelFolderName() {
		WebElement yuiButton = driver.findElementByXPath("//button[contains(.,'" +
				this.properties.get("buttonCancel") +
				"')]");
		yuiButton.click();
	}
/**
 * Clicks the Save changes button.
 */
	public void clickButtonSaveChanges() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonSaveChanges") +
				"']");
		button.click();
	}
/**
 * Clicks the Cancel button.
 */
	public void clickButtonCancel() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonCancel") +
				"']");
		button.click();
	}
/**
 * Clicks the Submit button.
 */
	public void clickButtonSubmit() {
		WebElement button = driver.findElementByCssSelector("input[value='" +
				this.properties.get("buttonSubmit") +
				"']");
		button.click();
	}
}