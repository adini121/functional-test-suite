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
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * DESCRIPTION:
 * This test requires a forum in which several students have posted.
 * Re-uses test data from MDLQA09.
 * TEST SCENARIO:
 * 1. Login as a teacher and select a forum post and edit it.
 * 2. Check that the sentence 'Edited by xxx - original submission xxx' is appended to the post.
 * 3. Select a forum post and delete it.
 */
public class MDLQA16TeacherEditDeletePost {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String testData = "properties/data/user/Forum/forumData.properties";
		public static String userData = "properties/data/user/Users/usersData.properties";
		//Weekly outline section
		//public String outlineSection = "1";
		private Map<String, String> properties = new HashMap<String, String>();
		private Courses course = new Courses(driver);
		private Forum forum = new Forum(driver);
		private Users teacherLogin = new Users(driver);
		private ForumPosts posts = new ForumPosts(driver);
		//Load test data from properties file
		public MDLQA16TeacherEditDeletePost(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(testData));
				forumTestData.load(new FileInputStream(userData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("nameOfForumSingleSimple", forumTestData.getProperty("nameOfForumSingleSimple"));
			this.properties.put("simpleForumReplyText", forumTestData.getProperty("simpleForumReplyText"));
			this.properties.put("editedText", forumTestData.getProperty("MDLQA16editedText"));
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
			//sm.startRemotes(gridHubURL, browserType);
			//sm.startChromeDriver(chromeDriverLocation);
			sm.startFirefoxDriver();
			//driver = sm.getRemoteDriver();
			//driver = sm.getChromeDriver();
			driver = sm.getFirefoxDriver();
			driver.get(moodleHomePage);
		}
		@Test
		//login teacher
		public void loginAsTeacher() {
			teacherLogin.selectLoginLink();
			teacherLogin.enterUsername(this.properties.get("teacherUsername"));
			teacherLogin.enterPassword(this.properties.get("password"));
			teacherLogin.clickLoginButton();
		}
		@Test
		//Edit Post
		public void editPost() throws Exception {
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForumSingleSimple"));
			posts.clickEditLink(this.properties.get("simpleForumReplyText"));
			posts.clickSaveChanges();
			posts.assertTeacherEdit();
		}
		//Delete Post
		@Test
		public void deletePost() {
			posts.clickDeleteLink(this.properties.get("simpleForumReplyText"));
			posts.clickContinue();
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