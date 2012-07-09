package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.moodle.testmanager.FilesElements;
/**
 * This is the page object model for File manager form element on Add/Edit page.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FormAddEditSettingsFileManager extends FormAddEditSettings {
	private FilesElements fileManager = new FilesElements(driver);
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/FileManager.properties";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public FormAddEditSettingsFileManager(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData(langFile);
		this.locNewFolderNameFieldLabel = this.properties.get("newFolderName");
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/FormAddEditSettingsFileManager.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties databaseAddData = new Properties();
		try {
			databaseAddData.load(new FileInputStream(langFile));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("newFolderName", databaseAddData.getProperty("newFolderName"));
	}
/**
 * Locator variables.
 */
	//Locators using class name
	private String locAddButton = "fp-btn-add";
	private String locCreateFolderIconButton = "fp-btn-mkdir";
	private String locIconBtn = "fp-vb-icons";
	private String locDetailBtn = "fp-vb-details";
	private String locTreeBtn = "fp-vb-tree";
	private String locNewFolderNameFieldLabel;
	private String locCreateFolder = "fp-dlg-butcreate";
	private String locCancelFolder = "fp-dlg-butcancel";
/*
 * Specific object methods.
 */
/**
 * Clicks the file manager form element "Add..." button.
 */
	public void clickAddButton() {
		fileManager.fileManButton(locAddButton);
	}
/**
 * Clicks the file manager form element "Create folder" button.
 */
	public void clickCreateFolderIconButton() {
		fileManager.fileManButton(locCreateFolderIconButton);
	}
/**
 * Clicks the file manager form element icon view button regardless of it's state.
 */
	public void clickIconViewButton() {
		fileManager.stickyButton(locIconBtn);
	}
/**
 * Clicks the file manager form element detail view button regardless of it's state.
 */
	public void clickDetailViewButton() {
		fileManager.stickyButton(locDetailBtn);
	}
/**
 * Clicks the file manager form element tree view button regardless of it's state.
 */
	public void clickTreeViewButton() {
		fileManager.stickyButton(locTreeBtn);
	}
/**
 * Clicks a given folder breadcrumb.
 * @param folderName The name of the folder that you would like to click.
 */
	public void clickBreadcrumb(String folderName) {
		fileManager.clickBreadcrumb(folderName);
	}
/**
 * Enters value in the "New folder name:" field.
 * @param folderName The name of the new folder. 
 */
	public void enterNewSubFolderName(CharSequence folderName) {
		this.fileManager.textEntryField(this.locNewFolderNameFieldLabel, folderName);
	}
/**
 * Clicks the Create folder button in the dialog launched when creating a new folder.
 */
	public void clickCreateFolderButton() {
		fileManager.fileManButton(locCreateFolder);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		@SuppressWarnings("unused")
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='fp-thumbnail']")));
	}
/**
 * Clicks the Cancel button in the dialog launched when creating a new folder.
 */
	public void clickCancelFolderButton() {
		fileManager.fileManButton(locCancelFolder);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		@SuppressWarnings("unused")
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='fp-thumbnail']")));
	}
/**
 * Clicks the X icon to close the dialog.
 */
	public void clickCancelX() {
		fileManager.closeIconX();
	}
}