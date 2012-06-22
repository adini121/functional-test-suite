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
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.ForumAddForum;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * A teacher can set one of 4 possible forum subscription options
 * TEST SCENARIO:
 * 1. Login as a teacher and start a discussion in a single simple discussion forum.
 * 2. Login as a teacher and reply to the discussion.
 * 3. Check that, as a student, it is not possible to start a discussion.
 */
public class MDLQA09SingleSimpleDiscussionOnlyTeachersStart {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		//Weekly outline section
		//public String outlineSection = "1";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA09SingleSimpleDiscussionOnlyTeachersStart(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
			this.properties.put("nameSimple", forumTestData.getProperty("nameOfForumSingleSimple"));
			this.properties.put("introText", forumTestData.getProperty("simpleForumIntroText"));
			this.properties.put("replySubject", forumTestData.getProperty("simpleForumReplySubject"));
			this.properties.put("replyText", forumTestData.getProperty("simpleForumReplyText"));
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
		//Start a discussion in a single simple forum
		@Test
		public void startDiscussion(){
		//Select the course
			Courses selectCourse = new Courses(driver);
			selectCourse.clickCourseLink(this.properties.get("courseName"));
			selectCourse.clickTurnEditingOn();
		//select activity drop down on courses page 
			CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
			activity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			ForumAddForum addForum = new ForumAddForum(driver);
			addForum.enterForumName(this.properties.get("nameSimple"));
			addForum.enterForumIntro(this.properties.get("introText"));
			addForum.selectForumTypeSimple();
			addForum.clickSaveAndRetToCourse();
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
		//Reply to the discussion
		@Test
		public void replyToDiscussion(){
			Courses selectCourse = new Courses(driver);
			selectCourse.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameSimple"));
			ForumPosts reply = new ForumPosts(driver);
			reply.clickReplyToPostLink(this.properties.get("introText"));
			reply.enterSubject(this.properties.get("replySubject"));
			reply.enterMessage(this.properties.get("replyText"));
			reply.clickPostToForum();
		}
		//Try to start a new discussion
		@Test
		public void tryToStartANewDiscussion() throws Exception{
			Courses studentCantAddDiscussion = new Courses(driver);
			studentCantAddDiscussion.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			studentCantAddDiscussion.assertTurnEditingOnIsDisabled();
		}
		//Log Student out
		@Test
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