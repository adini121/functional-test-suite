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
 * TEST SCENARIO:
 *<br>By default, a new course contains a news forum in which only teachers can post and subscription is forced
 *<br>TEST STEPS:
 *<br>1. Login as a teacher and check that you can start a discussion and post a reply in the news forum.
 *<br>2. Login as a student and check that there is no option to post in the news forum.
 *<br>3. Check that the news forum forces everyone to be subscribed.
 */
public class MDLQA06NewCourseNewsForum extends TestRunSettings {
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA06NewCourseNewsForum(){
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
			this.properties.put("newsSubject", forumTestData.getProperty("newsSubjectMDLQA06"));
			this.properties.put("newsMessage", forumTestData.getProperty("newsMessageMDLQA06"));
			this.properties.put("replySubject", forumTestData.getProperty("replySubjectMDLQA06"));
			this.properties.put("replyMessage", forumTestData.getProperty("replyMessageMDLQA06"));
		}
		//Login as teacher
		@Test
		public void loginAsTeacher() throws FileNotFoundException, IOException{
		//Run test
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Start Discussion in news forum
		@Test
		public void startDiscussionNews() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("newsForum"));
			forum.clickAddNewTopicButton();
			forumPosts.enterSubjectField(this.properties.get("newsSubject"));
			forumPosts.enterMessage(this.properties.get("newsMessage"));
			forumPosts.clickPostToForum();
			forumPosts.clickDiscussionLink(this.properties.get("newsSubject"));
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("newsSubject"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("newsMessage"));
		}
		//Post Reply in news forum
		@Test
		public void postReply() throws Exception{
			forumPosts.clickDiscussionLink(this.properties.get("newsSubject"));
			forumPosts.clickReplyToPostLink(this.properties.get("newsMessage"));
			forumPosts.enterSubjectField(this.properties.get("replySubject"));
			forumPosts.enterMessage(this.properties.get("replyMessage"));
			forumPosts.clickPostToForum();
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("replySubject"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("replyMessage"));
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
		//News forces subscription
		@Test
		public void subscriptionForced(){
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("newsForum"));
			forumAssertions.assertSubscriptionForced();
		}
		//Student cannot post
		@Test
		public void studentCannotPost() throws Exception{
			forumAssertions.assertAddNewTopicButtonDisabled();
			forum.clickForumLink(this.properties.get("newsForum"));
			forumPosts.clickDiscussionLink(this.properties.get("newsSubject"));
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("newsSubject"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("newsMessage"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("replyMessage"));
			forumAssertions.assertReplyLinkNotPresent(this.properties.get("newsMessage"));
			forumAssertions.assertReplyLinkNotPresent(this.properties.get("replyMessage"));
		}
}