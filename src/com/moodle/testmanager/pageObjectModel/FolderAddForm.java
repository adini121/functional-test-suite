package com.moodle.testmanager.pageObjectModel;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for adding a Folder resource to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FolderAddForm extends FormAddEditSettingsFileManager {
/**
 * Hashmap for language file.
 */
	@SuppressWarnings("unused")
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/FolderAddForm.properties";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public FolderAddForm(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData(langFile);
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	/*public void loadObjectData(String datafile) {
		Properties databaseAddData = new Properties();
		try {
			databaseAddData.load(new FileInputStream(langFile));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("PROPERTY", databaseAddData.getProperty("PROPERTY"));
	}*/
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
 * selectGroupMode is not used.
 */
	@Override
	public void selectGroupMode(String itemToSelect){
		itemToSelect=null;
	}
}