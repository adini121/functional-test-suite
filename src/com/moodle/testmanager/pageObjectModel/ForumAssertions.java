package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This is the page object model for interacting with the News Block.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ForumAssertions extends PassFailAssertions{
	//Internationalization file location
	public static String forumData = "properties/data/static/forum.properties";
	public static String forumPostsData = "properties/data/static/forumPosts.properties";
	RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
	private String editedBy = "(Edited by ";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
public ForumAssertions(RemoteWebDriver driver) {
	super(driver);
	this.loadObjectData();
}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNews.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
public void loadObjectData() {
	Properties dataLoad = new Properties();
	try {
		dataLoad.load(new FileInputStream(forumData));
		dataLoad.load(new FileInputStream(forumPostsData));
	} catch (Exception e) {}
	//put values from the properties file into hashmap
	//Locators
	this.properties.put("optionalSubscription", dataLoad.getProperty("optionalSubscription"));
	this.properties.put("forcedSubscription", dataLoad.getProperty("forcedSubscription"));
	this.properties.put("autoSubscription", dataLoad.getProperty("autoSubscription"));
	this.properties.put("subscriptionDisabled", dataLoad.getProperty("subscriptionDisabled"));
	this.properties.put("subscribeLinkText", dataLoad.getProperty("subscribeLinkText"));
	this.properties.put("unsubscribeLinkText", dataLoad.getProperty("unsubscribeLinkText"));
	this.properties.put("addNewTopicButton", dataLoad.getProperty("addNewTopicButton"));
	this.properties.put("flatOldestFirst", dataLoad.getProperty("flatOldestFirst"));
	this.properties.put("flatNewestFirst", dataLoad.getProperty("flatNewestFirst"));
	this.properties.put("threaded", dataLoad.getProperty("threaded"));
	this.properties.put("nested", dataLoad.getProperty("nested"));
	this.properties.put("subjectHidden", dataLoad.getProperty("subjectHidden"));
	this.properties.put("messageHidden", dataLoad.getProperty("messageHidden"));
	this.properties.put("deleteLink", dataLoad.getProperty("deleteLink"));
	//Exceptions
	this.properties.put("editTeacherException", dataLoad.getProperty("editTeacherException"));
}
/**
 * Asserts that a discussion in present in a forum. 
 * @param textToBeAsserted The discussion title is the value asserted and this is passed via a variable in the test.
 */
public void assertDiscussionPresent(String textToBeAsserted) {
	assertTextPresentByXpath(".//a[contains(.,'" +
		textToBeAsserted +
		"')]", "Discussion should be displayed", textToBeAsserted);	
}
/**
 * 	Asserts that subscription to a forum is optional
 */
public void assertSubscriptionOptional() {
	assertTextPresentByXpath("//span[contains(.,'" +
		this.properties.get("optionalSubscription") +
		"')]", "Optional subscription should be displayed", this.properties.get("optionalSubscription"));
}
/**
 * Asserts that subscription to a forum is forced
 */
public void assertSubscriptionForced() {
	assertTextPresentByXpath("//span[contains(.,'" +
		this.properties.get("forcedSubscription") +
		"')]", "Forced subscription should be displayed", this.properties.get("forcedSubscription"));
}
/**
 * Asserts that subscription to a forum is automatic
 */
public void assertSubscriptionAuto() {
	assertTextPresentByXpath("//span[contains(.,'" +
		this.properties.get("autoSubscription") +
		"')]", "Auto subscription should be displayed", this.properties.get("autoSubscription"));		
}
/**
 * Asserts that subscription is disabled
 */
public void assertSubscriptionDisabled() {
	assertTextPresentByXpath("//span[contains(.,'" +
		this.properties.get("subscriptionDisabled") +
		"')]", "Discuss subscription disabled should be displayed", this.properties.get("subscriptionDisabled"));
}
/**
 * Asserts that the subscribe link is present
 */
public void assertSubscribeOptionPresent() {
	assertTextPresentByXpath("//a[contains(.,'" +
		this.properties.get("subscribeLinkText") +
		"')]", "Should be able to subscribe to this forum", this.properties.get("subscribeLinkText"));
}
/**
 * 	Asserts that the unsubscribe link is present
 */
public void assertUnsubscribeOptionPresent() {
	assertTextPresentByXpath("//a[contains(.,'" +
		this.properties.get("unsubscribeLinkText") +
		"')]", "Should be able to unsubscribe from this forum", this.properties.get("unsubscribeLinkText"));		
}
/**
 * Asserts that the Add New Discussion button is not visible
 * @throws Exception Passes silently if the button is disabled and throws an exception if it is enabled.
 */
public void assertAddNewTopicButtonDisabled() throws Exception {
	assertItemNotOnscreenByCSSSelector("input[value='" +
		this.properties.get("addNewTopicButton") +
		"']", "Add new topic is enabled and should be disabled", 0);
}
/**
 * Asserts that the Flat with oldest first dropdown option is selected.
 */
public void assertFlatOldestOptionSelected() {
	assertTextPresentByXpath(".//option[contains(.,'" +
		this.properties.get("flatOldestFirst") +
		"')]", "Flat with oldest option should be selected", this.properties.get("flatOldestFirst"));	
}
/**
 * Asserts that the Flat with newest first dropdown option is selected.
 */
public void assertFlatNewestOptionSelected() {
	assertTextPresentByXpath(".//option[contains(.,'" +
		this.properties.get("flatNewestFirst") +
		"')]", "Flat with newest option should be selected", this.properties.get("flatNewestFirst"));	
}
/**
 * Asserts that the Threaded dropdown option is selected.
 */
public void assertThreadedOptionSelected() {
	assertTextPresentByXpath(".//option[contains(.,'" +
		this.properties.get("threaded") +
		"')]", "Threaded option should be selected", this.properties.get("threaded"));	
}
/**
 * Asserts that the threaded link exists.
 * @param linkText The link text of the value that is to be asserted.
 */
public void assertThreadedLink(String linkText) {
	assertTextPresentByXpath(".//a[contains(.,'" +
		linkText +
		"')]", "Thread link should exist", linkText);	
}
/**
 * Asserts that the Threaded dropdown option is selected.
 */
public void assertNestedOptionSelected() {
	assertTextPresentByXpath(".//option[contains(.,'" +
		this.properties.get("nested") +
		"')]", "Nested option should be selected", this.properties.get("nested"));	
}
/**
 * Asserts that a a forum post or reply has been successful by confirming that the subject text appears onscreen
 * WILL NOT WORK WITH THREADED VIEW USE assertThreadedLink method instead.
 * @param postSubject The text value for the subject of the post.
 */
public void assertForumPostSubjectSuccessful(String postSubject) {
	assertTextPresentByXpath(".//div[contains(.,'" +
		postSubject +
		"')]/*/div[@class='subject' ]", "Subject should be present", postSubject);			
}
/**
 * Asserts that a a forum post or reply has been successful by confirming that the message text appears onscreen
 * WILL NOT WORK WITH THREADED VIEW USE assertThreadedLink method instead.
 * @param postMessage The text value for the message.
 */
public void assertForumPostMessageSuccessful(String postMessage) {
	assertTextPresentByXpath(".//div[@class='posting fullpost'][contains(.,'" +
		postMessage +
		"')]", "Message should be present", postMessage);
}
/**
 * Asserts that the reply link is not present in a discussion.
 * @param postText The reply link is located using the link text for "reply" within the context of the text that that is included in the post.
 * The parameter is all or part of the text that makes up the post but needs to be unique on the page.
 * @throws Exception passes silently if the link is not present and throws an exception if it is.
 */
public void assertReplyLinkNotPresent(String postText) throws Exception {
	assertElementIsNotPresentByXpath("//div[contains(.,'" +
		postText +
		"')]/*/*/*/a[contains(.,'" +
		this.properties.get("replyLink") +
		"')]", "Reply link in" +
				postText +
				"is enabled and should be disabled", 0);
}
/**
 * Asserts that a subject or message in a discussion does not appear.
 * @param postText The post text that should not appear is passed from the test.
 * @throws Exception passes silently but throws an exception if the message appears onscreen.
 */
public void assertSubjectOrMessageNotPresent(String postText) throws Exception {
	assertElementIsNotPresentByXpath("//div[contains(.,'" +
		postText +
		"')]", "Text in" +
				postText +
				"is viewable and should not be.", 0);
}
/**
 * Asserts that the subject hidden message does appears on the page.
 */
public void assertSubjectHidden() {
	assertTextPresentByXpath(".//div[@class='topic']/div[contains(.,'" +
		this.properties.get("subjectHidden") +
		"')]", this.properties.get("subjectHidden") + 
		"should appear onscreen", this.properties.get("subjectHidden"));	
}
/**
 * Throws an exception if the automatically entered "edited" comments do not appear onscreen.
 * @param editedBy 
 * @param teacherName 
 * @throws Exception Exception message that is thrown when the test fails.
 */
public void assertTeacherEdit(String teacherName) throws Exception {
	assertElementIsPresentByXpath(".//div[@class='posting fullpost'][contains(.,'" + editedBy + teacherName + "')]", this.properties.get("editTeacherException"), 5);
}
}