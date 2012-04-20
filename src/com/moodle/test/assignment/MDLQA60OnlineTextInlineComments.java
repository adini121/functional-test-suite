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
import com.moodle.testmanager.pageObjectModel.AssignmentAddSubmission;
import com.moodle.testmanager.pageObjectModel.AssignmentGrading;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Users;
/*
 * DESCRIPTION:
 * In an Online text assignment, a teacher can add inline comments
 * 
 * PRE-REQUISITES:
 * This test requires an assignment with Online Text enabled and submitted assignments.
 * 
 * TEST SCENARIO:
 * 1. Login as a teacher and access the assignment.
 * 2. Follow the 'View x submitted assignments' link and click a Grade link.
 * 3. Add an inline comment then click the 'Save changes' button.
 * 4. Check that the 'Last modified (Teacher)' date is correctly displayed for the assignment just graded and the link 
 * text in the status column has changed from 'Grade' to 'Update'.
 */
public class MDLQA60OnlineTextInlineComments {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property File
		public static String runParameters = "properties/runParameters.properties";
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Users user = new Users(driver);
		private Courses courses = new Courses(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentGrading grading = new AssignmentGrading(driver);
		private AssignmentAddSubmission submission = new AssignmentAddSubmission(driver);
		private Toolkit screenCapture = new Toolkit(driver);
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA60OnlineTextInlineComments(){
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
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("studentSurname", testData.getProperty("studentSurname"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));			
			this.properties.put("MDLQA59AssignmentName", testData.getProperty("MDLQA59AssignmentName"));
			this.properties.put("MDLQA59StudentEditedSubmissionText", testData.getProperty("MDLQA59StudentEditedSubmissionText"));
			this.properties.put("MDLQA60SubmissionStatusSubmitted", testData.getProperty("MDLQA60SubmissionStatusSubmitted"));
			this.properties.put("MDLQA60SubmissionStatusGraded", testData.getProperty("MDLQA60SubmissionStatusGraded"));
			this.properties.put("MDLQA60FeedbackComments", testData.getProperty("MDLQA60FeedbackComments"));
			this.properties.put("MDLQA60Grade", testData.getProperty("MDLQA60Grade"));
			this.properties.put("MDLQA60ScreenCaptureLocation", testData.getProperty("MDLQA60ScreenCaptureLocation"));
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
		//PRE-REQUISITES
		//Re-use data from MDLQA-59
		//
		//TEST
		//
		// 1. Login as a teacher and access the assignment.
		@Test
		public void loginAsTeacherAccessAssignment() {
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			courses.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA59AssignmentName"));
			grading.assertGradingSummaryPage(this.properties.get("MDLQA59AssignmentName"));
		}
		// 2. Follow the 'View x submitted assignments' link and click a Grade link.
		@Test
		public void viewXSubmittedAssignments() {
			assignment.clickButtonGradeAssignment();
			grading.clickLinkSubmittedForGrading(this.properties.get("studentFirstname") + " " + this.properties.get("studentSurname"));
			submission.assertSubmissionOnlineText(this.properties.get("MDLQA59StudentEditedSubmissionText"));
			grading.assertSubmissionStatus(this.properties.get("MDLQA60SubmissionStatusSubmitted"));
		}
		// 3. Add an inline comment then click the 'Save changes' button.
		@Test
		public void addInlineComment() {
			grading.enterTextStandardGrade(this.properties.get("MDLQA60Grade"));
			grading.enterFeedbackComments(this.properties.get("MDLQA60FeedbackComments"));
			grading.clickButtonSaveChanges();
		}
		// 4. Check that the 'Last modified (Teacher)' date is correctly displayed for the assignment just graded and the link 
		// text in the status column has changed from 'Grade' to 'Update'.
		@Test
		public void checkLastMod() throws IOException {
			screenCapture.takeScreenshotWithGivenLocationAndName(this.properties.get("MDLQA60ScreenCaptureLocation"));
		}
		@Test
		public void linkTextChanged() {
			grading.assertSubmissionStatus(this.properties.get("MDLQA60SubmissionStatusGraded"));
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