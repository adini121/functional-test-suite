package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

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
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(forumData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("addNewDiscussionButtonCSSValue", dataLoad.getProperty("addNewDiscussionButtonCSSValue"));
		this.properties.put("optionalSubscription", dataLoad.getProperty("optionalSubscription"));
		this.properties.put("forcedSubscription", dataLoad.getProperty("forcedSubscription"));
		this.properties.put("autoSubscription", dataLoad.getProperty("autoSubscription"));
		this.properties.put("subscriptionDisabled", dataLoad.getProperty("subscriptionDisabled"));
		this.properties.put("subscribeLinkText", dataLoad.getProperty("subscribeLinkText"));
		this.properties.put("unsubscribeLinkText", dataLoad.getProperty("unsubscribeLinkText"));
		this.properties.put("addNewTopicButton", dataLoad.getProperty("addNewTopicButton"));
		this.properties.put("addNewQuestionButton", dataLoad.getProperty("addNewQuestionButton"));
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
 * Clicks the Add a new question button
 */
	public void clickAddNewQuestionButton() {
		WebElement addQuestion = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("addNewQuestionButton") +
				"']"));
		addQuestion.click();	
	}
}