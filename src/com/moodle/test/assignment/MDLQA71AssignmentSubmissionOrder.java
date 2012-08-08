package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA71AssignmentSubmissionOrder extends TestRunSettings {
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
		//Test data files.
		public static String usersTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		//Data Hashmap
		private Map<String, String> properties = new HashMap<String, String>();
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
			this.properties.put("student3Firstname", testData.getProperty("student3Firstname"));
			this.properties.put("student3Surname", testData.getProperty("student3Surname"));
			this.properties.put("student11Firstname", testData.getProperty("student11Firstname"));
			this.properties.put("student11Surname", testData.getProperty("student11Surname"));
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
			this.properties.put("courseShortname", testData.getProperty("courseShortname"));
			this.properties.put("MDLQA71OutlineSection", testData.getProperty("MDLQA71OutlineSection"));
			this.properties.put("MDLQA71AssignmentName", testData.getProperty("MDLQA71AssignmentName"));
			this.properties.put("MDLQA71AssignmentText", testData.getProperty("MDLQA71AssignmentText"));
			this.properties.put("MDLQA71AssignmentSubmissionA", testData.getProperty("MDLQA71AssignmentSubmissionA"));
			this.properties.put("MDLQA71AssignmentSubmissionB", testData.getProperty("MDLQA71AssignmentSubmissionB"));
			this.properties.put("MDLQA71Twenty", testData.getProperty("MDLQA71Twenty"));
		}
		/*
		 * PRE-REQUISITES:
		 * This test requires an assignment with several submissions.
		 */
		@Test
		public void setupData() throws Exception {
			//Teacher logs in.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			//Teacher accesses course and turns editing on.
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			//Teacher creates an online text assignment.
			addActivity.selectAssignment(this.properties.get("MDLQA71OutlineSection"));
			addAssignment.enterNameField(this.properties.get("MDLQA71AssignmentName"));
			addAssignment.enterDescriptionField(this.properties.get("MDLQA71AssignmentText"));
			addAssignment.selectOnlineTextEnabledYes();
			addAssignment.clickSaveAndDisplay();
			//Teacher logs out
			user.selectLogout();
			//Students submit assignment.
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionA"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student2Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			////
			user.loginToSystem(this.properties.get("student3Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student4Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student5Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student6Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student7Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student8Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student9Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student10Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
			user.selectLogout();
			//
			user.loginToSystem(this.properties.get("student11Username"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickButtonAddOrEditSubmission();
			submitAssignment.clickCheckboxSubmissionStatement();
			submitAssignment.enterOnlineText(this.properties.get("MDLQA71AssignmentSubmissionB"));
			submitAssignment.clickButtonSaveChanges();
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
			assignment.clickLinkGradeAllSub();
		}
		/*
		 * 2. Try sorting the submissions by first name by clicking the 'First name' heading.
		 */
		@Test
		public void firstNameSort() throws Exception {
			grading.clickLinkSortFirstName();
			grading.clickLinkSortFirstName();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickLinkGradeAllSub();
			assignmentAssertions.assertSortOrderStudentName(this.properties.get("student11Firstname"), this.properties.get("student11Surname"));
			//grading.assertFirstAndSurnameHidden(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
		}
		/*
		 * 3. Click the 'First name' heading again and check that the submissions are now sorted in the reverse order.
		 */
		@Test
		public void firstNameSortReverse() throws Exception {
			grading.clickLinkSortFirstName();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickLinkGradeAllSub();
			assignmentAssertions.assertSortOrderStudentName(this.properties.get("student2Firstname"), this.properties.get("student2Surname"));
			assignmentAssertions.assertSortOrderStudentName(this.properties.get("student3Firstname"), this.properties.get("student3Surname"));
			//grading.assertFirstAndSurnameHidden(this.properties.get("student11Firstname"), this.properties.get("student11Surname"));
			//Teacher logs out
			user.selectLogout();
		}
		/*
		 * 4. Change the submissions shown per page to 10 and click the 'Save preferences' button.
		 */
		//@Test
		public void tenIsTheDefaultNumber() {
			//Stub to make test pass as the default number is ten.
		}
		/*
		 * 5. Check that the submissions page now displays only 10 submissions.
		 */
	//	@Test
		public void submissionsPerPage() throws Exception {
			//Teacher logs in and navigates to grading table.
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickLinkGradeAllSub();
			//Verify that, using the default 10 assignments per page that there are 2 pages.
			assignmentAssertions.assertNumberOfGradingTablePages("2");
			//Sort by First name the navigate to second page.
			grading.clickLinkSortFirstName();
			grading.clickLinkGradingTablePageNumber("2");
			//Assert that the correct student appears on this page.
			assignmentAssertions.assertSortOrderStudentName(this.properties.get("student11Firstname"), this.properties.get("student11Surname"));
			//Change the number of assignments per page to 20 and verify that there is no pagination.
			grading.selectValueAssignmentsPerPage(this.properties.get("MDLQA71Twenty"));
			//assignmentAssertions.assertNoLinkGradingTablePageNumber("2");
			user.selectLogout();
		}
		/*
		 * 6. Try hiding one of the columns by clicking the hide icon next to a particular column heading.
		 */
		//@Test
		public void hideColumns() throws Exception {
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
			course.clickCourseLink(this.properties.get("courseName"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickLinkGradeAllSub();
			grading.clickHideName();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			assignment.clickAssignmentLink(this.properties.get("MDLQA71AssignmentName"));
			assignment.clickLinkGradeAllSub();
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("studentFirstname"), this.properties.get("studentSurname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student2Firstname"), this.properties.get("student2Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student3Firstname"), this.properties.get("student3Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student4Firstname"), this.properties.get("student4Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student5Firstname"), this.properties.get("student5Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student6Firstname"), this.properties.get("student6Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student7Firstname"), this.properties.get("student7Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student8Firstname"), this.properties.get("student8Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student9Firstname"), this.properties.get("student9Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student10Firstname"), this.properties.get("student10Surname"));
			assignmentAssertions.assertFirstAndSurnameHidden(this.properties.get("student11Firstname"), this.properties.get("student11Surname"));
			user.selectLogout();
		}
}