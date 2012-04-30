/**
 * Assign front page roles in Moodle
 * TEST SCENARIO:
 * Add one user to the teacher front page role and one user to the student front page role.
 * 1) Login to Moodle via RemoteWebDriver using a google Chrome browser as the default admin user.
 * 2) Navigate to Front Page Roles via the admin menu.
 * 3) Assign a user to the teacher role.
 * 4) Assign a user to the student role.
 * 5) logout.
 */
package com.moodle.datacreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.BlockSettings;
import com.moodle.testmanager.pageObjectModel.FrontPageRoles;
import com.moodle.testmanager.pageObjectModel.Users;

public class MDLQADT3AssignFrontPageRoles {	
	//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		//Weekly outline section
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQADT3AssignFrontPageRoles(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(usersData));
			} catch (Exception e) {}
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("teacherFirstname", testData.getProperty("teacherFirstname"));
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("student2Firstname", testData.getProperty("student2Firstname"));
			this.properties.put("student3Firstname", testData.getProperty("student3Firstname"));
			this.properties.put("student4Firstname", testData.getProperty("student4Firstname"));
			this.properties.put("student5Firstname", testData.getProperty("student5Firstname"));
			this.properties.put("student6Firstname", testData.getProperty("student6Firstname"));
			this.properties.put("student7Firstname", testData.getProperty("student7Firstname"));
			this.properties.put("student8Firstname", testData.getProperty("student8Firstname"));
			this.properties.put("student9Firstname", testData.getProperty("student9Firstname"));
			this.properties.put("student10Firstname", testData.getProperty("student10Firstname"));
			this.properties.put("student11Firstname", testData.getProperty("student11Firstname"));
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
			//String chromeDriverLocation = startupConfig.getProperty("chromeDriverLocation");
			//Call setup method
			sm = new SeleniumManager();
			sm.startRemotes(gridHubURL, browserType);
			//sm.startChromeDriver(chromeDriverLocation);
			driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
			driver.get(moodleHomePage);
		}
		//Login as default Admin user
		@Test
		public void login(){
			Users userLogin = new Users(driver);
			userLogin.selectLoginLink();
			userLogin.enterUsername(this.properties.get("adminUser"));
			userLogin.enterPassword(this.properties.get("password"));
			userLogin.clickLoginButton();
		}
		//Add a user to the teacher role
		//@Test
		public void assignTeacherRole(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectTeacherRole();
			frontPageRoles.selectPotentialUser(this.properties.get("teacherFirstname"));
			frontPageRoles.selectAdd();
		}
		//Add the first user to the student role
	//	@Test
		public void assignStudentRole(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("studentFirstname"));
			frontPageRoles.selectAdd();
		}
		//Add the second user to the student role
	//	@Test
		public void assignStudent2Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student2Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent3Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student3Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent4Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student4Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent5Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student5Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent6Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student6Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent7Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student7Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent8Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student8Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent9Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student9Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent10Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student10Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent11Role(){
			BlockSettings settingsMenu = new BlockSettings(driver);	
			settingsMenu.navigateTreeMenuToFrontPageRoles();
			FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student11Firstname"));
			frontPageRoles.selectAdd();
		}
		//Log admin user out
		@Test
		public void logout() {
			Users userLogout = new Users(driver);
			userLogout.selectLogout();
		}
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
			//sm.teardownChrome();
		}	
}