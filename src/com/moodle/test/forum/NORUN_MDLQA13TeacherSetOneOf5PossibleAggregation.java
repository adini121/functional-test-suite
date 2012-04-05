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
 * TEST SCENARIO:
 * A teacher can set one of 5 possible forum rating aggregation types
 * TEST STEPS:
 * This test requires a forum in which several students have posted and in which forum posts have been rated several times.
 * 1. Login as a teacher and set the forum aggregation type to Average.
 * 2. Check that the final grade for each post in the forum and for the whole forum activity is the mean of all the ratings given.
 * 3. Set the forum aggregation type to Count.
 * 4. Check that the final grade for each post in the forum and for the whole forum activity is the number of ratings given (limited to the maximum grade for the forum).
 * 5. Set the forum aggregation type to Max.
 * 6. Check that the final grade for each post in the forum and for the whole forum activity is the highest rating given.
 * 7. Set the forum aggregation type to Min.
 * 8. Check that the final grade for each post in the forum and for the whole forum activity is the lowest rating given.
 * 9. Set the forum aggregation type to Sum.
 * 10. Check that the final grade for each post in the forum and for the whole forum activity is the sum of all the ratings given (limited to the maximum grade for the forum).
 */
public class NORUN_MDLQA13TeacherSetOneOf5PossibleAggregation {
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
		public NORUN_MDLQA13TeacherSetOneOf5PossibleAggregation(){
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
			this.properties.put("nameOfForum", forumTestData.getProperty("MDLQA13nameOfForum"));
			this.properties.put("introTextOfForum", forumTestData.getProperty("MDLQA13introTextOfForum"));
			this.properties.put("student1Subject", forumTestData.getProperty("MDLQA13student1Subject"));
			this.properties.put("student1Message", forumTestData.getProperty("MDLQA13student1Message"));
			this.properties.put("student2Subject", forumTestData.getProperty("MDLQA13student2Subject"));
			this.properties.put("student2Message", forumTestData.getProperty("MDLQA13student2Message"));
			this.properties.put("scale", forumTestData.getProperty("MDLQA13scale"));
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
		//SETUP TEST DATA
		//Login as teacher
		@Test
		public void loginAsTeacher() {
			teacherLogin();
		}
		private void teacherLogin() {
			Users teacherLogin = new Users(driver);
			teacherLogin.selectLoginLink();
			teacherLogin.enterUsername(this.properties.get("teacherUsername"));
			teacherLogin.enterPassword(this.properties.get("password"));
			teacherLogin.clickLoginButton();
		}
		//Create a forum
		@Test
		public void createForumAggregation(){
		//Select the course
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
		//select forum activity from drop down on courses page 
			CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
			activity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			ForumAddForum addForum = new ForumAddForum(driver);
			addForum.selectForumTypeStandardGeneral();
			addForum.enterForumName(this.properties.get("nameOfForum"));
			addForum.enterForumIntro(this.properties.get("introTextOfForum"));
			addForum.selectAggregateTypeAveRatings();
			addForum.selectScale(this.properties.get("scale"));
			addForum.clickSaveAndRetToCourse();
		}
		//Logout teacher
		@Test
		public void logoutTeacher(){
			teacherLogout();
		}
		private void teacherLogout() {
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
		//Student posts to forum
		@Test
		public void studentPostsToForum(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
		//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("student1Subject"));
			discussion.enterMessage(this.properties.get("student1Message"));
			discussion.clickPostToForum();
		}
		//Log out Student
		@Test
		public void logoutStudent(){
			Users student = new Users(driver);
			student.selectLogout();
		}
		//Login as Student 2
		@Test
		public void loginAsStudent2(){
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("student2Username"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//Student 2 posts to forum
		//Student posts to forum
		@Test
		public void student2PostsToForum(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
		//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("student1Subject"));
			discussion.clickPostToForum();
		}
		//Teacher logs in to grade
		@Test
		public void teacherLogin2(){
			teacherLogin();
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