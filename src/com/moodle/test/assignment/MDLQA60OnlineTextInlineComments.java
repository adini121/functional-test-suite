package com.moodle.test.assignment;

import java.io.IOException;
import org.junit.Test;

import com.moodle.test.TestDataLoad;

public class MDLQA60OnlineTextInlineComments extends TestDataLoad {
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
	private String teacherUsername = this.properties.get("teacherUsername");
	private String password = this.properties.get("password");
	private String courseName = this.properties.get("courseName");
	private String studentFirstname = this.properties.get("studentFirstname");
	private String studentSurname = this.properties.get("studentSurname");
	private String MDLQA59AssignmentName = this.properties.get("MDLQA59AssignmentName");
	private String MDLQA59StudentEditedSubmissionText = this.properties.get("MDLQA59StudentEditedSubmissionText");
	private String MDLQA60SubmissionStatusSubmitted = this.properties.get("MDLQA60SubmissionStatusSubmitted");
	private String MDLQA60Grade = this.properties.get("MDLQA60Grade");
	private String MDLQA60FeedbackComments = this.properties.get("MDLQA60FeedbackComments");
	private String MDLQA60ScreenCaptureLocation = this.properties.get("MDLQA60ScreenCaptureLocation");
	private String MDLQA60SubmissionStatusGraded = this.properties.get("MDLQA60SubmissionStatusGraded");
	//PRE-REQUISITES
	//Re-use data from MDLQA-59
	//
	//TEST
	//
	// 1. Login as a teacher and access the assignment.
	@Test
	public void loginAsTeacherAccessAssignment() {
		user.selectLoginLink();
		user.enterUsername(teacherUsername);
		user.enterPassword(password);
		user.clickLoginButton();
		course.clickCourseLink(courseName);
		assignment.clickAssignmentLink(MDLQA59AssignmentName);
		assignmentAssertions.assertGradingSummaryPage(MDLQA59AssignmentName);
	}
	// 2. Follow the 'View x submitted assignments' link and click a Grade link.
	@Test
	public void viewXSubmittedAssignments() throws Exception {
		//assignment.clickButtonGradeAssignment();
		assignment.clickLinkGradeAllSub();
		grading.clickLinkGrade(studentFirstname, studentSurname);
		assignmentAssertions.assertSubmissionOnlineText(MDLQA59StudentEditedSubmissionText);
		assignmentAssertions.assertSubmissionStatusGradingForm(MDLQA60SubmissionStatusSubmitted);
	}
	// 3. Add an inline comment then click the 'Save changes' button.
	@Test
	public void addInlineComment() throws Exception {
		grading.enterTextStandardGrade(MDLQA60Grade);
		grading.enterFeedbackComments(MDLQA60FeedbackComments);
		grading.clickButtonSaveChanges();
	}
	// 4. Check that the 'Last modified (Teacher)' date is correctly displayed for the assignment just graded and the link 
	// text in the status column has changed from 'Grade' to 'Update'.
	//@Test
	public void checkLastMod() throws IOException {
		frameworkTools.takeScreenshotWithGivenLocationAndName(MDLQA60ScreenCaptureLocation);
	}
	@Test
	public void linkTextChanged() throws Exception {
		assignmentAssertions.assertSubmissionStatusGradingTable(MDLQA60SubmissionStatusGraded, studentFirstname, studentSurname);
			user.selectLogout();
	}
}