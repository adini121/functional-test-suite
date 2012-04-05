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
/**
 * This is the page object model for the Navigation Block. All interaction with the navigation block is contained in here.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class UsersEnrolled {
	//Internationalization file location
	public static String userEnrolledData = "properties/data/static/usersEnrolled.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public UsersEnrolled(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties usersEnrolled = new Properties();
		try {
			usersEnrolled.load(new FileInputStream(userEnrolledData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("buttonEnrolUsers", usersEnrolled.getProperty("buttonEnrolUsers"));
		this.properties.put("buttonEnrol", usersEnrolled.getProperty("buttonEnrol"));
		this.properties.put("buttonFinishEnrolUsers", usersEnrolled.getProperty("buttonFinishEnrolUsers"));
		this.properties.put("buttonConfirmRemove", usersEnrolled.getProperty("buttonConfirmRemove"));
		this.properties.put("buttonAssignTeacherRole", usersEnrolled.getProperty("buttonAssignTeacherRole"));
		this.properties.put("linkEnrolledUsers", usersEnrolled.getProperty("linkEnrolledUsers"));
	}
/**
 * Click the first enrol user button on the page
 */
	public void clickEnrolUserButton() {
		WebElement enrolUsersButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("buttonEnrolUsers") +
				"']"));
		enrolUsersButton.click();
	}
/**
 * Click the enrol button adjacent to the user to enrol based upon a concatenation of the First Name and Surname.
 * @param userToEnrolFirstName The text value for the users first name, the value is passed from the test.
 * @param userToEnrolSurname The text value for the users surname, the value is passed from the test.
 */
	public void selectUserToEnrol(String userToEnrolFirstName, String userToEnrolSurname) {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement e = driver.findElement(By .xpath("//div[contains(.,'" +
					userToEnrolFirstName +
					" " +
					userToEnrolSurname +
					"')]/*/input[@value='" +
					this.properties.get("buttonEnrol") +
					"']"));
			itemVisible = e.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
		WebElement enrolButtonUserContext = driver.findElement(By .xpath("//div[contains(.,'" +
				userToEnrolFirstName +
				" " +
				userToEnrolSurname +
				"')]/*/input[@value='" +
				this.properties.get("buttonEnrol") +
				"']"));
		enrolButtonUserContext.click();
		}
		else {
			WebElement potentialUser = driver.findElement(By.xpath(".//*[@id='addselect']/optgroup/option[contains(.,'" +
					userToEnrolFirstName +
					" " +
					userToEnrolSurname +
					"')]"));
			potentialUser.click();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
/**
 * Clicks the finish enrolling button.
 */
	public void clickFinishEnrollingButton() {
		boolean itemVisible = false;
		try{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebElement finishEnrollingUsersButton = driver.findElement(By .cssSelector("input[value='Finish enrolling users']"));
			itemVisible = finishEnrollingUsersButton.isDisplayed();
		}
		catch (NoSuchElementException ex){}
		if (itemVisible){
			WebElement finishEnrollingUsersButton = driver.findElement(By .cssSelector("input[value='Finish enrolling users']"));
			finishEnrollingUsersButton.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		else{
			WebElement addButton = driver.findElement(By .id("add"));
			addButton.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}
/**
 * Clicks the Enrolled users breadcrumb
 */
	public void clickEnrolledUsersBreadcrumb() {
		WebElement enrolledUserBreadcrumb = driver.findElement(By .cssSelector("a[title='" +
				this.properties.get("linkEnrolledUsers") +
				"']"));
		enrolledUserBreadcrumb.click();
	}
/**
 * Clicks the 'X' to remove a student roll based upon a concatenation of the First Name and Surname.
 * @param usersFirstName The text value for the users first name, the value is passed from the test.
 * @param usersSurname The text value for the users surname, the value is passed from the test.
 */
	public void removeStudentRole(String usersFirstName, String usersSurname) {
		WebElement removeStudentRole = driver.findElement(By .xpath("//tr[contains(.,'" +
				usersFirstName +
				" " +
				usersSurname +
				"')]/*/div[@class='roles']/*/a[@class='unassignrolelink']"));
		removeStudentRole.click();
	}
/**
 * Clicks remove in the confirm remove dialog
 */
	public void confirmRemove() {
		WebElement removeButton = driver.findElement(By .cssSelector("input[value='" +
				this.properties.get("buttonConfirmRemove") +
				"']"));
		removeButton.click();
	}
/**
 * Clicks add a course role based upon a concatenation of the First Name and Surname.
 * @param firstName The text value for the users first name, the value is passed from the test.
 * @param surname The text value for the users surname, the value is passed from the test.
 */
	public void clickAddRole(String firstName, String surname) {
		WebElement assignRoles = driver.findElement(By .xpath("//tr[contains(.,'" +
				firstName +
				" " +
				surname +
				"')]/*/div[@class='addrole']/a[@class='assignrolelink']"));
		assignRoles.click();
	}
/**
 * Clicks the teacher button to assign the teacher role to the user
 */
	public void assignTeacherRole() {
		WebElement assignTeacherRole = driver.findElement(By .xpath("//input[@value='" +
				this.properties.get("buttonAssignTeacherRole") +
				"']"));
		assignTeacherRole.click();
	}
}