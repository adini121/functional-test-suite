package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.moodle.seleniumutils.FormActions;
/**
 * This is the page object model for the add forum form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ForumAdd {
	//Internationalization file location
	public static String forumAddData = "properties/data/static/forumAdd.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public ForumAdd(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadTestData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadTestData() {
		Properties addForum = new Properties();
		try {
			addForum.load(new FileInputStream(forumAddData));
		} catch (Exception e) {}
		this.properties.put("forumTypeSingleSimple", addForum.getProperty("forumTypeSingleSimple"));
		this.properties.put("forumTypeEachPersonOneDiscussion", addForum.getProperty("forumTypeEachPersonOneDiscussion"));	
		this.properties.put("forumTypeQA", addForum.getProperty("forumTypeQA"));	
		this.properties.put("forumTypeStandardBlog", addForum.getProperty("forumTypeStandardBlog"));	
		this.properties.put("forumTypeStandardGeneral", addForum.getProperty("forumTypeStandardGeneral"));	
		this.properties.put("subscriptionTypeSelectionOptional", addForum.getProperty("subscriptionTypeSelectionOptional"));
		this.properties.put("subscriptionTypeSelectionForced", addForum.getProperty("subscriptionTypeSelectionForced"));
		this.properties.put("subscriptionTypeSelectionAuto", addForum.getProperty("subscriptionTypeSelectionAuto"));
		this.properties.put("subscriptionTypeSelectionDisabled", addForum.getProperty("subscriptionTypeSelectionDisabled"));
		this.properties.put("noRatings", addForum.getProperty("noRatings"));
		this.properties.put("aveRatings", addForum.getProperty("aveRatings"));
		this.properties.put("countRatings", addForum.getProperty("countRatings"));
		this.properties.put("maxRatings", addForum.getProperty("maxRatings"));
		this.properties.put("minRatings", addForum.getProperty("minRatings"));
		this.properties.put("sumRatings", addForum.getProperty("sumRatings"));
	}
/**
 * 	Enter a value in the forum name field.
 * @param name The value to be entered passed from the test.
 */
	public void enterForumName(String name) {
		WebElement forumName = driver.findElement(By .id("id_name"));
		forumName.sendKeys(name);
	}
/**
 * Enter a value in the Forum introduction field.
 * @param text The value to be entered passed from the test.
 */
	public void enterForumIntro(String text) {
		WebElement frame = driver.findElement(By .id("id_introeditor_ifr"));
		driver.switchTo().frame(frame);
		WebElement messagebox = driver.findElement(By.id("tinymce"));
		messagebox.click();
		messagebox.sendKeys(text);
		driver.switchTo().window(driver.getWindowHandle());
	}
/**
 * 	Select optional subscription type option.
 */
	public void selectSubscriptionTypeOptional() {
		Select subscriptionOption = new Select(driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionOptional"));
	}
/**
 * Select forced subscription type option.
 */
	public void selectSubscriptionTypeForced() {
		Select subscriptionOption = new Select(driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionForced"));
	}
/**
 * Select Auto subscription type option.
 */
	public void selectSubscriptionTypeAuto() {
		Select subscriptionOption = new Select(driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionAuto"));
	}
/**
 * Select disabled subscription type option.
 */
	public void selectSubscriptionTypeDisabled() {
		Select subscriptionOption = new Select(driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionDisabled"));
	}
/**
 * Select a value for forum type for single simple discussion.
 */
	public void selectForumTypeSimple() {
		Select forumType = new Select(driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeSingleSimple"));
	}
/**
 * Select a value for forum type for each person posts one discussion.
 */
	public void selectForumTypeEachPerson() {
		Select forumType = new Select(driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeEachPersonOneDiscussion"));
	}
/**
 * Select a value for forum type for QA type forum.
 */
	public void selectForumTypeQA() {
		Select forumType = new Select(driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeQA"));
	}
/**
 * Select a value for forum type for standard forum in a blog format.
 */
	public void selectForumTypeStandardBlog() {
		Select forumType = new Select(driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeStandardBlog"));
	}
/**
 * Select a value for forum type for standard forum for general use
 */
	public void selectForumTypeStandardGeneral() {
		Select forumType = new Select(driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeStandardGeneral"));
	}	
/**
 * Select Save and return to course.
 */
	public void clickSaveAndRetToCourse() {
		WebElement save = driver.findElement(By .xpath(".//*[@value='Save and return to course']"));
		save.click();
	}
/**
 * Selects an option from the Read Tracking dropdown.
 * @param trackingOption
 */
	public void selectReadTrackingOption(String trackingOption) {
		Select readTrackingOption = new Select(driver.findElement(By .id("id_trackingtype")));
		readTrackingOption.selectByVisibleText(trackingOption);	
	}
/**
 * Selects No Ratings from the aggregate type dropdown.
 */
	public void selectAggregateTypeNoRatings() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed",this.properties.get("noRatings"));
	}
/**
 * Selects Average of ratings from the aggregate type dropdown.
 */
	public void selectAggregateTypeAveRatings() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed",this.properties.get("aveRatings"));
	}
/**
 * Selects Count of ratings from the aggregate type dropdown.
 */
	public void selectAggregateTypeCountRatings() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed",this.properties.get("countRatings"));
		
	}
/**
 * Selects Maximum rating from the aggregate type dropdown.
 */
	public void selectAggregateTypeMaxRatings() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed",this.properties.get("maxRatings"));
		
	}
/**
 * Selects Minimum rating from the aggregate type dropdown.
 */
	public void selectAggregateTypeMinRatings() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed",this.properties.get("minRatings"));	
	}
/**
 * Selects Sum of Ratings from the aggregate type dropdown. 
 */
	public void selectAggregateTypeSumRatings() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_assessed",this.properties.get("sumRatings"));		
	}
/**
 * Selects a value for rating from the rating dropdown.
 * @param scale The value for rating is passed from the test. 
 */
	public void selectScale(String scale) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("id_scale", scale);
	}
}