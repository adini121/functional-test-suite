package com.moodle.testmanager.pageObjectModel;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is the page object model for Front Page Roles.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FrontPageRoles {
	//Internationalization file location
	public static String frontPageRolesData = "properties/data/static/frontPageRoles.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 * loadObjectData constructs internationalization layer in the context of this page object.
 */
	public FrontPageRoles(RemoteWebDriver driver) {
		this.driver = driver;
		this.loadObjectData();
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData() {
		Properties frontPageRoles = new Properties();
		try {
			frontPageRoles.load(new FileInputStream(frontPageRolesData));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("teacherRole", frontPageRoles.getProperty("teacherRole"));
		this.properties.put("studentRole", frontPageRoles.getProperty("studentRole"));
	}
/**
 * Select the teacher role from the front page roles page
 */
	public void selectTeacherRole() {
		WebElement teacherRole = driver.findElement(By .xpath("//a[contains(.,'" +
				this.properties.get("teacherRole") +
				"')]"));
		teacherRole.click();
	}
/**
 * Select the student role from the front page roles page
 */
	public void selectStudentRole() {
		WebElement studentRole = driver.findElement(By .xpath("//a[contains(.,'" +
				this.properties.get("studentRole") +
				"')]"));
		studentRole.click();
	}
/**
 * Select a potential user.
 * @param potentialUserName The first name of the user. Will only work if the firts name is unique.
 */
	public void selectPotentialUser(String potentialUserName) {
		WebElement potentialUser = driver.findElement(By.xpath(".//*[@id='addselect']/optgroup/option[contains(.,'" +
				potentialUserName +
				"')]"));
		potentialUser.click();
	}
/**
 * Click on the Add button
 */
	public void selectAdd() {
		WebElement addButton = driver.findElement(By .id("add"));
		addButton.click();
	}
/**
 * Click on the Remove button
 */
	public void selectRemove() {
		WebElement removeButton = driver.findElement(By .id("remove"));
		removeButton.click();
	}
}