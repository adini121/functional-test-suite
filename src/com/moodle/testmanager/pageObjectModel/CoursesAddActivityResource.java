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
import org.openqa.selenium.support.ui.Select;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the adding activities to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class CoursesAddActivityResource {
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
	public CoursesAddActivityResource(RemoteWebDriver driver) {
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
		this.properties.put("resource", activityDropdown.getProperty("resource"));
		this.properties.put("resourceBook", activityDropdown.getProperty("resourceBook"));
		this.properties.put("resourceFile", activityDropdown.getProperty("resourceFile"));
		this.properties.put("resourceFolder", activityDropdown.getProperty("resourceFolder"));
		this.properties.put("resourceIMS", activityDropdown.getProperty("resourceIMS"));
		this.properties.put("resourceLabel", activityDropdown.getProperty("resourceLabel"));
		this.properties.put("resourcePage", activityDropdown.getProperty("resourcePage"));
		this.properties.put("resourceURL", activityDropdown.getProperty("resourceURL"));
	}
	/*
	 * locator variables
	 */	
	private String mapActivity = this.properties.get("activity");
	private String mapAssign = this.properties.get("activityAssignment");
	private String locAssignRadio="module_assign";
	private String mapChat = this.properties.get("activityChat");
	private String locChatRadio="module_chat";
	private String mapChoice = this.properties.get("activityChoice");
	private String locChoiceRadio = "module_choice";
	private String mapDatabase = this.properties.get("activityDatabase");
	private String locDatabaseRadio = "module_data";
	private String mapLTI = this.properties.get("activityExternalTool");
	private String locLTIRadio = "module_lti";
	private String mapForum = this.properties.get("activityForum");
	private String locForumRadio = "module_forum";
	private String mapGlossary = this.properties.get("activityGlossary");
	private String locGlossaryRadio = "module_glossary";
	private String mapLesson = this.properties.get("activityLesson");
	private String locLessonRadio = "module_lesson";
	private String mapQuiz = this.properties.get("activityQuiz");
	private String locQuizRadio = "module_quiz";
	private String mapSCORM = this.properties.get("activitySCORMPackage");
	private String locSCORMRadio = "module_scorm";
	private String mapSurvey = this.properties.get("activitySurvey");
	private String locSurveyRadio = "module_survey";
	private String mapWiki = this.properties.get("activityWiki");
	private String locWikiRadio = "module_wiki";
	private String mapWorkshop = this.properties.get("activityWorkshop");
	private String locWorkshopRadio = "module_workshop";
	private String mapResource = this.properties.get("resource");
	private String mapBook = this.properties.get("resourceBook");
	private String locBookRadio = "module_book";
	private String mapFile = this.properties.get("resourceFile");
	private String locFileRadio = "module_resource";
	private String mapFolder = this.properties.get("resourceFolder");
	private String locFolderRadio = "module_folder";
	private String mapIMS = this.properties.get("resourceIMS");
	private String locIMSRadio = "module_imscp" ;
	private String mapLabel = this.properties.get("resourceLabel");
	private String locLabelRadio = "module_label";
	private String mapPage = this.properties.get("resourcePage");
	private String locPageRadio = "module_page";
	private String mapURL = this.properties.get("resourceURL");
	private String locURLRadio = "module_url";
/*
 * Generic methods
 */
	private void selectActivityResource(String outlineSection, String activityOrResource, String activityOrResourceType, String radioID) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				activityOrResource +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXPathHandlesJS(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				activityOrResource +
				"')]", activityOrResourceType, ".//*[@id='" +
				outlineSection +
				"']/div/noscript/div/input", 0);
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, radioID);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
 * 2.3 onwards
 */
/**
 * Selects the single 2.3 assignment
 * @param outlineSection the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectAssignment(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapAssign, locAssignRadio);
	}
/**
 * Selects the Chat activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectChat(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapChat, locChatRadio);
	}
/**
 * Selects the Choice activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectChoice(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapChoice, locChoiceRadio);
	}
/**
 * Selects the Database activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectDatabase(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapDatabase, locDatabaseRadio);
	}
/**
 * Selects the Exernal Tool activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectExternalTool(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapLTI, locLTIRadio);
	}
/**
 * Selects the 'add forum activity option'. 
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectForum(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapForum, locForumRadio);
	}
/**
 * Selects the Glossary activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectGlossary(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapGlossary, locGlossaryRadio);
	}
/**
 * Selects the Lesson activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectLesson(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapLesson, locLessonRadio);
	}
/**
 * Selects the Quiz activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectQuiz(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapQuiz, locQuizRadio);
	}
/**
 * Selects the SCORM package activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectSCORMPackage(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapSCORM, locSCORMRadio);
	}
/**
 * Selects the Survey activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectSurvey(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapSurvey, locSurveyRadio);
	}
/**
 * Selects the Wiki activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectWiki(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapWiki, locWikiRadio);
	}
/**
 * Selects the Workshop activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectWorkshop(String outlineSection) {
		selectActivityResource(outlineSection, mapActivity, mapWorkshop, locWorkshopRadio);
	}
/**
 * Selects the Book resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectBook(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapBook, locBookRadio);
	}
/**
 * Selects the file resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectFile(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapFile, locFileRadio);
	}
/**
 * Selects the Folder resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectFolder(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapFolder, locFolderRadio);
	}
/**
 * Selects the IMS resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectIMS(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapIMS, locIMSRadio);
	}
/**
 * Selects the Label resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectLabel(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapLabel, locLabelRadio);
	}
/**
 * Selects the Page resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectPage(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapPage, locPageRadio);
	}
/**
 * Selects the URL resource.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectURL(String outlineSection){
		selectActivityResource(outlineSection, mapResource, mapURL, locURLRadio);
	}
}