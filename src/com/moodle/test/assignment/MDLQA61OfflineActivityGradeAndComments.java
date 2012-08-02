package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA61OfflineActivityGradeAndComments extends TestRunSettings {
		/**
		 * DESCRIPTION:
		 * In an Offline activity assignment, a teacher can grade and give comments on an assignment completed offline
		 * 
		 * PRE-REQUISITES:
		 * This test requires an activity with File Submissions and Online Text disabled. i.e. a replication of the old off-line activity using the new single assignment type.
		 * 
		 * TEST SCENARIO:
		 * 1. Login as a teacher and access the Off-line activity assignment.
		 * 2. Follow the 'View assignment grades and feedback' link and select a student to grade.
		 * 3. Add a grade and comment then click the 'Save changes' button.
		 * 4. Check that the grade, comment and 'Last modified (Teacher)' date are correctly displayed for 
		 * the assignment just graded and the link text in the status column has changed from 'Grade' to 'Update'. 
		 */
		//Test Data Property Files
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA61OfflineActivityGradeAndComments(){
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
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("studentSurname", testData.getProperty("studentSurname"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("MDLQA61OutlineSection", testData.getProperty("MDLQA61OutlineSection"));
			this.properties.put("MDLQA61AssignmentName", testData.getProperty("MDLQA61AssignmentName"));
			this.properties.put("MDLQA61AssignmentDescription", testData.getProperty("MDLQA61AssignmentDescription"));
			this.properties.put("MDLQA61Grade", testData.getProperty("MDLQA61Grade"));
			this.properties.put("MDLQA61FeedbackComment", testData.getProperty("MDLQA61FeedbackComment"));
			this.properties.put("MDLQA61StatusUpdate", testData.getProperty("MDLQA61StatusUpdate"));
		}
		/*
		 * PRE-REQUISITES:
 		 * This test requires an activity with File Submissions and Online Text disabled. i.e. a replication of the old off-line activity using the new single assignment type. 
		 */
		@Test
		public void createOfflineAssignment() throws Exception {
			//Teacher logs in.
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Teacher accesses course and creates an assignment
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			addActivity.selectAssignment(this.properties.get("MDLQA61OutlineSection"));
			//Teacher sets up assignment
			addAssignment.enterNameField(this.properties.get("MDLQA61AssignmentName"));
			addAssignment.enterDescriptionField(this.properties.get("MDLQA61AssignmentDescription"));
			addAssignment.selectFeedbackCommentsYes();
			addAssignment.clickSaveAndDisplay();
			//Teacher logs out
			user.selectLogout();
		}
		/*
		 * 1. Login as a teacher and access the Off-line activity assignment.
		 */
		@Test
		public void teacherAccessAssignment() {
			//Teacher logs in.
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Teacher accesses course
			course.clickCourseLink(this.properties.get("courseName"));
			//Teacher accesses the assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA61AssignmentName"));
		}
		/*
		 * 2. Follow the 'View assignment grades and feedback' link and select a student to grade.
		 */
		@Test
		public void navigateToGradeAssignment() {
			assignment.clickLinkGradeAllSub();
		}
		/*
		 * 3. Add a grade and comment then click the 'Save changes' button. 
		 */
		@Test
		public void gradeAndComment() throws Exception {
			//Teacher grades assignment
			grading.clickLinkGrade(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			grading.enterTextStandardGrade(this.properties.get("MDLQA61Grade"));
			//Teacher adds comments
			grading.enterFeedbackComments(this.properties.get("MDLQA61FeedbackComment"));
			grading.clickButtonSaveChanges();
		}
		/*
		 * 4. Check that the grade, comment and 'Last modified (Teacher)' date are correctly displayed for 
		 * the assignment just graded and the link text in the status column has changed from 'Grade' to 'Update'. 
		 */
		@Test
		public void checkGrade() throws Exception {
			//Check the feedback comments
			assignmentAssertions.assertFeedbackComments(this.properties.get("MDLQA61FeedbackComment"), this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			//Check the final grade
			assignmentAssertions.assertFinalGradeStandard(this.properties.get("MDLQA61Grade"), this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			//Check the submission status is set to "Update"
			assignmentAssertions.assertSubmissionStatusGradingTable(this.properties.get("MDLQA61StatusUpdate"), this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
		}
}