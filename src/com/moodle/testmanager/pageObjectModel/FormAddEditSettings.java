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
 * The page object model for the Add Assignment form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public abstract class FormAddEditSettings {
	RemoteWebDriver driver;
	protected Map<String, String> properties = new HashMap<String, String>();
	protected FormActions formActions = new FormActions(driver);
	private String locAdvBtn = "showadvancedbtn";
	public FormAddEditSettings(RemoteWebDriver driver) {
		super();
		this.driver = driver;
		this.loadSuperData("properties/data/static/formSettings.properties");
	}
/**
 * Loads data for the page object from the internationalization layer
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadSuperData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/formSettings.properties"));
		} catch (Exception e) {}
		this.properties.put("formButtonSaveReturn", dataLoad.getProperty("formButtonSaveReturn"));
		this.properties.put("formButtonSaveDisplay", dataLoad.getProperty("formButtonSaveDisplay"));
		this.properties.put("formButtonCancel", dataLoad.getProperty("formButtonCancel"));
	}
/**
 * Enter a value in the name field of the form.
 * @param name The value that yo want to enter in the name field.
 */
	public void enterNameField(String name) {
		WebElement nameField = this.driver.findElement(By .id("id_name"));
		nameField.sendKeys(name);
	}
/**
 * The value that you would like to enter in the Intro/Description field.
 * @param text The value that you would like to enter.
 * @throws Exception 
 */
	public void enterDescriptionField(CharSequence text) throws Exception {
		FormActions textArea = new FormActions(driver);
		textArea.enterValueInTextArea(text);	
	}
/**
 * Selects or deselects the "Display description on page" checkbox.  
 */
	public void checkboxDescOnCourse(){
		driver.findElementById("id_showdescription").click();
	}
/**
 * Selects a value from the "Group mode" dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectGroupMode(String itemToSelect){
		formActions.selectDropdownItemByID("id_groupmode", itemToSelect);
	}
/**
 * Selects a value from the "Visible" dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectVisible(String itemToSelect){
		formActions.selectDropdownItemByID("id_visible", itemToSelect);
	}
/**
 * Enters a vlaue in the "ID number" field.
 * @param idNumber The ID number that you want to send.
 */
	public void idNumber(String idNumber){
		driver.findElementById("id_cmidnumber").sendKeys(idNumber);
	}
/**
 * Selects a value for grade from the Grade dropdown.
 * @param grade The value for grade to be passed from the test. Valid values in a default Moodle install are currently:
 * A range from 1 to 100
 * "No Grade"
 * "Scale: Separate and Connected ways of knowing"
 */
	public void selectGrade(String grade) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_grade", grade);
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
 * Selects the show advanced button by class name.
 */
	public void clickShowHideAdvanced(){
		WebElement advButton = driver.findElementByClassName(locAdvBtn);
		advButton.click();
	}
/**
 * Select Save and return to course.
 */
	public void clickSaveAndRetToCourse() {
		WebElement save = this.driver.findElement(By .xpath(".//*[@value='" + this.properties.get("formButtonSaveReturn") + "']"));
		save.click();
	}
/**
 * Select Save and return to course.
 */
	public void clickSaveAndDisplay() {
		WebElement save = this.driver.findElement(By .xpath(".//*[@value='" + this.properties.get("formButtonSaveDisplay") + "']"));
		save.click();
	}
/**
 * Select Save and return to course.
 */
	public void clickCancel() {
		WebElement save = this.driver.findElement(By .xpath(".//*[@value='" + this.properties.get("formButtonCancel") + "']"));
		save.click();
	}
}