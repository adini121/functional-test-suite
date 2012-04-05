package com.moodle.moodleinstallation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.Installation;
import com.moodle.testmanager.pageObjectModel.UsersAddNewUser;
/**
 * Installer for 2.3
 * @author tim
 */
public class Install23 {
	//TEST DATA
		//Test Data Property Files
	public static String runParameters = "properties/runParameters.properties";
	public static String usersData = "properties/data/user/Users/usersData.properties";
	//Weekly outline section
	private Map<String, String> properties = new HashMap<String, String>();
	//Load test data from properties file
	public Install23(){
		this.loadTestData();
	}
	public void loadTestData() {
		Properties testData = new Properties();
		try {
			testData.load(new FileInputStream(usersData));
		} catch (Exception e) {}
		this.properties.put("adminUser", testData.getProperty("adminUser"));
		this.properties.put("password", testData.getProperty("password"));
		this.properties.put("adminEmail", testData.getProperty("adminEmail"));
		this.properties.put("city", testData.getProperty("city"));
		this.properties.put("country", testData.getProperty("country"));
		this.properties.put("fullSiteName", testData.getProperty("fullSiteName"));
		this.properties.put("shortSiteName", testData.getProperty("shortSiteName"));
		}
	//define "driver" in a field
	static RemoteWebDriver driver;
	static SeleniumManager sm;
	//Setup webdriver for @Test methods
	@BeforeClass
	static public void automateTestSetup()throws FileNotFoundException, IOException{
		//Load properties required for test run
		Properties startupConfig = new Properties();
		startupConfig.load(new FileInputStream(runParameters));
		String gridHubURL = startupConfig.getProperty("gridHubURL");
		String browserType = startupConfig.getProperty("browserType");
		String moodleHomePage = startupConfig.getProperty("moodleHomePage");
		//Call setup method
		sm = new SeleniumManager();
		sm.startRemotes(gridHubURL, browserType);
		driver = sm.getRemoteDriver();
		driver.get(moodleHomePage);
	}	
	@Test
	public void install (){
		Installation install = new Installation(driver);
		install.clickContinue();
		install.clickContinue();
		install.clickContinue();
		UsersAddNewUser user = new UsersAddNewUser(driver);
		user.enterPassword(this.properties.get("password"));
		user.enterEmail(this.properties.get("adminEmail"));
		user.enterCity(this.properties.get("city"));
		user.enterCountry(this.properties.get("country"));
		user.clickUpdateProfile();
		install.enterFullSiteName(this.properties.get("fullSiteName"));
		install.enterShortSiteName(this.properties.get("shortSiteName"));
		install.clickSaveChanges();
	}
	
	//Tear Down webdriver for @Test methods
	@AfterClass
	static public void Quit() {
		//End Webdriver Session by calling teardown method
		sm.teardown();
	}		
}
