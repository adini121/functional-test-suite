package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The page object model for Assignment Submission Comments.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class AssignmentSubmissionComments {
	//Internationalization file location
	public static String data = "properties/data/static/assignmentSubmissionComments.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public AssignmentSubmissionComments(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/assignment.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(data));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("linkComments", dataLoad.getProperty("linkComments"));
		this.properties.put("linkSaveComment", dataLoad.getProperty("linkSaveComment"));
		this.properties.put("linkCancelComment", dataLoad.getProperty("linkCancelComment"));
		this.properties.put("linkYes", dataLoad.getProperty("linkYes"));
	}
/**
 * Clicks the comments link to display submission comments.
 */
	public void clickLinkSubmissionComments() {
		WebElement link = driver.findElement(By .className("comment-link"));
		link.click();
	}
/**
 * Enters text in the submission comments text box.
 * @param desiredComment The text that you want to enter in the comments box. Pass from the test.
 */
	public void enterTextSubmissionComments(String desiredComment) {
		@SuppressWarnings("unused")
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				  .until(new ExpectedCondition<WebElement>(){
					@Override
					public WebElement apply(WebDriver d) {
						return d.findElement(By.xpath("//div[@class='comment-area']/div/textarea"));
					}});
		WebElement textArea = driver.findElement(By .xpath("//div[@class='comment-area']/div/textarea"));
		textArea.sendKeys(desiredComment);
	}
/**
 * Clicks the link to save a submission comment.
 */
	public void clickLinkSaveComment() {
		WebElement link = driver.findElement(By .linkText(this.properties.get("linkSaveComment")));
		link.click();
	}
/**
 * Clicks the link to cancel a submission comment.
 */
	public void clickLinkCancelComment() {
		WebElement link = driver.findElement(By .linkText(this.properties.get("linkCancelComment")));
		link.click();
	}
/**
 * Deletes a comment and confirms the deletion.
 * @param commentText the text of the comment.
 */
	public void clickLinkDeleteSubmissionCommentAndConfirm(String commentText) {
		WebElement link = driver.findElementByXPath("//div[contains(.," + commentText + ")]/div/a[@title='Delete this comment']");
		link.click();
		WebElement yesLink = driver.findElementByLinkText(this.properties.get("linkYes"));
		yesLink.click();
	}
}