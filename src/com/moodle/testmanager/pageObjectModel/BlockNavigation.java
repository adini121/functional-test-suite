package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.Navigation;
/**
 * This is the page object model for the Navigation Block. All interaction with the navigation block is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class BlockNavigation {
	//Internationalization file location
	public static String blockNavigationData = "properties/data/static/blockNavigation.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public BlockNavigation(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties blockNavigation = new Properties();
		try {
			blockNavigation.load(new FileInputStream(blockNavigationData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("home", blockNavigation.getProperty("home"));
		this.properties.put("treeItemMyCourses", blockNavigation.getProperty("treeItemMyCourses"));
		this.properties.put("treeItemReports", blockNavigation.getProperty("treeItemReports"));
		this.properties.put("treeItemActivityCompletionReport", blockNavigation.getProperty("treeItemActivityCompletionReport"));
	}
/**
 * Clicks the home menu option where the link text is loaded from the internationalization layer.
 */
	public void clickHome() {
		WebElement home = driver.findElement(By .xpath(".//a[contains(.,'" +
				this.properties.get("home") +
				"')]"));
		home.click();
	}
/**
 * Clicks an exposed link specified in the test. 
 * @param linkText The text that is displayed as the link text via the user interface.
 */
	public void clickExposedLink(String linkText) {
		WebElement link = driver.findElement(By .xpath(".//a[contains(.,'" +
				linkText +
				"')]"));
		link.click();
	}
/**
 * Navigates to the activity completion report.
 * @param courseShortname The course shortname, pass this value from the test.
 */
	public void navigateReportActivityCompletion(String courseShortname) {
		Navigation navigate = new Navigation(driver);
		navigate.navigateTree2DeepByXpath(".//li/ul[contains(.,'" + this.properties.get("treeItemMyCourses") + "')]/li/ul[contains(.,'" + courseShortname + "')]/li/ul/li[contains(.,'" + this.properties.get("treeItemReports") + "')]", 
				".//li/ul[contains(.,'" + this.properties.get("treeItemMyCourses") + "')]/li/ul[contains(.,'" + courseShortname + "')]/li/ul/li[contains(.,'" + this.properties.get("treeItemReports") + "')]/ul/li/p/a[@title='" + this.properties.get("treeItemActivityCompletionReport") + "']");
	}
}