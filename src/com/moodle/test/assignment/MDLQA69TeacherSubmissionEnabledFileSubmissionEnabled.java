package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA69TeacherSubmissionEnabledFileSubmissionEnabled extends TestRunSettings{
		/**
		 * DESCRIPTION:
		 * A teacher can enable submission comments when file submissions is enabled
		 * 
		 * TEST PRE-REQUISITES:
		 * An assignment exists with file submissions enabled and 'Allow submission comments' set to Yes.
		 * 
		 * TEST SCENARIO:
		 * 1. Login as a student and access the assignment.
		 * 2. Add a submission comment and save the comment.
		 * 3. Delete the submission comment, and enter a new comment, and save your changes.
		 * 4. Check that the changes have been saved.
		 */
		//Test Data Property Files
		public static String userTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA69TeacherSubmissionEnabledFileSubmissionEnabled(){
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
			this.properties.put("MDLQA69OutlineSection", testData.getProperty("MDLQA69OutlineSection"));
			this.properties.put("MDLQA69AssignmentName", testData.getProperty("MDLQA69AssignmentName"));
			this.properties.put("MDLQA69AssignmentText", testData.getProperty("MDLQA69AssignmentText"));
			this.properties.put("MDLQA69StudentSubmissionComment", testData.getProperty("MDLQA69StudentSubmissionComment"));
			this.properties.put("MDLQA69StudentSubmissionCommentEdit", testData.getProperty("MDLQA69StudentSubmissionCommentEdit"));
			this.properties.put("MDLQA69OnlineText", testData.getProperty("MDLQA69OnlineText"));
		}
		/*
		 * TEST PRE-REQUISITES:
		 * An assignment exists with file submissions enabled.
		 */
		@Test
		public void createAssignment() throws Exception {
			//Login Teacher
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Navigate to course and turn editing on
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Add the Assignment activity
			addActivity.selectAssignment(this.properties.get("MDLQA69OutlineSection"));
			//Setup Assigment activity
			addAssignment.enterNameField(this.properties.get("MDLQA69AssignmentName"));
			addAssignment.enterDescriptionField(this.properties.get("MDLQA69AssignmentText"));
			addAssignment.selectFileSubmissionsEnabledNo();
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
			assignment.clickAssignmentLink(this.properties.get("MDLQA69AssignmentName"));
			assignmentAssertions.assertSubmissionPage(this.properties.get("MDLQA69AssignmentName"));
		}
		/*
		 * 2. Add a submission comment and save the comment.
		 */
		@Test
		public void addSubmissionComment() throws Exception {
			//Student adds a file submission.
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA69OnlineText"));
			submitAssignment.clickButtonSaveChanges();
			//Enter a submission comment.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.enterTextSubmissionComments(this.properties.get("MDLQA69StudentSubmissionComment"));
			//Cancel it.
			submissionComments.clickLinkCancelComment();
			//Check that it HASN'T been saved. 
			assignmentAssertions.assertCommentNotSaved(this.properties.get("MDLQA69StudentSubmissionComment"));
			//Enter the submission comment again.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.enterTextSubmissionComments(this.properties.get("MDLQA69StudentSubmissionComment"));
			//This time save it.
			submissionComments.clickLinkSaveComment();
			//Check that this time it has been saved.
			assignmentAssertions.assertCommentSaved(this.properties.get("MDLQA69StudentSubmissionComment"));
			submissionComments.clickLinkSubmissionComments();
		}
		/*
		 * 3. Delete the submission comment, and enter a new comment, and save your changes.
		 */
		@Test
		public void editSubmissionComment() throws Exception {
			//Delete the old comment.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.clickLinkDeleteSubmissionCommentAndConfirm("MDLQA69StudentSubmissionComment");
			//Check that it's been deleted.
			assignment.clickAssignmentLink(this.properties.get("MDLQA69AssignmentName"));
			assignmentAssertions.assertCommentNotSaved(this.properties.get("MDLQA69StudentSubmissionComment"));
			//Enter a new comment and save it.
			submissionComments.clickLinkSubmissionComments();
			submissionComments.enterTextSubmissionComments(this.properties.get("MDLQA69StudentSubmissionCommentEdit"));
			submissionComments.clickLinkSaveComment();
		}
		/*
		 * 4. Check that the changes have been saved.
		 */
		@Test
		public void checkChangesMade() throws Exception {
			//Check that it's been saved.
			assignmentAssertions.assertCommentSaved(this.properties.get("MDLQA69StudentSubmissionCommentEdit"));
		}
}