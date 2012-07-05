package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for adding a Folder resource to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FileManager extends FormAddEditSettings {
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/FileManager.properties";
/**
 * Locator variables.
 */
	//Locators using class name
	private String locAddButton = "fp-btn-add";
	private String locCreateFolderButton = "fp-btn-mkdir";
	private String locIconBtn = "fp-vb-icons";
	private String locDetailBtn = "fp-vb-details";
	private String locTreeBtn = "fp-vb-tree";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public FileManager(RemoteWebDriver driver) {
		super(driver);
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
	}
/*
 * Specific object methods.
 */
	/**
	 * Clicks the file manager form element "Add..." button.
	 */
	public void clickAddButton() {
		fileManButton(locAddButton);
	}
	/**
	 * Clicks the file manager form element "Create folder" button.
	 */
	public void clickCreateFolderButton() {
		fileManButton(locCreateFolderButton);
	}
	/**
	 * Clicks the file manager form element icon view button regardless of it's state.
	 */
	public void clickIconViewButton() {
		stickyButton(locIconBtn);
	}
	/**
	 * Clicks the file manager form element detail view button regardless of it's state.
	 */
	public void clickDetailViewButton() {
		stickyButton(locDetailBtn);
	}
	/**
	 * Clicks the file manager form element tree view button regardless of it's state.
	 */
	public void clickTreeViewButton() {
		stickyButton(locTreeBtn);
	}
	/**
	 * Clicks a given folder breadcrumb.
	 * @param folderName The name of the folder that you would like to click.
	 */
	public void clickBreadcrumb(String folderName) {
		WebElement breadcrumb = driver.findElementByLinkText(folderName);
		breadcrumb.click();
	}
}