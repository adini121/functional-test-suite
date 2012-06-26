/**
 * Assign front page roles in Moodle
 * TEST SCENARIO:
 * Add one user to the teacher front page role and one user to the student front page role.
 * 1) Login to Moodle via RemoteWebDriver using a google Chrome browser as the default admin user.
 * 2) Navigate to Front Page Roles via the admin menu.
 * 3) Assign a user to the teacher role.
 * 4) Assign a user to the student role.
 * 5) logout.
 */
package com.moodle.test.datacreation;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQADT3AssignFrontPageRoles extends TestRunSettings {	
		public static String usersData = "properties/data/user/Users/usersData.properties";
		//Weekly outline section
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQADT3AssignFrontPageRoles(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(usersData));
			} catch (Exception e) {}
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("teacherFirstname", testData.getProperty("teacherFirstname"));
			this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
			this.properties.put("student2Firstname", testData.getProperty("student2Firstname"));
			this.properties.put("student3Firstname", testData.getProperty("student3Firstname"));
			this.properties.put("student4Firstname", testData.getProperty("student4Firstname"));
			this.properties.put("student5Firstname", testData.getProperty("student5Firstname"));
			this.properties.put("student6Firstname", testData.getProperty("student6Firstname"));
			this.properties.put("student7Firstname", testData.getProperty("student7Firstname"));
			this.properties.put("student8Firstname", testData.getProperty("student8Firstname"));
			this.properties.put("student9Firstname", testData.getProperty("student9Firstname"));
			this.properties.put("student10Firstname", testData.getProperty("student10Firstname"));
			this.properties.put("student11Firstname", testData.getProperty("student11Firstname"));
		}
		//Login as default Admin user
		@Test
		public void login(){
			user.loginToSystem(this.properties.get("adminUser"), this.properties.get("password"));
		}
		//Add a user to the teacher role
		@Test
		public void assignTeacherRole(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectTeacherRole();
			frontPageRoles.selectPotentialUser(this.properties.get("teacherFirstname"));
			frontPageRoles.selectAdd();
		}
		//Add the first user to the student role
		@Test
		public void assignStudentRole(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("studentFirstname"));
			frontPageRoles.selectAdd();
		}
		//Add the second user to the student role
		@Test
		public void assignStudent2Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student2Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent3Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student3Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent4Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student4Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent5Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student5Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent6Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student6Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent7Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student7Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent8Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student8Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent9Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student9Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent10Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student10Firstname"));
			frontPageRoles.selectAdd();
		}
		@Test
		public void assignStudent11Role(){
			settingsBlock.navigateTreeMenuToFrontPageRoles();
			frontPageRoles.selectStudentRole();
			frontPageRoles.selectPotentialUser(this.properties.get("student11Firstname"));
			frontPageRoles.selectAdd();
		}
}