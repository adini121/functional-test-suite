package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.PassFailCriteria;
/**
 * This is the page object model for High level forum actions.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Forum {
	//Internationalization file location
	public static String forumData = "properties/data/static/forum.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public Forum(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties forum = new Properties();
		try {
			forum.load(new FileInputStream(forumData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("addNewDiscussionButtonCSSValue", forum.getProperty("addNewDiscussionButtonCSSValue"));
		this.properties.put("optionalSubscription", forum.getProperty("optionalSubscription"));
		this.properties.put("forcedSubscription", forum.getProperty("forcedSubscription"));
		this.properties.put("autoSubscription", forum.getProperty("autoSubscription"));
		this.properties.put("subscriptionDisabled", forum.getProperty("subscriptionDisabled"));
		this.properties.put("subscribeLinkText", forum.getProperty("subscribeLinkText"));
		this.properties.put("unsubscribeLinkText", forum.getProperty("unsubscribeLinkText"));
		this.properties.put("addNewTopicButton", forum.getProperty("addNewTopicButton"));
		this.properties.put("addNewQuestionButton", forum.getProperty("addNewQuestionButton"));
	}
/**
 * Click on a forum link using the forum name passed from the test case.
 * @param forumName The Link Text associated with the forum to be selected.
 */
	public void clickForumLink(String forumName) {
		WebElement forumLink = driver.findElement(By .xpath("//a[contains(.,'" +
				forumName +
				"')]"));
		forumLink.click();		
	}
/**
 * Subscribe to the forum
 */
	//TODO internationalization
	public void subscribe() {
		WebElement subscribe = driver.findElement(By .xpath("//a[contains(.,'Subscribe to this forum')]"));
		subscribe.click();
	}
/**
 * Unsubscribe from the forum
 */
	//TODO internationalization
	public void unsubscribe() {
		WebElement unsubscribe = driver.findElement(By .xpath("//a[contains(.,'Unsubscribe from this forum')]"));
		unsubscribe.click();
	}
/**
 * Clicks the add new discussion topic button
 */
	public void clickAddNewDiscussionTopicButton() {
		WebElement addDiscussion = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("addNewDiscussionButtonCSSValue") +
				"']"));
		addDiscussion.click();
	}
/**
 * Clicks the add new topic button in the news forum.
 */
	public void clickAddNewTopicButton() {
		WebElement addDiscussion = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("addNewTopicButton") +
				"']"));
		addDiscussion.click();
	}
/**
 * Asserts that a discussion in present in a forum. 
 * @param textToBeAsserted The discussion title is the value asserted and this is passed via a variable in the test.
 */
	public void assertDiscussionPresent(String textToBeAsserted) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//a[contains(.,'" +
				textToBeAsserted +
				"')]", "Discussion should be displayed", textToBeAsserted);	
	}
/**
 * 	Asserts that subscription to a forum is optional
 */
	public void assertSubscriptionOptional() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//span[contains(.,'" +
				this.properties.get("optionalSubscription") +
				"')]", "Optional subscription should be displayed", this.properties.get("optionalSubscription"));
	}
/**
 * Asserts that subscription to a forum is forced
 */
	public void assertSubscriptionForced() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//span[contains(.,'" +
				this.properties.get("forcedSubscription") +
				"')]", "Forced subscription should be displayed", this.properties.get("forcedSubscription"));
	}
/**
 * Asserts that subscription to a forum is automatic
 */
	public void assertSubscriptionAuto() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//span[contains(.,'" +
				this.properties.get("autoSubscription") +
				"')]", "Auto subscription should be displayed", this.properties.get("autoSubscription"));		
	}
/**
 * Asserts that subscription is disabled
 */
	public void assertSubscriptionDisabled() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//span[contains(.,'" +
				this.properties.get("subscriptionDisabled") +
				"')]", "Discuss subscription disabled should be displayed", this.properties.get("subscriptionDisabled"));
	}
/**
 * Asserts that the subscribe link is present
 */
	public void assertSubscribeOptionPresent() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//a[contains(.,'" +
				this.properties.get("subscribeLinkText") +
				"')]", "Should be able to subscribe to this forum", this.properties.get("subscribeLinkText"));
	}
/**
 * 	Asserts that the unsubscribe link is present
 */
	public void assertUnsubscribeOptionPresent() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//a[contains(.,'" +
				this.properties.get("unsubscribeLinkText") +
				"')]", "Should be able to unsubscribe from this forum", this.properties.get("unsubscribeLinkText"));		
	}
/**
 * Asserts that the Add New Discussion button is not visible
 * @throws Exception Passes silently if the button is disabled and throws an exception if it is enabled.
 */
	public void assertAddNewTopicButtonDisabled() throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertItemNotOnscreenByCSSSelector("input[value='" +
				this.properties.get("addNewTopicButton") +
				"']", "Add new topic is enabled and should be disabled", 0);
	}
/**
 * Clicks the Add a new question button
 */
	public void clickAddNewQuestionButton() {
		WebElement addQuestion = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("addNewQuestionButton") +
				"']"));
		addQuestion.click();	
	}
}