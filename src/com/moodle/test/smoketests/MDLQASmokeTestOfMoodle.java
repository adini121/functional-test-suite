package com.moodle.test.smoketests;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.moodle.test.TestRunSettings;
import com.moodle.test.datacreation.MDLQADT1AddCourse;
import com.moodle.test.datacreation.MDLQADT2AddUsers;
import com.moodle.test.datacreation.MDLQADT3AssignFrontPageRoles;
import com.moodle.test.datacreation.MDLQADT4EnrolUsers;
import com.moodle.test.moodleinstallation.Install23;

public class MDLQASmokeTestOfMoodle extends TestRunSettings {
	public static String usersData = "properties/data/user/Users/usersData.properties";
	public static String courseData = "properties/data/user/Courses/courseData.properties";
	public static String smokeData = "properties/data/user/Smoke/smoke.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	//Load test data from properties file
	public MDLQASmokeTestOfMoodle(){
		this.loadTestData();
	}
	public void loadTestData() {
		Properties testData = new Properties();
		try {
			testData.load(new FileInputStream(usersData));
			testData.load(new FileInputStream(courseData));
		} catch (Exception e) {}
		//User Data
		this.properties.put("adminUser", testData.getProperty("adminUser"));
		this.properties.put("teacher", testData.getProperty("teacherUsername"));
		this.properties.put("password", testData.getProperty("password"));
		this.properties.put("adminEmail", testData.getProperty("adminEmail"));
		this.properties.put("city", testData.getProperty("city"));
		this.properties.put("country", testData.getProperty("country"));
		this.properties.put("fullSiteName", testData.getProperty("fullSiteName"));
		this.properties.put("shortSiteName", testData.getProperty("shortSiteName"));
		//Course Data
		this.properties.put("courseName", testData.getProperty("courseName"));
		//Smoke Data
		this.properties.put("assignmentName", testData.getProperty("assignmentName"));
		this.properties.put("assignmentDescription", testData.getProperty("assignmentDescription"));
		}
	@Test
	public void installation() {
		Install23 install23 = new Install23();
		install23.install();
	}
	@Test
	public void addCourse() throws MalformedURLException {
		MDLQADT1AddCourse addCourse = new MDLQADT1AddCourse();
		addCourse.addCourse();
	}
	@Test
	public void addUsers() throws Exception {
		MDLQADT2AddUsers addUsers = new MDLQADT2AddUsers();
		addUsers.addTeacher();
		addUsers.addFirstStudent();
		addUsers.addSecondStudent();
		addUsers.addThirdStudent();
		addUsers.addFourthStudent();
		addUsers.addFifthStudent();
		addUsers.addSixthStudent();
		addUsers.addSeventhStudent();
		addUsers.addEighthStudent();
		addUsers.addNinthStudent();
		addUsers.addTenthStudent();
		addUsers.addEleventhStudent();
		//TODO Create Add Manager Step
		//addUsers.addManager();
	}
	@Test
	public void assignFrontPageRoles() {
		MDLQADT3AssignFrontPageRoles roles = new MDLQADT3AssignFrontPageRoles();
		roles.assignTeacherRole();
		roles.assignStudentRole();
		roles.assignStudent2Role();
		roles.assignStudent3Role();
		roles.assignStudent4Role();
		roles.assignStudent5Role();
		roles.assignStudent6Role();
		roles.assignStudent7Role();
		roles.assignStudent8Role();
		roles.assignStudent9Role();
		roles.assignStudent10Role();
		roles.assignStudent11Role();
		navigationBlock.clickHome();
		//TODO Create Add manager role step
		//roles.assignManagerRole();
	}
	@Test
	public void enrolUsers() {
		MDLQADT4EnrolUsers enrol = new MDLQADT4EnrolUsers();
		//TODO re-write the enrol teacher step so you don't have to use the ajax buttons
		enrol.enrolTeacher();
		enrol.assignTeacherRole();
		enrol.enrolStudent();
		enrol.enrolStudent2();
		enrol.enrolStudent3();
		enrol.enrolStudent4();
		enrol.enrolStudent5();
		enrol.enrolStudent6();
		enrol.enrolStudent7();
		enrol.enrolStudent8();
		enrol.enrolStudent9();
		enrol.enrolStudent10();
		enrol.enrolStudent11();
		//TODO create enrol manager step
		//enrol.enrolManager();
		user.selectLogout();
	}
	@Test
	public void addAssignment() {
		user.loginToSystem(this.properties.get("teacherUsername"), this.properties.get("password"));
		course.clickCourseLink(this.properties.get("courseName"));
		course.clickTurnEditingOn();
		addActivity.selectAssignment("2");
		addAssignment.enterNameField(this.properties.get("assignmentName"));
		addAssignment.enterIntroField(this.properties.get("assignmentDescription"));
		addAssignment.selectFileSubmissionsEnabledNo();
		addAssignment.selectOnlineTextEnabledYes();
		addAssignment.clickSaveAndRetToCourse();
	}
	@Test
	public void addChat() {
		addActivity.selectChat("2");
		
	}
}
