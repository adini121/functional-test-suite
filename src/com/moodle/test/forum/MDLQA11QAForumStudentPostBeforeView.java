package com.moodle.test.forum;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
/**
 * TEST SCENARIO:
 *<br>In a Q & A forum students must post first before they can view other posts
 *<br>TEST STEPS:
 *<br>1. Login as a student and view a Q & A forum.
 *<br>2. Check that forum posts are not viewable.
 *<br>3. Post in the forum.
 *<br>MANUAL TESTING STEPS:
 *<br>4. Wait for the maximum time to elapse.
 *<br>5. Check that forum posts are now viewable.
 */
public class MDLQA11QAForumStudentPostBeforeView extends TestRunSettings {
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA11QAForumStudentPostBeforeView(){
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
			this.properties.put("nameOfForum", forumTestData.getProperty("MDLQA11nameOfForum"));
			this.properties.put("introTextOfForum", forumTestData.getProperty("MDLQA11introTextOfForum"));
			this.properties.put("teacherSubject", forumTestData.getProperty("MDLQA11teacherSubject"));
			this.properties.put("teacherMessage", forumTestData.getProperty("MDLQA11teacherMessage"));
			this.properties.put("studentSubject", forumTestData.getProperty("MDLQA11studentSubject"));
			this.properties.put("studentMessage", forumTestData.getProperty("MDLQA11studentMessage"));
			this.properties.put("student2Subject", forumTestData.getProperty("MDLQA11student2Subject"));
			this.properties.put("student2Message", forumTestData.getProperty("MDLQA11student2Message"));
		}
		//Login as teacher
		@Test
		public void loginAsTeacher() {
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Create QA forum
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
			addForum.selectForumTypeQA();
			addForum.clickSaveAndRetToCourse();
			course.clickTurnEditingOff();
		}
		//Teacher creates a question
		@Test
		public void teacherCreateDiscussion() throws Exception {
			//Adding a discussion
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewQuestionButton();
			forumPosts.enterSubjectField(this.properties.get("teacherSubject"));
			forumPosts.enterMessage(this.properties.get("teacherMessage"));
			forumPosts.clickPostToForum();
		}
		//Logout teacher
		@Test
		public void logoutTeacher(){
			user.selectLogout();
		}
		//Login as a the first student
		@Test
		public void loginAsStudent(){
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
		}
		//Reply to question as first student
		@Test
		public void replyDiscussion() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forumPosts.clickDiscussionLink(this.properties.get("teacherSubject"));
			forumPosts.clickReplyToPostLink(this.properties.get("teacherMessage"));
			forumPosts.enterSubjectField(this.properties.get("studentSubject"));
			forumPosts.enterMessage(this.properties.get("studentMessage"));
			forumPosts.clickPostToForum();
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("studentSubject"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("studentMessage"));
		}
		//Log out Student 1
		@Test
		public void logoutStudent1(){
			user.selectLogout();
		}
		//Log in Student 2
		@Test
		public void loginStudent2(){
			user.loginToSystem(this.properties.get("student2Username"), this.properties.get("password"));
		}
		//Check forum posts are not viewable
		@Test
		public void checkPostsCannotBeViewed() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forumPosts.clickDiscussionLink(this.properties.get("teacherSubject"));
			forumAssertions.assertSubjectOrMessageNotPresent(this.properties.get("studentSubject"));
			forumAssertions.assertSubjectOrMessageNotPresent(this.properties.get("studentMessage"));
			forumAssertions.assertSubjectHidden();
		}
		//Reply to question
		@Test
		public void student2RepliesToPost() throws Exception{
			forumPosts.clickReplyToPostLink(this.properties.get("teacherMessage"));
			forumPosts.enterSubjectField(this.properties.get("student2Subject"));
			forumPosts.enterMessage(this.properties.get("student2Message"));
			forumPosts.clickPostToForum();
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("student2Subject"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("student2Message"));
		}
}