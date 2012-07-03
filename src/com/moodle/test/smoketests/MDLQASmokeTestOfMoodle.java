package com.moodle.test.smoketests;

import java.net.MalformedURLException;

import org.junit.Test;

import com.moodle.test.TestDataLoad;
import com.moodle.test.datacreation.MDLQADT1AddCourse;
import com.moodle.test.datacreation.MDLQADT2AddUsers;
import com.moodle.test.datacreation.MDLQADT3AssignFrontPageRoles;
import com.moodle.test.datacreation.MDLQADT4EnrolUsers;
import com.moodle.test.moodleinstallation.Install23;

public class MDLQASmokeTestOfMoodle extends TestDataLoad {
	
	//@Test
	public void installation() {
		Install23 install23 = new Install23();
		install23.install();
	}
	//@Test
	public void addCourse() throws MalformedURLException {
		MDLQADT1AddCourse addCourse = new MDLQADT1AddCourse();
		addCourse.addCourse();
	}
	//@Test
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
	//@Test
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
	//@Test
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
	//@Test
	public void addAssignment() {
		user.loginToSystem(this.properties.get("teacher"), this.properties.get("password"));
		course.clickCourseLink(this.properties.get("courseName"));
		course.clickTurnEditingOn();
		addActivity.selectAssignment("2");
		addAssignment.enterNameField(this.properties.get("assignmentName"));
		addAssignment.enterIntroField(this.properties.get("assignmentDescription"));
		addAssignment.selectFileSubmissionsEnabledNo();
		addAssignment.selectOnlineTextEnabledYes();
		addAssignment.clickSaveAndRetToCourse();
	}
	//@Test
	public void addChat() {
		addActivity.selectChat("2");
		addChat.enterNameField(this.properties.get("chatName"));
		addChat.enterIntroField(this.properties.get("chatDescription"));
		addChat.clickSaveAndRetToCourse();
	}
	//@Test
	public void addChoice() {
		addActivity.selectChoice("2");
		addChoice.enterNameField(this.properties.get("choiceName"));
		addChoice.enterIntroField(this.properties.get("choiceDescription"));
		addChoice.enterOptionField("0", "option1");
		addChoice.enterOptionField("1", "option2");
		addChoice.clickSaveAndRetToCourse();
	}
	//@Test
	public void addDatabase() {
		addActivity.selectDatabase("2");
		addDatabase.enterNameField(this.properties.get("databaseName"));
		addDatabase.enterIntroField(this.properties.get("databaseDescription"));
		addDatabase.clickSaveAndRetToCourse();
	}
	//@Test
	public void addLTI() {
		//TODO will come back to this as it isn't a priority and we will need some setup doing in advance
	}
	//@Test
	public void addForum() {
		addActivity.selectForum("2");
		addForum.enterNameField(this.properties.get("forumName"));
		addForum.enterIntroField(this.properties.get("forumDescription"));
		addForum.clickSaveAndRetToCourse();
	}
	//@Test
	public void addGlossary() {
		addActivity.selectGlossary("2");
		addGlossary.enterNameField(this.properties.get("glossaryName"));
		addGlossary.enterIntroField(this.properties.get("glossaryDescription"));
		addGlossary.clickSaveAndRetToCourse();
	}
	//@Test
	public void addLesson() {
		addActivity.selectLesson("2");
		addLesson.enterNameField(this.properties.get("lessonName"));
		addLesson.clickSaveAndRetToCourse();
	}
	//@Test
	public void addSurvey() {
		addActivity.selectSurvey("2");
		addSurvey.enterNameField(this.properties.get("surveyName"));
		addSurvey.enterIntroField(this.properties.get("surveyDescription"));
		addSurvey.selectSurveyType(this.properties.get("surveyType"));
		addLesson.clickSaveAndRetToCourse();
	}
	//@Test
	public void addQuiz() {
		addActivity.selectQuiz("2");
		addQuiz.enterNameField(this.properties.get("quizName"));
		addQuiz.enterIntroField(this.properties.get("quizDescription"));
		addQuiz.clickSaveAndRetToCourse();
	}
	//@Test
	public void addWiki() {
		addActivity.selectWiki("2");
		addWiki.enterNameField(this.properties.get("wikiName"));
		addWiki.enterIntroField(this.properties.get("wikiDescription"));
		addWiki.enterFirstPageNameField(this.properties.get("wikiFirstPageName"));
		addWiki.clickSaveAndRetToCourse();
	}
	//@Test
	public void addWorkshop() {
		addActivity.selectWorkshop("2");
		addWorkshop.enterNameField(this.properties.get("workshopName"));
		addWorkshop.enterIntroField(this.properties.get("workshopDescription"));
		addWorkshop.clickSaveAndRetToCourse();
	}
	@Test
	public void addBook() {
		user.loginToSystem(this.properties.get("teacher"), this.properties.get("password"));
		course.clickCourseLink(this.properties.get("courseName"));
		course.clickTurnEditingOn();
		addActivity.selectBook("3");
		addBook.enterNameField(this.properties.get("bookName"));
		addBook.enterIntroField(this.properties.get("bookDescription"));
		addBook.clickSaveAndRetToCourse();
	}
}