package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the add forum form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ForumAddForm extends FormAddEditSettings {
	protected Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 */
	public ForumAddForm(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData("properties/data/static/forumAdd.properties");
	}
/**
 * Loads data for the page object from the internationalization layer
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/forumAdd.properties"));
		} catch (Exception e) {}
		this.properties.put("forumTypeSingleSimple", dataLoad.getProperty("forumTypeSingleSimple"));
		this.properties.put("forumTypeEachPersonOneDiscussion", dataLoad.getProperty("forumTypeEachPersonOneDiscussion"));	
		this.properties.put("forumTypeQA", dataLoad.getProperty("forumTypeQA"));	
		this.properties.put("forumTypeStandardBlog", dataLoad.getProperty("forumTypeStandardBlog"));	
		this.properties.put("forumTypeStandardGeneral", dataLoad.getProperty("forumTypeStandardGeneral"));	
		this.properties.put("subscriptionTypeSelectionOptional", dataLoad.getProperty("subscriptionTypeSelectionOptional"));
		this.properties.put("subscriptionTypeSelectionForced", dataLoad.getProperty("subscriptionTypeSelectionForced"));
		this.properties.put("subscriptionTypeSelectionAuto", dataLoad.getProperty("subscriptionTypeSelectionAuto"));
		this.properties.put("subscriptionTypeSelectionDisabled", dataLoad.getProperty("subscriptionTypeSelectionDisabled"));
		this.properties.put("noRatings", dataLoad.getProperty("noRatings"));
		this.properties.put("aveRatings", dataLoad.getProperty("aveRatings"));
		this.properties.put("countRatings", dataLoad.getProperty("countRatings"));
		this.properties.put("maxRatings", dataLoad.getProperty("maxRatings"));
		this.properties.put("minRatings", dataLoad.getProperty("minRatings"));
		this.properties.put("sumRatings", dataLoad.getProperty("sumRatings"));
	}
/**
 * 	Select optional subscription type option.
 */
	public void selectSubscriptionTypeOptional() {
		Select subscriptionOption = new Select(this.driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionOptional"));
	}
/**
 * Select forced subscription type option.
 */
	public void selectSubscriptionTypeForced() {
		Select subscriptionOption = new Select(this.driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionForced"));
	}
/**
 * Select Auto subscription type option.
 */
	public void selectSubscriptionTypeAuto() {
		Select subscriptionOption = new Select(this.driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionAuto"));
	}
/**
 * Select disabled subscription type option.
 */
	public void selectSubscriptionTypeDisabled() {
		Select subscriptionOption = new Select(this.driver.findElement(By .id("id_forcesubscribe")));
		subscriptionOption.selectByVisibleText(this.properties.get("subscriptionTypeSelectionDisabled"));
	}
/**
 * Select a value for forum type for single simple discussion.
 */
	public void selectForumTypeSimple() {
		Select forumType = new Select(this.driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeSingleSimple"));
	}
/**
 * Select a value for forum type for each person posts one discussion.
 */
	public void selectForumTypeEachPerson() {
		Select forumType = new Select(this.driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeEachPersonOneDiscussion"));
	}
/**
 * Select a value for forum type for QA type forum.
 */
	public void selectForumTypeQA() {
		Select forumType = new Select(this.driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeQA"));
	}
/**
 * Select a value for forum type for standard forum in a blog format.
 */
	public void selectForumTypeStandardBlog() {
		Select forumType = new Select(this.driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeStandardBlog"));
	}
/**
 * Select a value for forum type for standard forum for general use
 */
	public void selectForumTypeStandardGeneral() {
		Select forumType = new Select(this.driver.findElement(By .id("id_type")));
		forumType.selectByVisibleText(this.properties.get("forumTypeStandardGeneral"));
	}	
/**
 * Selects an option from the Read Tracking dropdown.
 * @param trackingOption
 */
	public void selectReadTrackingOption(String trackingOption) {
		Select readTrackingOption = new Select(this.driver.findElement(By .id("id_trackingtype")));
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