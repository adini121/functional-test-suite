package com.moodle.test.forum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.seleniumutils.SeleniumManager;
import com.moodle.testmanager.pageObjectModel.BlockSettings;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.ForumAddForum;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.ProfileEdit;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * TEST SCENARIO:
 * A teacher can set one of 3 possible options for tracking read forum posts
 * TEST STEPS:
 * 1. Login as a teacher and create a forum with 'Read tracking for this forum' set to Optional in the forum settings.
 * 2. Login as a student and check you can choose whether to turn tracking on or off. The link to do this should be in the Settings block under Forum administration.
 * 3. Login as a teacher and create a forum with 'Read tracking for this forum' set to Off.
 * 4. Login as a student and check that read forum posts are not tracked and that you have no option to enable the feature.
 * 5. Login as a teacher and create a forum with 'Read tracking for this forum' set to On.
 * 6. Login as a student and check that read forum posts are tracked and that you have no option to disable the feature.
 */
public class MDLQA03TeacherSets1Of3TrackingOptions {
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
		public MDLQA03TeacherSets1Of3TrackingOptions(){
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
			this.properties.put("nameOfForumOptional", forumTestData.getProperty("MDLQA03nameOfForumOptional"));
			this.properties.put("introTextOfForumOptional", forumTestData.getProperty("MDLQA03introTextOfForumOptional"));
			this.properties.put("teacherSubjectOptional", forumTestData.getProperty("MDLQA03teacherSubjectOptional"));
			this.properties.put("teacherMessageOptional", forumTestData.getProperty("MDLQA03teacherMessageOptional"));
			this.properties.put("nameOfForumOff", forumTestData.getProperty("MDLQA03nameOfForumOff"));
			this.properties.put("introTextOfForumOff", forumTestData.getProperty("MDLQA03introTextOfForumOff"));
			this.properties.put("teacherSubjectOff", forumTestData.getProperty("MDLQA03teacherSubjectOff"));
			this.properties.put("teacherMessageOff", forumTestData.getProperty("MDLQA03teacherMessageOff"));
			this.properties.put("nameOfForumOn", forumTestData.getProperty("MDLQA03nameOfForumOn"));
			this.properties.put("introTextOfForumOn", forumTestData.getProperty("MDLQA03introTextOfForumOn"));
			this.properties.put("teacherSubjectOn", forumTestData.getProperty("MDLQA03teacherSubjectOn"));
			this.properties.put("teacherMessageOn", forumTestData.getProperty("MDLQA03teacherMessageOn"));
			this.properties.put("off", forumTestData.getProperty("off"));
			this.properties.put("on", forumTestData.getProperty("on"));
		}
		@BeforeClass
		public static void automateTestSetup() throws FileNotFoundException,
				IOException {
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
		//Create a forum with 'Read tracking for this forum' set to Optional in the forum settings.
		@Test
		public void createForumOptional(){
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
			addForum.enterForumName(this.properties.get("nameOfForumOptional"));
			addForum.enterForumIntro(this.properties.get("introTextOfForumOptional"));
			addForum.clickSaveAndRetToCourse();
			//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumOptional"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("teacherSubjectOptional"));
			discussion.enterMessage(this.properties.get("teacherMessageOptional"));
			discussion.clickPostToForum();
		}
		//Create a forum with 'Read tracking for this forum' set to Off.
		@Test
		public void createForumOff(){
		//Select the course
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseShortname"));
		//select forum activity from drop down on courses page 
			CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
			activity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			ForumAddForum addForum = new ForumAddForum(driver);
			addForum.enterForumName(this.properties.get("nameOfForumOff"));
			addForum.enterForumIntro(this.properties.get("introTextOfForumOff"));
			addForum.selectForumTypeStandardGeneral();
			addForum.selectReadTrackingOption(this.properties.get("off"));
			addForum.clickSaveAndRetToCourse();
		//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumOff"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("teacherSubjectOff"));
			discussion.enterMessage(this.properties.get("teacherMessageOff"));
			discussion.clickPostToForum();
		}
		//Create a forum with 'Read tracking for this forum' set to On.
		@Test
		public void createForumOn(){
		//Select the course
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseShortname"));
		//select forum activity from drop down on courses page 
			CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
			activity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			ForumAddForum addForum = new ForumAddForum(driver);
			addForum.enterForumName(this.properties.get("nameOfForumOn"));
			addForum.enterForumIntro(this.properties.get("introTextOfForumOn"));
			addForum.selectForumTypeStandardGeneral();
			addForum.selectReadTrackingOption(this.properties.get("on"));
			addForum.clickSaveAndRetToCourse();
			//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumOn"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("teacherSubjectOn"));
			discussion.enterMessage(this.properties.get("teacherMessageOn"));
			discussion.clickPostToForum();
			course.clickCourseLink(this.properties.get("courseShortname"));
			course.clickTurnEditingOff();
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
		//Ensure yes highlight new posts is set to yes
		@Test
		public void readTrackingTurnedOn(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			BlockSettings profile = new BlockSettings(driver);
			profile.navigateEditProfile();
			ProfileEdit profileEdit = new ProfileEdit(driver);
			profileEdit.selectForumTrackingOn();
			profileEdit.clickUpdateProfile();
		}
		//check you can choose whether to turn tracking on or off. The link to do this should be in the Settings block under Forum administration.
		@Test
		public void trackingOptional() throws Exception{
			Courses course = new Courses(driver);
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			course.assertTrackingEnabled(this.properties.get("nameOfForumOptional"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumOptional"));
			BlockSettings settings = new BlockSettings(driver);
			settings.navigateDontTrackUnread();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			course.assertTrackingDisabled(this.properties.get("nameOfForumOptional"));
		}
		//Check that read forum posts are not tracked and that you have no option to enable the feature.
		@Test
		public void trackingOff() throws Exception{
			Courses course = new Courses(driver);
			course.assertTrackingDisabled(this.properties.get("nameOfForumOff"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumOff"));
			BlockSettings navigate = new BlockSettings(driver);
			navigate.assertTrackingCannotBeEnabled();
			course.clickCourseLink(this.properties.get("courseShortname"));
		}
		//Check that read forum posts are tracked and that you have no option to disable the feature.
		@Test
		public void trackingOn() throws Exception{
			Courses course = new Courses(driver);
			course.assertTrackingEnabled(this.properties.get("nameOfForumOn"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumOff"));
			BlockSettings navigate = new BlockSettings(driver);
			navigate.assertTrackingCannotBeDisabled();
		}
		//Log out Student
		@Test
		public void logoutStudent(){
			Users student = new Users(driver);
			student.selectLogout();
		}
		//@AfterClass
		public static void Quit() {
		//End Webdriver Session by calling teardown method
			sm.teardown();
		}
		//
		//END OF TEST
		//
}