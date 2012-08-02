package com.moodle.test.forum;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
/**
 * DESCRIPTION:
 *<br>This test requires a forum in which several students have posted.
 *<br>Re-uses test data from MDLQA09.
 *<br>TEST SCENARIO:
 *<br>1. Login as a teacher and select a forum post and edit it.
 *<br>2. Check that the sentence 'Edited by xxx - original submission xxx' is appended to the post.
 *<br>3. Select a forum post and delete it.
 */
public class MDLQA16TeacherEditDeletePost extends TestRunSettings {
		//Test Data Property Files
		public static String testData = "properties/data/user/Forum/forumData.properties";
		public static String userData = "properties/data/user/Users/usersData.properties";
		//Weekly outline section
		//public String outlineSection = "1";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA16TeacherEditDeletePost(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(testData));
				forumTestData.load(new FileInputStream(userData));
			} catch (Exception e) {}
			this.properties.put("teacherUsername", forumTestData.getProperty("teacherUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			this.properties.put("teacherFirstname", forumTestData.getProperty("teacherFirstname"));
			this.properties.put("teacherSurname", forumTestData.getProperty("teacherSurname"));
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("nameOfForumSingleSimple", forumTestData.getProperty("nameOfForumSingleSimple"));
			this.properties.put("simpleForumReplyText", forumTestData.getProperty("simpleForumReplyText"));
			this.properties.put("editedText", forumTestData.getProperty("MDLQA16editedText"));
		}
		@Test
		//login teacher
		public void loginAsTeacher() {
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		@Test
		//Edit Post
		public void editPost() throws Exception {
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForumSingleSimple"));
			forumPosts.clickEditLink(this.properties.get("simpleForumReplyText"));
			forumPosts.clickSaveChanges();
			forumAssertions.assertTeacherEdit(this.properties.get("teacherFirstname") + " " + this.properties.get("teacherSurname"));
		}
		//Delete Post
		@Test
		public void deletePost() {
			forumPosts.clickDeleteLink(this.properties.get("simpleForumReplyText"));
			forumPosts.clickContinue();
		}
}