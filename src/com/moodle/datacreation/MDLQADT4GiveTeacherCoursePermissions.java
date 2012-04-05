/**
 * MDLQADT-4
 * TEST SCENARIO:
 * Assign a teacher course permissions
 * 1) Call RemoteWebDriver setup method.
 * 2) Login as admin user.
 * 3) Enrol teacher in course.
 * 4) Assign Teacher Role to Teacher.
 * 5) Enrol the student in the course.
 * 5) Logout Admin user.
 * 6) Call RemoteWebDriver teardown method.
 */
package com.moodle.datacreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.UsersEnrolled;
import com.moodle.testmanager.pageObjectModel.Users;
import com.moodle.testmanager.pageObjectModel.BlockSettings;

public class MDLQADT4GiveTeacherCoursePermissions {
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
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
			this.properties.put("courseName", testData.getProperty("courseName"));
		}
		//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//Setup webdriver for @Test methods
		@BeforeClass
		static public void automateTestSetup()throws FileNotFoundException, IOException{
			//Load properties required for test run
			Properties startupConfig = new Properties();
			startupConfig.load(new FileInputStream(runParameters));
			String gridHubURL = startupConfig.getProperty("gridHubURL");
			String browserType = startupConfig.getProperty("browserType");
			String moodleHomePage = startupConfig.getProperty("moodleHomePage");
			//Call setup method
			sm = new SeleniumManager();
			sm.startRemotes(gridHubURL, browserType);
			driver = sm.getRemoteDriver();
			driver.get(moodleHomePage);
		}
		//Login as default Admin user
		@Test
		public void login(){
			Users userLogin = new Users(driver);
			userLogin.selectLoginLink();
			userLogin.enterUsername(this.properties.get("adminUser"));
			userLogin.enterPassword(this.properties.get("password"));
			userLogin.clickLoginButton();
		}
		@Test
		//Enrol Teacher in course
		public void enrolTeacher(){
			Courses availableCourses = new Courses(driver);
			availableCourses.clickCourseLink(this.properties.get("courseName"));
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("teacherFirstname"),this.properties.get("teacherSurname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		//Assign Teacher Role in Course
		public void assignTeacherRole(){
			UsersEnrolled userRole = new UsersEnrolled(driver);
			userRole.removeStudentRole(this.properties.get("teacherFirstname"),this.properties.get("teacherSurname"));
			userRole.confirmRemove();
			userRole.clickAddRole(this.properties.get("teacherFirstname"),this.properties.get("teacherSurname"));
			userRole.assignTeacherRole();
		}
		@Test
		//Enrol the Student1 in the course
		public void enrolStudent(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("studentFirstname"),this.properties.get("studentSurname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		//Enrol the Student2 in the course
		public void enrolStudent2(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student2Firstname"),this.properties.get("student2Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		//Log admin user out
		@Test
		public void logout() {
			Users userLogout = new Users(driver);
			userLogout.selectLogout();
		}
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}		
}