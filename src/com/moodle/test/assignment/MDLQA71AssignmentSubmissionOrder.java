package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.Assignment;
import com.moodle.testmanager.pageObjectModel.AssignmentGrading;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * A teacher can change the order in which assignment submissions are listed.
 * 
 * TEST PRE-REQUISITES:
 * This test requires an assignment with several submissions.
 * 
 * TEST SCENARIO:
 * 1. Login as a teacher, access the assignment and follow the 'View x submitted assignments' link.
 * 2. Try sorting the submissions by first name by clicking the 'First name' heading.
 * 3. Click the 'First name' heading again and check that the submissions are now sorted in the reverse order.
 * 4. Change the submissions shown per page to 2 and click the 'Save preferences' button.
 * 5. Check that the submissions page now displays only 2 submissions.
 * 6. Try hiding one of the columns by clicking the hide icon next to a particular column heading. 
 */
public class MDLQA71AssignmentSubmissionOrder {
		//The WebDriver
		static RemoteWebDriver driver;
		//The Selenium Manager class
		static SeleniumManager sm;
		//Test Data Property Files
		//Run parameters are static and apply to all tests
		public static String runParameters = "properties/runParameters.properties";
		//Test data files.
		public static String usersTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		//Data Hashmap
		private Map<String, String> properties = new HashMap<String, String>();
		//Construct page objects e.g. Users.
		private Users user = new Users(driver);
		private Courses course = new Courses(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentGrading grading = new AssignmentGrading(driver);
		//Load test data from properties file
		public MDLQA71AssignmentSubmissionOrder(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(usersTestData));
				testData.load(new FileInputStream(courseTestData));
				testData.load(new FileInputStream(assignmentTestData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", testData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", testData.getProperty("studentUsername"));
			this.properties.put("student2Username", testData.getProperty("student2Username"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
		}
		
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
		/*
		 * PRE-REQUISITES:
		 * This test requires an assignment with several submissions.
		 */
		public void setupData() {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			
		}
		/*
		 * START OF TEST
		 */
		/*
		 * 1. Login as a teacher, access the assignment and follow the 'View x submitted assignments' link.
		 */
		//TODO
		/*
		 * 2. Try sorting the submissions by first name by clicking the 'First name' heading.
		 */
		//TODO
		/*
		 * 3. Click the 'First name' heading again and check that the submissions are now sorted in the reverse order.
		 */
		//TODO
		/*
		 * 4. Change the submissions shown per page to 2 and click the 'Save preferences' button.
		 */
		//TODO
		/*
		 * 5. Check that the submissions page now displays only 2 submissions.
		 */
		//TODO
		/*
		 * 6. Try hiding one of the columns by clicking the hide icon next to a particular column heading.
		 */
		//TODO
		/*
		 * END OF TEST
		 */
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}
}