package com.moodle.testmanager.pageObjectModel;


import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.FormActions;
/**
 * The page object model for the Add Assignment form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public abstract class FormAddEditSettings {
	RemoteWebDriver driver;
	protected Map<String, String> properties = new HashMap<String, String>();
	public FormAddEditSettings(RemoteWebDriver driver) {
		super();
		this.driver = driver;
		this.loadSuperData("properties/data/static/formSettings.properties");
	}
	/**
	 * Loads data for the page object from the internationalization layer
	 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
	 */
	public void loadSuperData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/formSettings.properties"));
		} catch (Exception e) {}
		this.properties.put("formButtonSaveReturn", dataLoad.getProperty("formButtonSaveReturn"));
		this.properties.put("formButtonSaveDisplay", dataLoad.getProperty("formButtonSaveDisplay"));
		this.properties.put("formButtonCancel", dataLoad.getProperty("formButtonCancel"));
	}
	/**
	 * 	Enter a value in the forum name field.
	 * @param name The value to be entered passed from the test.
	 */
	public void enterNameField(String name) {
		WebElement nameField = this.driver.findElement(By .id("id_name"));
		nameField.sendKeys(name);
	}
	/**
	 * Enter a value in the Forum introduction field.
	 * @param text The value to be entered passed from the test.
	 */
	public void enterIntroField(String message) {
		FormActions richText = new FormActions(driver);
		richText.enterValueInTinyMCE(message);	
	}
	/**
	 * Select Save and return to course.
	 */
	public void clickSaveAndRetToCourse() {
		WebElement save = this.driver.findElement(By .xpath(".//*[@value='" + this.properties.get("formButtonSaveReturn") + "']"));
		save.click();
	}
	/**
	 * Select Save and return to course.
	 */
	public void clickSaveAndDisplay() {
		WebElement save = this.driver.findElement(By .xpath(".//*[@value='" + this.properties.get("formButtonSaveDisplay") + "']"));
		save.click();
	}
	/**
	 * Select Save and return to course.
	 */
	public void clickCancel() {
		WebElement save = this.driver.findElement(By .xpath(".//*[@value='" + this.properties.get("formButtonCancel") + "']"));
		save.click();
	}
}