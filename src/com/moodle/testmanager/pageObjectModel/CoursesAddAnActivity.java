package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.FormActions;
/**
 * This is the page object model for the adding activities to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class CoursesAddAnActivity {
	//Internationalization file location
	public static String activitiesData = "properties/data/static/coursesAddAnActivity.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public CoursesAddAnActivity(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadTestData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/coursesAddAnActivity.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadTestData() {
		Properties activityDropdown = new Properties();
		try {
			activityDropdown.load(new FileInputStream(activitiesData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("activity", activityDropdown.getProperty("activity"));
		this.properties.put("activityAdvancedUpload", activityDropdown.getProperty("activityAdvancedUpload"));
		this.properties.put("activityOnlineText", activityDropdown.getProperty("activityOnlineText"));
		this.properties.put("activityUploadSingleFile", activityDropdown.getProperty("activityUploadSingleFile"));
		this.properties.put("activityOfflineActivity", activityDropdown.getProperty("activityOfflineActivity"));
		this.properties.put("activityChat", activityDropdown.getProperty("activityChat"));
		this.properties.put("activityChoice", activityDropdown.getProperty("activityChoice"));
		this.properties.put("activityDatabase", activityDropdown.getProperty("activityDatabase"));
		this.properties.put("activityExternalTool", activityDropdown.getProperty("activityExternalTool"));
		this.properties.put("activityForum", activityDropdown.getProperty("activityForum"));		
		this.properties.put("activityGlossary", activityDropdown.getProperty("activityGlossary"));
		this.properties.put("activityLesson", activityDropdown.getProperty("activityLesson"));
		this.properties.put("activityQuiz", activityDropdown.getProperty("activityQuiz"));
		this.properties.put("activitySCORMPackage", activityDropdown.getProperty("activitySCORMPackage"));
		this.properties.put("activitySurvey", activityDropdown.getProperty("activitySurvey"));
		this.properties.put("activityWiki", activityDropdown.getProperty("activityWiki"));
		this.properties.put("activityWorkshop", activityDropdown.getProperty("activityWorkshop"));
		this.properties.put("activityGo", activityDropdown.getProperty("activityGo"));
		this.properties.put("activityAssignment", activityDropdown.getProperty("activityAssignment"));
	}
/*
 * ASSIGNMENTS, these subtypes are required for versions prior to 2.3	
 */
/**
 * Selects the Advanced Uploading of Files Activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectAssignmentAdvancedUpload(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityAdvancedUpload"));
	}
/**
 * Selects the Online Text activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectAssignmentOnlineText(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityOnlineText"));
	}
/**
 * Selects the Upload Single File activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectAssignmentUploadSingleFile(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityUploadSingleFile"));
	}
/**
 * Selects the Offline Activity, activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectAssignmentOfflineActivity(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityOfflineActivity"));
	}
/*
 * ASSIGNMENTS 2.3 onwards
 */
/**
 * Selects teh single 2.3 assignment
 * @param outlineSection the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectAssignment(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityAssignment"), ".//*[@id='" +
						outlineSection +
						"']/div/noscript/div/input", 0);
	}
/**
 * Selects the Chat activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectChat(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityChat"));
	}
/**
 * Selects the Choice activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectChoice(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityChoice"));
	}
/**
 * Selects the Database activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectDatabase(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityDatabase"));
	}
/**
 * Selects the Exernal Tool activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectExternalTool(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityExternalTool"));
	}
/**
 * Selects the 'add forum activity option'. 
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectForum(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityForum"));
	}
/**
 * Selects the Glossary activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectGlossary(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityGlossary"));
	}
/**
 * Selects the Lesson activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectLesson(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityLesson"));
	}
/**
 * Selects the Quiz activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectQuiz(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityQuiz"));
	}
/**
 * Selects the SCORM package activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectSCORMPackage(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activitySCORMPackage"));
	}
/**
 * Selects the Survey activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectSurvey(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activitySurvey"));
	}
/**
 * Selects the Wiki activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectWiki(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityWiki"));
	}
/**
 * Selects the Workshop activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectWorkshop(String outlineSection) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityWorkshop"));
	}
}