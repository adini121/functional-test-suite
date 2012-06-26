/**
 * Create a test course as a pre-requisite for any testing
 */
package com.moodle.test.datacreation;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;

public class MDLQADT1AddCourse extends TestRunSettings {
		//Test Data Property Files
		public static String courseData = "properties/data/user/Courses/courseData.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQADT1AddCourse(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(courseData));
				testData.load(new FileInputStream(usersData));
			} catch (Exception e) {}
			this.properties.put("adminUser", testData.getProperty("adminUser"));
			this.properties.put("password", testData.getProperty("password"));
			this.properties.put("courseName", testData.getProperty("courseName"));
			this.properties.put("courseShortname", testData.getProperty("courseShortname"));
		}
		@Test
		public void login(){
		//Log in as the admin user
		user.loginToSystem(this.properties.get("adminUser"), this.properties.get("password"));
		}
		//Test to add a course
		@Test
		public void addCourse() throws MalformedURLException {
		course.clickAddCourse();
		course.enterFullname(this.properties.get("courseName"));
		course.enterShortname(this.properties.get("courseShortname"));
		course.clickSubmitButton();
		}		
}