package com.moodle.datacreation;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.SeleniumManager;


public class Selenium2ManagerTests {
	//define "driver" in a field
	static RemoteWebDriver driver;
	static SeleniumManager sm;
	public static String runParameters = "properties/runParameters.properties";
	@BeforeClass
	public static void automateTestSetup()throws MalformedURLException, IOException{		
		//Load properties required for test run
		Properties properties = new Properties();
		properties.load(new FileInputStream(runParameters));
		String gridHubURL = properties.getProperty("gridHubURL");
		String browserType = properties.getProperty("browserType");
	//Call setup method
		sm = new SeleniumManager();
		sm.startRemotes(gridHubURL, browserType);
		driver = sm.getRemoteDriver();
		}	
	@Test
	public void getRemoteWebdriver() throws MalformedURLException, IOException{
		//Load properties required for test
		Properties properties = new Properties();
		properties.load(new FileInputStream(runParameters));
		String moodleHomePage = properties.getProperty("moodleHomePage");
		driver.get(moodleHomePage);
	}
	@AfterClass
	static public void logoutAndQuit() {
		sm.teardown();
		}
}