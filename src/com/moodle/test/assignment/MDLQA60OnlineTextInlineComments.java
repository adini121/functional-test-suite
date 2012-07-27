package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA60OnlineTextInlineComments extends TestRunSettings {
		/**
		 * DESCRIPTION:
		 *<br>In an Online text assignment, a teacher can add inline comments
		 *<br> 
		 *<br>PRE-REQUISITES:
		 *<br>This test requires an assignment with Online Text enabled and submitted assignments.
		 *<br>
		 *<br>TEST SCENARIO:
		 *<br>1. Login as a teacher and access the assignment.
		 *<br>2. Follow the 'View x submitted assignments' link and click a Grade link.
		 *<br>3. Add an inline comment then click the 'Save changes' button.
		 *<br>4. Check that the 'Last modified (Teacher)' date is correctly displayed for the assignment just graded and the link 
		 *<br>text in the status column has changed from 'Grade' to 'Update'.
		 */
		//Test Data Property Files
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
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
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA59AssignmentName"));
			grading.assertGradingSummaryPage(this.properties.get("MDLQA59AssignmentName"));
		}
		// 2. Follow the 'View x submitted assignments' link and click a Grade link.
		@Test
		public void viewXSubmittedAssignments() throws Exception {
			//assignment.clickButtonGradeAssignment();
			assignment.clickLinkGradeAllSub();
			grading.clickLinkGrade(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			submitAssignment.assertSubmissionOnlineText(this.properties.get("MDLQA59StudentEditedSubmissionText"));
			grading.assertSubmissionStatusGradingForm(this.properties.get("MDLQA60SubmissionStatusSubmitted"));
		}
		// 3. Add an inline comment then click the 'Save changes' button.
		@Test
		public void addInlineComment() throws Exception {
			grading.enterTextStandardGrade(this.properties.get("MDLQA60Grade"));
			grading.enterFeedbackComments(this.properties.get("MDLQA60FeedbackComments"));
			grading.clickButtonSaveChanges();
		}
		// 4. Check that the 'Last modified (Teacher)' date is correctly displayed for the assignment just graded and the link 
		// text in the status column has changed from 'Grade' to 'Update'.
		@Test
		public void checkLastMod() throws IOException {
			frameworkTools.takeScreenshotWithGivenLocationAndName(this.properties.get("MDLQA60ScreenCaptureLocation"));
		}
		@Test
		public void linkTextChanged() throws Exception {
			grading.assertSubmissionStatusGradingForm(this.properties.get("MDLQA60SubmissionStatusGraded"));
			user.selectLogout();
		}
}