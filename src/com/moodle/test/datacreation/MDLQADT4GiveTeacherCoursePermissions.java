package com.moodle.test.datacreation;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQADT4GiveTeacherCoursePermissions extends TestRunSettings {
		//Test Data Property Files
		public static String usersData = "properties/data/user/Users/usersData.properties";
		public static String courseData = "properties/data/user/Courses/courseData.properties";
		//Weekly outline section
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQADT4GiveTeacherCoursePermissions(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(usersData));
				testData.load(new FileInputStream(courseData));
			} catch (Exception e) {}
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("teacherFirstname", testData.getProperty("teacherFirstname"));
			this.properties.put("teacherSurname", testData.getProperty("teacherSurname"));
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("studentSurname", testData.getProperty("studentSurname"));
			this.properties.put("student2Firstname", testData.getProperty("student2Firstname"));
			this.properties.put("student2Surname", testData.getProperty("student2Surname"));
			this.properties.put("student3Firstname", testData.getProperty("student3Firstname"));
			this.properties.put("student3Surname", testData.getProperty("student3Surname"));
			this.properties.put("student4Firstname", testData.getProperty("student4Firstname"));
			this.properties.put("student4Surname", testData.getProperty("student4Surname"));
			this.properties.put("student5Firstname", testData.getProperty("student5Firstname"));
			this.properties.put("student5Surname", testData.getProperty("student5Surname"));
			this.properties.put("student6Firstname", testData.getProperty("student6Firstname"));
			this.properties.put("student6Surname", testData.getProperty("student6Surname"));
			this.properties.put("student7Firstname", testData.getProperty("student7Firstname"));
			this.properties.put("student7Surname", testData.getProperty("student7Surname"));
			this.properties.put("student8Firstname", testData.getProperty("student8Firstname"));
			this.properties.put("student8Surname", testData.getProperty("student8Surname"));
			this.properties.put("student9Firstname", testData.getProperty("student9Firstname"));
			this.properties.put("student9Surname", testData.getProperty("student9Surname"));
			this.properties.put("student10Firstname", testData.getProperty("student10Firstname"));
			this.properties.put("student10Surname", testData.getProperty("student10Surname"));
			this.properties.put("student11Firstname", testData.getProperty("student11Firstname"));
			this.properties.put("student11Surname", testData.getProperty("student11Surname"));
			this.properties.put("courseName", testData.getProperty("courseName"));
		}
		//Login as default Admin user
		@Test
		public void login(){
			user.loginToSystem(this.properties.get("adminUser"), this.properties.get("password"));
		}
		@Test
		//Enrol Teacher in course
		public void enrolTeacher(){
			course.clickCourseLink(this.properties.get("courseName"));
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("teacherFirstname"),this.properties.get("teacherSurname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		//Assign Teacher Role in Course
		public void assignTeacherRole(){
			enrolledUsers.removeStudentRole(this.properties.get("teacherFirstname"),this.properties.get("teacherSurname"));
			enrolledUsers.confirmRemove();
			enrolledUsers.clickAddRole(this.properties.get("teacherFirstname"),this.properties.get("teacherSurname"));
			enrolledUsers.assignTeacherRole();
		}
		@Test
		//Enrol the Student1 in the course
		public void enrolStudent(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("studentFirstname"),this.properties.get("studentSurname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent2(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student2Firstname"),this.properties.get("student2Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent3(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student3Firstname"),this.properties.get("student3Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent4(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student4Firstname"),this.properties.get("student4Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent5(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student5Firstname"),this.properties.get("student5Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent6(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student6Firstname"),this.properties.get("student6Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent7(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student7Firstname"),this.properties.get("student7Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent8(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student8Firstname"),this.properties.get("student8Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent9(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student9Firstname"),this.properties.get("student9Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent10(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student10Firstname"),this.properties.get("student10Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent11(){
			settingsBlock.navigateEnrolledUsers();
			enrolledUsers.clickEnrolUserButton();
			enrolledUsers.selectUserToEnrol(this.properties.get("student11Firstname"),this.properties.get("student11Surname"));
			enrolledUsers.clickFinishEnrollingButton();
			enrolledUsers.clickEnrolledUsersBreadcrumb();
		}	
}