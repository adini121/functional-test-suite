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
import com.moodle.seleniumutils.Toolkit;
import com.moodle.testmanager.pageObjectModel.Assignment;
import com.moodle.testmanager.pageObjectModel.AssignmentAddAssignment;
import com.moodle.testmanager.pageObjectModel.AssignmentAddSubmission;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.Users;
/*
 * DESCRIPTION:
 * 
 * TEST PRE-REQUISITES:
 * This test requires and assignment with Online Text enabled.
 * 
 * TEST SCENARIO:
 * 1. Login as a student and access the assignment.
 * 2. Click the 'Add submission' button, add some text, then click the 'Save changes' button.
 * 3. Click the 'Edit my submission' button again, edit the text, then click the 'Save changes' button.
 * 4. Check that the changes have been saved and the latest submission date and time is displayed.
 * 5. Check that the submission date and time is also displayed on the assignment index page.
 */
public class MDLQA59OnlineTextAddEdit {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		private Users user = new Users(driver);
		private Courses courses = new Courses(driver);
		private CoursesAddAnActivity addActivity = new CoursesAddAnActivity(driver);
		private AssignmentAddAssignment addAssignment= new AssignmentAddAssignment(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentAddSubmission submission = new AssignmentAddSubmission(driver);
		private Toolkit screenCapture = new Toolkit(driver);
		//Load test data from properties file
		public MDLQA59OnlineTextAddEdit(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(userTestData));
				testData.load(new FileInputStream(courseTestData));
				testData.load(new FileInputStream(assignmentTestData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", testData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", testData.getProperty("studentUsername"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("MDLQA59OutlineSection", testData.getProperty("MDLQA59OutlineSection"));
			this.properties.put("MDLQA59AssignmentName", testData.getProperty("MDLQA59AssignmentName"));
			this.properties.put("MDLQA59AssignmentDescription", testData.getProperty("MDLQA59AssignmentDescription"));
			this.properties.put("MDLQA59StudentSubmissionText", testData.getProperty("MDLQA59StudentSubmissionText"));
			this.properties.put("MDLQA59StudentEditedSubmissionText", testData.getProperty("MDLQA59StudentEditedSubmissionText"));
			this.properties.put("MDLQA59ScreenCaptureLocation", testData.getProperty("MDLQA59ScreenCaptureLocation"));
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
			//sm.startRemotes(gridHubURL, browserType);
			//sm.startChromeDriver(chromeDriverLocation);
			sm.startFirefoxDriver();
			//driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
			driver = sm.getFirefoxDriver();
			driver.get(moodleHomePage);
		}
		//PRE-REQUISITES
		//Create Assignment with online text enabled
		@Test
		public void loginAsTeacherAndSetupAssignment() {
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			courses.clickCourseLink(this.properties.get("courseName"));
			courses.clickTurnEditingOn();
			addActivity.selectAssignment(this.properties.get("MDLQA59OutlineSection"));
			addAssignment.enterAssignmentName(this.properties.get("MDLQA59AssignmentName"));
			addAssignment.enterAssignmentDescription(this.properties.get("MDLQA59AssignmentDescription"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.selectFeedbackCommentsYes();
			addAssignment.clickSaveAndDisplay();
			user.selectLogout();
		}
		//TEST
		//1. Login as student and access the assignment
		@Test
		public void loginAsStudentAccessAssingment() {
			user.selectLoginLink();
			user.enterUsername(this.properties.get("studentUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			courses.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA59AssignmentName"));
			submission.assertSubmissionPage(this.properties.get("MDLQA59AssignmentName"));
		}
		@Test
		//2. Click the 'Add submission' button, add some text, then click the 'Save changes' button.
		public void addSubmission() throws Exception {
			assignment.clickButtonAddOrEditSubmission();
			submission.clickCheckboxSubmissionStatement();
			submission.enterOnlineText(this.properties.get("MDLQA59StudentSubmissionText"));
			submission.clickButtonSaveChanges();
			submission.assertSubmissionPage(this.properties.get("MDLQA59AssignmentName"));
			submission.assertSubmissionOnlineText(this.properties.get("MDLQA59StudentSubmissionText"));
		}
		@Test
		//3. Click the 'Edit my submission' button again, edit the text, then click the 'Save changes' button.
		public void editSubmission() {
			assignment.clickButtonAddOrEditSubmission();
			submission.clickCheckboxSubmissionStatement();
			submission.enterOnlineText(this.properties.get("MDLQA59StudentEditedSubmissionText"));
			submission.clickButtonSaveChanges();
		}
		@Test
		//4. Check that the changes have been saved and the latest submission date and time is displayed.
		public void verifyChangesSaved() throws Exception {
			submission.assertSubmissionPage(this.properties.get("MDLQA59AssignmentName"));
			submission.assertSubmissionOnlineText(this.properties.get("MDLQA59StudentSubmissionText") + this.properties.get("MDLQA59StudentEditedSubmissionText"));
		}
		@Test
		//5. Check that the submission date and time is also displayed on the assignment index page.
		public void verifySubmissionDateAndTime() throws IOException {
			screenCapture.takeScreenshotWithGivenLocationAndName(this.properties.get("MDLQA59ScreenCaptureLocation"));
			user.selectLogout();
		}
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			//sm.teardownChrome();
			//sm.teardown();
			sm.teardownFirefox();
		}
		//
		//END OF TEST
		//
}