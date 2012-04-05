
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
import com.moodle.testmanager.pageObjectModel.ForumSplit;
import com.moodle.testmanager.pageObjectModel.Users;
/**
 * TEST SCENARIO:
 * Teachers can split discussions and move discussions between forums in the same course 
 * TEST STEPS:
 * 1. Login as a teacher and view a discussion thread.
 * 2. Split one or more posts from the thread and give them a new discussion title.
 * 3. Check that the thread has been split into two discussions, one of which with a new title.
 * 4. View another discussion thread.
 * 5. Move it to another forum by selecting the forum in the 'Move this discussion to...' dropdown menu and then clicking the Move button.
 * 6. Check that the thread has been moved to the selected forum.
 */
public class MDLQA04TeacherSplitAndMoveDiscussions {
	//define "driver" in a field
		static RemoteWebDriver driver;
		static SeleniumManager sm;
		//TEST DATA
		//Test Data Property Files
		public static String runParameters = "properties/runParameters.properties";
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA04TeacherSplitAndMoveDiscussions(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
			} catch (Exception e) {}
			//login details
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			//course details
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSectionMDLQA04"));
			//forum details
			this.properties.put("nameOfForum", forumTestData.getProperty("nameOfForum1MDLQA04"));
			this.properties.put("introTextOfForum", forumTestData.getProperty("introTextOfForum1MDLQA04"));
			this.properties.put("nameOfForumForMove", forumTestData.getProperty("nameOfForum2MDLQA04"));
			this.properties.put("introTextOfForumForMove", forumTestData.getProperty("introTextOfForum2MDLQA04"));
			//discussion details
			this.properties.put("discussSplitSubject", forumTestData.getProperty("discussionSubjectMDLQA04"));
			this.properties.put("discussSplitMessage", forumTestData.getProperty("discussionTextMDLQA04"));
			this.properties.put("teachersPostReplySubject", forumTestData.getProperty("teacherReplySubjectMDLQA04"));
			this.properties.put("teachersPostReplyMessage", forumTestData.getProperty("teacherReplyMessageMDLQA04"));
			this.properties.put("studentsPostReplySubject", forumTestData.getProperty("studentsReplySubjectMDLQA04"));
			this.properties.put("studentsPostReplyMessage", forumTestData.getProperty("studentsReplyMessageMDLQA04"));
			this.properties.put("newName", forumTestData.getProperty("newName"));
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
			sm.startRemotes(gridHubURL, browserType);
			driver = sm.getRemoteDriver();
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
		//SETUP TEST DATA
		//Start a discussion in a standard forum
		@Test
		public void startDiscussion(){
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
			addForum.selectSubscriptionTypeForced();
			addForum.selectForumTypeStandardGeneral();
			addForum.clickSaveAndRetToCourse();
		//Adding a second forum
			activity.selectForum(this.properties.get("outlineSection"));
			addForum.enterForumName(this.properties.get("nameOfForumForMove"));
			addForum.enterForumIntro(this.properties.get("introTextOfForumForMove"));
			addForum.selectSubscriptionTypeForced();
			addForum.selectForumTypeStandardGeneral();
			addForum.clickSaveAndRetToCourse();
			course.clickTurnEditingOff();
		//Adding a discussion
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			ForumPosts discussion = new ForumPosts(driver);
			discussion.enterSubject(this.properties.get("discussSplitSubject"));
			discussion.enterMessage(this.properties.get("discussSplitMessage"));
			discussion.clickPostToForum();
		//Reply to discussion
			discussion.clickDiscussionLink(this.properties.get("discussSplitSubject"));
			discussion.clickReplyToPostLink(this.properties.get("discussSplitMessage"));
			discussion.enterSubject(this.properties.get("teachersPostReplySubject"));
			discussion.enterMessage(this.properties.get("teachersPostReplyMessage"));
			discussion.clickPostToForum();
		}
		//Log Teacher out
		@Test
		public void teacherLogout(){
			Users teacherLogout = new Users(driver);
			teacherLogout.selectLogout();
		}
		//Login as a student
		@Test
		public void loginAsStudent() throws FileNotFoundException, IOException{
			Users studentLogin = new Users(driver);
			studentLogin.selectLoginLink();
			studentLogin.enterUsername(this.properties.get("studentUsername"));
			studentLogin.enterPassword(this.properties.get("password"));
			studentLogin.clickLoginButton();
		}
		//Reply to the discussion
		@Test
		public void replyToDiscussionToBeSplit(){
			Courses selectCourse = new Courses(driver);
			selectCourse.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussSplitSubject"));
			discussion.clickReplyToPostLink(this.properties.get("discussSplitMessage"));
			discussion.enterSubject(this.properties.get("studentsPostReplySubject"));
			discussion.enterMessage(this.properties.get("studentsPostReplyMessage"));
			discussion.clickPostToForum();
		}
		//Log Student out
		@Test
		public void studentLogout(){
			Users studentLogout = new Users(driver);
			studentLogout.selectLogout();
		}
		//
		//START OF TEST
		//
		//Login as teacher
		@Test
		public void loginAsTeacherAgain() throws FileNotFoundException, IOException{
		//Run test
			Users teacherLogin = new Users(driver);
			teacherLogin.selectLoginLink();
			teacherLogin.enterUsername(this.properties.get("teacherUsername"));
			teacherLogin.enterPassword(this.properties.get("password"));
			teacherLogin.clickLoginButton();
		}
		//Split Discussion and verify that split has occued with one having a new title
		@Test
		public void splitDiscussion(){
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForum"));
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("discussSplitSubject"));
			discussion.clickSplitLink(this.properties.get("studentsPostReplySubject"));
			ForumSplit splitDiscussion = new ForumSplit(driver);
			splitDiscussion.enterDiscussionName(this.properties.get("newName"));
			splitDiscussion.clickSplitButton();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));	
			forum.clickForumLink(this.properties.get("nameOfForum"));
			//Test Pass/Fail Criteria
			forum.assertDiscussionPresent(this.properties.get("discussSplitSubject"));
			forum.assertDiscussionPresent(this.properties.get("newName"));
		}
		//Move discussion to new forum and verify that the discussion has been moved
		@Test
		public void moveDiscussion(){
			ForumPosts discussion = new ForumPosts(driver);
			discussion.clickDiscussionLink(this.properties.get("newName"));
			discussion.selectValueFromMoveDropdown(this.properties.get("nameOfForumForMove"));
			discussion.clickMoveButton();
			BlockNavigation navigate = new BlockNavigation(driver);
			navigate.clickHome();
			Courses course = new Courses(driver);
			course.clickCourseLink(this.properties.get("courseName"));
			Forum forum = new Forum(driver);
			forum.clickForumLink(this.properties.get("nameOfForumForMove"));
			forum.assertDiscussionPresent(this.properties.get("newName"));
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