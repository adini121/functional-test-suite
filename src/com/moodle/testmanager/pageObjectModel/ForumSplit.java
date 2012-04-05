package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for the Navigation Block. All interaction with the navigation block is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ForumSplit {
	//Internationalization file location
	public static String forumsplitData = "properties/data/static/forumSplit.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public ForumSplit(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties forumSplit = new Properties();
		try {
			forumSplit.load(new FileInputStream(forumsplitData));
		} catch (Exception e) {}
		this.properties.put("fieldLabel", forumSplit.getProperty("fieldLabel"));
	}
/**
 * Enters a discussion name.
 * @param name The desired name of the discussion to be passed from the test.
 */
	public void enterDiscussionName(String name) {
		WebElement discussionName = driver.findElement(By .xpath(".//tr[contains(.,'" +
				this.properties.get("fieldLabel") +
				"')]/*/input[@type='text']"));
		discussionName.clear();
		discussionName.sendKeys(name);		
	}
/**
 * Click the split button
 */
	public void clickSplitButton() {
		WebElement splitButton = driver.findElement(By .xpath(".//input[@type='submit']"));
		splitButton.click();		
	}
}