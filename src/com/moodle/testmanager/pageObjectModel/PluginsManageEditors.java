package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.Navigation;
/**
 * This is the page object model for the Settings Block. All interaction with the settings block is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class PluginsManageEditors {
	//Internationalization file location
	public static String blockSettingsData = "properties/data/static/PluginManageEditors.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	RemoteWebDriver driver;
	protected Navigation navigate = new Navigation(driver);
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public PluginsManageEditors(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties blockSettings = new Properties();
		try {
			blockSettings.load(new FileInputStream(blockSettingsData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("locDisable", blockSettings.getProperty("locDisable"));
		this.properties.put("locEnable", blockSettings.getProperty("locEnable"));
		this.properties.put("locSettings", blockSettings.getProperty("locSettings"));
		this.properties.put("locTinyMCE", blockSettings.getProperty("locTinyMCE"));
		this.properties.put("locPlainText", blockSettings.getProperty("locPlainText"));
	}
	/*
	 * Generic methods for class.
	 */
	/**
	 * Clicks Disable if it is enabled and vice versa.
	 * @param locName The value for name from each table row that you want to interact with.
	 */
	private void clickEnableDisable(String locName) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement e = driver.findElementByXPath(".//tr[contains(.,'" + locName + "')]/*/a/img[@alt='" + this.properties.get("locDisable") + "']");
			itemVisible = e.isDisplayed();
		}
		catch (NoSuchElementException ex) {};
		if(itemVisible) {
			WebElement e = driver.findElementByXPath(".//tr[contains(.,'" + locName + "')]/*/a/img[@alt='" + this.properties.get("locDisable") + "']");
			e.click();	
		}
		else {
			WebElement e = driver.findElementByXPath(".//tr[contains(.,'" + locName + "')]/*/a/img[@alt='" + this.properties.get("locEnable") + "']");
			e.click();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	/**
	 * Clicks the Select link.
	 * @param locName The value for name from each table row that you want to interact with. 
	 */
	private void clickSettings(String locName) {
		WebElement e = driver.findElementByXPath(".//tr[contains(.,'" + locName + "')]/*/a[contains(.,'" + this.properties.get("locSettings") + "')]");
		e.click();
	}
	/*
	 * Specific methods for class.
	 */
	/**
	 * Enables or disables the TinyMCE editor plugin.
	 */
	public void clickEnableDisableTinyMCE() {
		clickEnableDisable(this.properties.get("locTinyMCE"));
	}
	/**
	 * Enables or disables the Plain text plugin.
	 */
	public void clickEnableDisablePlainText() {
		clickEnableDisable(this.properties.get("locPlainText"));
	}
	/**
	 * Clicks the settings link for TinyMCE.
	 */
	public void clickSettingsTinyMCE() {
		clickSettings(this.properties.get("locTinyMCE"));
	}
}