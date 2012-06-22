package com.moodle.test.forum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.BlockNavigation;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * TEST SCENARIO:
 * Students can choose from 4 discussion dispay options and their choice is remembered
 * PREREQUISITES:
 * Re-uses data fom MDLQA-4 which must run first.
 * TEST STEPS:
 * 1. Login as a student and view a discussion thread containing several replies.
 * 2. Select a display option - nested, flat or threaded, oldest or newest first.
 * 3. View a different discussion thread and check that the display option is the same as previously selected.
 */
public class MDLQA05StudentsDisplayOptions {
//define "driver" in a field
		//static RemoteWebDriver driver;
		static FirefoxDriver driver; 
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA05StudentsDisplayOptions(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
			} catch (Exception e) {}
			//login details
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			//course details
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			//forum details
			this.properties.put("nameOfFirstForum", forumTestData.getProperty("nameOfForum1MDLQA04"));
			this.properties.put("introTextOfFirstForum", forumTestData.getProperty("introTextOfForum1MDLQA04"));
			this.properties.put("nameOfSecondForum", forumTestData.getProperty("nameOfForum2MDLQA04"));
			this.properties.put("introTextOfSecondForum", forumTestData.getProperty("introTextOfForum2MDLQA04"));
			//discussion details
			this.properties.put("discussion1Subject", forumTestData.getProperty("discussionSubjectMDLQA04"));
			this.properties.put("discuss1Message", forumTestData.getProperty("discussionTextMDLQA04"));
			this.properties.put("forum1OriginalReply", forumTestData.getProperty("teacherReplySubjectMDLQA04"));
			this.properties.put("forum1OriginalMessage", forumTestData.getProperty("teacherReplyMessageMDLQA04"));
			this.properties.put("forum2Subject", forumTestData.getProperty("studentsReplySubjectMDLQA04"));
			this.properties.put("discussion2Message", forumTestData.getProperty("studentsReplyMessageMDLQA04"));
			this.properties.put("discussion2Subject", forumTestData.getProperty("newName"));
			//Screen Dump locations
			
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
			//sm.startRemotes(gridHubURL, browserType);
			//sm.startChromeDriver(chromeDriverLocation);
			sm.startFirefoxDriver();
			//driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
			driver = sm.getFirefoxDriver();
			driver.get(moodleHomePage);
		}
		//CREATE TEST DATA

		//Log in as a Student
		@Test
		public void studentLogin(){
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("studentUsername"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//Add an additional reply to first discussion
		@Test
		public void addReplyToFirstForum(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfFirstForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion1Subject"));
			discussion.clickReplyToPostLink(this.properties.get("forum1OriginalReply"));
			discussion.enterSubject(this.properties.get("forum1OriginalReply"));
			discussion.enterMessage(this.properties.get("forum1OriginalMessage"));
			discussion.clickPostToForum();
		}
		//Add an additional reply to second discussion
		@Test
		public void addReplyToSecondForum(){
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfSecondForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion2Subject"));
			discussion.clickReplyToPostLink(this.properties.get("discussion2Message"));
			discussion.enterSubject(this.properties.get("forum1OriginalReply"));
			discussion.enterMessage(this.properties.get("forum1OriginalMessage"));
			discussion.clickPostToForum();
		}
		//START TEST
		//Navigate to Discussion Thread
		@Test
		public void navigateToDiscussionThread(){
			Courses course = new Courses(driver);
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfFirstForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion1Subject"));			
		}
		//Change display option to "Display replies flat, with oldest first" and assert display correct
		@Test
		public void displayFlatOldest() throws IOException{
			ForumPosts display = new ForumPosts(driver);
			display.selectDisplayOptionDropdownFlatOldest();
			display.assertFlatOldestOptionSelected();
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayFlatOldestRetained() throws IOException{
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfSecondForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion2Subject"));
			discussion.assertFlatOldestOptionSelected();
		}

		//Change display option to "Display replies flat, with newest first" and assert display correct
		@Test
		public void displayFlatNewest() throws IOException{
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfFirstForum"));
			ForumPosts display = new ForumPosts(driver);
			display.clickDiscussionLink(this.properties.get("discussion1Subject"));
			display.selectDisplayOptionDropdownFlatNewest();
			display.assertFlatNewestOptionSelected();
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayFlatNewestRetained() throws IOException{
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfSecondForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion2Subject"));
			discussion.assertFlatNewestOptionSelected();
		}	
		//Change display option to "Display replies in threaded form" and assert display correct
		@Test
		public void displayThreaded() throws IOException{
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfFirstForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion1Subject"));
			ForumPosts display = new ForumPosts(driver);
			display.selectDisplayOptionDropdownThreaded();
			display.assertThreadedOptionSelected();
			display.assertThreadedLink(this.properties.get("forum1OriginalReply"));
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayThreadedRetained() throws IOException{
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfSecondForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion2Subject"));
			discussion.assertThreadedOptionSelected();
			discussion.assertThreadedLink(this.properties.get("forum1OriginalReply"));
			
		}
		//Change display option to "Display replies in nested form" and assert display correct
		@Test
		public void displayNested(){
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfFirstForum"));
			ForumPosts display = new ForumPosts(driver);
			display.clickDiscussionLink(this.properties.get("discussion1Subject"));
			display.selectDisplayOptionNested();
			display.assertNestedOptionSelected();
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayNestedRetained(){
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickExposedLink(this.properties.get("nameOfSecondForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussion2Subject"));
			discussion.assertNestedOptionSelected();
		}	
		//Tear Down webdriver for @Test methods
		@AfterClass
		static public void Quit() {
		//End Webdriver Session by calling teardown method
			//sm.teardown();
			sm.teardownFirefox();
		}
		//
		//END OF TEST
		//
}