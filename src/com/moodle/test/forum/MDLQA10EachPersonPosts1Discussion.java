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
import com.moodle.testmanager.pageObjectModel.BlockNavigation;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.ForumAddForum;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * TEST SCENARIO:
 * In a 'Each person posts one discussion' forum students can start exactly one discussion and can reply to all discussions
 * TEST STEPS:
 * 1. Login as a student and start a discussion in a 'Each person posts one discussion' forum.
 * 2. Check that it is not possible to start another discussion in the forum.
 * 3. Reply to several discussions.
 */
public class MDLQA10EachPersonPosts1Discussion {
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
		public MDLQA10EachPersonPosts1Discussion(){
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
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
			this.properties.put("nameOfForum", forumTestData.getProperty("MDLQA10nameOfForum"));
			this.properties.put("introTextOfForum", forumTestData.getProperty("MDLQA10introTextOfForum"));
			this.properties.put("teacherSubject", forumTestData.getProperty("MDLQA10teacherSubject"));
			this.properties.put("teacherMessage", forumTestData.getProperty("MDLQA10teacherMessage"));
			this.properties.put("studentSubject", forumTestData.getProperty("MDLQA10studentSubject"));
			this.properties.put("studentMessage", forumTestData.getProperty("MDLQA10studentMessage"));
			this.properties.put("replySubjectStudent", forumTestData.getProperty("MDLQA10replySubjectStudent"));
			this.properties.put("replyMessageStudent", forumTestData.getProperty("MDLQA10replyMessageStudent"));
			this.properties.put("replySubjectTeacher", forumTestData.getProperty("MDLQA10replySubjectTeacher"));
			this.properties.put("replyMessageTeacher", forumTestData.getProperty("MDLQA10replyMessageTeacher"));
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
		//START TEST
		//Login as teacher
		@Test
		public void loginAsTeacher() {
			Users teacherLogin = new Users(driver);
			teacherLogin.selectLoginLink();
			teacherLogin.enterUsername(this.properties.get("teacherUsername"));
			teacherLogin.enterPassword(this.properties.get("password"));
			teacherLogin.clickLoginButton();
		}
		//Create Each person posts one discussion forum
		@Test
		public void createForum(){
		//Select the course
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
		//select forum activity from drop down on courses page 
			CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
			activity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			ForumAddForum addForum = new ForumAddForum(driver);
			addForum.enterForumName(this.properties.get("nameOfForum"));
			addForum.enterForumIntro(this.properties.get("introTextOfForum"));
			addForum.selectForumTypeEachPerson();
			addForum.clickSaveAndRetToCourse();
			course.clickTurnEditingOff();
		}
		//Teacher creates a discussion
		@Test
		public void teacherCreateDiscussion() {
			//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("teacherSubject"));
			discussion.enterMessage(this.properties.get("teacherMessage"));
			discussion.clickPostToForum();
		}
		//Logout teacher
		@Test
		public void logoutTeacher(){
			Users teacherLogout = new Users(driver);
			teacherLogout.selectLogout();
		}
		//Login as a student
		@Test
		public void loginAsStudent(){
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("studentUsername"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//Start a Discussion
		@Test
		public void startDiscussion(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("studentSubject"));
			discussion.enterMessage(this.properties.get("studentMessage"));
			discussion.clickPostToForum();
		}
		//Check that it is not possible to start another discussion in the forum.
		@Test
		public void checkNoDiscussion() throws Exception{
			Forum forum = new Forum(driver);
			forum.assertAddNewTopicButtonDisabled();
		}
		//Reply to several discussions
		@Test
		public void replyDiscussions(){
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("studentSubject"));
			discussion.clickReplyToPostLink(this.properties.get("studentMessage"));
			discussion.enterSubject(this.properties.get("replySubjectStudent"));
			discussion.enterMessage(this.properties.get("replyMessageStudent"));
			discussion.clickPostToForum();
			discussion.assertForumPostSubjectSuccessful(this.properties.get("replySubjectStudent"));
			discussion.assertForumPostMessageSuccessful(this.properties.get("replyMessageStudent"));
			BlockNavigation navigation = new BlockNavigation(driver);
			navigation.clickHome();
			navigation.clickExposedLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			discussion.clickDiscussionLink(this.properties.get("teacherSubject"));
			discussion.clickReplyToPostLink(this.properties.get("teacherMessage"));
			discussion.enterSubject(this.properties.get("replySubjectTeacher"));
			discussion.enterMessage(this.properties.get("replyMessageTeacher"));
			discussion.clickPostToForum();
			discussion.assertForumPostSubjectSuccessful(this.properties.get("replySubjectTeacher"));
			discussion.assertForumPostMessageSuccessful(this.properties.get("replyMessageTeacher"));
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