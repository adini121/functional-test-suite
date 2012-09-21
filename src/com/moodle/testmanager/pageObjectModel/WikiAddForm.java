package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for adding a Wiki activity to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class WikiAddForm extends FormAddEditSettings {
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
	private String locFirstPageName="id_firstpagetitle";
	private String locWikiMode="id_wikimode";
	private String locDefaultFormat="id_defaultformat";
	private String locForceFormat="id_forceformat";

/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public WikiAddForm(RemoteWebDriver driver) {
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
	private void checkboxWiki(String cboxLocator){
		WebElement checkbox = driver.findElementById(cboxLocator);
		checkbox.click();
	}
/**
 * Generic dropdown method for re-use in this class.
 * @param dropdownLocator The locator of the dropdown. With everything here is an id.
 * @param itemToSelect The item that is to be selected.
 */
	private void dropdownWiki(String dropdownLocator, String itemToSelect){
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID(dropdownLocator, itemToSelect);
	}
/**
 * Generic text entry field for this class.
 * @param fieldLocator The locator value for field. Allways ID in this form.
 * @param valueToEnter The value that you would like to enter in the text field.
 */
	private void textFieldWiki(String fieldLocator, CharSequence valueToEnter){
		WebElement textField = driver.findElementById("id_firstpagetitle");
		textField.sendKeys(valueToEnter);
	}
/**
 * Enters a value in the "First page name" field.
 * @param firstPageName
 */
	public void enterFirstPageNameField(CharSequence firstPageName){
		textFieldWiki(locFirstPageName, firstPageName);
	}
/**
 * Selects a value from the "Wiki mode" dropdown.
 * @param wikiMode The value that you would like to select.
 */
	public void selectWikiMode(String wikiMode){
		dropdownWiki(locWikiMode, wikiMode);
	}
/**
 * Selects a value for the "Default format" dropdown.
 * @param defaultFormat The value that you would like to select.
 */
	public void selectDefaultFormat(String defaultFormat){
		dropdownWiki(locDefaultFormat, defaultFormat);
	}
/**
 * Selects or deselects the Force format checkbox.
 */
	public void checkboxForceFormat(){
		checkboxWiki(locForceFormat);
	}
}