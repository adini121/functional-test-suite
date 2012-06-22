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
 * TEST SCENARIO:
 * By default, a new course contains a news forum in which only teachers can post and subscription is forced
 * TEST STEPS:
 * 1. Login as a teacher and check that you can start a discussion and post a reply in the news forum.
 * 2. Login as a student and check that there is no option to post in the news forum.
 * 3. Check that the news forum forces everyone to be subscribed.
 */
public class MDLQA06NewCourseNewsForum {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA06NewCourseNewsForum(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
			} catch (Exception e) {}
			//Login details
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			//Course details
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
			//Forum details
			this.properties.put("newsForum", forumTestData.getProperty("newsForum"));
			this.properties.put("newsSubject", forumTestData.getProperty("newsSubjectMDLQA06"));
			this.properties.put("newsMessage", forumTestData.getProperty("newsMessageMDLQA06"));
			this.properties.put("replySubject", forumTestData.getProperty("replySubjectMDLQA06"));
			this.properties.put("replyMessage", forumTestData.getProperty("replyMessageMDLQA06"));
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
		//Login as teacher
		@Test
		public void loginAsTeacher() throws FileNotFoundException, IOException{
		//Run test
			Users teacherLogin = new Users(driver);
			teacherLogin.selectLoginLink();
			teacherLogin.enterUsername(this.properties.get("teacherUsername"));
			teacherLogin.enterPassword(this.properties.get("password"));
			teacherLogin.clickLoginButton();
		}
		//Start Discussion in news forum
		@Test
		public void startDiscussionNews(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum newsForum = new Forum(driver);
			newsForum.clickForumLink(this.properties.get("newsForum"));
			newsForum.clickAddNewTopicButton();
			ForumPosts newsDiscussion = new ForumPosts(driver);
			newsDiscussion.enterSubject(this.properties.get("newsSubject"));
			newsDiscussion.enterMessage(this.properties.get("newsMessage"));
			newsDiscussion.clickPostToForum();
			newsDiscussion.clickDiscussionLink(this.properties.get("newsSubject"));
			newsDiscussion.assertForumPostSubjectSuccessful(this.properties.get("newsSubject"));
			newsDiscussion.assertForumPostMessageSuccessful(this.properties.get("newsMessage"));
		}
		//Post Reply in news forum
		@Test
		public void postReply(){
			ForumPosts postReply = new ForumPosts(driver);
			postReply.clickDiscussionLink(this.properties.get("newsSubject"));
			postReply.clickReplyToPostLink(this.properties.get("newsMessage"));
			postReply.enterSubject(this.properties.get("replySubject"));
			postReply.enterMessage(this.properties.get("replyMessage"));
			postReply.clickPostToForum();
			postReply.assertForumPostSubjectSuccessful(this.properties.get("replySubject"));
			postReply.assertForumPostMessageSuccessful(this.properties.get("replyMessage"));
		}
		//Log Teacher out
		@Test
		public void teacherLogout(){
			Users teacherLogout = new Users(driver);
			teacherLogout.selectLogout();
		}
		//Login as student
		@Test
		public void loginAsStudent() throws FileNotFoundException, IOException{
		//Run test
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("studentUsername"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//News forces subscription
		@Test
		public void subscriptionForced(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum newsForum = new Forum(driver);
			newsForum.clickForumLink(this.properties.get("newsForum"));
			newsForum.assertSubscriptionForced();
		}
		//Student cannot post
		@Test
		public void studentCannotPost() throws Exception{
			Forum cantCreateDiscussion = new Forum(driver);
			cantCreateDiscussion.assertAddNewTopicButtonDisabled();
			cantCreateDiscussion.clickForumLink(this.properties.get("newsForum"));
			ForumPosts cannotPost = new ForumPosts(driver);
			cannotPost.clickDiscussionLink(this.properties.get("newsSubject"));
			cannotPost.assertForumPostSubjectSuccessful(this.properties.get("newsSubject"));
			cannotPost.assertForumPostMessageSuccessful(this.properties.get("newsMessage"));
			cannotPost.assertForumPostMessageSuccessful(this.properties.get("replyMessage"));
			cannotPost.assertReplyLinkNotPresent(this.properties.get("newsMessage"));
			cannotPost.assertReplyLinkNotPresent(this.properties.get("replyMessage"));
		}
		//Log Student out
	//	@Test
		public void studentLogout(){
			Users studentLogout = new Users(driver);
			studentLogout.selectLogout();
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