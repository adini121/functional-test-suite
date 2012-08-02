package com.moodle.testmanager.pageObjectModel;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * The utility class for asserting pass/fail criteria. Page objects re-use these utilities to perform functions relevant to Moodle. 
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public abstract class PassFailAssertions {
	private RemoteWebDriver driver;
/**
 * Constructor for the PassFailCriteria utility class.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the page object using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public PassFailAssertions(RemoteWebDriver driver) {
		this.driver = driver;
	}
/**
 * Asserts that an element does not appear onscreen when located by css selector. The selector is passed from the page object.
 * @param cssSelector The CSS locator which is passed by the page object.
 * @param exceptionMessage The Exception Message which is passed by the page object.
 * @throws Exception Throws an exception with the text from the Exception Message parameter.
 */
	public void assertItemNotOnscreenByCSSSelector(String cssSelector, String exceptionMessage, int timeToWait) throws Exception {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			WebElement onscreenElement = driver.findElement(By .cssSelector(cssSelector));
			itemVisible = onscreenElement.isDisplayed();
		}
		catch (NoSuchElementException ex){}
			if (itemVisible){
				throw new Exception (exceptionMessage);
		}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
/**
 * Asserts that an element is present onscreen by xpath
 * @param forumName The name of the forum, the value of which is passed from the page object.
 * @param exceptionMessage The text to appear in the exception message, the value of which is passed from the forum.
 * @throws Exception Throws an exception with the text from the Exception Message parameter.
 */
	public void assertElementIsPresentByXpath(String xpathLocator, String exceptionMessage, int timeToWait) throws Exception {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			WebElement onscreenElement = driver.findElement(By .xpath(xpathLocator));
			itemVisible = onscreenElement.isDisplayed();
		}
		catch (NoSuchElementException ex){}
			if (itemVisible){}
			else{
				throw new Exception (exceptionMessage);
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Asserts that an element, located by Xpath, is not present onscreen.
 * @param xpath A valid xpath to the locator
 * @param exceptionMessage The exception message that is thrown when the element is present on the screen.
 * @param timeToWait The number of seconds that you want to wait for the element to be displayed onscreen. A higher value can be useful if the page loading times are long or another page is temporarily displayed first.
 * @throws Exception Throws an exception if the xpath element can be located.
 */
	public void assertElementIsNotPresentByXpath(String xpath, String exceptionMessage, int timeToWait) throws Exception {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
			WebElement onscreenElement = driver.findElement(By .xpath(xpath));
			itemVisible = onscreenElement.isDisplayed();
		}
		catch (NoSuchElementException ex){}
			if (itemVisible){
				throw new Exception (exceptionMessage);
		}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
/**
 * Assert that a whole block of text in a given element is present onscreen using the JUnit Assert function.  
 * @param xpath The xpath locator of the element containing the text to assert.
 * @param failText The assertion message that will be thrown upon failure.
 * @param textToAssert The text that makes up the pass/fail criteria of the test.
 */
	public void assertTextPresentByXpath(String xpath, String failText, String textToAssert) {
		WebElement forumSettingsMenuSubscribeLink = driver.findElement(By .xpath(xpath));
		assertEquals(failText,textToAssert,forumSettingsMenuSubscribeLink.getText());
	}
}