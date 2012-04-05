package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.PassFailCriteria;
/**
 * This is the page object model for interacting with the News Block.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class BlockNews {
	//Internationalization file location
	public static String blockNewsData = "properties/data/static/blockNews.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public BlockNews(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNews.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties blockNews = new Properties();
		try {
			blockNews.load(new FileInputStream(blockNewsData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("exceptionNewsBlockNotDisplayed", blockNews.getProperty("exceptionNewsBlockNotDisplayed"));
		this.properties.put("exceptionNewsBlockDiscussion", blockNews.getProperty("exceptionNewsBlockDiscussion"));
	}
/**
 * Asserts that an item appears in the news block and makes the test fail if it doesn't.
 * @param textToAssert The Sting that is to be asserted, the value of which is passed from the test.
 */
	public void assertDiscussionInNewsBlock(String textToAssert) {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertTextPresentByXpath("//a[contains(.,'" +
				textToAssert +
				"')]", this.properties.get("exceptionNewsBlockDiscussion"), textToAssert);
	}
/**
 * Asserts that the news block is not displayed onscreen.
 * @throws Exception "News block is visible and should not be" is thrown when the element is present.
 */
	public void assertNewsBlockNotDisplayed() throws Exception {
		PassFailCriteria passFail = new PassFailCriteria(driver);
		passFail.assertItemNotOnscreenByCSSSelector("div[class='block_news_items block']", this.properties.get("exceptionNewsBlockNotDisplayed"), 0);
	}
}