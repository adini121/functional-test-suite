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
public class PageAddForm extends FormAddEditSettings {
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/PageAddForm.properties";
/**
 * Locator variables.
 */
	private String locCBExamples="id_useexamples";


/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public PageAddForm(RemoteWebDriver driver) {
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
	private void checkboxPage(String cboxLocator){
		WebElement checkbox = driver.findElementById(cboxLocator);
		checkbox.click();
	}
/**
 * This form contains multiple rich text editors so it is necessary to locate anything other than the first on the page specifically.
 * The Module description is located by the "iframe" tagname as this is the fastest location strategy. This will still work as WebDriver will just find the
 * First element on the page. XPath is programmable so that has been used to locate the other TinyMCE elements. In this method the
 * Xpath is formed by using the ID of the table containing the form element and concatenating a prefix containing the XPath either side of that. 
 * @param message The character sequence that you would like to enter. 
 * @param tableID The ID of the table contining the iframe form element that locates the editor that you want to use. 
 */
	private void richTextFieldPage(CharSequence message, String tableID){
		formActions.enterValueGenericTinyMCE(message, tableID);
	}
/*
 * Page objects
 */

}