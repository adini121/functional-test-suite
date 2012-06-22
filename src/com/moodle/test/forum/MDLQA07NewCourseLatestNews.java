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
import com.moodle.testmanager.pageObjectModel.BlockNews;
import com.moodle.testmanager.pageObjectModel.BlockSettings;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.CoursesEditCourseSettings;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.ForumPosts;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * TEST SCENARIO:
 * By default, a new course contains a Latest News block which displays a specific number of recent discussions from the news forum.
 * TEST STEPS:
 * 1. Login as a teacher and create 3 discussions in the news forum.
 * 2. Check that all discussion titles are displayed in the Latest News block.
 * 3. Change the 'News items to show' in the course settings to 2.
 * 4. Check that only the most recent 2 discussion titles are displayed in the Latest News block.
 * 5. Change the 'News items to show' in the course settings to 0.
 * 6. Check that the Latest News block is not displayed at all.
 */
public class MDLQA07NewCourseLatestNews {
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
		public MDLQA07NewCourseLatestNews(){
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
			this.properties.put("newsSubject1", forumTestData.getProperty("newsSubject1MDLQA07"));
			this.properties.put("newsMessage1", forumTestData.getProperty("newsMessage1MDLQA07"));
			this.properties.put("newsSubject2", forumTestData.getProperty("newsSubject2MDLQA07"));
			this.properties.put("newsMessage2", forumTestData.getProperty("newsMessage2MDLQA07"));
			this.properties.put("newsSubject3", forumTestData.getProperty("newsSubject3MDLQA07"));
			this.properties.put("newsMessage3", forumTestData.getProperty("newsMessage3MDLQA07"));
			this.properties.put("selectShow2Items", forumTestData.getProperty("selectShow2Items"));
			this.properties.put("selectShow0Items", forumTestData.getProperty("selectShow0Items"));
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
		public void loginAsTeacher() throws FileNotFoundException, IOException{
			Users teacherLogin = new Users(driver);
			teacherLogin.selectLoginLink();
			teacherLogin.enterUsername(this.properties.get("teacherUsername"));
			teacherLogin.enterPassword(this.properties.get("password"));
			teacherLogin.clickLoginButton();
		}
		//Delete news from previous test
		@Test
		public void deleteNews(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("newsForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.deleteAllForumPosts();
		}
		//Start Three Discussions in news forum
		@Test
		public void startDiscussionNews(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOff();
			//Create discussion 1
			Forum newsForum = new Forum(driver);
			newsForum.clickForumLink(this.properties.get("newsForum"));
			newsForum.clickAddNewTopicButton();
			ForumPosts newsDiscussion = new ForumPosts(driver);
			newsDiscussion.enterSubject(this.properties.get("newsSubject1"));
			newsDiscussion.enterMessage(this.properties.get("newsMessage1"));
			newsDiscussion.clickPostToForum();
			newsDiscussion.clickDiscussionLink(this.properties.get("newsSubject1"));
			//Pass/Fail criteria
			newsDiscussion.assertForumPostSubjectSuccessful(this.properties.get("newsSubject1"));
			newsDiscussion.assertForumPostMessageSuccessful(this.properties.get("newsMessage1"));
			//Create discussion 2
			newsForum.clickForumLink(this.properties.get("newsForum"));
			newsForum.clickAddNewTopicButton();
			newsDiscussion.enterSubject(this.properties.get("newsSubject2"));
			newsDiscussion.enterMessage(this.properties.get("newsMessage2"));
			newsDiscussion.clickPostToForum();
			newsDiscussion.clickDiscussionLink(this.properties.get("newsSubject2"));
			//Pass/Fail criteria
			newsDiscussion.assertForumPostSubjectSuccessful(this.properties.get("newsSubject2"));
			newsDiscussion.assertForumPostMessageSuccessful(this.properties.get("newsMessage2"));
			//Create discussion 3
			newsForum.clickForumLink(this.properties.get("newsForum"));
			newsForum.clickAddNewTopicButton();
			newsDiscussion.enterSubject(this.properties.get("newsSubject3"));
			newsDiscussion.enterMessage(this.properties.get("newsMessage3"));
			newsDiscussion.clickPostToForum();
			newsDiscussion.clickDiscussionLink(this.properties.get("newsSubject3"));
			//Pass/Fail criteria
			newsDiscussion.assertForumPostSubjectSuccessful(this.properties.get("newsSubject3"));
			newsDiscussion.assertForumPostMessageSuccessful(this.properties.get("newsMessage3"));
		}
		//Check that all discussions appear in the news block
		@Test
		public void check3InNewsBlock(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseShortname"));
			BlockNews checkNewsBlock = new BlockNews(driver);
			checkNewsBlock.assertDiscussionInNewsBlock(this.properties.get("newsSubject1"));
			checkNewsBlock.assertDiscussionInNewsBlock(this.properties.get("newsSubject2"));
			checkNewsBlock.assertDiscussionInNewsBlock(this.properties.get("newsSubject3"));
		}
		//Change News Items to show settings to 2
		@Test
		public void show2Items(){
			BlockSettings settings = new BlockSettings(driver);
			settings.navigateEditCourseSettings();
			CoursesEditCourseSettings editCourse = new CoursesEditCourseSettings(driver);
			editCourse.selectNewsItemsToShow(this.properties.get("selectShow2Items"));
			editCourse.clickSaveChanges();
		}
		//Check that only the most recent 2 discussion titles are displayed in the Latest News block.
		@Test
		public void check2InNewsBlock(){
			BlockNews checkNewsBlock = new BlockNews(driver);
			checkNewsBlock.assertDiscussionInNewsBlock(this.properties.get("newsSubject2"));
			checkNewsBlock.assertDiscussionInNewsBlock(this.properties.get("newsSubject3"));
		}
		//Change the 'News items to show' in the course settings to 0.
		@Test
		public void show0Items(){
			BlockSettings settings = new BlockSettings(driver);
			settings.navigateEditCourseSettings();
			CoursesEditCourseSettings editCourse = new CoursesEditCourseSettings(driver);
			editCourse.selectNewsItemsToShow(this.properties.get("selectShow0Items"));
			editCourse.clickSaveChanges();
		}
		//Check that the Latest News block is not displayed at all.
		@Test
		public void checkNewsBlockNotDisplayed() throws Exception{
			BlockNews checkNewsBlock = new BlockNews(driver);
			checkNewsBlock.assertNewsBlockNotDisplayed();
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