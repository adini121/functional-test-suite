/**
 * Create a test course as a pre-requisite for any testing
 */
package com.moodle.datacreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Users;

public class MDLQADT1AddCourse {
	//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String courseData = "properties/data/user/Courses/courseData.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		//Weekly outline section
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQADT1AddCourse(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(courseData));
				testData.load(new FileInputStream(usersData));
			} catch (Exception e) {}
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("courseShortname", testData.getProperty("courseShortname"));
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
	@Before
		public void login(){
		//Log in as the admin user
		Users userLogin = new Users(driver);
		userLogin.selectLoginLink();
		userLogin.enterUsername(this.properties.get("adminUser"));
		userLogin.enterPassword(this.properties.get("password"));
		userLogin.clickLoginButton();
		}
		//Test to add a course
	@Test
		public void addCourse() throws MalformedURLException {
		Courses addCourse = new Courses(driver);
		addCourse.clickAddCourse();
		addCourse.enterFullname(this.properties.get("courseName"));
		addCourse.enterShortname(this.properties.get("courseShortname"));
		addCourse.clickSubmitButton();
		}
	@After
	//Logout Method
		public void logout() {
		Users userLogout = new Users(driver);
		userLogout.selectLogout();
		}
	//Tear Down webdriver for @Test methods
	@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
		sm.teardown();
		}		
}