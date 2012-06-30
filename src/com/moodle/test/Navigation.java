package com.moodle.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * The utility class for interacting with forms. Page objects re-use these utilities to perform functions relevant to Moodle. 
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Navigation {
	private RemoteWebDriver driver;
/**
 * Constructor for the FormActions utility class.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the page object using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public Navigation(RemoteWebDriver driver) {
	this.driver = driver;
	}
/**
 * Intelligently navigates to a tree menu to an item that is 2 levels deep. 
 * @param level1 The Xpath locator for the top level parent node in the tree.
 * @param level2 The Xpath locator for the second level parent node in the tree.
 */
	public void navigateTree2DeepByXpath(String level1, String level2){
		boolean itemVisible = false;
		try{
			WebElement e = driver.findElement(By .xpath(level2));
			itemVisible = e.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			WebElement level2Element =  driver.findElement(By .xpath(level2));
			level2Element.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else {
			WebElement level1Element = driver.findElement(By .xpath(level1));
			level1Element.click();
			WebElement level2Element = driver.findElement(By .xpath(level2));
			level2Element.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}
/**
 * Intelligently navigates to a tree menu to an item that is 3 levels deep. 
 * @param level1 The Xpath locator for the top level parent node in the tree.
 * @param level2 The Xpath locator for the second level parent node in the tree.
 * @param level3 The Xpath locator for the third level parent node in the tree.
 */
	public void navigateTree3DeepByXpath(String level1, String level2, String level3){
		boolean itemVisible = false;
		try{
			WebElement e = driver.findElement(By .xpath(level3));
			itemVisible = e.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			WebElement level3Element =  driver.findElement(By .xpath(level3));
			level3Element.click();
		}
		else {
			WebElement level1Element = driver.findElement(By .xpath(level1));
			level1Element.click();
			WebElement level2Element = driver.findElement(By .xpath(level2));
			level2Element.click();
			WebElement level3Element = driver.findElement(By .xpath(level3));
			level3Element.click();
		}
	}
/**
 * Intelligently navigates to a tree menu to an item that is 4 levels deep e.g. "Add a new user". 
 * @param level1 The Xpath locator for the top level parent node in the tree e.g. "Site administration".
 * @param level2 The Xpath locator for the second level parent node in the tree e.g. "Users".
 * @param level3 The Xpath locator for the third level parent node in the tree e.g. "Browse list of users".
 * @param level4 The Xpath locator for the fourth level parent node in the tree e.g. "Add a new user".
 */
	public void navigateTree4DeepByXpath(String level1, String level2,
			String level3, String level4) {
		boolean itemVisible = false;
		try{
			WebElement e = driver.findElement(By .xpath(level4));
			itemVisible = e.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			WebElement level4Element =  driver.findElement(By .xpath(level4));
			level4Element.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else {
			WebElement level1Element = driver.findElement(By .xpath(level1));
			level1Element.click();
			WebElement level2Element = driver.findElement(By .xpath(level2));
			level2Element.click();
			WebElement level3Element = driver.findElement(By .xpath(level3));
			level3Element.click();
			WebElement level4Element =  driver.findElement(By .xpath(level4));
			level4Element.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}
}