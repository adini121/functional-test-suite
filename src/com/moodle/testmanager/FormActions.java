package com.moodle.testmanager;

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
	private static final String TINYMCE = "tinymce";
	private static final String IFRAME = "iframe";
	private static final String TEXTAREA = "textarea";
	private RemoteWebDriver driver;
	private String locDay = "day";
	private String locMonth = "month";
	private String locYear = "year";
	private String locHour = "hour";
	private String locMin = "min";
//Generic locators
	private String locGenXpathPrefix=".//table[@id='";
	private String locGenXpathSuffix="']/*/*/*/iframe";
	private String exceptionNoTextEditor="The text editor is missing from the page and could either be broken or have changed in come way that has broken the test.";
//Other locator variables
	
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
		Select dropdown = new Select(this.driver.findElement(By .id(fieldID)));
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
 * Enters a value in either of the two default text entry area plugins.
 * @param textEntryAreaID The ID of the text entry area.
 * @param message The message to enter in the field.
 * @throws Exception Throws a descriptive exception if the plugins are not available as this would imply that
 * <br/> a) The text area editor is broken.
 * <br/> b) Someone has changed something that has broken the test.
 * <br/> c) A third party plugin is implemented as the default text editor.
 */
public void enterValueInTextArea(CharSequence message) throws Exception {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean itemVisible = false;
		boolean mceVisible = false;
		try {
			WebElement e = driver.findElementByTagName(TEXTAREA);
			itemVisible = e.isDisplayed();
			WebElement emce = driver.findElement(By.tagName(IFRAME));
			mceVisible = emce.isDisplayed();
		}
		catch (Exception e) {}
		if (itemVisible) {
			WebElement e = driver.findElementByTagName(TEXTAREA);
			e.sendKeys(message);
		}
		else if (mceVisible) {
			WebElement messagebox = driver.findElementByTagName(IFRAME);
			driver.switchTo().frame(messagebox);
			WebElement richTextBox = driver.findElement(By.id(TINYMCE));
			richTextBox.click();
			richTextBox.sendKeys(message);
			driver.switchTo().window(driver.getWindowHandle());
		}
		else {
			throw new Exception(exceptionNoTextEditor);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
/**
 * Picks a given item from the resource picker on the course page to add a resource or activity.
 * @param outlineSection The Section that you would like to add the activity/resource in.
 * @param radioID The id of the radio button to select to add an activity or resource.
 */
	public void addItemResourcePicker(String outlineSection, String radioID) {
		driver.findElement(By .xpath("//*[@id='section-" + outlineSection + "']/*/*/*/div[@class='section-modchooser']/*/*")).click();
		WebElement radio = driver.findElement(By .id(radioID));
		radio.click();
		radio.submit();
	}
/**
 * Enters text in a repeating form element by ID.
 * @param idPrefix The prefix to the option number e.g. id_option_ for id_option_1
 * @param itemNumber The item number as it appears to the user on the form e.g. the first element generated is option 0 but it will actually be option 1.
 * @param optionText The text that you would like to enter in the text field of the repeating element.
 */
	public void enterTextByIDRepeatingElement(String idPrefix, String itemNumber, String optionText) {
		//int optionIDInt = itemNumber - 1;
		//String optionID = convertInteger(optionIDInt);
		this.driver.findElementById(idPrefix + itemNumber).sendKeys(optionText);
	}
/*	public static String convertInteger(int i) {
		return "" + i;
	}*/
	
/**
 * Selects a full date in a date field from a dropdown. 
 * @param day The day that you want to enter.
 * @param month The month that you want to enter.
 * @param year The year that you want to enter.
 * @param hour The hour that you want to enter.
 * @param minute The minute that you want to enter.
 * @param idPrefix The The prefix to the option number e.g. id_timeopen_ for id_timeopen_day, id_timeopen_month etc.
 */
	public void selectDateByID(String day, String month, String year, String hour, String minute, String idPrefix) {
		selectDay(day, idPrefix);
		selectMonth(month, idPrefix);
		selectYear(year, idPrefix);
		selectHour(hour, idPrefix);
		selectMin(minute, idPrefix);
	}
/**
 * Selects a value only for the day dropdown of the "Next chat time".
 * @param day The value that you would like to select for day.
 * @param idPrefix The The prefix to the option number e.g. id_timeopen_ for id_timeopen_day, id_timeopen_month etc.
 */
	public void selectDay(String day, String idPrefix) {
		selectDropdownItemByID(idPrefix + locDay, day);
	}
/**
 * Selects a value only for the month dropdown of the "Next chat time".
 * @param month The value that you would like to select for month.
 * @param idPrefix The The prefix to the option number e.g. id_timeopen_ for id_timeopen_day, id_timeopen_month etc.
 */
	public void selectMonth(String month, String idPrefix) {
		selectDropdownItemByID(idPrefix + locMonth, month);
	}
/**
 * Selects a value only for the year dropdown of the "Next chat time".
 * @param year The value that you would like to select for year.
 * @param idPrefix The The prefix to the option number e.g. id_timeopen_ for id_timeopen_day, id_timeopen_month etc.
 */
	public void selectYear(String year, String idPrefix) {
		selectDropdownItemByID(idPrefix + locYear, year);
	}
/**
 * Selects a value only for the hour dropdown of the "Next chat time".
 * @param hour The value that you would like to select for hour.
 * @param idPrefix The The prefix to the option number e.g. id_timeopen_ for id_timeopen_day, id_timeopen_month etc.
 */
	public void selectHour(String hour, String idPrefix) {
		selectDropdownItemByID(idPrefix + locHour, hour);
	}
/**
 * Selects a value only for the minute dropdown of the "Next chat time".
 * @param minute The value that you would like to select for minute.
 * @param idPrefix The The prefix to the option number e.g. id_timeopen_ for id_timeopen_day, id_timeopen_month etc.
 */
	public void selectMin(String minute, String idPrefix) {
		selectDropdownItemByID(idPrefix + locMin, minute);
	}
/**
 * Selects a value from all dropdowns of a short date field.
 * @param day The desired value for day.
 * @param month The desired value for month.
 * @param year The desired value for year.
 * @param locDay The locator for Day.
 * @param locMonth The locator for Month.
 * @param locYear The locator for Year.
 */
	public void selectShortDateByID(String day, String month, String year, String idPrefix) {
		selectDay(day, idPrefix);
		selectMonth(month, idPrefix);
		selectYear(year, idPrefix);
	}
	public void enterValueGenericTinyMCE(CharSequence message, String tableID) {
		WebElement messagebox = driver.findElement(By.xpath(locGenXpathPrefix + tableID + locGenXpathSuffix));
		driver.switchTo().frame(messagebox);
		WebElement richTextBox = driver.findElement(By.id(TINYMCE));
		richTextBox.click();
		richTextBox.sendKeys(message);
		driver.switchTo().window(driver.getWindowHandle());
		
	}
}