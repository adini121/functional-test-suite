package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA59OnlineTextAddEdit extends TestRunSettings {
		/**
		 * DESCRIPTION:
		 *<br>
		 *<br>TEST PRE-REQUISITES:
		 *<br>This test requires and assignment with Online Text enabled.
		 *<br>
		 *<br>TEST SCENARIO:
		 *<br>1. Login as a student and access the assignment.
		 *<br>2. Click the 'Add submission' button, add some text, then click the 'Save changes' button.
		 *<br>3. Click the 'Edit my submission' button again, edit the text, then click the 'Save changes' button.
		 *<br>4. Check that the changes have been saved and the latest submission date and time is displayed.
		 *<br>5. Check that the submission date and time is also displayed on the assignment index page.
		 */
		//Test Data Property Files
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
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
		//PRE-REQUISITES
		//Create Assignment with online text enabled
		@Test
		public void loginAsTeacherAndSetupAssignment() throws Exception {
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			addActivity.selectAssignment(this.properties.get("MDLQA59OutlineSection"));
			addAssignment.enterNameField(this.properties.get("MDLQA59AssignmentName"));
			addAssignment.enterDescriptionField(this.properties.get("MDLQA59AssignmentDescription"));
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
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA59AssignmentName"));
			assignmentAssertions.assertSubmissionPage(this.properties.get("MDLQA59AssignmentName"));
		}
		@Test
		//2. Click the 'Add submission' button, add some text, then click the 'Save changes' button.
		public void addSubmission() throws Exception {
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA59StudentSubmissionText"));
			submitAssignment.clickButtonSaveChanges();
			assignmentAssertions.assertSubmissionPage(this.properties.get("MDLQA59AssignmentName"));
			assignmentAssertions.assertSubmissionOnlineText(this.properties.get("MDLQA59StudentSubmissionText"));
		}
		@Test
		//3. Click the 'Edit my submission' button again, edit the text, then click the 'Save changes' button.
		public void editSubmission() throws Exception {
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA59StudentEditedSubmissionText"));
			submitAssignment.clickButtonSaveChanges();
		}
		@Test
		//4. Check that the changes have been saved and the latest submission date and time is displayed.
		public void verifyChangesSaved() throws Exception {
			assignmentAssertions.assertSubmissionPage(this.properties.get("MDLQA59AssignmentName"));
			assignmentAssertions.assertSubmissionOnlineText(this.properties.get("MDLQA59StudentSubmissionText") + this.properties.get("MDLQA59StudentEditedSubmissionText"));
		}
		//@Test
		//5. Check that the submission date and time is also displayed on the assignment index page.
		public void verifySubmissionDateAndTime() throws IOException {
			frameworkTools.takeScreenshotWithGivenLocationAndName(this.properties.get("MDLQA59ScreenCaptureLocation"));
			user.selectLogout();
		}
}