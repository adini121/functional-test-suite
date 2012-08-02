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
public class BlockAssertions extends PassFailAssertions{
	//Internationalization file location
	public static String blockNewsData = "properties/data/static/blockNews.properties";
	public static String blockSettingsData = "properties/data/static/blockSettings.properties";
	RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
public BlockAssertions(RemoteWebDriver driver) {
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
		dataLoad.load(new FileInputStream(blockNewsData));
		dataLoad.load(new FileInputStream(blockSettingsData));
	} catch (Exception e) {}
	//put values from the properties file into hashmap
	//Locators
	this.properties.put("treeForumAdmin", dataLoad.getProperty("treeForumAdmin"));
	this.properties.put("treeDontTrack", dataLoad.getProperty("treeDontTrack"));
	//Exceptions
	this.properties.put("exceptionNewsBlockNotDisplayed", dataLoad.getProperty("exceptionNewsBlockNotDisplayed"));
	this.properties.put("exceptionNewsBlockDiscussion", dataLoad.getProperty("exceptionNewsBlockDiscussion"));
	this.properties.put("exceptionTrackingCanBeEnabled", dataLoad.getProperty("exceptionTrackingCanBeEnabled"));
	this.properties.put("exceptionTrackingCanBeDisabled", dataLoad.getProperty("exceptionTrackingCanBeDisabled"));
}
/**
 * Asserts that an item appears in the news block and makes the test fail if it doesn't.
 * @param textToAssert The Sting that is to be asserted, the value of which is passed from the test.
 */
public void assertDiscussionInNewsBlock(String textToAssert) {
	assertTextPresentByXpath("//a[contains(.,'" + textToAssert + "')]", this.properties.get("exceptionNewsBlockDiscussion"), textToAssert);
}
/**
 * Asserts that the news block is not displayed onscreen.
 * @throws Exception "News block is visible and should not be" is thrown when the element is present.
 */
public void assertNewsBlockNotDisplayed() throws Exception {
	assertItemNotOnscreenByCSSSelector("div[class='block_news_items block']", this.properties.get("exceptionNewsBlockNotDisplayed"), 0);
}
/**
 * Asserts that tracking cannot be enabled by the user.
 * @throws Exception An exception is thrown if tracking can be enabled.
 */
public void assertTrackingCannotBeEnabled() throws Exception {
	assertElementIsNotPresentByXpath(".//li[contains(.,'" +
			this.properties.get("treeForumAdmin") +
			"')]/ul/li/*/a[contains(.,'" +
			this.properties.get("treeDontTrack") +
			"')]", this.properties.get("exceptionTrackingCanBeEnabled"), 0);
}
/**
 * Asserts that tracking cannot be disabled by the user.
 * @throws Exception An exception is thrown if tracking can be disabled.
 */
public void assertTrackingCannotBeDisabled() throws Exception {
	assertElementIsNotPresentByXpath(".//li[contains(.,'" +
			this.properties.get("treeForumAdmin") +
			"')]/ul/li/*/a[contains(.,'" +
			this.properties.get("treeDontTrack") +
			"')]", this.properties.get("exceptionTrackingCanBeDisabled"), 0);
}
}