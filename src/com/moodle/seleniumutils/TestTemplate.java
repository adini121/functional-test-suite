package com.moodle.seleniumutils;

import com.moodle.test.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.Users;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * DESCRIPTION:
 * 
 * 
 * TEST PRE-REQUISITES:
 * 
 * 
 * TEST SCENARIO:
 * 1. 
 */
public class TestTemplate {
		//The WebDriver
		static RemoteWebDriver driver;
		//The Selenium Manager class
		static SeleniumManager sm;
		//Test Data Property Files
		//Run parameters are static and apply to all tests
		public static String runParameters = "properties/runParameters.properties";
		//Test data files.
		public static String someTestData = "properties/data/user/Forum/TESTDATA.properties";
		//Data Hashmap
		private Map<String, String> properties = new HashMap<String, String>();
		//Construct page objects e.g. Users.
		private Users user = new Users(driver);
		//Load test data from properties file
		public TestTemplate(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties testData = new Properties();
			try {
				testData.load(new FileInputStream(someTestData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", testData.getProperty("teacherUsername"));
		}
		
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
			sm.startRemotes(gridHubURL);
			driver = sm.getRemoteDriver();
			driver.get(moodleHomePage);
		}
		/*
		 * START OF TEST
		 */
		
		/*
		 * END OF TEST
		 */
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}
}