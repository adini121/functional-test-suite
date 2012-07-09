package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the add survey form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class SurveyAddForm extends FormAddEditSettings{
/**
 * Constructors
 */
	private FormActions formActions = new FormActions(driver);
/**
 * Locator variables.
 */
	private String locSurveyType = "id_template";
/**
 * Constructor for the page object.	
 */
	public SurveyAddForm(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData("properties/data/static/SurveyAddForm.properties");
	}
/**
 * Loads data for the page object from the internationalization layer
 * where a locator requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/SurveyAddForm.properties"));
		} 
		catch (Exception e) {}
	}
/*
 * Override unused methods from Super.	
 */
/**
 * Selects a value for grade from the Grade dropdown.
 * @param grade The value for grade to be passed from the test. Valid values in a default Moodle install are currently:
 * A range from 1 to 100
 * "No Grade"
 * "Scale: Separate and Connected ways of knowing"
 */
	@Override
	public void selectGrade(String grade) {
		grade = null;
	}
/**
 * Selects a value for Grade category from the Grade category dropdown.
 * @param gradeCategory The value for Grade category to be passed from the test. Valid values in a default Moodle install are currently:
 * "Uncategorised"
 */
	@Override
	public void selectGradeCategory(String gradeCategory) {
		gradeCategory = null;
	}
/*
 * Specific object methods.
 */
/**
 * Selects a value from the survey type dropdown.
 * @param surveyType The desired value for survey type.
 */
	public void selectSurveyType(String surveyType) {
		formActions.selectDropdownItemByID(locSurveyType, surveyType);
	}
}