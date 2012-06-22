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
import com.moodle.testmanager.pageObjectModel.AssignmentAddAssignment;
import com.moodle.testmanager.pageObjectModel.AssignmentAddSubmission;
import com.moodle.testmanager.pageObjectModel.AssignmentSubmissionComments;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * A teacher can enable submission comments when online text is enabled
 * 
 * TEST PRE-REQUISITES:
 * An assignment exists with online text enabled and 'Allow submission comments' set to Yes.
 * 
 * TEST SCENARIO:
 * 1. Login as a student and access the assignment.
 * 2. Create a submission, add a submission comment and save the comment.
 * 3. Delete the submission comment, and enter a new comment, and save your changes.
 * 4. Check that the changes have been saved.
 */
public class MDLQA1464TeacherSubmissionEnabledOnlineTextEnabled {
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
		private Courses course = new Courses(driver);
		private CoursesAddAnActivity addActivity = new CoursesAddAnActivity(driver);
		private AssignmentAddAssignment addAssignment = new AssignmentAddAssignment(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentAddSubmission submission = new AssignmentAddSubmission(driver);
		private AssignmentSubmissionComments submissionComments = new AssignmentSubmissionComments(driver);
		//Load test data from properties file
		public MDLQA1464TeacherSubmissionEnabledOnlineTextEnabled(){
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
			this.properties.put("courseShortname", testData.getProperty("courseShortname"));
			this.properties.put("MDLQA1464OutlineSection", testData.getProperty("MDLQA1464OutlineSection"));
			this.properties.put("MDLQA1464AssignmentName", testData.getProperty("MDLQA1464AssignmentName"));
			this.properties.put("MDLQA1464AssignmentText", testData.getProperty("MDLQA1464AssignmentText"));
			this.properties.put("MDLQA1464MDLQA1464OnlineTextSubmission", testData.getProperty("MDLQA1464OnlineTextSubmission"));
			this.properties.put("MDLQA1464StudentSubmissionComment", testData.getProperty("MDLQA1464StudentSubmissionComment"));
			this.properties.put("MDLQA1464StudentSubmissionCommentEdit", testData.getProperty("MDLQA1464StudentSubmissionCommentEdit"));
		}
		/*
		 * START OF TEST
		 * Setup webdriver for @Test methods
		 */ 
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
			//sm.startRemotes(gridHubURL, browserType);
			//sm.startChromeDriver(chromeDriverLocation);
			sm.startFirefoxDriver();
			//driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
			driver = sm.getFirefoxDriver();
			driver.get(moodleHomePage);
		}
		/*
		 * TEST PRE-REQUISITES:
		 * An assignment exists with file submissions enabled.
		 */
		@Test
		public void createAssignment() {
			//Login Teacher
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Navigate to course and turn editing on
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Add the Assignment activity
			addActivity.selectAssignment(this.properties.get("MDLQA1464OutlineSection"));
			//Setup Assigment activity
			addAssignment.enterAssignmentName(this.properties.get("MDLQA1464AssignmentName"));
			addAssignment.enterAssignmentDescription(this.properties.get("MDLQA1464AssignmentText"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.selectSubmissionCommentsYes();
			addAssignment.clickSaveAndDisplay();
			//Log Teacher out
			user.selectLogout();
		}
		/*
		 * 1. Login as a student and access the assignment.
		 */
		@Test
		public void loginStudentAccessAssignment() {
			//Login as student
			user.selectLoginLink();
			user.enterUsername(this.properties.get("studentUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Click Course Link
			course.clickCourseLink(this.properties.get("courseName"));
			//Access the assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1464AssignmentName"));
			submission.assertSubmissionPage(this.properties.get("MDLQA1464AssignmentName"));
		}
		/*
		 * 2. Create a submission, add a submission comment and save the comment.
		 */
		@Test
		public void addSubmissionComment() throws Exception {
			//Student adds an online text submission.
			assignment.clickButtonAddOrEditSubmission();
			submission.clickCheckboxSubmissionStatement();
			submission.enterOnlineText(this.properties.get("MDLQA1464OnlineTextSubmission"));
			submission.clickButtonSaveChanges();
			//Enter a submission comment.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.enterTextSubmissionComments(this.properties.get("MDLQA1464StudentSubmissionComment"));
			//Cancel it.
			submissionComments.clickLinkCancelComment();
			//Check that it HASN'T been saved. 
			submissionComments.assertCommentNotSaved(this.properties.get("MDLQA1464StudentSubmissionComment"));
			//Enter the submission comment again.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.enterTextSubmissionComments(this.properties.get("MDLQA1464StudentSubmissionComment"));
			//This time save it.
			submissionComments.clickLinkSaveComment();
			//Go back to course
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			//Access the assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1464AssignmentName"));
			submission.assertSubmissionPage(this.properties.get("MDLQA1464AssignmentName"));
			//Check that this time it has been saved.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.assertCommentSaved(this.properties.get("MDLQA1464StudentSubmissionComment"));
			//submissionComments.clickLinkSubmissionComments();
		}
		/*
		 * 3. Delete the submission comment, and enter a new comment, and save your changes.
		 */
		@Test
		public void editSubmissionComment() throws Exception {
			//Delete the old comment.
			//submissionComments.clickLinkSubmissionComments();
			submissionComments.clickLinkDeleteSubmissionCommentAndConfirm("MDLQA1464StudentSubmissionComment");
			//Go back to course
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			//Access the assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1464AssignmentName"));
			submission.assertSubmissionPage(this.properties.get("MDLQA1464AssignmentName"));
			//Check that it's been deleted.
			submissionComments.assertCommentNotSaved(this.properties.get("MDLQA1464StudentSubmissionComment"));
			//Enter a new comment and save it.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.enterTextSubmissionComments(this.properties.get("MDLQA1464StudentSubmissionCommentEdit"));
			submissionComments.clickLinkSaveComment();
		}
		/*
		 * 4. Check that the changes have been saved.
		 */
		@Test
		public void checkChangesMade() throws Exception {
			//Check that it's been saved.
			submissionComments.assertCommentSaved(this.properties.get("MDLQA1464StudentSubmissionCommentEdit"));
		}
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			//sm.teardown();
			//sm.teardownChrome();
			sm.teardownFirefox();
		}
		//
		//END OF TEST
		//
}