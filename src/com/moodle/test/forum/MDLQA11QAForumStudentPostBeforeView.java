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
import com.moodle.testmanager.pageObjectModel.ForumAddForm;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * TEST SCENARIO:
 * In a Q & A forum students must post first before they can view other posts
 * TEST STEPS:
 * 1. Login as a student and view a Q & A forum.
 * 2. Check that forum posts are not viewable.
 * 3. Post in the forum.
 * MANUAL TESTING STEPS:
 * 4. Wait for the maximum time to elapse.
 * 5. Check that forum posts are now viewable.
 */
public class MDLQA11QAForumStudentPostBeforeView {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA11QAForumStudentPostBeforeView(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
				forumTestData.load(new FileInputStream(usersData));
			} catch (Exception e) {}
			//Login details
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("student2Username", forumTestData.getProperty("student2Username"));
			this.properties.put("password", forumTestData.getProperty("password"));
			//Course details
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
			//Forum details
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
			this.properties.put("nameOfForum", forumTestData.getProperty("MDLQA11nameOfForum"));
			this.properties.put("introTextOfForum", forumTestData.getProperty("MDLQA11introTextOfForum"));
			this.properties.put("teacherSubject", forumTestData.getProperty("MDLQA11teacherSubject"));
			this.properties.put("teacherMessage", forumTestData.getProperty("MDLQA11teacherMessage"));
			this.properties.put("studentSubject", forumTestData.getProperty("MDLQA11studentSubject"));
			this.properties.put("studentMessage", forumTestData.getProperty("MDLQA11studentMessage"));
			this.properties.put("student2Subject", forumTestData.getProperty("MDLQA11student2Subject"));
			this.properties.put("student2Message", forumTestData.getProperty("MDLQA11student2Message"));
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
		//Create QA forum
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
			ForumAddForm addForum = new ForumAddForm(driver);
			addForum.enterNameField(this.properties.get("nameOfForum"));
			addForum.enterIntroField(this.properties.get("introTextOfForum"));
			addForum.selectForumTypeQA();
			addForum.clickSaveAndRetToCourse();
			course.clickTurnEditingOff();
		}
		//Teacher creates a question
		@Test
		public void teacherCreateDiscussion() {
			//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewQuestionButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubjectField(this.properties.get("teacherSubject"));
			discussion.enterMessage(this.properties.get("teacherMessage"));
			discussion.clickPostToForum();
		}
		//Logout teacher
		@Test
		public void logoutTeacher(){
			Users teacherLogout = new Users(driver);
			teacherLogout.selectLogout();
		}
		//Login as a the first student
		@Test
		public void loginAsStudent(){
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("studentUsername"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//Reply to question as first student
		@Test
		public void replyDiscussion(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("teacherSubject"));
			discussion.clickReplyToPostLink(this.properties.get("teacherMessage"));
			discussion.enterSubjectField(this.properties.get("studentSubject"));
			discussion.enterMessage(this.properties.get("studentMessage"));
			discussion.clickPostToForum();
			discussion.assertForumPostSubjectSuccessful(this.properties.get("studentSubject"));
			discussion.assertForumPostMessageSuccessful(this.properties.get("studentMessage"));
		}
		//Log out Student 1
		@Test
		public void logoutStudent1(){
			Users student = new Users(driver);
			student.selectLogout();
		}
		//Log in Student 2
		@Test
		public void loginStudent2(){
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("student2Username"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//Check forum posts are not viewable
		@Test
		public void checkPostsCannotBeViewed() throws Exception{
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("teacherSubject"));
			discussion.assertSubjectOrMessageNotPresent(this.properties.get("studentSubject"));
			discussion.assertSubjectOrMessageNotPresent(this.properties.get("studentMessage"));
			discussion.assertSubjectHidden();
		}
		//Reply to question
		@Test
		public void student2RepliesToPost(){
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickReplyToPostLink(this.properties.get("teacherMessage"));
			discussion.enterSubjectField(this.properties.get("student2Subject"));
			discussion.enterMessage(this.properties.get("student2Message"));
			discussion.clickPostToForum();
			discussion.assertForumPostSubjectSuccessful(this.properties.get("student2Subject"));
			discussion.assertForumPostMessageSuccessful(this.properties.get("student2Message"));
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