package com.moodle.test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestDataLoad extends TestRunSettings{

	public static String usersData = "properties/data/user/Users/usersData.properties";
	public static String assignmentData = "properties/data/user/Assignment/assignmentData.properties";
	public static String courseData = "properties/data/user/Courses/courseData.properties";
	public static String smokeData = "properties/data/user/Smoke/smoke.properties";
	protected Map<String, String> properties = new HashMap<String, String>();

	public TestDataLoad() {
		super();
		this.loadTestData();
	}

	public void loadTestData() {
	Properties testData = new Properties();
	try {
		testData.load(new FileInputStream(usersData));
		testData.load(new FileInputStream(courseData));
		testData.load(new FileInputStream(smokeData));
		testData.load(new FileInputStream(assignmentData));
	} catch (Exception e) {}
	//User Data
	this.properties.put("adminUser", testData.getProperty("adminUser"));
	this.properties.put("teacherUsername", testData.getProperty("teacherUsername"));
	this.properties.put("studentUsername", testData.getProperty("studentUsername"));
	this.properties.put("studentFirstname", testData.getProperty("studentFirstname"));
	this.properties.put("studentSurname", testData.getProperty("studentSurname"));
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
	this.properties.put("chatName", testData.getProperty("chatName"));
	this.properties.put("chatDescription", testData.getProperty("chatDescription"));
	this.properties.put("choiceName", testData.getProperty("choiceName"));
	this.properties.put("choiceDescription", testData.getProperty("choiceDescription"));
	this.properties.put("databaseName", testData.getProperty("databaseName"));
	this.properties.put("databaseDescription", testData.getProperty("databaseDescription"));
	this.properties.put("forumName", testData.getProperty("forumName"));
	this.properties.put("forumDescription", testData.getProperty("forumDescription"));
	this.properties.put("glossaryName", testData.getProperty("glossaryName"));
	this.properties.put("glossaryDescription", testData.getProperty("glossaryDescription"));
	this.properties.put("lessonName", testData.getProperty("lessonName"));
	this.properties.put("quizName", testData.getProperty("quizName"));
	this.properties.put("quizDescription", testData.getProperty("quizDescription"));
	this.properties.put("surveyName", testData.getProperty("surveyName"));
	this.properties.put("surveyDescription", testData.getProperty("surveyDescription"));
	this.properties.put("surveyType", testData.getProperty("surveyType"));
	this.properties.put("wikiName", testData.getProperty("wikiName"));
	this.properties.put("wikiDescription", testData.getProperty("wikiDescription"));
	this.properties.put("wikiFirstPageName", testData.getProperty("wikiFirstPageName"));
	this.properties.put("workshopName", testData.getProperty("workshopName"));
	this.properties.put("workshopDescription", testData.getProperty("workshopDescription"));
	this.properties.put("bookName", testData.getProperty("bookName"));
	this.properties.put("bookDescription", testData.getProperty("bookDescription"));
	this.properties.put("folderName", testData.getProperty("folderName"));
	this.properties.put("folderDescription", testData.getProperty("folderDescription"));
	this.properties.put("subFolderName", testData.getProperty("subFolderName"));
	this.properties.put("label", testData.getProperty("label"));
	//Assignment Test Data	
	this.properties.put("MDLQA59AssignmentName", testData.getProperty("MDLQA59AssignmentName"));
	this.properties.put("MDLQA59StudentEditedSubmissionText", testData.getProperty("MDLQA59StudentEditedSubmissionText"));
	this.properties.put("MDLQA60SubmissionStatusSubmitted", testData.getProperty("MDLQA60SubmissionStatusSubmitted"));
	this.properties.put("MDLQA60SubmissionStatusGraded", testData.getProperty("MDLQA60SubmissionStatusGraded"));
	this.properties.put("MDLQA60FeedbackComments", testData.getProperty("MDLQA60FeedbackComments"));
	this.properties.put("MDLQA60Grade", testData.getProperty("MDLQA60Grade"));
	this.properties.put("MDLQA60ScreenCaptureLocation", testData.getProperty("MDLQA60ScreenCaptureLocation"));
	}
}