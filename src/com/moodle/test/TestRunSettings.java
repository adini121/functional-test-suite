package com.moodle.test;

import com.moodle.testmanager.pageObjectModel.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.sql.*;

import java.io.*;
import java.util.Calendar;
import java.util.Properties;

public class TestRunSettings {
	//Load test data from properties file
	public TestRunSettings() {
		super();
	}
	protected static RemoteWebDriver driver;
	static SeleniumManager sm;
	public static String runParameters = "properties/runParameters.properties";
	protected Assignment assignment = new Assignment(driver);
	protected AssignmentAddAssignmentForm addAssignment = new AssignmentAddAssignmentForm(driver);
	protected AssignmentAddSubmission submitAssignment = new AssignmentAddSubmission(driver);
	protected AssignmentAdminUpgradeAssignments upgradeAssignment = new AssignmentAdminUpgradeAssignments(driver); 
	protected AssignmentGrading grading = new AssignmentGrading(driver);
	protected AssignmentPluginManageFeedback manageFeedbackPlugin = new AssignmentPluginManageFeedback(driver);
	protected AssignmentPluginManageSubmission manageSubmissionPlugin = new AssignmentPluginManageSubmission(driver);
	protected AssignmentSubmissionComments submissionComments = new AssignmentSubmissionComments(driver);
	protected BlockNavigation navigationBlock = new BlockNavigation(driver);
	protected BlockNews newsBlock = new BlockNews(driver);
	protected BlockSettings settingsBlock = new BlockSettings(driver);
	protected BookAddForm addBook = new BookAddForm(driver);
	protected ChatAddForm addChat = new ChatAddForm(driver);
	protected ChoiceAddForm addChoice = new ChoiceAddForm(driver);
	protected Courses course = new Courses(driver);
	protected CoursesAddActivityResource addActivity = new CoursesAddActivityResource(driver);
	protected CoursesEditCourseSettings editCourseSettings = new CoursesEditCourseSettings(driver);
	protected Databases databases = new Databases(driver);
	protected DatabasesAddDatabase addDatabase = new DatabasesAddDatabase(driver);
	protected DatabasesFields databaseFields = new DatabasesFields(driver);
	protected FolderAddForm addFolder = new FolderAddForm(driver);
	protected Forum forum = new Forum(driver);
	protected ForumAddForm addForum = new ForumAddForm(driver);
	protected ForumPosts forumPosts = new ForumPosts(driver);
	protected ForumSplit splitForum = new ForumSplit(driver);
	protected FrontPageRoles frontPageRoles = new FrontPageRoles(driver);
	protected GlossaryAddForm addGlossary = new GlossaryAddForm(driver);
	protected LabelAddForm addLabel = new LabelAddForm(driver);
	protected LessonAddForm addLesson = new LessonAddForm(driver);
	protected Installation installation = new Installation(driver);
	protected SurveyAddForm addSurvey = new SurveyAddForm(driver);
	protected ProfileEdit editProfile = new ProfileEdit(driver);
	protected QuizAddForm addQuiz = new QuizAddForm(driver);
	protected ReportActivityCompletion activityCompletionReport = new ReportActivityCompletion(driver);
	protected SiteAdministration siteAdmin = new SiteAdministration(driver);
	protected Users user = new Users(driver);
	protected UsersAddNewUser addNewUser = new UsersAddNewUser(driver);
	protected UsersEnrolled enrolledUsers = new UsersEnrolled(driver);
	protected Toolkit frameworkTools = new Toolkit(driver);
	protected WikiAddForm addWiki = new WikiAddForm(driver);
	protected WorkshopAddForm addWorkshop = new WorkshopAddForm(driver);
	
	@BeforeClass
	public static void automateTestSetup() throws FileNotFoundException, IOException {
		//Load properties required for test run
			Properties startupConfig = new Properties();
			startupConfig.load(new FileInputStream(runParameters));
			String gridHubURL = startupConfig.getProperty("gridHubURL");
//			String browserType = startupConfig.getProperty("browserType");
			String moodleHomePage = startupConfig.getProperty("moodleHomePage");
		//Call setup method
			sm = new SeleniumManager();
			sm.startRemotes(gridHubURL);
			//sm.startChromeDriver(chromeDriverLocation);
			//sm.startFirefoxDriver();
			driver = sm.getRemoteDriver();
			String sessionID = driver.getSessionId().toString();

		// Saving browser sessionID to mysql database

			String dburl = "jdbc:mysql://localhost:3306/moodle_sessionIDs";
			String uname = "root";
			String dropQuery = "DROP TABLE IF EXISTS test_session_ids";
			String sqlQuery = "CREATE TABLE IF NOT EXISTS test_session_ids("
					+ "id int unsigned auto_increment not null,"
					+ "session_id VARCHAR(60) not null,"
					+ "date_created VARCHAR(100) not null,"
					+ "primary key(id)"
					+ ")";
			String tblQuery = "INSERT INTO test_session_ids(date_created, session_id)" + "VALUES" + "(?, ?)";

		//Get time stamp
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
			java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			Connection conn = null;
			PreparedStatement ps = null;
			try
			{
				//Register the JDBC Driver Class
				Class.forName("com.mysql.jdbc.Driver");
				//Create the JDBC connection

				conn = DriverManager.getConnection(dburl, uname, "");
				ps = conn.prepareStatement(sqlQuery);
				ps.execute();
				conn = DriverManager.getConnection(dburl,uname, "");
				ps = conn.prepareStatement(tblQuery);
				ps.setString(1, sessionID);
				ps.setString(2, String.valueOf(timestamp));
				ps.execute();

			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(conn != null)
					{

						ps.close();
						//Close the JDBC Connection
						conn.close();

					}
			}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		// Writing to file and saving it to dropbox
		try {
				FileWriter fileWriter = new FileWriter("/home/adi/Dropbox/TestResults/Moodle/sessionIds.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write("testDBsessionID: " + sessionID);
				bufferedWriter.newLine();
				bufferedWriter.close();
			} catch (IOException e) {
			// execption handling
		}
		//driver = sm.getChromeDriver();
		//driver = sm.getFirefoxDriver();
		System.out.println(sessionID);
		driver.get(moodleHomePage);
	}
	@AfterClass
	public static void Quit() {
	//End Webdriver Session by calling teardown method
			sm.teardown();
			//sm.teardownFirefox();
	}

}