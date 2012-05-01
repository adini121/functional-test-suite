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
import com.moodle.testmanager.pageObjectModel.ReportActivityCompletion;
import com.moodle.testmanager.pageObjectModel.Assignment;
import com.moodle.testmanager.pageObjectModel.AssignmentAddAssignment;
import com.moodle.testmanager.pageObjectModel.AssignmentAddSubmission;
import com.moodle.testmanager.pageObjectModel.AssignmentGrading;
import com.moodle.testmanager.pageObjectModel.BlockNavigation;
import com.moodle.testmanager.pageObjectModel.BlockSettings;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.CoursesEditCourseSettings;
import com.moodle.testmanager.pageObjectModel.SiteAdministration;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * A teacher can set 'receive grade' as a completion condition for an assignment
 * 
 * TEST PRE-REQUISITES:
 * This test requires completion tracking to be enabled by an administrator in Site administration > Advanced features and in the course settings.
 * This test requires an assignment to have been created.
 * 
 * TEST SCENARIO:
 * 1. Login as a teacher, access the course and edit the course settings.
 * 2. Set Completion Tracking to "Enabled, control via completion and activity settings".
 * 3. Access an assignment and edit the assignment settings.
 * 4. Set the completion tracking to 'Show activity as complete when conditions are met'.
 * 5. Select 'Student must receive a grade to complete this activity'.
 * 6. Give a particular student a grade for the assignment.
 * 7. Check that the assignment is marked as complete for the student in Reports > Activity completion.
 */
public class MDLQA517TeacherReceiveGradeCompCondition {
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
		private CoursesAddAnActivity addActivity = new CoursesAddAnActivity(driver);
		private CoursesEditCourseSettings courseSettings = new CoursesEditCourseSettings(driver); 
		private Assignment assignment = new Assignment(driver);
		private AssignmentAddAssignment addAssignment = new AssignmentAddAssignment(driver);
		private AssignmentAddSubmission addSubmission = new AssignmentAddSubmission(driver);
		private AssignmentGrading grading = new AssignmentGrading(driver);
		private BlockSettings navigateSettings = new BlockSettings(driver);
		private BlockNavigation navigate = new BlockNavigation(driver);
		private SiteAdministration siteAdmin = new SiteAdministration(driver);
		private ReportActivityCompletion activityCompletionReport = new ReportActivityCompletion(driver);
		//Load test data from properties file
		public MDLQA517TeacherReceiveGradeCompCondition(){
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
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("courseShortname", testData.getProperty("courseShortname"));
			this.properties.put("MDLQA517OutlineSection", testData.getProperty("MDLQA517OutlineSection"));
			this.properties.put("MDLQA517AssignmentName", testData.getProperty("MDLQA517AssignmentName"));
			this.properties.put("MDLQA517AssignmentText", testData.getProperty("MDLQA517AssignmentText"));
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
			String chromeDriverLocation = startupConfig.getProperty("chromeDriverLocation");
		//Call setup method
			sm = new SeleniumManager();
			//sm.startRemotes(gridHubURL, browserType);
			sm.startChromeDriver(chromeDriverLocation);
			//driver = sm.getRemoteDriver();
			driver = sm.getChromeDriver();
			driver.get(moodleHomePage);
		}
		/*
		 * PRE-REQUISITES
		 */
		/*
		 * Turn completion tracking on.
		 */
		//@Test
		public void turnTrackingOn() {
			//Login as admin
			user.loginToSystem(this.properties.get("adminUser"), this.properties.get("password"));
			//Turn on completion tracking (if it's off).
			navigateSettings.treeMenuAdvancedFeatures();
			siteAdmin.turnCompletionTrackingOn();
			siteAdmin.clickSaveChanges();
			user.selectLogout();
		}
		/*
		 * Create Assignment
		 */
		//@Test
		public void createAssignment() {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Teacher accesses course and turns editing on.
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Teacher creates an online text assignment.
			addActivity.selectAssignment(this.properties.get("MDLQA517OutlineSection"));
			addAssignment.enterAssignmentName(this.properties.get("MDLQA517AssignmentName"));
			addAssignment.enterAssignmentDescription(this.properties.get("MDLQA517AssignmentText"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.clickSaveAndDisplay();
			//Teacher logs out
			user.selectLogout();
		}
		/*
		 * Student Makes a subission.
		 */
		//@Test
		public void studentSubmitsAssignment() {
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA517AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA517AssignmentText"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
		}
		/*
		 * START OF TEST
		 */
		/*
		 * 1. Login as a teacher, access the course and edit the course settings.
		 */
		@Test
		public void teacherAccessesCourse() {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Teacher accesses course and turns editing on.
			course.clickCourseLink(this.properties.get("courseName"));
			navigateSettings.navigateEditCourseSettings();
		}
		/*
		 * 2. Set Completion Tracking to "Enabled, control via completion and activity settings".
		 */
		@Test
		public void enableCompletionTracking() {
			//Enable completion tracking.
			courseSettings.selectCompletionTrackingEnabled();
			courseSettings.clickSaveChanges();
		}
		/*
		 * 3. Access an assignment and edit the assignment settings.
		 */
		@Test
		public void assignmentSettings() {
			//Access assignment and edit settings.
			assignment.clickAssignmentLink(this.properties.get("MDLQA517AssignmentName"));
			navigateSettings.navigateEditAssignmentSettings();
		}
		/*
		 * 4. Set the completion tracking to 'Show activity as complete when conditions are met'.
		 */
		@Test
		public void showActivityComplete() {
			addAssignment.selectCompletionTrackingConditionsMet();
		}
		/*
		 * 5. Select 'Student must receive a grade to complete this activity'.
		 */
		@Test
		public void criteria() {
			addAssignment.clickCheckboxStudentReceiveGrade();
			addAssignment.clickSaveAndDisplay();
		}
		/*
		 * 6. Give a particular student a grade for the assignment.
		 */
		@Test
		public void gradeAssignment() {
			assignment.clickButtonGradeAssignment();
			grading.clickLinkSubmittedForGrading(this.properties.get("studentUsername"));
			grading.enterTextStandardGrade("100");
			grading.clickButtonSaveChanges();
		}
		/*
		 * 7. Check that the assignment is marked as complete for the student in Reports > Activity completion.
		 */
		@Test
		public void checkMarkedComplete() {
			navigate.navigateReportActivityCompletion(this.properties.get("courseShortname"));
			activityCompletionReport.assertCompleted(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
		}
		/*
		 * END OF TEST
		 */
		//Tear Down webdriver for @Test methods
		//@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			//sm.teardown();
			sm.teardownChrome();
		}
}