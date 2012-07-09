package com.moodle.testmanager;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for File manager form element on Add/Edit page.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FilesElements {
	public RemoteWebDriver driver;
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/FileElement.properties";
/**
 * Locator variables.
 */
	public String locLabelToLeftPrefix = "//tr[contains(.,'";
	public String locLabelToLeftSuffix = "')]/td/input";	
	public String locLabelAbovePrefix = ".//div[contains(.,'";
	public String locLabelAboveSuffix = "')]/input";
	private String locCancelX = "yui3-button yui3-button-close";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public FilesElements(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData(langFile);
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties databaseAddData = new Properties();
		try {
			databaseAddData.load(new FileInputStream(langFile));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("PROPERTY", databaseAddData.getProperty("PROPERTY"));
	}
/*
 * Object templates
 */
/**
 * click any file manager button.
 * @param className The class name of the button.
 */
	public void fileManButton(String className) {
		WebElement button = driver.findElementByClassName(className);
		button.click();
	}
/**
 * Clicks a two state button whether it is switched on or not.
 * @param className
 */
	public void stickyButton(String className) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean itemVisible = false;
		try {
			WebElement element = driver.findElementByClassName(className);
			itemVisible = element.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible) {
			fileManButton(className);
		}
		else {
			fileManButton(className + " " + "checked");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks a folder breadcrumb.
 * @param folderName The name of the folder breadcrumb that you would like to click.
 */
	public void clickBreadcrumb(String folderName) {
		WebElement breadcrumb = driver.findElementByLinkText(folderName);
		breadcrumb.click();
	}
/**
 * Enters a given value in a text field in the file picker. The field is located by the field label text and the location strategy used here
 * will work regardless of whether the field label is above the field or to the left. May not work with rtl sites
 * @param fieldLabel The field label.
 * @param keysToSend The value that you aould like to enter in the field.
 */
	public void textEntryField(String fieldLabel, CharSequence keysToSend) {
		//TODO RTL sites
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean itemVisible = false;
		try {
			WebElement element = driver.findElementByXPath(locLabelToLeftPrefix + fieldLabel + locLabelToLeftSuffix);
			itemVisible = element.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible) {
			driver.findElementByXPath(locLabelToLeftPrefix + fieldLabel + locLabelToLeftSuffix).sendKeys(keysToSend);
		}
		else {
			driver.findElementByXPath(locLabelAbovePrefix + fieldLabel + locLabelAboveSuffix).sendKeys(keysToSend);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks on the X to close the dialog.
 */
	public void closeIconX() {
		WebElement button = driver.findElementByClassName(locCancelX);
		button.click();
	}
}