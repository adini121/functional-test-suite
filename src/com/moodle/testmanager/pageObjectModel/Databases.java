package com.moodle.testmanager.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * This is the page object model for Database activities in a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Databases {
	public static String blockNavigationData = "properties/data/static/databases.properties";
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * NOT YET IMPLEMENTED: loadObjectData constructs internationalization layer in the context of this page object.
 */
	public Databases(RemoteWebDriver driver) {
		this.driver = driver;
	}
/**
 * Click on a database link using the database name passed from the test case.
 * @param databaseName the link text for the link to the database.
 */
	public void clickdatabaseLink(String databaseName) {
		WebElement databaseLink = driver.findElement(By .xpath("//a[contains(.,'" +
				databaseName +
				"')]"));
		databaseLink.click();
	}
/**
 * Click advanced search checkbox
 */
	public void clickAdvancedSearchCheckbox() {
		WebElement advancedCheckbox = driver.findElement(By .id("advancedcheckbox"));
		advancedCheckbox.click();
	}
/**
 * Enter Data in advanced search text field
 * @param fieldName the literal text associated with the field label.
 * @param inputValue The value to be entered as search criteria.
 */
	public void enterDataAdvancedSearchTextField(String fieldName, String inputValue) {
		WebElement advancedText = driver.findElement(By .xpath(".//tr[contains(.,'" +
				fieldName +
				"')]/*/input"));
		advancedText.sendKeys(inputValue);
	}
/**
 * Select a value for in a dropdown passed via properties.
 * @param fieldName the literal text associated with the field label.
 * @param choice The value to be selected from the dropdown.
 */
	public void selectDropdownOption(String fieldName, String choice) {
		Select selectDropdownOption = new Select(driver.findElement(By .xpath(".//tr[contains(.,'" +
				fieldName +
				"')]/*/select[contains(.,'Choose')]")));
		selectDropdownOption.selectByVisibleText(choice);
	}
/**
 * Click 'Save Settings' button then wait for notify success to be returned.
 */
	//TODO: revert back to original version and add a condition to handle no results returned 
	public void clickSaveSettings() {
		WebElement saveSettingsButton = driver.findElement(By .xpath(".//input[@value='Save settings']"));
		saveSettingsButton.click();
		(new WebDriverWait(driver, 60)).until(new ExpectedCondition<WebElement>(){
					@Override
					public WebElement apply(WebDriver d) {
						return d.findElement(By.className("notifysuccess"));
					}});
	}
}