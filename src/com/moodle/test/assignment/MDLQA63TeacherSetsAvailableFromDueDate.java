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
import com.moodle.testmanager.pageObjectModel.BlockSettings;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * A teacher can set 'Available from' and 'Due date' for an assignment
 * 
 * TEST PRE-REQUISITES:
 * This test requires an assignment with File Submissions enabled.
 * 
 * TEST SCENARIO:
 * 1. Login as a teacher, update the assignment and set an 'Available from' date.
 * 2. Login as a student and check that the 'Available from' date is displayed in the assignment and that before this date there is no upload file option.
 * 3. Login as a teacher, update the assignment and set a due date. Set 'Prevent late submissions' to Yes.
 * 4. Login as a student and check that the due date is displayed in the assignment and also in the course calendar.
 * 5. Check that after the due date there is no longer an upload file option.
 */
public class MDLQA63TeacherSetsAvailableFromDueDate {
		//The WebDriver
		static RemoteWebDriver driver;
		//The Selenium Manager class
		static SeleniumManager sm;
		//Test Data Property Files
		//Run parameters are static and apply to all tests
		public static String runParameters = "properties/runParameters.properties";
		//Test data files.
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		//Data Hashmap
		private Map<String, String> properties = new HashMap<String, String>();
		//Declare page objects.
		private Users user = new Users(driver);
		private Courses course = new Courses(driver);
		private CoursesAddAnActivity addActivity = new CoursesAddAnActivity(driver);
		private Assignment assignment = new Assignment(driver);
		private AssignmentAddAssignment addAssignment = new AssignmentAddAssignment(driver);
		private BlockSettings navigateSettingsBlock = new BlockSettings(driver);
		//Load test data from properties file
		public MDLQA63TeacherSetsAvailableFromDueDate(){
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
			this.properties.put("MDLQA63OutlineSection", testData.getProperty("MDLQA63OutlineSection"));
			this.properties.put("MDLQA63AssignmentName", testData.getProperty("MDLQA63AssignmentName"));
			this.properties.put("MDLQA63AssignmentDescription", testData.getProperty("MDLQA63AssignmentDescription"));
			this.properties.put("MDLQA63AssignmentDayFrom", testData.getProperty("MDLQA63AssignmentDayFrom"));
			this.properties.put("MDLQA63AssignmentDayTo", testData.getProperty("MDLQA63AssignmentDayTo"));
			this.properties.put("MDLQA63AssignmentMonth", testData.getProperty("MDLQA63AssignmentMonth"));
			this.properties.put("MDLQA63AssignmentYearFuture", testData.getProperty("MDLQA63AssignmentYearFuture"));
			this.properties.put("MDLQA63AssignmentYearPast", testData.getProperty("MDLQA63AssignmentYearPast"));
			this.properties.put("MDLQA63AssignmentHour", testData.getProperty("MDLQA63AssignmentHour"));
			this.properties.put("MDLQA63AssignmentMin", testData.getProperty("MDLQA63AssignmentMin"));
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
			//sm.startChromeDriver();
			driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
			driver.get(moodleHomePage);
		}
		/*
		 * PRE-REQUISITES
		 * This test requires an assignment with File Submissions enabled.
		 */
		@Test
		public void setupAssignment() {
			//Login as the teacher. 
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));			
			//Access the course and turn editing on
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Select the assignment option to add an activity.
			addActivity.selectAssignment(this.properties.get("MDLQA63OutlineSection"));
			//Setup the assignment.
			addAssignment.enterAssignmentName(this.properties.get("MDLQA63AssignmentName"));
			addAssignment.enterAssignmentDescription(this.properties.get("MDLQA63AssignmentDescription"));
			addAssignment.selectFileSubmissionsEnabledYes();
			addAssignment.clickSaveAndDisplay();
			//Teacher Logs out.
			user.selectLogout();
		}
		/*
		 * START OF TEST
		 * 1. Login as a teacher, update the assignment and set an 'Available from' date.
		 */
		@Test
		public void setAvailableFrom() {
			//Login as the teacher. 
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Access the course.
			course.clickCourseLink(this.properties.get("courseName"));
			//Access the assignment.
			assignment.clickAssignmentLink(this.properties.get("MDLQA63AssignmentName"));
			//Update the assignment and set an 'Available from' date.
			navigateSettingsBlock.navigateEditAssignmentSettings();
			//Set the Allow submissions from date into the future.
			addAssignment.selectAllowSubmissionsFrom(this.properties.get("MDLQA63AssignmentDayFrom"), 
														this.properties.get("MDLQA63AssignmentMonth"), 
														this.properties.get("MDLQA63AssignmentYearFuture"), 
														this.properties.get("MDLQA63AssignmentHour"), 
														this.properties.get("MDLQA63AssignmentMin"));
			//Set the Due date into the future.
			addAssignment.selectDueDate(this.properties.get("MDLQA63AssignmentDayTo"), 
											this.properties.get("MDLQA63AssignmentMonth"), 
											this.properties.get("MDLQA63AssignmentYearFuture"), 
											this.properties.get("MDLQA63AssignmentHour"), 
											this.properties.get("MDLQA63AssignmentMin"));
			//Set the Final date into the future.
			addAssignment.selectFinalDate(this.properties.get("MDLQA63AssignmentDayTo"), 
											this.properties.get("MDLQA63AssignmentMonth"), 
											this.properties.get("MDLQA63AssignmentYearFuture"), 
											this.properties.get("MDLQA63AssignmentHour"), 
											this.properties.get("MDLQA63AssignmentMin"));
			addAssignment.clickSaveAndDisplay();
			//Log Teacher out
			user.selectLogout();
		}
		/*
		 * 2. Login as a student and check that the 'Available from' date is displayed in the assignment and that before this date there is no upload file option.
		 */
		@Test
		public void studentChecksFromDates() throws Exception {
			//Student logs in.
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			//Student accesses course.
			course.clickCourseLink(this.properties.get("courseName"));
			//Student accesses assignment.
			assignment.clickAssignmentLink(this.properties.get("MDLQA63AssignmentName"));
			//Check file submission is not present
			assignment.assertFileSubmisisonDisabled();
			//Student logs out.
			user.selectLogout();
		}
		/*
		 * 3. Login as a teacher, update the assignment and set a due date. Set 'Prevent late submissions' to Yes.
		 */
		@Test
		public void setDueDateInsidePartition() {
			//Login as the teacher. 
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Access the course.
			course.clickCourseLink(this.properties.get("courseName"));
			//Access the assignment.
			assignment.clickAssignmentLink(this.properties.get("MDLQA63AssignmentName"));
			//Update the assignment and set an 'Available from' date.
			navigateSettingsBlock.navigateEditAssignmentSettings();
			//Set the Allow submissions from date into the past.
			addAssignment.selectAllowSubmissionsFrom(this.properties.get("MDLQA63AssignmentDayFrom"), 
														this.properties.get("MDLQA63AssignmentMonth"), 
														this.properties.get("MDLQA63AssignmentYearPast"), 
														this.properties.get("MDLQA63AssignmentHour"), 
														this.properties.get("MDLQA63AssignmentMin"));
			//Set the Due date into the past.
			addAssignment.selectDueDate(this.properties.get("MDLQA63AssignmentDayTo"), 
											this.properties.get("MDLQA63AssignmentMonth"), 
											this.properties.get("MDLQA63AssignmentYearFuture"), 
											this.properties.get("MDLQA63AssignmentHour"), 
											this.properties.get("MDLQA63AssignmentMin"));
			//Set the Final date into the future.
			addAssignment.selectFinalDate(this.properties.get("MDLQA63AssignmentDayTo"), 
											this.properties.get("MDLQA63AssignmentMonth"), 
											this.properties.get("MDLQA63AssignmentYearFuture"), 
											this.properties.get("MDLQA63AssignmentHour"), 
											this.properties.get("MDLQA63AssignmentMin"));
			addAssignment.clickSaveAndDisplay();
			//Log Teacher out
			user.selectLogout();
		}
		/*
		 * 4. Login as a student and check that the due date is displayed in the assignment and also in the course calendar.
		 */
		@Test
		public void studentChecksSubmissionCanBeMade() {
			//Student logs in.
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			//Student accesses course.
			course.clickCourseLink(this.properties.get("courseName"));
			//Student accesses assignment.
			assignment.clickAssignmentLink(this.properties.get("MDLQA63AssignmentName"));
			assignment.clickButtonEditMySubmission();
			user.selectLogout();
		}
		@Test
		public void setDueDateOutsidePartition() {
			//Login as the teacher. 
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Access the course.
			course.clickCourseLink(this.properties.get("courseName"));
			//Access the assignment.
			assignment.clickAssignmentLink(this.properties.get("MDLQA63AssignmentName"));
			//Update the assignment and set an 'Available from' date.
			navigateSettingsBlock.navigateEditAssignmentSettings();
			//Set the Allow submissions from date into the past.
			addAssignment.selectAllowSubmissionsFrom(this.properties.get("MDLQA63AssignmentDayFrom"), 
														this.properties.get("MDLQA63AssignmentMonth"), 
														this.properties.get("MDLQA63AssignmentYearPast"), 
														this.properties.get("MDLQA63AssignmentHour"), 
														this.properties.get("MDLQA63AssignmentMin"));
			//Set the Due date into the past.
			addAssignment.selectDueDate(this.properties.get("MDLQA63AssignmentDayTo"), 
											this.properties.get("MDLQA63AssignmentMonth"), 
											this.properties.get("MDLQA63AssignmentYearPast"), 
											this.properties.get("MDLQA63AssignmentHour"), 
											this.properties.get("MDLQA63AssignmentMin"));
			//Set the Final date into the future.
			addAssignment.selectFinalDate(this.properties.get("MDLQA63AssignmentDayTo"), 
											this.properties.get("MDLQA63AssignmentMonth"), 
											this.properties.get("MDLQA63AssignmentYearPast"), 
											this.properties.get("MDLQA63AssignmentHour"), 
											this.properties.get("MDLQA63AssignmentMin"));
			addAssignment.clickSaveAndDisplay();
			//Log Teacher out
			user.selectLogout();
		}
		/*
		 * 5. Check that after the due date there is no longer an upload file option.
		 */
		@Test
		public void studentChecksSubmissionCannotBeMade() throws Exception {
			//Student logs in.
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			//Student accesses course.
			course.clickCourseLink(this.properties.get("courseName"));
			//Student accesses assignment.
			assignment.clickAssignmentLink(this.properties.get("MDLQA63AssignmentName"));
			//Check file submission is not present
			assignment.assertFileSubmisisonDisabled();
			//Student logs out.
			user.selectLogout();
		}
		/*
		 * END OF TEST
		 */
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
			//sm.teardownChrome();
		}
}