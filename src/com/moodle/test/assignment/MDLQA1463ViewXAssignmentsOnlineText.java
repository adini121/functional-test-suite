package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;


public class MDLQA1463ViewXAssignmentsOnlineText extends TestRunSettings {
		/**
		 * DESCRIPTION:
		 *<br>A 'View x submitted assignments' link informs teachers of the number of assignments submitted to-date for online text submissions
		 *<br> 
		 *<br>TEST PRE-REQUISITES:
		 *<br>This test requires an assignment with online text submissions enabled.
		 *<br>
		 *<br>TEST SCENARIO:
		 *<br>1. Login as student1 and submit online text for the assignment.
		 *<br>2. Login as a teacher and check that a 'View 1 submitted assignments' link is displayed on the assignment page and also the assignments index page.
		 *<br>3. Login as student2 and submit online text for the assignment.
		 *<br>4. Login as a teacher and check that the link now states 'View 2 submitted assignments'.
		 */
		public static String usersTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA1463ViewXAssignmentsOnlineText(){
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
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("MDLQA1463AssigmentName", testData.getProperty("MDLQA1463AssigmentName"));
			this.properties.put("MDLQA1463AssignmentDescription", testData.getProperty("MDLQA1463AssignmentDescription"));
			this.properties.put("MDLQA1463OutlineSection", testData.getProperty("MDLQA1463OutlineSection"));
			this.properties.put("MDLQA1463AssignmentSubmissionText", testData.getProperty("MDLQA1463AssignmentSubmissionText"));
		}
		/*
		 * PRE-REQUISITES:
		 * This test requires an assignment with online text submissions enabled.
		 */
		@Test
		public void createAssignment() throws Exception {
			//Login as teacher
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Navigate to the course and turn editing on
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Select the assignment activity from "Add an Activity..."
			addActivity.selectAssignment(this.properties.get("MDLQA1463OutlineSection"));
			//Setup the assignment
			addAssignment.enterNameField(this.properties.get("MDLQA1463AssigmentName"));
			addAssignment.enterDescriptionField(this.properties.get("MDLQA1463AssignmentDescription"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.clickSaveAndDisplay();
			//Log the teacher out
			user.selectLogout();
		}
		/*
		 * 1. Login as student1 and submit online text for the assignment.
		 */
		@Test
		public void student1SubmitsOnlineText() throws Exception {
			//Login as student 1
			user.selectLoginLink();
			user.enterUsername(this.properties.get("studentUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Access course
			course.clickCourseLink(this.properties.get("courseName"));
			//Access assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1463AssigmentName"));
			//Enter Online text
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA1463AssignmentSubmissionText"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
		}
		/*
		 * 2. Login as a teacher and check that a 'View 1 submitted assignments' link is displayed on the assignment page and also the assignments index page.
		 */
		@Test
		public void teacherChecksLink() throws Exception {
			//Teacher logs in
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Teacher accesses course
			course.clickCourseLink(this.properties.get("courseName"));
			//Teacher accesses assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1463AssigmentName"));
			//Check that the number of submissions is displayed on the page.
			assignmentAssertions.assertNumberOfSubmissions("1");
			//Teacher logs out
			user.selectLogout();
		}
		/*
		 * 3. Login as student2 and submit online text for the assignment.
		 */
		@Test
		public void student2SubmitsOnlineText() throws Exception {
			//Login as student 2
			user.selectLoginLink();
			user.enterUsername(this.properties.get("student2Username"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Access course
			course.clickCourseLink(this.properties.get("courseName"));
			//Access assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1463AssigmentName"));
			//Enter Online text
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA1463AssignmentSubmissionText"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
		}
		/*
		 * 4. Login as a teacher and check that the link now states 'View 2 submitted assignments'.
		 */
		@Test
		public void teacherChecksSingleLink() throws Exception {
			//Teacher logs in
			user.selectLoginLink();
			user.enterUsername(this.properties.get("teacherUsername"));
			user.enterPassword(this.properties.get("password"));
			user.clickLoginButton();
			//Teacher accesses course
			course.clickCourseLink(this.properties.get("courseName"));
			//Teacher accesses assignment
			assignment.clickAssignmentLink(this.properties.get("MDLQA1463AssigmentName"));
			//Check that the number of submissions is displayed on the page.
			assignmentAssertions.assertNumberOfSubmissions("2");
			//Teacher logs out
			user.selectLogout();
		}
}