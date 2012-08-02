package com.moodle.test.assignment;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQA70ViewXAssignments extends TestRunSettings {
		/**
		 * DESCRIPTION:
		 * A 'View x submitted assignments' link informs teachers of the number of assignments submitted to-date 
		 * 
		 * TEST PRE-REQUISITES:
		 * This test requires an assignment with file submissions enabled. Two students have already submitted their assignments.
		 * 
		 * TEST SCENARIO:
		 * 1. Login as a teacher and check that the link now states 'View 2 submitted assignments'. 
		 */
		//Test Data Property Files
		public static String usersTestData = "properties/data/user/Users/usersData.properties";
		public static String courseTestData = "properties/data/user/Courses/courseData.properties";
		public static String assignmentTestData = "properties/data/user/Assignment/assignmentData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA70ViewXAssignments(){
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
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("MDLQA70AssigmentName", testData.getProperty("MDLQA70AssigmentName"));
			this.properties.put("MDLQA70NumberOfSubmissions", testData.getProperty("MDLQA70NumberOfSubmissions"));
		}
		/*
		 * 1. Login as a teacher and check that the link now states 'View 2 submitted assignments'.
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
			//TODO The test won't pass without seeding the database first. See Tracker issue MDLTEST-143
			assignment.clickAssignmentLink(this.properties.get("MDLQA70AssigmentName"));
			//Check that the number of submissions is displayed on the page.
			assignmentAssertions.assertNumberOfSubmissions(this.properties.get("MDLQA70NumberOfSubmissions"));
			//Teacher logs out
			user.selectLogout();
		}
}