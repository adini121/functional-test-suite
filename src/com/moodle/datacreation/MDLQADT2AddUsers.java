package com.moodle.datacreation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.UsersAddNewUser;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Users;
import com.moodle.testmanager.pageObjectModel.BlockSettings;
/**
 * Add Users To Moodle
 * TEST SCENARIO:
 * Test that ads two users to Moodle. 1 Teacher and 1 Student.
 * 1) Login to Moodle via RemoteWebDriver using a google Chrome browser as the default admin user.
 * 2) Navigate to add user via the settings menu.
 * 3) Add a teacher.
 * 4) Navigate to add user via the settings menu.
 * 5) Add a Student.
 */
public class MDLQADT2AddUsers {
	//TEST DATA
	//Test Data Property Files
	public static String runParameters = "properties/runParameters.properties";
	public static String usersData = "properties/data/user/Users/usersData.properties";
	//Weekly outline section
	private Map<String, String> properties = new HashMap<String, String>();
	//Load test data from properties file
	public MDLQADT2AddUsers(){
		this.loadTestData();
	}
	public void loadTestData() {
		Properties testData = new Properties();
		try {
			testData.load(new FileInputStream(usersData));
		} catch (Exception e) {}
		this.properties.put("adminUser", testData.getProperty("adminUser"));
		this.properties.put("city", testData.getProperty("city"));
		this.properties.put("country", testData.getProperty("country"));
		this.properties.put("password", testData.getProperty("password"));
		this.properties.put("studentEmail", testData.getProperty("studentEmail"));
		this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
		this.properties.put("studentUsername", testData.getProperty("studentUsername"));
		this.properties.put("studentSurname", testData.getProperty("studentSurname"));
		this.properties.put("student2Email", testData.getProperty("student2Email"));
		this.properties.put("student2Firstname", testData.getProperty("student2Firstname"));
		this.properties.put("student2Username", testData.getProperty("student2Username"));
		this.properties.put("student2Surname", testData.getProperty("student2Surname"));
		this.properties.put("teacherEmail", testData.getProperty("teacherEmail"));
		this.properties.put("teacherUsername", testData.getProperty("teacherUsername"));
		this.properties.put("teacherFirstname", testData.getProperty("teacherFirstname"));
		this.properties.put("teacherSurname", testData.getProperty("teacherSurname"));
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
		//Login as default Admin user
		@Test
		public void login(){
			Users userLogin = new Users(driver);
			userLogin.selectLoginLink();
			userLogin.enterUsername(this.properties.get("adminUser"));
			userLogin.enterPassword(this.properties.get("password"));
			userLogin.clickLoginButton();
	}
		@Test
		public void addTeacher() throws Exception{
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();		
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("teacherUsername"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("teacherFirstname"));
			addNewUser.enterSurname(this.properties.get("teacherSurname"));
			addNewUser.enterEmail(this.properties.get("teacherEmail"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();
	}
		@Test
		public void addFirstStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("studentUsername"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("studentFirstname"));
			addNewUser.enterSurname(this.properties.get("studentSurname"));
			addNewUser.enterEmail(this.properties.get("studentEmail"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addSecondStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student2Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student2Firstname"));
			addNewUser.enterSurname(this.properties.get("student2Surname"));
			addNewUser.enterEmail(this.properties.get("student2Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		//Log admin user out
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