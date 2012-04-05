package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
/**
 * This is the page object model for the add forum form. All interaction with the navigation block is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ProfileEdit {
	//Internationalization file location
	public static String profileEditData = "properties/data/static/profileEdit.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public ProfileEdit(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadTestData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadTestData() {
		Properties profileEdit = new Properties();
		try {
			profileEdit.load(new FileInputStream(profileEditData));
		} catch (Exception e) {}
		this.properties.put("trackingOption", profileEdit.getProperty("trackingOption"));
		this.properties.put("updateProfile", profileEdit.getProperty("updateProfile"));
	}
/**
 * Turns forum tracking on.
 */
	public void selectForumTrackingOn() {
		Select forumTrackingOption = new Select(driver.findElement(By .id("id_trackforums")));
		forumTrackingOption.selectByVisibleText(this.properties.get("trackingOption"));	
	}
/**
 * Clicks Update profile in the Edit profile form.
 */
	public void clickUpdateProfile() {
		WebElement save = driver.findElement(By .xpath(".//*[@value='" +
				this.properties.get("updateProfile") +
				"']"));
		save.click();
	}
}