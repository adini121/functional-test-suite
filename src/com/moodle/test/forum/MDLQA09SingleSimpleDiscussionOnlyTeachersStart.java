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
 *<br>1. Login as a teacher and start a discussion in a single simple discussion forum.
 *<br>2. Login as a teacher and reply to the discussion.
 *<br>3. Check that, as a student, it is not possible to start a discussion.
 */
public class MDLQA09SingleSimpleDiscussionOnlyTeachersStart extends TestRunSettings{
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA09SingleSimpleDiscussionOnlyTeachersStart(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			this.properties.put("outlineSection", forumTestData.getProperty("outlineSection"));
			this.properties.put("nameSimple", forumTestData.getProperty("nameOfForumSingleSimple"));
			this.properties.put("introText", forumTestData.getProperty("simpleForumIntroText"));
			this.properties.put("replySubject", forumTestData.getProperty("simpleForumReplySubject"));
			this.properties.put("replyText", forumTestData.getProperty("simpleForumReplyText"));
		}
		//Login as teacher
		@Test
		public void loginAsTeacher() throws FileNotFoundException, IOException{
		//Run test
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Start a discussion in a single simple forum
		@Test
		public void startDiscussion() throws Exception{
		//Select the course
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
		//select activity drop down on courses page 
			addActivity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			addForum.enterNameField(this.properties.get("nameSimple"));
			addForum.enterDescriptionField(this.properties.get("introText"));
			addForum.selectForumTypeSimple();
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
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
		}
		//Reply to the discussion
		@Test
		public void replyToDiscussion() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameSimple"));
			forumPosts.clickReplyToPostLink(this.properties.get("introText"));
			forumPosts.enterSubjectField(this.properties.get("replySubject"));
			forumPosts.enterMessage(this.properties.get("replyText"));
			forumPosts.clickPostToForum();
		}
		//Try to start a new discussion
		@Test
		public void tryToStartANewDiscussion() throws Exception{
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			courseAssertions.assertTurnEditingOnIsDisabled();
		}
}