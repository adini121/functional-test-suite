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

import com.moodle.test.FormActions;
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
 * Selects the single 2.3 assignment
 * @param outlineSection the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectAssignment(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXPathHandlesJS(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]", this.properties.get("activityAssignment"), ".//*[@id='" +
				outlineSection +
				"']/div/noscript/div/input", 0);
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_assign");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Chat activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectChat(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityChat"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_chat");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Choice activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectChoice(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityChoice"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_choice");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Database activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectDatabase(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityDatabase"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_data");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Exernal Tool activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectExternalTool(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityExternalTool"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_lti");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the 'add forum activity option'. 
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectForum(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityForum"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_forum");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Glossary activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectGlossary(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityGlossary"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_glossary");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Lesson activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectLesson(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityLesson"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_lesson");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Quiz activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectQuiz(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityQuiz"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_quiz");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the SCORM package activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectSCORMPackage(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activitySCORMPackage"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_scorm");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Survey activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectSurvey(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activitySurvey"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_survey");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Wiki activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test. 
 */
	public void selectWiki(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityWiki"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_wiki");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Selects the Workshop activity.
 * @param outlineSection is the outline section in which you want to add the activity. Pass with value from the test.
 */
	public void selectWorkshop(String outlineSection) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Select activityDropdown = new Select(driver.findElement(By .xpath(".//*[@id='section-" +
				outlineSection +
				"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
				this.properties.get("activity") +
				"')]")));
			itemVisible = ((WebElement) activityDropdown).isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			FormActions dropdown = new FormActions(driver);
			dropdown.selectDropdownItemByXpath(".//*[@id='section-" +
					outlineSection +
					"']/*/div[@class='section_add_menus']/div/div/form/div/select[contains(.,'" +
					this.properties.get("activity") +
					"')]", this.properties.get("activityWorkshop"));
		}
		else {
			FormActions picker = new FormActions(driver);
			picker.addItemResourcePicker(outlineSection, "module_workshop");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}