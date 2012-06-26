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

import com.moodle.seleniumutils.FormActions;
import com.moodle.seleniumutils.PassFailCriteria;
/**
 * This is the page object model for the Posting to a forum.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ForumPosts {
	//Internationalization file location
	private Map<String, String> properties = new HashMap<String, String>();
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public ForumPosts(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData("properties/data/static/forumPosts.properties");
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/forumPosts.properties"));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("replyLink", dataLoad.getProperty("replyLink"));
		this.properties.put("splitLink", dataLoad.getProperty("splitLink"));
		this.properties.put("editLink", dataLoad.getProperty("editLink"));
		this.properties.put("deleteLink", dataLoad.getProperty("deleteLink"));
		this.properties.put("continueButton", dataLoad.getProperty("continueButton"));
		this.properties.put("saveChangesButton", dataLoad.getProperty("saveChangesButton"));
		this.properties.put("moveButton", dataLoad.getProperty("moveButton"));
		this.properties.put("flatOldestFirst", dataLoad.getProperty("flatOldestFirst"));
		this.properties.put("flatNewestFirst", dataLoad.getProperty("flatNewestFirst"));
		this.properties.put("threaded", dataLoad.getProperty("threaded"));
		this.properties.put("nested", dataLoad.getProperty("nested"));
		this.properties.put("subjectHidden", dataLoad.getProperty("subjectHidden"));
		this.properties.put("messageHidden", dataLoad.getProperty("messageHidden"));
		this.properties.put("deleteLink", dataLoad.getProperty("deleteLink"));
		this.properties.put("editTeacherException", dataLoad.getProperty("editTeacherException"));
	}
/**
 * Enter a value for subject
 * @param subject The value for subject is passed fromthe test.
 */
	public void enterSubjectField(String subject) {
		WebElement subjectField = driver.findElement(By .xpath(".//input[@id='id_subject']"));
		subjectField.clear();
		subjectField.sendKeys(subject);
	}
/**
 * Enter reply message.
 * @param message The message text is passed from the test.
 */
	public void enterMessage(String message) {
		FormActions richText = new FormActions(driver);
		richText.enterValueInTinyMCE(message);		
	}
/**
 * Click post to forum button.
 */
	public void clickPostToForum(){
		WebElement postButton = driver.findElement(By .id("id_submitbutton"));
		postButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
/**
 * Clicks a discussion link in a forum passed as a variable from the test
 * @param subject The subject of the discussion forms teh text of the link to the discussion. The link text is passed from the test.
 */
	public void clickDiscussionLink(String subject) {
		WebElement discussionLink = driver.findElement(By .xpath(".//a[contains(.,'" +
				subject +
				"')]"));
		discussionLink.click();		
	}
/**
 * Reply to a specified post
 * @param postText The reply link is located using the link text for "reply" within the context of the text that that is included in the post.
 * The parameter is all or part of the text that makes up the post but needs to be unique on the page.
 */
	public void clickReplyToPostLink(String postText) {
		WebElement reply = driver.findElement(By .xpath("//div[contains(.,'" +
				postText +
				"')]/*/*/*/a[contains(.,'" +
				this.properties.get("replyLink") +
				"')]"));
		reply.click();
	}
/**
 * Clicks the split link
 * @param postText The split link is located using the link text for "reply" within the context of the text that that is included in the post.
 * The parameter is all or part of the text that makes up the post but needs to be unique on the page.
 */
	public void clickSplitLink(String postText) {
		WebElement reply = driver.findElement(By .xpath("//div[contains(.,'" +
				postText +
				"')]/*/*/*/a[contains(.,'" +
				this.properties.get("splitLink") +
				"')]"));
		reply.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
/**
 * Selects a value from the Move dropdown passed via a variable in the test.
 * @param forumName The name of the forum i.e. the text that makes up the Forum Name in the UI.
 */
	public void selectValueFromMoveDropdown(String forumName) {
		Select forumType = new Select(driver.findElement(By .xpath(".//select[contains(.,'" +
				forumName +
				"')]")));
		forumType.selectByVisibleText(forumName);		
	}
/**
 * Clicks the move button.
 */
	public void clickMoveButton() {
		WebElement move = driver.findElement(By .xpath(".//input[@value='" +
				this.properties.get("moveButton") +
				"']"));
		move.click();		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
/**
 * Selects Display replies flat, with oldest first from the display dropdown.
 */
	public void selectDisplayOptionDropdownFlatOldest() {
		Select forumType = new Select(driver.findElement(By .xpath(".//select[contains(.,'" +
				this.properties.get("flatOldestFirst") +
				"')]")));
		forumType.selectByVisibleText(this.properties.get("flatOldestFirst"));
	}
/**
 * Selects Display replies flat, with newest first from the display dropdown.
 */
	public void selectDisplayOptionDropdownFlatNewest() {
		Select forumType = new Select(driver.findElement(By .xpath(".//select[contains(.,'" +
				this.properties.get("flatNewestFirst") +
				"')]")));
		forumType.selectByVisibleText(this.properties.get("flatNewestFirst"));
	}
/**
 * Selects Display replies in threaded form from the display dropdown.
 */
	public void selectDisplayOptionDropdownThreaded() {
		Select forumType = new Select(driver.findElement(By .xpath(".//select[contains(.,'" +
				this.properties.get("threaded") +
				"')]")));
		forumType.selectByVisibleText(this.properties.get("threaded"));
	}
/**
 * Selects Display replies in nested form option from the display dropdown.
 */
	public void selectDisplayOptionNested() {
		Select forumType = new Select(driver.findElement(By .xpath(".//select[contains(.,'" +
				this.properties.get("nested") +
				"')]")));
		forumType.selectByVisibleText(this.properties.get("nested"));
	}
/**
 * Asserts that the Flat with oldest first dropdown option is selected.
 */
	public void assertFlatOldestOptionSelected() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//option[contains(.,'" +
				this.properties.get("flatOldestFirst") +
				"')]", "Flat with oldest option should be selected", this.properties.get("flatOldestFirst"));	
	}
/**
 * Asserts that the Flat with newest first dropdown option is selected.
 */
	public void assertFlatNewestOptionSelected() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//option[contains(.,'" +
				this.properties.get("flatNewestFirst") +
				"')]", "Flat with newest option should be selected", this.properties.get("flatNewestFirst"));	
	}
/**
 * Asserts that the Threaded dropdown option is selected.
 */
	public void assertThreadedOptionSelected() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//option[contains(.,'" +
				this.properties.get("threaded") +
				"')]", "Threaded option should be selected", this.properties.get("threaded"));	
	}
/**
 * Asserts that the threaded link exists.
 * @param linkText The link text of the value that is to be asserted.
 */
	public void assertThreadedLink(String linkText) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//a[contains(.,'" +
				linkText +
				"')]", "Thread link should exist", linkText);	
	}
/**
 * Asserts that the Threaded dropdown option is selected.
 */
	public void assertNestedOptionSelected() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//option[contains(.,'" +
				this.properties.get("nested") +
				"')]", "Nested option should be selected", this.properties.get("nested"));	
	}
/**
 * Asserts that a a forum post or reply has been successful by confirming that the subject text appears onscreen
 * WILL NOT WORK WITH THREADED VIEW USE assertThreadedLink method instead.
 * @param postSubject The text value for the subject of the post.
 */
	public void assertForumPostSubjectSuccessful(String postSubject) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//div[contains(.,'" +
				postSubject +
				"')]/*/div[@class='subject' ]", "Subject should be present", postSubject);			
	}
/**
 * Asserts that a a forum post or reply has been successful by confirming that the message text appears onscreen
 * WILL NOT WORK WITH THREADED VIEW USE assertThreadedLink method instead.
 * @param postMessage The text vallue for the message.
 */
	public void assertForumPostMessageSuccessful(String postMessage) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//div[@class='posting fullpost']/p[contains(.,'" +
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
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsNotPresentByXpath("//div[contains(.,'" +
				postText +
				"')]/*/*/*/a[contains(.,'" +
				this.properties.get("replyLink") +
				"')]", "Reply link in" +
						postText +
						"is enabled and should be disabled", 0);
	}
/**
 * Deletes all forum posts(discussions) within any forum and returns to the Moodle home page when it is done.
 * Manage timeouts has to be set to 0 otherwise the catching the thrown exception slows the test down. Rest the driver timeout when
 * the loop is complete.
 */
	public void deleteAllForumPosts() {
		boolean itemVisible = true;
			try{
				//Set driver time out to 0. This prevents the caught exception from slowing down the test.
				//TODO find out how to reset the timeout back to it's original value.
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				while (itemVisible){
					WebElement firstDiscussion = driver.findElement(By .xpath(".//tr[@class='discussion r0']/td[@class='topic starter']/a"));
					itemVisible = firstDiscussion.isDisplayed();
					firstDiscussion.click();
					WebElement deleteLink = driver.findElement(By .xpath("//a[contains(.,'" +
							this.properties.get("deleteLink") +
							"')]"));
					deleteLink.click();
					WebElement continueButton = driver.findElement(By .xpath("//input[@value='" +
							this.properties.get("continueButton") +
							"']"));
					continueButton.click();
				}
				//Temporary solution to reset the driver timeout to 5 seconds.
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			catch (NoSuchElementException ex){
				BlockNavigation navigate = new BlockNavigation(driver);
				navigate.clickHome();
			}
	}
/**
 * Asserts that a subject or message in a discussion does not appear.
 * @param postText The post text that should not appear is passed from the test.
 * @throws Exception passes silently but throws an exception if the message appears onscreen.
 */
	public void assertSubjectOrMessageNotPresent(String postText) throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsNotPresentByXpath("//div[contains(.,'" +
				postText +
				"')]", "Text in" +
						postText +
						"is viewable and should not be.", 0);
	}
/**
 * Asserts that the subject hidden message does appears on the page.
 */
	public void assertSubjectHidden() {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath(".//div[@class='topic']/div[contains(.,'" +
				this.properties.get("subjectHidden") +
				"')]", this.properties.get("subjectHidden") + 
				"should appear onscreen", this.properties.get("subjectHidden"));	
	}
/**
 * Clicks the edit link.
 * @param postText The text of the forum post.
 */
	public void clickEditLink(String postText) {
		WebElement reply = driver.findElement(By .xpath("//div[contains(.,'" +
				postText +
				"')]/*/*/*/a[contains(.,'" +
				this.properties.get("editLink") +
				"')]"));
		reply.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
/**
 * Clicks the save changes button on editing a post.
 */
	public void clickSaveChanges() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement saveChangesButton = driver.findElement(By .xpath("//input[@value='" +
				this.properties.get("saveChangesButton") +
				"']"));
		saveChangesButton.click();
	}
/**
 * Clicks the delete link.
 * @param postText Unique block of text from the post to the character limit for xpath. this value is passed from the test.
 */
	public void clickDeleteLink(String postText) {
		WebElement reply = driver.findElement(By .xpath("//div[contains(.,'" +
				postText +
				"')]/*/*/*/a[contains(.,'" +
				this.properties.get("deleteLink") +
				"')]"));
		reply.click();
	}
/**
 * Clicks any continue button.
 */
	public void clickContinue() {
		WebElement continueButton = driver.findElement(By .xpath(".//input[@value='" +
				this.properties.get("continueButton") +
				"']"));
		continueButton.click();		
	}
/**
 * Throws an exception if the automatically entered "edited" comments do not appear onscreen.
 * @throws Exception Exception message that is thrown when the test fails.
 */
	public void assertTeacherEdit() throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertElementIsPresentByXpath(".//span[@class='edited']", "" +
				this.properties.get("editTeacherException") +
				"", 5);
	}
}