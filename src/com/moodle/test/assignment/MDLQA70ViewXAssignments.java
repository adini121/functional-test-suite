package com.moodle.test.assignment;

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
import com.moodle.testmanager.pageObjectModel.Assignment;
import com.moodle.testmanager.pageObjectModel.AssignmentGrading;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * A 'View x submitted assignments' link informs teachers of the number of assignments submitted to-date 
 * 
 * TEST PRE-REQUISITES:
 * This test requires an assignment with file submissions enabled. Two students have already submitted their assignments.
 * 
 * TEST SCENARIO:
 * 1. Login as a teacher and check that the link now states 'View 2 submitted assignments'. 
 */
public class MDLQA70ViewXAssignments {
		//The WebDriver
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String usersTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		private Users user = new Users(driver);
		private Courses course = new Courses(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentGrading grading = new AssignmentGrading(driver);
		//Load test data from properties file
		public MDLQA70ViewXAssignments(){
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
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("MDLQA70AssigmentName", testData.getProperty("MDLQA70AssigmentName"));
			this.properties.put("MDLQA70NumberOfSubmissions", testData.getProperty("MDLQA70NumberOfSubmissions"));
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
			String browserType = startupConfig.getProperty("browserType");
			String moodleHomePage = startupConfig.getProperty("moodleHomePage");
		//Call setup method
			sm = new SeleniumManager();
			sm.startRemotes(gridHubURL, browserType);
			driver = sm.getRemoteDriver();
			driver.get(moodleHomePage);
		}
		/*
		 * 1. Login as a teacher and check that the link now states 'View 2 submitted assignments'.
		 */
		@Test
		public void teacherChecksLink() throws Exception {
			//Teacher logs in
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Teacher accesses course
			course.clickCourseLink(this.properties.get("courseName"));
			//Teacher accesses assignment
			//TODO The test won't pass without seeding the database first. See Tracker issue MDLTEST-143
			assignment.clickAssignmentLink(this.properties.get("MDLQA70AssigmentName"));
			//Check that the number of submissions is displayed on the page.
			grading.assertNumberOfSubmissions(this.properties.get("MDLQA70NumberOfSubmissions"));
			//Teacher logs out
			user.selectLogout();
		}
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}
		//
		//END OF TEST
		//
}