package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA517TeacherReceiveGradeCompCondition extends TestRunSettings {
		/**
		 * DESCRIPTION:
		 *<br>A teacher can set 'receive grade' as a completion condition for an assignment
		 *<br>
		 *<br>TEST PRE-REQUISITES:
		 *<br>This test requires completion tracking to be enabled by an administrator in Site administration > Advanced features and in the course settings.
		 *<br>This test requires an assignment to have been created.
		 *<br>
		 *<br>TEST SCENARIO:
		 *<br>1. Login as a teacher, access the course and edit the course settings.
		 *<br>2. Set Completion Tracking to "Enabled, control via completion and activity settings".
		 *<br>3. Access an assignment and edit the assignment settings.
		 *<br>4. Set the completion tracking to 'Show activity as complete when conditions are met'.
		 *<br>5. Select 'Student must receive a grade to complete this activity'.
		 *<br>6. Give a particular student a grade for the assignment.
		 *<br>7. Check that the assignment is marked as complete for the student in Reports > Activity completion.
		 */
		//Test data files.
		public static String usersTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		//Data Hashmap
		private Map<String, String> properties = new HashMap<String, String>();
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
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("studentSurname", testData.getProperty("studentSurname"));
			this.properties.put("student2Firstname", testData.getProperty("student2Firstname"));
			this.properties.put("student2Surname", testData.getProperty("student2Surname"));
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("courseShortname", testData.getProperty("courseShortname"));
			this.properties.put("MDLQA517OutlineSection", testData.getProperty("MDLQA517OutlineSection"));
			this.properties.put("MDLQA517AssignmentName", testData.getProperty("MDLQA517AssignmentName"));
			this.properties.put("MDLQA517AssignmentText", testData.getProperty("MDLQA517AssignmentText"));
		}
		/*
		 * PRE-REQUISITES
		 */
		/*
		 * Turn completion tracking on.
		 */
		@Test
		public void turnTrackingOn() {
			//Login as admin
			user.loginToSystem(this.properties.get("adminUser"), this.properties.get("password"));
			//Turn on completion tracking (if it's off).
			settingsBlock.treeMenuAdvancedFeatures();
			siteAdmin.turnCompletionTrackingOn();
			siteAdmin.clickSaveChanges();
			user.selectLogout();
		}
		/*
		 * Create Assignment
		 */
		@Test
		public void createAssignment() throws Exception {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Teacher accesses course and turns editing on.
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Teacher creates an online text assignment.
			addActivity.selectAssignment(this.properties.get("MDLQA517OutlineSection"));
			addAssignment.enterNameField(this.properties.get("MDLQA517AssignmentName"));
			addAssignment.enterDescriptionField(this.properties.get("MDLQA517AssignmentText"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.clickSaveAndDisplay();
			//Teacher logs out
			user.selectLogout();
		}
		/*
		 * Student Makes a subission.
		 */
		@Test
		public void studentSubmitsAssignment() throws Exception {
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA517AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA517AssignmentText"));
			submitAssignment.clickButtonSaveChanges();
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
			settingsBlock.navigateEditCourseSettings();
		}
		/*
		 * 2. Set Completion Tracking to "Enabled, control via completion and activity settings".
		 */
		@Test
		public void enableCompletionTracking() {
			//Enable completion tracking.
			editCourseSettings.selectCompletionTrackingEnabled();
			editCourseSettings.clickSaveChanges();
		}
		/*
		 * 3. Access an assignment and edit the assignment settings.
		 */
		@Test
		public void assignmentSettings() {
			//Access assignment and edit settings.
			assignment.clickAssignmentLink(this.properties.get("MDLQA517AssignmentName"));
			settingsBlock.navigateEditAssignmentSettings();
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
			assignment.clickLinkGradeAllSub();
			grading.clickLinkGrade(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			grading.enterTextStandardGrade("100");
			grading.clickButtonSaveChanges();
		}
		/*
		 * 7. Check that the assignment is marked as complete for the student in Reports > Activity completion.
		 */
		@Test
		public void checkMarkedComplete() throws Exception {
			navigationBlock.navigateReportActivityCompletion(this.properties.get("courseShortname"));
			activityCompletionReport.assertCompleted(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			activityCompletionReport.assertNotCompleted(this.properties.get("student2Firstname"), this.properties.get("student2Surname"));
			user.selectLogout();
		}
}