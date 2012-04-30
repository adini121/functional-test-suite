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
		this.properties.put("student3Email", testData.getProperty("student3Email"));
		this.properties.put("student3Firstname", testData.getProperty("student3Firstname"));
		this.properties.put("student3Username", testData.getProperty("student3Username"));
		this.properties.put("student3Surname", testData.getProperty("student3Surname"));
		this.properties.put("student4Email", testData.getProperty("student4Email"));
		this.properties.put("student4Firstname", testData.getProperty("student4Firstname"));
		this.properties.put("student4Username", testData.getProperty("student4Username"));
		this.properties.put("student4Surname", testData.getProperty("student4Surname"));
		this.properties.put("student5Email", testData.getProperty("student5Email"));
		this.properties.put("student5Firstname", testData.getProperty("student5Firstname"));
		this.properties.put("student5Username", testData.getProperty("student5Username"));
		this.properties.put("student5Surname", testData.getProperty("student5Surname"));
		this.properties.put("student6Email", testData.getProperty("student6Email"));
		this.properties.put("student6Firstname", testData.getProperty("student6Firstname"));
		this.properties.put("student6Username", testData.getProperty("student6Username"));
		this.properties.put("student6Surname", testData.getProperty("student6Surname"));
		this.properties.put("student7Email", testData.getProperty("student7Email"));
		this.properties.put("student7Firstname", testData.getProperty("student7Firstname"));
		this.properties.put("student7Username", testData.getProperty("student7Username"));
		this.properties.put("student7Surname", testData.getProperty("student7Surname"));
		this.properties.put("student8Email", testData.getProperty("student8Email"));
		this.properties.put("student8Firstname", testData.getProperty("student8Firstname"));
		this.properties.put("student8Username", testData.getProperty("student8Username"));
		this.properties.put("student8Surname", testData.getProperty("student8Surname"));
		this.properties.put("student9Email", testData.getProperty("student9Email"));
		this.properties.put("student9Firstname", testData.getProperty("student9Firstname"));
		this.properties.put("student9Username", testData.getProperty("student9Username"));
		this.properties.put("student9Surname", testData.getProperty("student9Surname"));
		this.properties.put("student10Email", testData.getProperty("student10Email"));
		this.properties.put("student10Firstname", testData.getProperty("student10Firstname"));
		this.properties.put("student10Username", testData.getProperty("student10Username"));
		this.properties.put("student10Surname", testData.getProperty("student10Surname"));
		this.properties.put("student11Email", testData.getProperty("student11Email"));
		this.properties.put("student11Firstname", testData.getProperty("student11Firstname"));
		this.properties.put("student11Username", testData.getProperty("student11Username"));
		this.properties.put("student11Surname", testData.getProperty("student11Surname"));
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
		//String chromeDriverLocation = startupConfig.getProperty("chromeDriverLocation");
		//Call setup method
		sm = new SeleniumManager();
		//sm.startChromeDriver(chromeDriverLocation);
		sm.startRemotes(gridHubURL, browserType);
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
		public void addThirdStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student3Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student3Firstname"));
			addNewUser.enterSurname(this.properties.get("student3Surname"));
			addNewUser.enterEmail(this.properties.get("student3Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addFourthStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student4Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student4Firstname"));
			addNewUser.enterSurname(this.properties.get("student4Surname"));
			addNewUser.enterEmail(this.properties.get("student4Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addFifthStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student5Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student5Firstname"));
			addNewUser.enterSurname(this.properties.get("student5Surname"));
			addNewUser.enterEmail(this.properties.get("student5Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addSixthStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student6Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student6Firstname"));
			addNewUser.enterSurname(this.properties.get("student6Surname"));
			addNewUser.enterEmail(this.properties.get("student6Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addSeventhStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student7Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student7Firstname"));
			addNewUser.enterSurname(this.properties.get("student7Surname"));
			addNewUser.enterEmail(this.properties.get("student7Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addEighthStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student8Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student8Firstname"));
			addNewUser.enterSurname(this.properties.get("student8Surname"));
			addNewUser.enterEmail(this.properties.get("student8Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addNinthStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student9Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student9Firstname"));
			addNewUser.enterSurname(this.properties.get("student9Surname"));
			addNewUser.enterEmail(this.properties.get("student9Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addTenthStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student10Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student10Firstname"));
			addNewUser.enterSurname(this.properties.get("student10Surname"));
			addNewUser.enterEmail(this.properties.get("student10Email"));
			addNewUser.enterCity(this.properties.get("city"));
			addNewUser.enterCountry(this.properties.get("country"));
			//Re-use submit button object from Courses
			Courses submitUser = new Courses(driver);
			submitUser.clickSubmitButton();			
	}
		@Test
		public void addEleventhStudent(){
			//Navigate to add new user page via settings menu
			BlockSettings settingsMenu = new BlockSettings(driver);
			settingsMenu.navigateTreeMenuToAddNewUserPage();
			//Add a new user
			UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
			addNewUser.enterUsername(this.properties.get("student11Username"));
			addNewUser.enterPassword(this.properties.get("password"));
			addNewUser.enterFirstName(this.properties.get("student11Firstname"));
			addNewUser.enterSurname(this.properties.get("student11Surname"));
			addNewUser.enterEmail(this.properties.get("student11Email"));
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
			//sm.teardownChrome();
	}		
}