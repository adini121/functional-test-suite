package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.moodle.testmanager.FormActions;
/**
 * This is the page object model for adding a Glossary activity to a course.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class GlossaryAddForm extends FormAddEditSettings {
/**
 * Constructors
 */
	private FormActions formActions = new FormActions(driver);
/**
 * Hashmap for language file.
 */
	private Map<String, String> properties = new HashMap<String, String>();
/**
 * Language file location.
 */
	private String langFile =  "properties/data/static/GlossaryAddForm.properties";
/**
 * Locator variables.
 */
	private String locEntriesShown = "id_entbypage";
	private String locIsGlossaryGlobal = "id_globalglossary";
	private String locGlossaryType = "id_mainglossary";
	private String locDuplicateAllowed = "id_allowduplicatedentries";
	private String locAllowComments = "id_allowcomments";
	private String locAllowPrint = "id_allowprintview";
	private String locAllowAutoLink = "id_usedynalink";
	private String locAppByDefault = "id_defaultapproval";
	private String locDisplayFormat = "id_displayformat";
	private String locAppDisplayFormat = "id_approvaldisplayformat";
	private String locSpecLink = "id_showspecial";
	private String locShowAlphabet = "id_showalphabet";
	private String locShowAllLink = "id_showall";
	private String locEditAlways = "id_editalways";
	private String locAggregateType = "id_assessed";
	private String locScale = "id_scale";
	private String locRestrictRatings = "id_ratingtime";
	private String locRestrictRatingFromPrefix = "id_assesstimestart_";
	private String locRestrictRatingToPrefix = "id_assesstimefinish_";
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public GlossaryAddForm(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData(langFile);
	}
/**
 * Loads data for the page object from the internationalization layer /properties/data/static/blockNavigation.properties
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties databaseAddData = new Properties();
		try {
			databaseAddData.load(new FileInputStream(langFile));
		} catch (Exception e) {}
		//put values from the properties file into hashmap
		this.properties.put("showAdvanced", databaseAddData.getProperty("showAdvanced"));
	}
/**
 * Enters a value in the "Entries shown per page" field.
 * @param numberOfEntries The value that you would like to enter.
 */
	public void enterEntriesShownPerPageField(String numberOfEntries) {
		driver.findElementById(locEntriesShown).sendKeys(numberOfEntries);
	}
/**
 * Selects or de-selects the "Is this glossary global?" checkbox. 
 */
	public void checkboxGlossaryGlobal() {
		driver.findElementById(locIsGlossaryGlobal).click();
	}
/**
 * Selects a value from the "Glossary type" dropdown.
 * @param glossaryType The value you would like to select for "Glossary type".
 */
	public void selectGlossaryType(String glossaryType) {
		formActions.selectDropdownItemByID(locGlossaryType, glossaryType);
	}
/**
 * Selects a value from the "Duplicate entries allowed" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectDuplicateAllowed(String yesNo) {
		formActions.selectDropdownItemByID(locDuplicateAllowed, yesNo);
	}
/**
 * Selects a value from the "Allow comments on all entries" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectAllowComments(String yesNo) {
		formActions.selectDropdownItemByID(locAllowComments, yesNo);
	}
/**
 * Selects a value from the "Allow print view" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectAllowPrintView(String yesNo) {
		formActions.selectDropdownItemByID(locAllowPrint, yesNo);
	}
/**
 * Selects a value from the "Automatically link glossary entries" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectAutoLinkEntries(String yesNo) {
		formActions.selectDropdownItemByID(locAllowAutoLink, yesNo);
	}
/**
 * Selects a value from the "Approval by default" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectApprovedByDefault(String yesNo) {
		formActions.selectDropdownItemByID(locAppByDefault, yesNo);
	}
/**
 * Selects a value from the "Display format" dropdown.
 * @param displayFormat The desired value for Display format.
 */
	public void selectDisplayFormat(String displayFormat) {
		formActions.selectDropdownItemByID(locDisplayFormat, displayFormat);
	}
/**
 * Selects a value from the "Approval display format" dropdown.
 * @param displayFormat The desires value for Display format.
 */
	public void selectApprovalDisplayFormat(String displayFormat) {
		formActions.selectDropdownItemByID(locAppDisplayFormat, displayFormat);
	}
/**
 * Selects a value from the "Show 'Special' link" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectShowSpecialLink(String yesNo) {
		formActions.selectDropdownItemByID(locSpecLink, yesNo);
	}
/**
 * Selects a value from the "Show alphabet" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectShowAlphabet(String yesNo) {
		formActions.selectDropdownItemByID(locShowAlphabet, yesNo);
	}
/**
 * Selects a value from the "Show 'ALL' link" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectShowAllLink(String yesNo) {
		formActions.selectDropdownItemByID(locShowAllLink, yesNo);
	}
/**
 * Selects a value from the "Edit always" dropdown.
 * @param yesNo The valid values are Yes or No.
 */
	public void selectEditAlways(String yesNo) {
		formActions.selectDropdownItemByID(locEditAlways, yesNo);
	}
/**
 * Selects a value from the "Aggregate type" dropdown.
 * @param aggregateType The desired value for "Aggregate type"
 */
	public void selectAggregateType(String aggregateType) {
		formActions.selectDropdownItemByID(locAggregateType, aggregateType);
	}
/**
 * Selects a value from the "Scale" dropdown.
 * @param scale The desired value for "Scale".
 */
	public void selectScale(String scale) {
		formActions.selectDropdownItemByID(locScale, scale);
	}
/**
 * Selects or de-selects the "Restrict ratings to items with dates in this range" checkbox.
 */
	public void checkboxRestrictRatings() {
		driver.findElementById(locRestrictRatings);
	}
/**
 * Selects a date range in the "Restrict ratings to items with dates in this range: From" field.
 * @param dd The desired value for day.
 * @param month The desired value for month.
 * @param yyyy The desired value for year.
 * @param hh The desired value for hour.
 * @param mm The desired value for minutes.
 */
	public void selectRestrictRatingsFrom(String dd, String month, String yyyy, String hh, String mm) {
		formActions.selectDateByID(dd, month, yyyy, hh, mm, locRestrictRatingFromPrefix);
	}
/**
 * Selects a date range in the "Restrict ratings to items with dates in this range: To" field.
 * @param dd The desired value for day.
 * @param month The desired value for month.
 * @param yyyy The desired value for year.
 * @param hh The desired value for hour.
 * @param mm The desired value for minutes.
 */
	public void selectRestrictRatingsTo(String dd, String month, String yyyy, String hh, String mm) {
		formActions.selectDateByID(dd, month, yyyy, hh, mm, locRestrictRatingToPrefix);
	}
}