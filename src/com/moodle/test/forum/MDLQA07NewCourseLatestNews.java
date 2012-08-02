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
 *<br>By default, a new course contains a Latest News block which displays a specific number of recent discussions from the news forum.
 *<br>TEST STEPS:
 *<br>1. Login as a teacher and create 3 discussions in the news forum.
 *<br>2. Check that all discussion titles are displayed in the Latest News block.
 *<br>3. Change the 'News items to show' in the course settings to 2.
 *<br>4. Check that only the most recent 2 discussion titles are displayed in the Latest News block.
 *<br>5. Change the 'News items to show' in the course settings to 0.
 *<br>6. Check that the Latest News block is not displayed at all.
 */
public class MDLQA07NewCourseLatestNews extends TestRunSettings {
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA07NewCourseLatestNews(){
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
			this.properties.put("newsSubject1", forumTestData.getProperty("newsSubject1MDLQA07"));
			this.properties.put("newsMessage1", forumTestData.getProperty("newsMessage1MDLQA07"));
			this.properties.put("newsSubject2", forumTestData.getProperty("newsSubject2MDLQA07"));
			this.properties.put("newsMessage2", forumTestData.getProperty("newsMessage2MDLQA07"));
			this.properties.put("newsSubject3", forumTestData.getProperty("newsSubject3MDLQA07"));
			this.properties.put("newsMessage3", forumTestData.getProperty("newsMessage3MDLQA07"));
			this.properties.put("selectShow2Items", forumTestData.getProperty("selectShow2Items"));
			this.properties.put("selectShow0Items", forumTestData.getProperty("selectShow0Items"));
		}
		//START TEST
		//Login as teacher
		@Test
		public void loginAsTeacher() throws FileNotFoundException, IOException{
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Delete news from previous test
		@Test
		public void deleteNews(){
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			forum.clickForumLink(this.properties.get("newsForum"));
			forumPosts.deleteAllForumPosts();
		}
		//Start Three Discussions in news forum
		@Test
		public void startDiscussionNews() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOff();
			//Create discussion 1
			forum.clickForumLink(this.properties.get("newsForum"));
			forum.clickAddNewTopicButton();
			forumPosts.enterSubjectField(this.properties.get("newsSubject1"));
			forumPosts.enterMessage(this.properties.get("newsMessage1"));
			forumPosts.clickPostToForum();
			forumPosts.clickDiscussionLink(this.properties.get("newsSubject1"));
			//Pass/Fail criteria
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("newsSubject1"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("newsMessage1"));
			//Create discussion 2
			forum.clickForumLink(this.properties.get("newsForum"));
			forum.clickAddNewTopicButton();
			forumPosts.enterSubjectField(this.properties.get("newsSubject2"));
			forumPosts.enterMessage(this.properties.get("newsMessage2"));
			forumPosts.clickPostToForum();
			forumPosts.clickDiscussionLink(this.properties.get("newsSubject2"));
			//Pass/Fail criteria
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("newsSubject2"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("newsMessage2"));
			//Create discussion 3
			forum.clickForumLink(this.properties.get("newsForum"));
			forum.clickAddNewTopicButton();
			forumPosts.enterSubjectField(this.properties.get("newsSubject3"));
			forumPosts.enterMessage(this.properties.get("newsMessage3"));
			forumPosts.clickPostToForum();
			forumPosts.clickDiscussionLink(this.properties.get("newsSubject3"));
			//Pass/Fail criteria
			forumAssertions.assertForumPostSubjectSuccessful(this.properties.get("newsSubject3"));
			forumAssertions.assertForumPostMessageSuccessful(this.properties.get("newsMessage3"));
		}
		//Check that all discussions appear in the news block
		@Test
		public void check3InNewsBlock(){
			course.clickCourseLink(this.properties.get("courseShortname"));
			blockAssertions.assertDiscussionInNewsBlock(this.properties.get("newsSubject1"));
			blockAssertions.assertDiscussionInNewsBlock(this.properties.get("newsSubject2"));
			blockAssertions.assertDiscussionInNewsBlock(this.properties.get("newsSubject3"));
		}
		//Change News Items to show settings to 2
		@Test
		public void show2Items(){
			settingsBlock.navigateEditCourseSettings();
			editCourseSettings.selectNewsItemsToShow(this.properties.get("selectShow2Items"));
			editCourseSettings.clickSaveChanges();
		}
		//Check that only the most recent 2 discussion titles are displayed in the Latest News block.
		@Test
		public void check2InNewsBlock(){
			blockAssertions.assertDiscussionInNewsBlock(this.properties.get("newsSubject2"));
			blockAssertions.assertDiscussionInNewsBlock(this.properties.get("newsSubject3"));
		}
		//Change the 'News items to show' in the course settings to 0.
		@Test
		public void show0Items(){
			settingsBlock.navigateEditCourseSettings();
			editCourseSettings.selectNewsItemsToShow(this.properties.get("selectShow0Items"));
			editCourseSettings.clickSaveChanges();
		}
		//Check that the Latest News block is not displayed at all.
		@Test
		public void checkNewsBlockNotDisplayed() throws Exception{
			blockAssertions.assertNewsBlockNotDisplayed();
		}
}