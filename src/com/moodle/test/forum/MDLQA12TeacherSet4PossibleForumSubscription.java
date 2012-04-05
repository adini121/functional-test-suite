/**
 * A teacher can set one of 4 possible forum subscription options
 * TEST SCENARIO:
 * By default, a new course contains a news forum in which only teachers can post and subscription is forced.
 * 1. Login as a teacher and create a forum with 'Subscription Mode' set to 'Optional subscription'.
 * 2. Login as a student and check that you can choose to subscribe/unsubscribe.
 * 3. Login as a teacher and create a forum with 'Subscription Mode' set to 'Forced Subscription'.
 * 4. Login as a student and check that you are subscribed with no option to unsubscribe.
 * 5. Login as a teacher and create a forum with 'Subscription Mode' set to 'Auto subscription'.
 * 6. Login as a student and check that you are subscribed initially but have the option to unsubscribe.
 * 7. Login as a teacher and create a forum with 'Subscription Mode' set to 'Subscription disabled'.
 * 8. Login as a student and check that you have no option to subscribe.
 */
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
import com.moodle.testmanager.pageObjectModel.CoursesAddAnActivity;
import com.moodle.testmanager.pageObjectModel.ForumAddForum;
import com.moodle.testmanager.pageObjectModel.Courses;
import com.moodle.testmanager.pageObjectModel.Forum;
import com.moodle.testmanager.pageObjectModel.Users;

public class MDLQA12TeacherSet4PossibleForumSubscription {
	//define "driver" in a field
	static RemoteWebDriver driver;
	static SeleniumManager sm;
	//TEST DATA
	//Test Data Property Files
	public static String runParameters = "properties/runParameters.properties";
	public static String forumData = "properties/data/user/Forum/forumData.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	//Load test data from properties files
	public MDLQA12TeacherSet4PossibleForumSubscription() {
		this.loadTestData();
	}
	public void loadTestData() {
	//Load test data from properties
		Properties forumTestData = new Properties();
		try {
			forumTestData.load(new FileInputStream(forumData));
		} catch (Exception e) {}
		this.properties.put("nameOfForumOptional", forumTestData.getProperty("nameOfForumOptional"));
		this.properties.put("entryTextOptional", forumTestData.getProperty("entryTextOptional"));
		this.properties.put("nameOfForumForced", forumTestData.getProperty("nameOfForumForced"));
		this.properties.put("entryTextForced", forumTestData.getProperty("entryTextForced"));
		this.properties.put("nameOfForumAuto", forumTestData.getProperty("nameOfForumAuto"));
		this.properties.put("entryTextAuto", forumTestData.getProperty("entryTextAuto"));
		this.properties.put("nameOfForumDisabled", forumTestData.getProperty("nameOfForumDisabled"));
		this.properties.put("entryTextDisabled", forumTestData.getProperty("entryTextDisabled"));
		this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
		this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
		this.properties.put("password", forumTestData.getProperty("password"));
		this.properties.put("courseName", forumTestData.getProperty("courseName"));
		this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
		this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
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
	//Start a discussion with the 'subscription mode' set to 'Optional subscription'.
	@Test
	public void startDiscussionOptional() throws FileNotFoundException, IOException{
	//Select the course
		Courses selectCourse = new Courses(driver);
		selectCourse.clickCourseLink(this.properties.get("courseName"));
		selectCourse.clickTurnEditingOn();
	//select activity drop down on courses page 
		CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
		activity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		ForumAddForum addForum = new ForumAddForum(driver);
		addForum.enterForumName(this.properties.get("nameOfForumOptional"));
		addForum.enterForumIntro(this.properties.get("entryTextOptional"));
		addForum.selectSubscriptionTypeOptional();
		addForum.clickSaveAndRetToCourse();
	}
	//Start a discussion with the 'subscription mode' set to 'Forced subscription'.
	@Test
	public void startDiscussionForced() throws FileNotFoundException, IOException{
	//select activity drop down on courses page 
		CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
		activity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		ForumAddForum addForum = new ForumAddForum(driver);
		addForum.enterForumName(this.properties.get("nameOfForumForced"));
		addForum.enterForumIntro(this.properties.get("entryTextForced"));
		addForum.selectSubscriptionTypeForced();
		addForum.clickSaveAndRetToCourse();
	}
	//Start a discussion with the 'subscription mode' set to 'Auto subscription'.
	@Test
	public void startDiscussionAuto() throws FileNotFoundException, IOException{
	//select activity drop down on courses page 
		CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
		activity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		ForumAddForum addForum = new ForumAddForum(driver);
		addForum.enterForumName(this.properties.get("nameOfForumAuto"));
		addForum.enterForumIntro(this.properties.get("entryTextAuto"));
		addForum.selectSubscriptionTypeAuto();
		addForum.clickSaveAndRetToCourse();
	}
	//Start a discussion with the 'subscription mode' set to 'Subscription disabled'.
	@Test
	public void startDiscussionSubscriptionDisabled() throws FileNotFoundException, IOException{
	//select activity drop down on courses page 
		CoursesAddAnActivity activity = new CoursesAddAnActivity(driver);
		activity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		ForumAddForum addForum = new ForumAddForum(driver);
		addForum.enterForumName(this.properties.get("nameOfForumDisabled"));
		addForum.enterForumIntro(this.properties.get("entryTextDisabled"));
		addForum.selectSubscriptionTypeDisabled();
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
	//Subscribe to optional
	@Test
	public void subscribeOptional() throws FileNotFoundException, IOException{
	//Run test
		Courses selectCourse = new Courses(driver);
		selectCourse.clickCourseLink(this.properties.get("courseName"));
		Forum forum = new Forum(driver);
		forum.clickForumLink(this.properties.get("nameOfForumOptional"));
	//Test Pass/Fail Criteria
		forum.assertSubscriptionOptional();
		forum.assertSubscribeOptionPresent();	
	//Subscribe to discussion
		forum.subscribe();
	//Test Pass/Fail Criteria
		forum.assertUnsubscribeOptionPresent();
	//Unsubscribe from Discussion
		forum.unsubscribe();
		selectCourse.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}
	//Verify forced subscription
	@Test
	public void subscribeForced() throws FileNotFoundException, IOException{
	//Run test
		Courses selectCourse = new Courses(driver);
		Forum forum = new Forum(driver);
		forum.clickForumLink(this.properties.get("nameOfForumForced"));
	//Test Pass/Fail Criteria
		forum.assertSubscriptionForced();
	//Back to course outline
		selectCourse.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}
	//Verify Auto Subscription
	@Test
	public void subscribeAuto() throws FileNotFoundException, IOException{
	//Run test
		Courses selectCourse = new Courses(driver);
		Forum forum = new Forum(driver);
		forum.clickForumLink(this.properties.get("nameOfForumAuto"));
	//Test Pass/Fail Criteria
		forum.assertSubscriptionAuto();
		forum.assertUnsubscribeOptionPresent();
	//Unsubscribe from Discussion
		forum.unsubscribe();
	//Test Pass/Fail Criteria
		forum.assertSubscriptionAuto();
		forum.assertSubscribeOptionPresent();
	//Subscribe to discussion
		forum.subscribe();
		selectCourse.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}	
	//Verify subscription disabled
	@Test
	public void subscribeDisabled() throws FileNotFoundException, IOException{
	//Run test
		Courses selectCourse = new Courses(driver);
		Forum forum = new Forum(driver);
		forum.clickForumLink(this.properties.get("nameOfForumDisabled"));
	//Test Pass/Fail Criteria
		forum.assertSubscriptionDisabled();
	//Back to course outline
		selectCourse.clickCourseBreadcrumb(this.properties.get("courseShortname"));
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
		sm.teardown();
	}	
}