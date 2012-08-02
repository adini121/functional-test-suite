package com.moodle.test.forum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
/**
 * A teacher can set one of 4 possible forum subscription options
 *<br>TEST SCENARIO:
 *<br>By default, a new course contains a news forum in which only teachers can post and subscription is forced.
 *<br>1. Login as a teacher and create a forum with 'Subscription Mode' set to 'Optional subscription'.
 *<br>2. Login as a student and check that you can choose to subscribe/unsubscribe.
 *<br>3. Login as a teacher and create a forum with 'Subscription Mode' set to 'Forced Subscription'.
 *<br>4. Login as a student and check that you are subscribed with no option to unsubscribe.
 *<br>5. Login as a teacher and create a forum with 'Subscription Mode' set to 'Auto subscription'.
 *<br>6. Login as a student and check that you are subscribed initially but have the option to unsubscribe.
 *<br>7. Login as a teacher and create a forum with 'Subscription Mode' set to 'Subscription disabled'.
 *<br>8. Login as a student and check that you have no option to subscribe.
 */
public class MDLQA12TeacherSet4PossibleForumSubscription extends TestRunSettings {
	//Test Data Property Files
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
	//Login as teacher
	@Test
	public void loginAsTeacher() throws FileNotFoundException, IOException{
	//Run test
		user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
	}
	//Start a discussion with the 'subscription mode' set to 'Optional subscription'.
	@Test
	public void startDiscussionOptional() throws Exception{
	//Select the course
		course.clickCourseLink(this.properties.get("courseName"));
		course.clickTurnEditingOn();
	//select activity drop down on courses page 
		addActivity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		addForum.enterNameField(this.properties.get("nameOfForumOptional"));
		addForum.enterDescriptionField(this.properties.get("entryTextOptional"));
		addForum.selectSubscriptionTypeOptional();
		addForum.clickSaveAndRetToCourse();
	}
	//Start a discussion with the 'subscription mode' set to 'Forced subscription'.
	@Test
	public void startDiscussionForced() throws Exception{
	//select activity drop down on courses page 
		addActivity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		addForum.enterNameField(this.properties.get("nameOfForumForced"));
		addForum.enterDescriptionField(this.properties.get("entryTextForced"));
		addForum.selectSubscriptionTypeForced();
		addForum.clickSaveAndRetToCourse();
	}
	//Start a discussion with the 'subscription mode' set to 'Auto subscription'.
	@Test
	public void startDiscussionAuto() throws Exception{
	//select activity drop down on courses page 
		addActivity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		addForum.enterNameField(this.properties.get("nameOfForumAuto"));
		addForum.enterDescriptionField(this.properties.get("entryTextAuto"));
		addForum.selectSubscriptionTypeAuto();
		addForum.clickSaveAndRetToCourse();
	}
	//Start a discussion with the 'subscription mode' set to 'Subscription disabled'.
	@Test
	public void startDiscussionSubscriptionDisabled() throws Exception{
	//select activity drop down on courses page 
		addActivity.selectForum(this.properties.get("outlineSection"));
	//Adding a new forum
		addForum.enterNameField(this.properties.get("nameOfForumDisabled"));
		addForum.enterDescriptionField(this.properties.get("entryTextDisabled"));
		addForum.selectSubscriptionTypeDisabled();
		addForum.clickSaveAndRetToCourse();
	}
	//Log Teacher out
	@Test
	public void teacherLogout(){
		user.selectLogout();
	}
	//Login as student
	@Test
	public void loginAsStudent() throws FileNotFoundException, IOException{
	//Run test
		user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
	}
	//Subscribe to optional
	@Test
	public void subscribeOptional() throws FileNotFoundException, IOException{
	//Run test
		course.clickCourseLink(this.properties.get("courseName"));
		forum.clickForumLink(this.properties.get("nameOfForumOptional"));
	//Test Pass/Fail Criteria
		forumAssertions.assertSubscriptionOptional();
		forumAssertions.assertSubscribeOptionPresent();	
	//Subscribe to discussion
		forum.subscribe();
	//Test Pass/Fail Criteria
		forumAssertions.assertUnsubscribeOptionPresent();
	//Unsubscribe from Discussion
		forum.unsubscribe();
		course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}
	//Verify forced subscription
	@Test
	public void subscribeForced() throws FileNotFoundException, IOException{
	//Run test
		forum.clickForumLink(this.properties.get("nameOfForumForced"));
	//Test Pass/Fail Criteria
		forumAssertions.assertSubscriptionForced();
	//Back to course outline
		course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}
	//Verify Auto Subscription
	@Test
	public void subscribeAuto() throws FileNotFoundException, IOException{
	//Run test
		forum.clickForumLink(this.properties.get("nameOfForumAuto"));
	//Test Pass/Fail Criteria
		forumAssertions.assertSubscriptionAuto();
		forumAssertions.assertUnsubscribeOptionPresent();
	//Unsubscribe from Discussion
		forum.unsubscribe();
	//Test Pass/Fail Criteria
		forumAssertions.assertSubscriptionAuto();
		forumAssertions.assertSubscribeOptionPresent();
	//Subscribe to discussion
		forum.subscribe();
		course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}	
	//Verify subscription disabled
	@Test
	public void subscribeDisabled() throws FileNotFoundException, IOException{
	//Run test
		forum.clickForumLink(this.properties.get("nameOfForumDisabled"));
	//Test Pass/Fail Criteria
		forumAssertions.assertSubscriptionDisabled();
	//Back to course outline
		course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
	}
}