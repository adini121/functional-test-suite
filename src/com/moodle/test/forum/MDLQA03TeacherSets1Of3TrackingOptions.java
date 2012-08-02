package com.moodle.test.forum;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
/**
 * TEST SCENARIO:
 *<br>A teacher can set one of 3 possible options for tracking read forum posts
 *<br>TEST STEPS:
 *<br>1. Login as a teacher and create a forum with 'Read tracking for this forum' set to Optional in the forum settings.
 *<br>2. Login as a student and check you can choose whether to turn tracking on or off. The link to do this should be in the Settings block under Forum administration.
 *<br>3. Login as a teacher and create a forum with 'Read tracking for this forum' set to Off.
 *<br>4. Login as a student and check that read forum posts are not tracked and that you have no option to enable the feature.
 *<br>5. Login as a teacher and create a forum with 'Read tracking for this forum' set to On.
 *<br>6. Login as a student and check that read forum posts are tracked and that you have no option to disable the feature.
 */
public class MDLQA03TeacherSets1Of3TrackingOptions extends TestRunSettings {
		//Test Data Property Files
		public static String forumData = "properties/data/user/Forum/forumData.properties";
		public static String usersData = "properties/data/user/Users/usersData.properties";
		private Map<String, String> properties = new HashMap<String, String>();
		//Load test data from properties file
		public MDLQA03TeacherSets1Of3TrackingOptions(){
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
			this.properties.put("nameOfForumOptional", forumTestData.getProperty("MDLQA03nameOfForumOptional"));
			this.properties.put("introTextOfForumOptional", forumTestData.getProperty("MDLQA03introTextOfForumOptional"));
			this.properties.put("teacherSubjectOptional", forumTestData.getProperty("MDLQA03teacherSubjectOptional"));
			this.properties.put("teacherMessageOptional", forumTestData.getProperty("MDLQA03teacherMessageOptional"));
			this.properties.put("nameOfForumOff", forumTestData.getProperty("MDLQA03nameOfForumOff"));
			this.properties.put("introTextOfForumOff", forumTestData.getProperty("MDLQA03introTextOfForumOff"));
			this.properties.put("teacherSubjectOff", forumTestData.getProperty("MDLQA03teacherSubjectOff"));
			this.properties.put("teacherMessageOff", forumTestData.getProperty("MDLQA03teacherMessageOff"));
			this.properties.put("nameOfForumOn", forumTestData.getProperty("MDLQA03nameOfForumOn"));
			this.properties.put("introTextOfForumOn", forumTestData.getProperty("MDLQA03introTextOfForumOn"));
			this.properties.put("teacherSubjectOn", forumTestData.getProperty("MDLQA03teacherSubjectOn"));
			this.properties.put("teacherMessageOn", forumTestData.getProperty("MDLQA03teacherMessageOn"));
			this.properties.put("off", forumTestData.getProperty("off"));
			this.properties.put("on", forumTestData.getProperty("on"));
		}
		//START TEST
		//Login as teacher
		@Test
		public void loginAsTeacher() {
			user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		}
		//Create a forum with 'Read tracking for this forum' set to Optional in the forum settings.
		@Test
		public void createForumOptional() throws Exception{
			course.clickCourseLink(this.properties.get("courseName"));
			course.clickTurnEditingOn();
			addActivity.selectForum(this.properties.get("outlineSection"));
			addForum.selectForumTypeStandardGeneral();
			addForum.enterNameField(this.properties.get("nameOfForumOptional"));
			addForum.enterDescriptionField(this.properties.get("introTextOfForumOptional"));
			addForum.clickSaveAndRetToCourse();
			forum.clickForumLink(this.properties.get("nameOfForumOptional"));
			forum.clickAddNewDiscussionTopicButton();
			forumPosts.enterSubjectField(this.properties.get("teacherSubjectOptional"));
			forumPosts.enterMessage(this.properties.get("teacherMessageOptional"));
			forumPosts.clickPostToForum();
		}
		//Create a forum with 'Read tracking for this forum' set to Off.
		@Test
		public void createForumOff() throws Exception{
			course.clickCourseLink(this.properties.get("courseShortname"));
			addActivity.selectForum(this.properties.get("outlineSection"));
			addForum.enterNameField(this.properties.get("nameOfForumOff"));
			addForum.enterDescriptionField(this.properties.get("introTextOfForumOff"));
			addForum.selectForumTypeStandardGeneral();
			addForum.selectReadTrackingOption(this.properties.get("off"));
			addForum.clickSaveAndRetToCourse();
		//Adding a discussion
			forum.clickForumLink(this.properties.get("nameOfForumOff"));
			forum.clickAddNewDiscussionTopicButton();
			forumPosts.enterSubjectField(this.properties.get("teacherSubjectOff"));
			forumPosts.enterMessage(this.properties.get("teacherMessageOff"));
			forumPosts.clickPostToForum();
		}
		//Create a forum with 'Read tracking for this forum' set to On.
		@Test
		public void createForumOn() throws Exception{
		//Select the course
			course.clickCourseLink(this.properties.get("courseShortname"));
		//select forum activity from drop down on courses page 
			addActivity.selectForum(this.properties.get("outlineSection"));
		//Adding a new forum
			addForum.enterNameField(this.properties.get("nameOfForumOn"));
			addForum.enterDescriptionField(this.properties.get("introTextOfForumOn"));
			addForum.selectForumTypeStandardGeneral();
			addForum.selectReadTrackingOption(this.properties.get("on"));
			addForum.clickSaveAndRetToCourse();
			//Adding a discussion
			forum.clickForumLink(this.properties.get("nameOfForumOn"));
			forum.clickAddNewDiscussionTopicButton();
			forumPosts.enterSubjectField(this.properties.get("teacherSubjectOn"));
			forumPosts.enterMessage(this.properties.get("teacherMessageOn"));
			forumPosts.clickPostToForum();
			course.clickCourseLink(this.properties.get("courseShortname"));
			course.clickTurnEditingOff();
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
		//Ensure yes highlight new posts is set to yes
		@Test
		public void readTrackingTurnedOn(){
			course.clickCourseLink(this.properties.get("courseName"));
			settingsBlock.navigateEditProfile();
			editProfile.selectForumTrackingOn();
			editProfile.clickUpdateProfile();
		}
		//check you can choose whether to turn tracking on or off. The link to do this should be in the Settings block under Forum administration.
		@Test
		public void trackingOptional() throws Exception{
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			courseAssertions.assertTrackingEnabled(this.properties.get("nameOfForumOptional"));
			forum.clickForumLink(this.properties.get("nameOfForumOptional"));
			settingsBlock.navigateDontTrackUnread();
			course.clickCourseBreadcrumb(this.properties.get("courseShortname"));
			courseAssertions.assertTrackingDisabled(this.properties.get("nameOfForumOptional"));
		}
		//Check that read forum posts are not tracked and that you have no option to enable the feature.
		@Test
		public void trackingOff() throws Exception{
			courseAssertions.assertTrackingDisabled(this.properties.get("nameOfForumOff"));
			forum.clickForumLink(this.properties.get("nameOfForumOff"));
			blockAssertions.assertTrackingCannotBeEnabled();
			course.clickCourseLink(this.properties.get("courseShortname"));
		}
		//Check that read forum posts are tracked and that you have no option to disable the feature.
		@Test
		public void trackingOn() throws Exception{
			courseAssertions.assertTrackingEnabled(this.properties.get("nameOfForumOn"));
			forum.clickForumLink(this.properties.get("nameOfForumOff"));
			blockAssertions.assertTrackingCannotBeDisabled();
		}
		//Log out Student
		//@Test
		public void logoutStudent(){
			user.selectLogout();
		}
}