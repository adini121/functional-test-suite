package com.moodle.test.database;

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
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.DatabasesAddDatabase;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * 
 * TEST SCENARIO:
 * 1. 
 */
public class TestTheMessageBox {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		public static String courseData = "properties/data/user/Courses/courseData.properties";
		//Weekly outline section
		//public String outlineSection = "1";
		private Map<String, String> properties = new HashMap<String, String>();
		private Users user = new Users(driver);
		private Courses course = new Courses(driver);
		private CoursesAddAnActivity database = new CoursesAddAnActivity(driver);
		private DatabasesAddDatabase form = new DatabasesAddDatabase(driver);
		//Load test data from properties file
		public TestTheMessageBox(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(usersData));
				testData.load(new FileInputStream(courseData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", testData.getProperty("teacherUsername"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
		}
		//
		//START OF TEST
		//
		//Setup webdriver for @Test methods
		@BeforeClass
		static public void automateTestSetup()throws FileNotFoundException, IOException{
		//Load properties required for test run
			Properties startupConfig = new Properties();
			startupConfig.load(new FileInputStream(runParameters));
			String gridHubURL = startupConfig.getProperty("gridHubURL");
			//String browserType = startupConfig.getProperty("browserType");
			String browserType = startupConfig.getProperty("additionalBrowserType");
			String moodleHomePage = startupConfig.getProperty("moodleHomePage");
		//Call setup method
			sm = new SeleniumManager();
			sm.startFirefoxDriver();
			driver = sm.getFirefoxDriver();
			driver.get(moodleHomePage);
		}
		@Test
		public void test() {
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			database.selectDatabase("1");
			form.fieldDatabaseName("test");
			form.fieldIntroduction("test");
		}
		//Tear Down webdriver for @Test methods
		//@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}
		//
		//END OF TEST
		//
}