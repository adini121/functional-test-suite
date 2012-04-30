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
import com.moodle.testmanager.pageObjectModel.AssignmentGrading;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
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
		private CoursesAddAnActivity addActivity = new CoursesAddAnActivity(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentAddAssignment addAssignment = new AssignmentAddAssignment(driver);
		private AssignmentAddSubmission addSubmission = new AssignmentAddSubmission(driver);
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
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("studentSurname", testData.getProperty("studentSurname"));
			this.properties.put("student2Firstname", testData.getProperty("student2Firstname"));
			this.properties.put("student2Surname", testData.getProperty("student2Surname"));
			this.properties.put("student3Username", testData.getProperty("student3Username"));
			this.properties.put("student4Username", testData.getProperty("student4Username"));
			this.properties.put("student5Username", testData.getProperty("student5Username"));
			this.properties.put("student6Username", testData.getProperty("student6Username"));
			this.properties.put("student7Username", testData.getProperty("student7Username"));
			this.properties.put("student8Username", testData.getProperty("student8Username"));
			this.properties.put("student9Username", testData.getProperty("student9Username"));
			this.properties.put("student10Username", testData.getProperty("student10Username"));
			this.properties.put("student11Username", testData.getProperty("student11Username"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("MDLQA71OutlineSection", testData.getProperty("MDLQA71OutlineSection"));
			this.properties.put("MDLQA71AssignmentName", testData.getProperty("MDLQA71AssignmentName"));
			this.properties.put("MDLQA71AssignmentText", testData.getProperty("MDLQA71AssignmentText"));
			this.properties.put("MDLQA71AssignmentSubmissionA", testData.getProperty("MDLQA71AssignmentSubmissionA"));
			this.properties.put("MDLQA71AssignmentSubmissionB", testData.getProperty("MDLQA71AssignmentSubmissionB"));
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
		 * PRE-REQUISITES:
		 * This test requires an assignment with several submissions.
		 */
		//@Test
		public void setupData() {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Teacher accesses course and turns editing on.
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Teacher creates an online text assignment.
			addActivity.selectAssignment(this.properties.get("MDLQA71OutlineSection"));
			addAssignment.enterAssignmentName(this.properties.get("MDLQA71AssignmentName"));
			addAssignment.enterAssignmentDescription(this.properties.get("MDLQA71AssignmentText"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.clickSaveAndDisplay();
			//Teacher logs out
			user.selectLogout();
			//2 students submit assignment.
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionA"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student2Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
		}
		/*
		 * START OF TEST
		 */
		/*
		 * 1. Login as a teacher, access the assignment and follow the 'View x submitted assignments' link.
		 */
		@Test
		public void viewAssignments() {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Teacher accesses course and assignment.
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonGradeAssignment();
		}
		/*
		 * 2. Try sorting the submissions by first name by clicking the 'First name' heading.
		 */
		@Test
		public void firstNameSort() throws Exception {
			grading.clickLinkSortFirstName();
			grading.clickLinkSortFirstName();
			grading.assertSortOrderStudentName("r1", this.properties.get("student2Firstname"), this.properties.get("student2Surname"));
			grading.assertSortOrderStudentName("r0", this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
		}
		/*
		 * 3. Click the 'First name' heading again and check that the submissions are now sorted in the reverse order.
		 */
		@Test
		public void firstNameSortReverse() throws Exception {
			grading.clickLinkSortFirstName();
			grading.assertSortOrderStudentName("r0", this.properties.get("student2Firstname"), this.properties.get("student2Surname"));
			grading.assertSortOrderStudentName("r1", this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			//Teacher logs out
			user.selectLogout();
		}
		/*
		 * 4. Change the submissions shown per page to 10 and click the 'Save preferences' button.
		 */
		@Test
		public void moreStudentsAddSubmissions() {
			//
			user.loginToSystem(this.properties.get("student3Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student4Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student5Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student6Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student7Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student8Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student9Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student10Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student11Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonEditMySubmission();
			addSubmission.clickCheckboxSubmissionStatement();
			addSubmission.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			addSubmission.clickButtonSaveChanges();
			user.selectLogout();
		}
		@Test
		public void submissionsPerPage() {
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonGradeAssignment();
			
		}
		/*
		 * 5. Check that the submissions page now displays only 10 submissions.
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
			//sm.teardown();
			sm.teardownChrome();
		}
}