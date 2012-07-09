package com.moodle.testmanager.pageObjectModel;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for the add chat form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ChatAddForm extends FormAddEditSettings {
/**
 * Locator variables.
 */
	private String locChatTime = "id_chattime_";
	private String locRepSessions = "id_schedule";
	private String locSavePast = "id_keepdays";
	private String locEveryoneViewPast = "id_studentlogs";
	private FormActions formActions = new FormActions(driver);
/**
 * Constructor for the page object.	
 */
	public ChatAddForm(RemoteWebDriver driver) {
		super(driver);
	}
/**
 * Selects a combination of values for "Next chat time" using the dropdowns. 
 * @param day The value for day that you want to select.
 * @param month The value for month that you want to select.
 * @param year The value for year that you want to select.
 * @param hour The value for hour that you want to select.
 * @param minute The value for minute that you want to select.
 */
	public void selectNextChatTime(String day, String month, String year, String hour, String minute){
		formActions.selectDateByID(day, month, year, hour, minute, locChatTime);
	}
/**
 * Selects a value only for the day dropdown of the "Next chat time".
 * @param day The value that you would like to select for day.
 */
	public void selectNextChatDay(String day) {
		formActions.selectDay(day, locChatTime);
	}
/**
 * Selects a value only for the month dropdown of the "Next chat time".
 * @param month The value that you would like to select for month.
 */
	public void selectNextChatMonth(String month) {
		formActions.selectMonth(month, locChatTime);
	}
/**
 * Selects a value only for the year dropdown of the "Next chat time".
 * @param year The value that you would like to select for year.
 */
	public void selectNextChatYear(String year) {
		formActions.selectYear(year, locChatTime);
	}
/**
 * Selects a value only for the hour dropdown of the "Next chat time".
 * @param hour The value that you would like to select for hour.
 */
	public void selectNextChatHour(String hour) {
		formActions.selectHour(hour, locChatTime);
	}
/**
 * Selects a value only for the minute dropdown of the "Next chat time".
 * @param minute The value that you would like to select for minute.
 */
	public void selectNextChatMin(String minute) {
		formActions.selectMin(minute, locChatTime);
	}
/**
 * Selects a value for the Repeat sessions dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectRepeatSessions(String itemToSelect){
		formActions.selectDropdownItemByID(locRepSessions, itemToSelect);
	}
/**
 * Selects a value for the Save past sessions dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectSavePastSessions(String itemToSelect){
		formActions.selectDropdownItemByID(locSavePast, itemToSelect);
	}
/**
 * Selects a value from the "Everyone can view past sessions" dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectEveryoneCanViewPastSessions(String itemToSelect){
		formActions.selectDropdownItemByID(locEveryoneViewPast, itemToSelect);
	}
}