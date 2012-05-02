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
			//String chromeDriverLocation = startupConfig.getProperty("chromeDriverLocation");
			//Call setup method
			sm = new SeleniumManager();
			sm.startRemotes(gridHubURL, browserType);
			//sm.startChromeDriver(chromeDriverLocation);
			driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
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
		public void enrolStudent2(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student2Firstname"),this.properties.get("student2Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent3(){
			Courses availableCourses = new Courses(driver);
			availableCourses.clickCourseLink(this.properties.get("courseName"));
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student3Firstname"),this.properties.get("student3Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent4(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student4Firstname"),this.properties.get("student4Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent5(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student5Firstname"),this.properties.get("student5Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent6(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student6Firstname"),this.properties.get("student6Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent7(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student7Firstname"),this.properties.get("student7Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent8(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student8Firstname"),this.properties.get("student8Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent9(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student9Firstname"),this.properties.get("student9Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent10(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student10Firstname"),this.properties.get("student10Surname"));
			enrolUser.clickFinishEnrollingButton();
			enrolUser.clickEnrolledUsersBreadcrumb();
		}
		@Test
		public void enrolStudent11(){
			BlockSettings enrolledUser = new BlockSettings(driver);
			enrolledUser.navigateEnrolledUsers();
			UsersEnrolled enrolUser = new UsersEnrolled(driver);
			enrolUser.clickEnrolUserButton();
			enrolUser.selectUserToEnrol(this.properties.get("student11Firstname"),this.properties.get("student11Surname"));
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
			//sm.teardownChrome();
		}		
}