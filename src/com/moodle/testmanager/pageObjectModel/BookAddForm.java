package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.test.FormActions;
/**
 * This is the page object model for adding a Book resource to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class BookAddForm extends FormAddEditSettings {
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/BookAddForm.properties";
/**
 * Locator variables.
 */
	private String locChapterFormatting="id_numbering";
	private String locCustom="id_customtitles";

/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public BookAddForm(RemoteWebDriver driver) {
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
	public void selectGrade(String grade){
		grade = null;
	}
/**
 * selectGradeCategory is not used.
 */
	@Override
	public void selectGradeCategory(String gradeCategory){
		gradeCategory = null;
	}
/**
 * selectGRoupMode is not used.
 */
	@Override
	public void selectGroupMode(String itemToSelect){
		itemToSelect=null;
	}
/*
 * Specific object methods.
 */
/**
 * Generic checkbox click method for re-use in this class.
 * @param cboxLocator The locator of the checkbox, using ID only.
 */
	private void checkboxBook(String cboxLocator){
		WebElement checkbox = driver.findElementById(cboxLocator);
		checkbox.click();
	}
/**
 * Generic dropdown method for re-use in this class.
 * @param dropdownLocator The locator of the dropdown. With everything here is an id.
 * @param itemToSelect The item that is to be selected.
 */
	private void dropdownBook(String dropdownLocator, String itemToSelect){
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID(dropdownLocator, itemToSelect);
	}
/**
 * Selects a value from the Chapter formatting dropdown.
 * @param format
 */
	public void selectChapterFormatting(String format){
		dropdownBook(locChapterFormatting, format);
	}
/**
 * Selects/de-selects the Custom titles checkbox.
 */
	public void clickCustomTitlesCheckbox(){
		checkboxBook(locCustom);
	}
}