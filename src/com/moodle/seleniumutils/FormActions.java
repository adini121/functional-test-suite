package com.moodle.seleniumutils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
/**
 * The utility class for interacting with forms. Page objects re-use these utilities to perform functions relevant to Moodle. 
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FormActions {
	private RemoteWebDriver driver;
/**
 * Constructor for the FormActions utility class.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the page object using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public FormActions(RemoteWebDriver driver) {
	this.driver = driver;
	}
/**
 * Selects an action from a dropdown field on a form by ID. If Javascript is disabled this selects a value and clicks on the
 * Go button.
 * @param fieldID The id of the select tag 
 * @param itemToSelect The literal text of the item to be selected.
 */
	public void selectDropdownItemByID(String fieldID, String itemToSelect) {
		Select dropdown = new Select(driver.findElement(By .id(fieldID)));
		dropdown.selectByVisibleText(itemToSelect);
	}	
/**
 * Selects an action from a dropdown field on a form by ID. If Javascript is disabled this selects a value and clicks on the
 * Go button.
 * @param fieldID The id of the select tag 
 * @param itemToSelect The literal text of the item to be selected.
 * @param timeToWait The time to wait for the Go button to appear onscreen.
 * @param cssSelector The CSS Selector of the Go button.
 */
	public void selectDropdownItemByIDHandlesJS(String fieldID, String itemToSelect, String cssSelector, int timeToWait) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			WebElement onscreenElement = driver.findElement(By .cssSelector(cssSelector));
			itemVisible = onscreenElement.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			Select dropdown = new Select(driver.findElement(By .id(fieldID)));
			dropdown.selectByVisibleText(itemToSelect);
			driver.findElement(By .cssSelector(cssSelector)).click();
		}
		else{
			Select dropdown = new Select(driver.findElement(By .id(fieldID)));
			dropdown.selectByVisibleText(itemToSelect);
		}
	}
/**
 * Selects an option from a dropdown field on a form by XPath.
 * @param xpath A valid XPath locator to the dropdown field.
 * @param itemToSelect The literal text of the item to be selected.
 */
	public void selectDropdownItemByXpath(String xpath, String itemToSelect) {
		Select activityDropDown = new Select(driver.findElement(By .xpath(xpath)));
		activityDropDown.selectByVisibleText(itemToSelect);
	}
/**
 * Selects an option from a dropdown field on a form by XPath. If Javascript is disabled this selects a value and clicks on the
 * Go button.
 * @param xpathField A valid XPath locator to the dropdown field.
 * @param itemToSelect The literal text of the item to be selected.
 * @param xpathGo A valid XPath locator to the Go button.
 * @param timeToWait The time to wait for the Go button to appear onscreen.
 */
	public void selectDropdownItemByXPathHandlesJS(String xpathField, String itemToSelect, String xpathGo, int timeToWait) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			WebElement onscreenElement = driver.findElement(By .xpath(xpathGo));
			itemVisible = onscreenElement.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			Select activityDropDown = new Select(driver.findElement(By .xpath(xpathField)));
			activityDropDown.selectByVisibleText(itemToSelect);
			driver.findElement(By .xpath(xpathGo)).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else{
			Select activityDropDown = new Select(driver.findElement(By .xpath(xpathField)));
			activityDropDown.selectByVisibleText(itemToSelect);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
/**
 * Enters a value in any tiny MCE text entry area.
 * @param textEntryAreaID The ID of the text entry area.
 * @param message The message to enter in the field.
 */
	public void enterValueInTinyMCE(String message) {
		WebElement messagebox = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(messagebox);
		WebElement richTextBox = driver.findElement(By.id("tinymce"));
		richTextBox.click();
		richTextBox.sendKeys(message);
		driver.switchTo().window(driver.getWindowHandle());
	}
/**
 * Handles a checkbox that you want to tick. If it is ticked then leave it ticked, if it is not ticked then tick it.
 * This is particularly useful for admin pages where a change may have already been made to the checkbox state.
 * @param fieldID The ID tag of the field yo want to be checked.
 */
	public void handleCheckboxStateAndEnterTick(String fieldID) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement tick = driver.findElement(By .xpath(".//*[@id='" + fieldID + "'][@checked='checked']"));
			itemVisible = tick.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){}
		else {
			WebElement unTicked = driver.findElement(By .xpath(".//*[@id='" + fieldID + "']"));
			unTicked.click();
		}
	}
}