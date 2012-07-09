package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the add quiz form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class QuizAddForm extends FormAddEditSettings{
/**
 * Locator variables.
 */
	private String locEnableOpen="id_timeopen_enabled";
	private String locOpenDatePrefix="id_timeopen_";
	private String locEnableClose="id_timeclose_enabled";
	private String locCloseDatePrefix="id_timeclose_";
	private String locEnableTimeLimit="id_timelimit_enabled";
	private String locUnitsLimit="id_timelimit_timeunit";
	private String locTimeLimitVal="id_timelimit_number";
	private String locWhenTimeExp="id_overduehandling";
	private String locEnableGrace="id_graceperiod_enabled";
	private String locGrace="id_graceperiod_number";
	private String locGraceUnit="id_graceperiod_timeunit";
	private String locAttemptsAllowed="id_attempts";
	private String locQuestionOrder="id_shufflequestions";
	private String locNewPage="id_questionsperpage";
	private String locShuffle="id_shuffleanswers";
	private String locBehave="id_preferredbehaviour";
	private String locDuringAtt="id_attemptduring";
	private String locDuringWhetherCor="id_correctnessduring";
	private String locDuringMarks="id_marksduring";
	private String locDuringSpec="id_specificfeedbackduring";
	private String locDuringGen="id_generalfeedbackduring";
	private String locDuringRight="id_rightanswerduring";
	private String locDuringOverall="id_overallfeedbackduring";
	private String locImmAttempt="id_attemptimmediately";
	private String locImmWhetherCorr="id_correctnessimmediately";
	private String locImmMarks="id_marksimmediately";
	private String locImmSpecFB="id_specificfeedbackimmediately";
	private String locImmGenFB="id_generalfeedbackimmediately";
	private String locImmRight="id_rightanswerimmediately";
	private String locImmOverall="id_overallfeedbackimmediately";
	private String locLaterAttempt="id_attemptopen";
	private String locLaterWhetherCorr="id_correctnessopen";
	private String locLaterMarks="id_marksopen";
	private String locLaterSpecFB="id_specificfeedbackopen";
	private String locLaterGenFB="id_generalfeedbackopen";
	private String locLaterRight="id_rightansweropen";
	private String locLaterOverall="id_overallfeedbackopen";
	private String locAfterAttempt="id_attemptclosed";
	private String locAfterWhetherCorr="id_correctnessclosed";
	private String locAfterMarks="id_marksclosed";
	private String locAfterSpecFB="id_specificfeedbackclosed";
	private String locAfterGenFB="id_generalfeedbackclosed";
	private String locAfterRight="id_rightanswerclosed";
	private String locAfterOverall="id_overallfeedbackclosed";
	private String locShowPic="id_showuserpicture";
	private String locNoDecPlaces="id_decimalpoints";
	private String locDecPlaceQuestion="id_questiondecimalpoints";
	private String locReqPW="id_quizpassword";
	private String locUnmaskPW="id_quizpasswordunmask";
	private String locReqNetworkAdd="id_subnet";
	private String locEnableDelay12="id_delay1_enabled";
	private String locEnablelater="id_delay2_enabled";
	private String locEnfDelay12Val="id_delay1_number";
	private String locEnfDelay12Unit="id_delay1_timeunit";
	private String locEnfDelayLaterVal="id_delay2_number";
	private String locEnfDelayLaterUnit="id_delay2_timeunit";
	private String locGradeBoundaryIDPrefix="id_feedbackboundaries_";
	private String locAdd3Fields="id_boundary_add_fields";
/**
 * Constructor for the page object.	
 */
	public QuizAddForm(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData("properties/data/static/SurveyAddForm.properties");
	}
/**
 * Loads data for the page object from the internationalization layer
 * where a locator requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/QuizAddForm.properties"));
		} 
		catch (Exception e) {}
	}
/*
 * Specific object methods.
 */
/**
 * Generic checkbox click method for re-use in this class.
 * @param cboxLocator The locator of the checkbox, using ID only.
 */
	private void checkboxQuiz(String cboxLocator){
		WebElement checkbox = driver.findElementById(cboxLocator);
		checkbox.click();
	}
/**
 * Generic dropdown method for re-use in this class.
 * @param dropdownLocator The locator of the dropdown. With everything here is an id.
 * @param itemToSelect The item that is to be selected.
 */
	private void dropdownQuiz(String dropdownLocator, String itemToSelect){
		FormActions dropdown = new FormActions(driver);
		dropdown.selectDropdownItemByID(dropdownLocator, itemToSelect);
	}
/**
 * Generic datetime dropdown selection for this class.
 * @param dd The day in the format dd.
 * @param month The month in the format month.
 * @param yyyy The year in the format yyyy.
 * @param hh The hour in the format hh.
 * @param mm The minute in the format mm.
 * @param dateLocatorPrefix The locator of the date field. This is a prefix value for ID here.
 * <br> e.g. id_foo_ where hour is appended automatically in FormActions.selectDateByID to make a concatenated value of id_foo_hour.  
 */
	private void dropdownQuizDateTime(String dd, String month, String yyyy, String hh, String mm, String dateLocatorPrefix){
		FormActions dateTime = new FormActions(driver);
		dateTime.selectDateByID(dd, month, yyyy, hh, mm, dateLocatorPrefix);
	}
/**
 * Generic text entry field for this class.
 * @param fieldLocator The locator value for field. Allways ID in this form.
 * @param valueToEnter The value that you would like to enter in the text field.
 */
	private void textFieldQuiz(String fieldLocator, CharSequence valueToEnter){
		WebElement textField = driver.findElementById(fieldLocator);
		textField.sendKeys(valueToEnter);
	}
/**
 * To be implemented
 * @param feedback
 */
	private void richTextQuiz(String feedback){
		//TODO the location strategy for this is going to be difficult. Going to leave it until I need it for testing.
	}
/**
 * Selects the "Enble" checkbox for the "Open the quiz" dropdowns.
 */
	public void checkboxEnableOpenQuiz() {
		checkboxQuiz(locEnableOpen);
	}
/**
 * Selects the datetime value for "Open the quiz".
 * @param dd The day in the format dd.
 * @param month The month in the format month.
 * @param yyyy The year in the format yyyy.
 * @param hh The hour in the format hh.
 * @param mm The minute in the format mm.
 */
	public void selectOpenTheQuiz(String dd, String month, String yyyy, String hh, String mm) {
		dropdownQuizDateTime(dd, month, yyyy, hh, mm, locOpenDatePrefix);
	}
/**
 * Selects the "Enble" checkbox for the "Close the quiz" dropdowns.
 */
	public void checkboxEnableCloseQuiz() {
		checkboxQuiz(locEnableClose);
	}
/**
 * Selects the datetime value for "Close the quiz".
 * @param dd The day in the format dd.
 * @param month The month in the format month.
 * @param yyyy The year in the format yyyy.
 * @param hh The hour in the format hh.
 * @param mm The minute in the format mm.
 */
	public void selectCloseTheQuiz(String dd, String month, String yyyy, String hh, String mm) {
		dropdownQuizDateTime(dd, month, yyyy, hh, mm, locCloseDatePrefix);
	}
/**
 * Selects the checkbox to enable the "Time limit" field.
 */
	public void checkboxEnableTimeLimit() {
		checkboxQuiz(locEnableTimeLimit);
	}
/**
 * Enters a value for "Time limit" and selects a value for the units.
 * @param unitOfTime The unit of time.
 * @param valueToEnter The vlaue that you would like to enter.
 */
	public void selectTimeLimit(String unitOfTime, CharSequence valueToEnter) {
		textFieldQuiz(locTimeLimitVal, valueToEnter);
		dropdownQuiz(locUnitsLimit, unitOfTime);
	}
/**
 * Selects a value from the "When time expires" dropdown.
 * @param desiredValue The value that you want to select from the dropdown.
 */
	public void enterWhenTimeExpires(String desiredValue) {
		dropdownQuiz(locWhenTimeExp, desiredValue);
	}
/**
 * Enables/disables "Submission grace period".
 */
	public void checkboxEnableSubmissionGracePeriod() {
		checkboxQuiz(locEnableGrace);
	}
/**
 * Enters a value for "Grace period" and selects a value for the units.
 * @param gracePeriod The numeric grace preiod value.
 * @param timeUnit The unit of time that you would like to use.
 */
	public void enterSubmissionGracePeriod(CharSequence gracePeriod, String timeUnit) {
		textFieldQuiz(locGrace, gracePeriod);
		dropdownQuiz(locGraceUnit, timeUnit);
	}
/**
 * Selects a value from the "Attempts allowed" field.
 * @param numberOfAttempts The value for number of attempts.
 */
	public void selectAttemptsAllowed(String numberOfAttempts) {
		dropdownQuiz(locAttemptsAllowed, numberOfAttempts);
	}
/**
 * Selects a value from the "Question order" dropdown.
 * @param questionOrder The value that you would like to select for "Question order".
 */
	public void selectQuestionOrder(String questionOrder) {
		dropdownQuiz(locQuestionOrder, questionOrder);
	}
/**
 * Selects a value from the "New page" dropdown.
 * @param newPage The value for "New page".
 */
	public void selectNewPage(String newPage) {
		dropdownQuiz(locNewPage, newPage);
	}
/**
 * Selects a value for "Shuffle within questions" from the dropdown. 
 * @param yesNo Yes or No.
 */
	public void selectShuffleQuestions(String yesNo) {
		dropdownQuiz(locShuffle, yesNo);
	}
/**
 * Selects a value for "How questions behave" from the dropdown.
 * @param howBehave The value that you would like to select.
 */
	public void selectHowQuestionsBehave(String howBehave) {
		dropdownQuiz(locBehave, howBehave);
	}
	//Review options checkboxes
	//During the attempt
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringTheAttempt() {
		checkboxQuiz(locDuringAtt);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringWhetherCorrect() {
		checkboxQuiz(locDuringWhetherCor);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringMarks() {
		checkboxQuiz(locDuringMarks);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringSpecificFeedback() {
		checkboxQuiz(locDuringSpec);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringGeneralFeedBack() {
		checkboxQuiz(locDuringGen);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringRightAnswer() {
		checkboxQuiz(locDuringRight);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxDuringOverallFeedback() {
		checkboxQuiz(locDuringOverall);
	}
	//During the attempt
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterTheAttempt() {
		checkboxQuiz(locImmAttempt);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterWhetherCorrect() {
		checkboxQuiz(locImmWhetherCorr);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterMarks() {
		checkboxQuiz(locImmMarks);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterSpecificFeedback() {
		checkboxQuiz(locImmSpecFB);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterGeneralFeedBack() {
		checkboxQuiz(locImmGenFB);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterRightAnswer() {
		checkboxQuiz(locImmRight);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxImmAfterOverallFeedback() {
		checkboxQuiz(locImmOverall);
	}
	//Later
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterTheAttempt() {
		checkboxQuiz(locLaterAttempt);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterWhetherCorrect() {
		checkboxQuiz(locLaterWhetherCorr);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterMarks() {
		checkboxQuiz(locLaterMarks);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterSpecificFeedback() {
		checkboxQuiz(locLaterSpecFB);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterGeneralFeedBack() {
		checkboxQuiz(locLaterGenFB);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterRightAnswer() {
		checkboxQuiz(locLaterRight);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxLaterOverallFeedback() {
		checkboxQuiz(locLaterOverall);
	}
	//After the attempt
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterTheAttempt() {
		checkboxQuiz(locAfterAttempt);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterWhetherCorrect() {
		checkboxQuiz(locAfterWhetherCorr);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterMarks() {
		checkboxQuiz(locAfterMarks);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterSpecificFeedback() {
		checkboxQuiz(locAfterSpecFB);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterGeneralFeedBack() {
		checkboxQuiz(locAfterGenFB);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterRightAnswer() {
		checkboxQuiz(locAfterRight);
	}
/**
 * Selects or deselects the checkbox.
 */
	public void checkboxAfterOverallFeedback() {
		checkboxQuiz(locAfterOverall);
	}
	//Display
/**
 * Selects whether to display the user's picture.
 * @param yesNo Yes or No.
 */
	public void selectShowUserPicture(String yesNo) {
		dropdownQuiz(locShowPic, yesNo);
	}
/**
 * Selects a value from the "Decimal places in grades" dropdown.
 * @param noDecimalPlaces The desired value for "Decimal places in grades".
 */
	public void selectDecimalPlacesGrades(String noDecimalPlaces) {
		dropdownQuiz(locNoDecPlaces, noDecimalPlaces);
	}
/**
 * Selects a value for "Decimal places in question grades" from the dropdown.
 * @param noDecimalPlaces
 */
	public void selectDecimalPlacesQuesGrades(String noDecimalPlaces) {
		dropdownQuiz(locDecPlaceQuestion, noDecimalPlaces);
	}
/**
 * Enters a value in the "Require password" text field.
 * @param valueToEnter The value that you would like to enter.
 */
	public void enterRequirePWField(CharSequence valueToEnter) {
		textFieldQuiz(locReqPW, valueToEnter);
	}
/**
 * Selects the checkbox to unmask the Password in the "Require password" field.
 */
	public void checkboxUnmaskReqPW() {
		checkboxQuiz(locUnmaskPW);
	}
/**
 * Enters a value in the "Require network address" field.
 */
	public void enterReqNetworkAddressField(CharSequence networkAddress) {
		textFieldQuiz(locReqNetworkAdd, networkAddress);
	}
/**
 * Selects the checkbox to enable the "Enforced delay between 1st and 2nd attempts" field.
 */
	public void checkboxEnableEnfDelay1and2() {
		checkboxQuiz(locEnableDelay12);
	}
/**
 * Enters a value for "Enforced delay between 1st and 2nd attempts" and selects a value for the units.
 * @param unitOfTime The unit of time.
 * @param delayValue The value that you would like to enter.
 */
	public void selectEnfDelay1and2(CharSequence delayValue, String timeUnit) {
		textFieldQuiz(locEnfDelay12Val, delayValue);
		dropdownQuiz(locEnfDelay12Unit, timeUnit);
	}
/**
 * Selects the checkbox to enable the "Enforced delay between later attempts" field.
 */
	public void checkboxEnableEnfDelayLaterAtt() {
		checkboxQuiz(locEnablelater);
	}
/**
 * Enters a value for "Enforced delay between later attempts" and selects a value for the units.
 * @param unitOfTime The unit of time.
 * @param delayValue The value that you would like to enter.
 */
	public void selectEnfDelayLater(CharSequence delayValue, String timeUnit) {
		textFieldQuiz(locEnfDelayLaterVal, delayValue);
		dropdownQuiz(locEnfDelayLaterUnit, timeUnit);
	}
/**
 * Enters a value for a specific "Grade boundary" field.
 * @param gradeBoundarySection The section that you would like to enter a value for "Grade boundary".
 * @param boundaryValue The value that you would like to enter for "Grade boundary".
 */
	public void enterGradeBoundaryField(String gradeBoundarySection, CharSequence boundaryValue) {
		textFieldQuiz(locGradeBoundaryIDPrefix + gradeBoundarySection, boundaryValue);
	}
/**
 * To be implemented
 * @param feedback
 */
	public void enterFeedbackField(String feedback) {
		//TODO Still some work to do on the location strategy for this element.
		richTextQuiz(feedback);
	}
/**
 * Clicks the button to add three fields to the "Overall feedback" section.
 */
	public void clickButtonAdd3Fields() {
		WebElement button = driver.findElement(By.id(locAdd3Fields));
		button.click();
	}
}