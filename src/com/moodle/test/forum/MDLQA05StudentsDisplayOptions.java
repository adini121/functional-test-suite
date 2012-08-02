package com.moodle.test.forum;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
/**
 * TEST SCENARIO:
 *<br>Students can choose from 4 discussion dispay options and their choice is remembered
 *<br>PREREQUISITES:
 *<br>Re-uses data fom MDLQA-4 which must run first.
 *<br>TEST STEPS:
 *<br>1. Login as a student and view a discussion thread containing several replies.
 *<br>2. Select a display option - nested, flat or threaded, oldest or newest first.
 *<br>3. View a different discussion thread and check that the display option is the same as previously selected.
 */
public class MDLQA05StudentsDisplayOptions extends TestRunSettings {
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA05StudentsDisplayOptions(){
			this.loadTestData();
		}
		public void loadTestData() {
			Properties forumTestData = new Properties();
			try {
				forumTestData.load(new FileInputStream(forumData));
			} catch (Exception e) {}
			//login details
			this.properties.put("studentUsername", forumTestData.getProperty("studentUsername"));
			this.properties.put("password", forumTestData.getProperty("password"));
			//course details
			this.properties.put("courseName", forumTestData.getProperty("courseName"));
			this.properties.put("courseShortname", forumTestData.getProperty("courseShortname"));
			//forum details
			this.properties.put("nameOfFirstForum", forumTestData.getProperty("nameOfForum1MDLQA04"));
			this.properties.put("introTextOfFirstForum", forumTestData.getProperty("introTextOfForum1MDLQA04"));
			this.properties.put("nameOfSecondForum", forumTestData.getProperty("nameOfForum2MDLQA04"));
			this.properties.put("introTextOfSecondForum", forumTestData.getProperty("introTextOfForum2MDLQA04"));
			//discussion details
			this.properties.put("discussion1Subject", forumTestData.getProperty("discussionSubjectMDLQA04"));
			this.properties.put("discuss1Message", forumTestData.getProperty("discussionTextMDLQA04"));
			this.properties.put("forum1OriginalReply", forumTestData.getProperty("teacherReplySubjectMDLQA04"));
			this.properties.put("forum1OriginalMessage", forumTestData.getProperty("teacherReplyMessageMDLQA04"));
			this.properties.put("forum2Subject", forumTestData.getProperty("studentsReplySubjectMDLQA04"));
			this.properties.put("discussion2Message", forumTestData.getProperty("studentsReplyMessageMDLQA04"));
			this.properties.put("discussion2Subject", forumTestData.getProperty("newName"));
		}
		//Log in as a Student
		@Test
		public void studentLogin(){
			user.loginToSystem(this.properties.get("studentUsername"), this.properties.get("password"));
		}
		//Add an additional reply to first discussion
		@Test
		public void addReplyToFirstForum() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			forum.clickForumLink(this.properties.get("nameOfFirstForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion1Subject"));
			forumPosts.clickReplyToPostLink(this.properties.get("forum1OriginalReply"));
			forumPosts.enterSubjectField(this.properties.get("forum1OriginalReply"));
			forumPosts.enterMessage(this.properties.get("forum1OriginalMessage"));
			forumPosts.clickPostToForum();
		}
		//Add an additional reply to second discussion
		@Test
		public void addReplyToSecondForum() throws Exception{
			navigationBlock.clickExposedLink(this.properties.get("nameOfSecondForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion2Subject"));
			forumPosts.clickReplyToPostLink(this.properties.get("discussion2Message"));
			forumPosts.enterSubjectField(this.properties.get("forum1OriginalReply"));
			forumPosts.enterMessage(this.properties.get("forum1OriginalMessage"));
			forumPosts.clickPostToForum();
		}
		//START TEST
		//Navigate to Discussion Thread
		@Test
		public void navigateToDiscussionThread(){
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			forum.clickForumLink(this.properties.get("nameOfFirstForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion1Subject"));			
		}
		//Change display option to "Display replies flat, with oldest first" and assert display correct
		@Test
		public void displayFlatOldest() throws IOException{
			forumPosts.selectDisplayOptionDropdownFlatOldest();
			forumAssertions.assertFlatOldestOptionSelected();
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayFlatOldestRetained() throws IOException{
			navigationBlock.clickExposedLink(this.properties.get("nameOfSecondForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion2Subject"));
			forumAssertions.assertFlatOldestOptionSelected();
		}

		//Change display option to "Display replies flat, with newest first" and assert display correct
		@Test
		public void displayFlatNewest() throws IOException{
			navigationBlock.clickExposedLink(this.properties.get("nameOfFirstForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion1Subject"));
			forumPosts.selectDisplayOptionDropdownFlatNewest();
			forumAssertions.assertFlatNewestOptionSelected();
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayFlatNewestRetained() throws IOException{
			navigationBlock.clickExposedLink(this.properties.get("nameOfSecondForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion2Subject"));
			forumAssertions.assertFlatNewestOptionSelected();
		}	
		//Change display option to "Display replies in threaded form" and assert display correct
		@Test
		public void displayThreaded() throws IOException{
			navigationBlock.clickExposedLink(this.properties.get("nameOfFirstForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion1Subject"));
			forumPosts.selectDisplayOptionDropdownThreaded();
			forumAssertions.assertThreadedOptionSelected();
			forumAssertions.assertThreadedLink(this.properties.get("forum1OriginalReply"));
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayThreadedRetained() throws IOException{
			navigationBlock.clickExposedLink(this.properties.get("nameOfSecondForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion2Subject"));
			forumAssertions.assertThreadedOptionSelected();
			forumAssertions.assertThreadedLink(this.properties.get("forum1OriginalReply"));
			
		}
		//Change display option to "Display replies in nested form" and assert display correct
		@Test
		public void displayNested(){
			navigationBlock.clickExposedLink(this.properties.get("nameOfFirstForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion1Subject"));
			forumPosts.selectDisplayOptionNested();
			forumAssertions.assertNestedOptionSelected();
		}
		//Verify display option is retained in second forum
		@Test
		public void verifyDisplayNestedRetained(){
			navigationBlock.clickExposedLink(this.properties.get("nameOfSecondForum"));
			forumPosts.clickDiscussionLink(this.properties.get("discussion2Subject"));
			forumAssertions.assertNestedOptionSelected();
		}	
}