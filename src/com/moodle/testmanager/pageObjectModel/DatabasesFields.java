package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the database fields tab.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class DatabasesFields {
	//Internationalization file location
	public static String databasesFields = "properties/data/static/databasesFields.properties";
	private RemoteWebDriver driver;
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public DatabasesFields(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dbFields = new Properties();
		try {
			dbFields.load(new FileInputStream(databasesFields));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("checkbox", dbFields.getProperty("checkbox"));
		this.properties.put("date", dbFields.getProperty("date"));
		this.properties.put("file", dbFields.getProperty("file"));
		this.properties.put("latlong", dbFields.getProperty("latlong"));
		this.properties.put("menu", dbFields.getProperty("menu"));
		this.properties.put("multimenu", dbFields.getProperty("multimenu"));
		this.properties.put("number", dbFields.getProperty("number"));
		this.properties.put("picture", dbFields.getProperty("picture"));
		this.properties.put("radioButton", dbFields.getProperty("radioButton"));
		this.properties.put("text", dbFields.getProperty("text"));
		this.properties.put("textarea", dbFields.getProperty("textarea"));
		this.properties.put("go", dbFields.getProperty("go"));
		this.properties.put("options", dbFields.getProperty("options"));
		this.properties.put("widthSingle", dbFields.getProperty("widthSingle"));
		this.properties.put("heightSingle", dbFields.getProperty("heightSingle"));
		this.properties.put("widthList", dbFields.getProperty("widthList"));
		this.properties.put("heightList", dbFields.getProperty("heightList"));
		this.properties.put("allowAutolink", dbFields.getProperty("allowAutolink"));
		this.properties.put("widthColumns", dbFields.getProperty("widthColumns"));
		this.properties.put("heightRows", dbFields.getProperty("heightRows"));
		this.properties.put("maxEmbed", dbFields.getProperty("maxEmbed"));
		this.properties.put("forcedName", dbFields.getProperty("forcedName"));
		this.properties.put("autolinkUrl", dbFields.getProperty("autolinkUrl"));
		this.properties.put("ascending", dbFields.getProperty("ascending"));
		this.properties.put("descending", dbFields.getProperty("descending"));
		this.properties.put("save", dbFields.getProperty("save"));
	}
/*
 * A set of objects for the Fields Tab page
 */
/**
 * Clicks the link to choose a predefined set of fields.
 */
	public void linkClickPredefinedSet() {
		WebElement link = driver.findElement(By .xpath(".//a[contains(.,'" +
				this.properties.get("predefinedSet") +
				"')]"));
		link.click();
	}
/**
 * Selects the dropdown option to create a new checkbox field.
 */
	public void selectCreateNewCheckboxField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("checkbox") +
		"')]", this.properties.get("checkbox"),	
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new date field.
 */
	public void selectCreateNewDateField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" +
		this.properties.get("date")
		+ "')]", 
		this.properties.get("date"),
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new file field.
 */
	public void selectCreateNewFileField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" +
		this.properties.get("file") +
		"')]", 
		this.properties.get("file"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Latitiude/Longitude field.
 */
	public void selectCreateNewLatLongField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("latlong") +
		"')]",
		this.properties.get("latlong"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Menu field.
 */
	public void selectCreateNewMenuField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" +
		this.properties.get("menu") + 
		"')]",
		this.properties.get("menu"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Menu (Multi-select) field.
 */
	public void selectCreateNewMultimenuField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("multimenu") +
		"')]", 
		this.properties.get("multimenu"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Number field.
 */
	public void selectCreateNewNumberField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("number") + 
		"')]",
		this.properties.get("number"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Picture field.
 */
	public void selectCreateNewPictureField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("picture") +
		"')]",
		this.properties.get("picture"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Radio button field.
 */
	public void selectCreateNewRadioButtonField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("radioButton") + 
		"')]",
		this.properties.get("radioButton"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Text field.
 */
	public void selectCreateNewTextField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("text") +
		"')]", 
		this.properties.get("text"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Textarea field.
 */
	public void selectCreateNewTextareaField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("textarea") +
		"')]",
		this.properties.get("textarea"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects the dropdown option to create a new Url field.
 */
	public void selectCreateNewUrlField() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXPathHandlesJS("//select/option[contains(.,'" + 
		this.properties.get("url") +
		"')]", 
		this.properties.get("url"), 
		"//input[@value='" +
		this.properties.get("go") +
		"']", 0);
	}
/**
 * Selects a value from the Default sort field.
 * @param itemToSelect The item to select from the sort order field.
 */
	public void selectDefaultSortField(String itemToSelect) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("defaultsort", itemToSelect);
	}
/**
 * Selects Ascending from the dropdown.
 */
	public void selectAscending() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("menudefaultsortdir", this.properties.get("ascending"));
	}
/**
 * Selects Descending from the dropdown.
 */
	public void selectDescending() {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID("menudefaultsortdir", this.properties.get("descending"));
	}
/**
 * Selects the Save button.
 */
	public void selectSave() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("save") +
				"']"));
		button.click();
	}
/*
 * A set of objects relating to the forms that are displayed after a selecting a field type from a dropdown
 * These objects complete and submit (or cancel) those forms. 
 */
/**
 * Enters a value in the Field name field.
 * @param fieldName The value you would like to enter.
 */
	public void enterFieldName(String fieldName) {
		WebElement field = driver.findElement(By .id("name"));
		field.sendKeys(fieldName);
	}
/**
 * Enters a value in the Field description field. 
 * @param fieldDescription The value you would like to enter.
 */
	public void enterFieldDescription(String fieldDescription) {
		WebElement field = driver.findElement(By .id("description"));
		field.sendKeys(fieldDescription);
	}
/**
 * Enters a single value for the options text area then hits the enter key to start a new line.
 * re-use for each option to be entered. 
 * @param option The value for the option that you want to enter.
 */
	public void enterOption(String option) {
		WebElement field = driver.findElement(By .xpath(".//tr[contains(.,'" +
				this.properties.get("options") +
				"')]/td/textarea"));
		field.sendKeys(option, Keys.ENTER);
	}
/**
 * Selects a value for size from the size dropdown.
 * @param size Valid values are:
 * 2MB
 * 1MB
 * 500KB
 * 100KB
 * 50KB
 * 10KB
 * 0 bytes
 */
	public void selectMaxSize(String size) {
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByXpath("//select[contains(.,'" +
				size +
				"')]",
				size);
	}
/**
 * Selects an option for Link-out services to display from the multi-select.
 * @param linkOption The option to select.
 * Default valid values are:
 * Google Maps
 * Google Earth
 * Geabios
 * OpenStreetMap
 * Multimap
 */
	public void selectLinkout(String linkOption) {
		FormActions multiSelect = new FormActions(driver);
		multiSelect.selectDropdownItemByXpath("//select[contains(.,'" +
				linkOption +
				"')]", 
				linkOption);
	}
/**
 * Enters a value for Width in single view.
 * @param value The required value.
 */
	public void enterWidthInSingleView(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("widthSingle")+
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Enters a value for Height in single view.
 * @param value The required value.
 */
	public void enterHeightInSingleView(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("heightSingle") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Enters a value for Width in list view.
 * @param value The required value.
 */
	public void enterWidthInListView(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("widthList") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Enters a value for Height in list view.
 * @param value The required value.
 */
	public void enterHeightInListView(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("heightList") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Selects/de-selects the Allow autolink checkbox.
 */
	public void clickAllowAutolink() {
		WebElement checkbox = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("allowAutolink") +
				"')]/td/input"));
		checkbox.click();
	}
/**
 * Enters a value for Width in columns.
 * @param value The number of columns.
 */
	public void enterWidthColumns(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("widthColumns") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Enters a value for Height in rows.
 * @param value The number of rows.
 */
	public void enterHeightRows(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("heightRows") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Enters a value for Maximum embeded file size (bytes).
 * @param value The maximum file size in bytes.
 */
	public void enterMaxEmbeded(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("maxEmbed") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Enters a value for Forced name for the link.
 * @param value The forced name for the link.
 */
	public void enterForcedName(String value) {
		WebElement field = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("forcedName") +
				"')]/td/input"));
		field.sendKeys(value);
	}
/**
 * Selects/de-selects the Allow autolink checkbox.
 */
	public void clickAutolinkUrl() {
		WebElement checkbox = driver.findElement(By .xpath("//tr[contains(.,'" +
				this.properties.get("autolinkUrl") +
				"')]/td/input"));
		checkbox.click();
	}
/**
 * Clicks the Add button.
 */
	public void clickAdd() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("add") +
				"']"));
		button.click();
	}
/**
 * Clicks the Close button.
 */
	public void clickCancel() {
		WebElement button = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("cancel") +
				"']"));
		button.click();
	}
}