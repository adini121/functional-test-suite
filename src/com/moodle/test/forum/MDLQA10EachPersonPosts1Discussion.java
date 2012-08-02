package com.moodle.test.forum;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
/**
 * TEST SCENARIO:
 *<br>In a 'Each person posts one discussion' forum students can start exactly one discussion and can reply to all discussions
 *<br>TEST STEPS:
 *<br>1. Login as a student and start a discussion in a 'Each person posts one discussion' forum.
 *<br>2. Check that it is not possible to start another discussion in the forum.
 *<br>3. Reply to several discussions.
 */
public class MDLQA10EachPersonPosts1Discussion extends TestRunSettings {
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
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
		//Login as teacher
		@Test
		public void loginAsTeacher() {
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Create Each person posts one discussion forum
		@Test
		public void createForum() throws Exception{
		//Select the course
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
		//select forum activity from drop down on courses page 
			addActivity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			addForum.enterNameField(this.properties.get("nameOfForum"));
			addForum.enterDescriptionField(this.properties.get("introTextOfForum"));
			addForum.selectForumTypeEachPerson();
			addForum.clickSaveAndRetToCourse();
			course.clickTurnEditingOff();
		}
		//Teacher creates a discussion
		@Test
		public void teacherCreateDiscussion() throws Exception {
			//Adding a discussion
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			forumPosts.enterSubjectField(this.properties.get("teacherSubject"));
			forumPosts.enterMessage(this.properties.get("teacherMessage"));
			forumPosts.clickPostToForum();
		}
		//Logout teacher
		@Test
		public void logoutTeacher(){
			user.selectLogout();
		}
		//Login as a student
		@Test
		public void loginAsStudent(){
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
		}
		//Start a Discussion
		@Test
		public void startDiscussion() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			//Adding a discussion
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			forumPosts.enterSubjectField(this.properties.get("studentSubject"));
			forumPosts.enterMessage(this.properties.get("studentMessage"));
			forumPosts.clickPostToForum();
		}
		//Check that it is not possible to start another discussion in the forum.
		@Test
		public void checkNoDiscussion() throws Exception{
			forumAssertions.assertAddNewTopicButtonDisabled();
		}
		//Reply to several discussions
		@Test
		public void replyDiscussions() throws Exception{
			forumPosts.clickDiscussionLink(this.properties.get("studentSubject"));
			forumPosts.clickReplyToPostLink(this.properties.get("studentMessage"));
			forumPosts.enterSubjectField(this.properties.get("replySubjectStudent"));
			forumPosts.enterMessage(this.properties.get("replyMessageStudent"));
			forumPosts.clickPostToForum();
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("replySubjectStudent"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("replyMessageStudent"));
			navigationBlock.clickHome();
			navigationBlock.clickExposedLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forumPosts.clickDiscussionLink(this.properties.get("teacherSubject"));
			forumPosts.clickReplyToPostLink(this.properties.get("teacherMessage"));
			forumPosts.enterSubjectField(this.properties.get("replySubjectTeacher"));
			forumPosts.enterMessage(this.properties.get("replyMessageTeacher"));
			forumPosts.clickPostToForum();
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("replySubjectTeacher"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("replyMessageTeacher"));
		}
}