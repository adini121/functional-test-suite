package com.moodle.seleniumutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * DESCRIPTION:
 * 
 * TEST SCENARIO:
 * 1. 
 */
public class TestTemplate {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String testData = "properties/data/user/Forum/TESTDATA.properties";
		//Weekly outline section
		//public String outlineSection = "1";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public TestTemplate(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties someTestData = new Properties();
			try {
				someTestData.load(new FileInputStream(testData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", someTestData.getProperty("teacherUsername"));
		}
		//
		//START OF TEST
		//
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

		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}
		//
		//END OF TEST
		//
}