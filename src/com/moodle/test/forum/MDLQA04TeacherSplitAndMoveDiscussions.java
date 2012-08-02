
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
 * Teachers can split discussions and move discussions between forums in the same course 
 * TEST STEPS:
 * 1. Login as a teacher and view a discussion thread.
 * 2. Split one or more posts from the thread and give them a new discussion title.
 * 3. Check that the thread has been split into two discussions, one of which with a new title.
 * 4. View another discussion thread.
 * 5. Move it to another forum by selecting the forum in the 'Move this discussion to...' dropdown menu and then clicking the Move button.
 * 6. Check that the thread has been moved to the selected forum.
 */
public class MDLQA04TeacherSplitAndMoveDiscussions extends TestRunSettings {
		//Test Data Property Files
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
		//Login as teacher
		@Test
		public void loginAsTeacher() throws FileNotFoundException, IOException{
		//Run test
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//SETUP TEST DATA
		//Start a discussion in a standard forum
		@Test
		public void startDiscussion() throws Exception{
		//Select the course
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
		//select forum activity from drop down on courses page 
			addActivity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			addForum.enterNameField(this.properties.get("nameOfForum"));
			addForum.enterDescriptionField(this.properties.get("introTextOfForum"));
			addForum.selectSubscriptionTypeForced();
			addForum.selectForumTypeStandardGeneral();
			addForum.clickSaveAndRetToCourse();
		//Adding a second forum
			addActivity.selectForum(this.properties.get("outlineSection"));
			addForum.enterNameField(this.properties.get("nameOfForumForMove"));
			addForum.enterDescriptionField(this.properties.get("introTextOfForumForMove"));
			addForum.selectSubscriptionTypeForced();
			addForum.selectForumTypeStandardGeneral();
			addForum.clickSaveAndRetToCourse();
			course.clickTurnEditingOff();
		//Adding a discussion
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forum.clickAddNewDiscussionTopicButton();
			forumPosts.enterSubjectField(this.properties.get("discussSplitSubject"));
			forumPosts.enterMessage(this.properties.get("discussSplitMessage"));
			forumPosts.clickPostToForum();
		//Reply to discussion
			forumPosts.clickDiscussionLink(this.properties.get("discussSplitSubject"));
			forumPosts.clickReplyToPostLink(this.properties.get("discussSplitMessage"));
			forumPosts.enterSubjectField(this.properties.get("teachersPostReplySubject"));
			forumPosts.enterMessage(this.properties.get("teachersPostReplyMessage"));
			forumPosts.clickPostToForum();
		}
		//Log Teacher out
		@Test
		public void teacherLogout(){
			user.selectLogout();
		}
		//Login as a student
		@Test
		public void loginAsStudent() throws FileNotFoundException, IOException{
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
		}
		//Reply to the discussion
		@Test
		public void replyToDiscussionToBeSplit() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussSplitSubject"));
			forumPosts.clickReplyToPostLink(this.properties.get("discussSplitMessage"));
			forumPosts.enterSubjectField(this.properties.get("studentsPostReplySubject"));
			forumPosts.enterMessage(this.properties.get("studentsPostReplyMessage"));
			forumPosts.clickPostToForum();
		}
		//Log Student out
		@Test
		public void studentLogout(){
			user.selectLogout();
		}
		//
		//START OF TEST
		//
		//Login as teacher
		@Test
		public void loginAsTeacherAgain() throws FileNotFoundException, IOException{
		//Run test
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Split Discussion and verify that split has occued with one having a new title
		@Test
		public void splitDiscussion(){
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussSplitSubject"));
			forumPosts.clickSplitLink(this.properties.get("studentsPostReplySubject"));
			splitForum.enterDiscussionName(this.properties.get("newName"));
			splitForum.clickSplitButton();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));	
			forum.clickForumLink(this.properties.get("nameOfForum"));
			forumAssertions.assertDiscussionPresent(this.properties.get("discussSplitSubject"));
			forumAssertions.assertDiscussionPresent(this.properties.get("newName"));
		}
		//Move discussion to new forum and verify that the discussion has been moved
		@Test
		public void moveDiscussion(){
			forumPosts.clickDiscussionLink(this.properties.get("newName"));
			forumPosts.selectValueFromMoveDropdown(this.properties.get("nameOfForumForMove"));
			forumPosts.clickMoveButton();
			navigationBlock.clickHome();
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfForumForMove"));
			forumAssertions.assertDiscussionPresent(this.properties.get("newName"));
		}
}