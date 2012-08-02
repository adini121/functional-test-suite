package com.moodle.testmanager.pageObjectModel;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is the page object model for courses. Hich level course interaction is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Courses {
	//Internationalization file location
	public static String coursesData = "properties/data/static/courses.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public Courses(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/courses.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream(coursesData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("addNewCourseButton", dataLoad.getProperty("addNewCourseButton"));
		this.properties.put("coursesNavBlock", dataLoad.getProperty("coursesNavBlock"));
		this.properties.put("turnEditingOn", dataLoad.getProperty("turnEditingOn"));
		this.properties.put("turnEditingOff", dataLoad.getProperty("turnEditingOff"));
		this.properties.put("deleteIconAlt", dataLoad.getProperty("deleteIconAlt"));
		this.properties.put("continueButton", dataLoad.getProperty("continueButton"));
		this.properties.put("exceptionTurnEditingOn", dataLoad.getProperty("exceptionTurnEditingOn"));
		this.properties.put("exceptionTrackingEnabled", dataLoad.getProperty("exceptionTrackingEnabled"));
		this.properties.put("exceptionPostTracked", dataLoad.getProperty("exceptionPostTracked"));
		}
/**
 * Clicks the Add a New Course Button. If there are no courses already it does this from the home page. If there is already a course
 * the page object locates an alternative Add a new course button.
 */
	public void clickAddCourse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean itemVisible = false;
		try {
			WebElement addCourseButton = driver.findElement(By .cssSelector("input[value='" +
					this.properties.get("addNewCourseButton") +
					"']"));
			itemVisible = addCourseButton.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible) {
			WebElement addCourseButton = driver.findElement(By .cssSelector("input[value='" +
					this.properties.get("addNewCourseButton") +
					"']"));
			addCourseButton.click();
		}
		else {
			//WebElement courseTreeItemRoot = driver.findElement(By .partialLinkText(this.properties.get("coursesNavBlock")));
			WebElement courseTreeItemRoot = driver.findElement(By .partialLinkText(this.properties.get("coursesNavBlock")));
			courseTreeItemRoot.click();
			WebElement addCourseButton = driver.findElement(By .cssSelector("input[value='" +
					this.properties.get("addNewCourseButton") +
					"']"));
			addCourseButton.click();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Enters a value passed from the test into the fullname field.
 * @param fullName The Full Name value for the course is passed from the test.
 */
	public void enterFullname(String fullName) {
		WebElement fullname = driver.findElement(By .id("id_fullname"));
		fullname.sendKeys(fullName);
	}
/**
 * Enters a value passed from the test into the shortname field.
 * @param shortName The Short Name value for the course is passed from the test.
 */
	public void enterShortname(String shortName) {
		WebElement shortname = driver.findElement(By .id("id_shortname"));
		shortname.sendKeys(shortName);
	}
/**
 * Click the submit button.
 */
	public void clickSubmitButton() {
		WebElement submitCourseButton = driver.findElement(By .cssSelector("#id_submitbutton")); 
		submitCourseButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}
/**
 * Clicks the courses root tree menu item.
 */
	public void clickCoursesInTreeMenu() {
		WebElement courseTreeItemRoot = driver.findElement(By .partialLinkText("" +
				this.properties.get("coursesNavBlock") +
				""));
		courseTreeItemRoot.click();
	}
/**
 * Clicks a course item defined in the test in the tree menu.
 * @param shortName
 */
	public void clickCourseInTreeMenu(String shortName) {
		WebElement specifiedCourseTreeItem = driver.findElement(By .partialLinkText(shortName));
		specifiedCourseTreeItem.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks the turn editing on button.
 */
	public void clickTurnEditingOn() {
		WebElement turnEditingOnButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("turnEditingOn") +
				"']"));
		turnEditingOnButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks the turn editing off button.
 */
	public void clickTurnEditingOff() {
		WebElement turnEditingOnButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("turnEditingOff") +
				"']"));
		turnEditingOnButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks a course category on the course categories page from the list of available course categories.
 * @param courseCategory The link text of the course category.
 */
	public void selectCourseCategory(String courseCategory) {
		WebElement aCourseCategory = driver.findElement(By .partialLinkText(courseCategory));
		aCourseCategory.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks on the topmost X icon to delete an object.
 */
	public void clickonXDeleteIcon() {
		WebElement deleteIcon = driver.findElement(By .cssSelector("img[alt='" +
				this.properties.get("deleteIconAlt") +
				"']"));
		deleteIcon.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks the continue button.
 */
	
	public void clickContinueButton() {
		WebElement continueButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("continueButton") +
				"']"));
		continueButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
/**
 * Clicks any course link using any part or all of course name as locator
 * @param courseLinkText any or all of the Course Full Name passed from the test.
 */
	public void clickCourseLink(String courseLinkText) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement courseLink = driver.findElementByLinkText(courseLinkText);
		courseLink.click();
	}
/**
 * Clicks a course breadcrumb using all or part of the course shortname as a locator
 * @param courseShortname any or all of the Course Short Name passed from the test.
 */
	public void clickCourseBreadcrumb(String courseShortname) {
		WebElement enrolledUserBreadcrumb = driver.findElement(By .xpath("//a[contains(.,'" +
				courseShortname +
				"')]"));
		enrolledUserBreadcrumb.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}