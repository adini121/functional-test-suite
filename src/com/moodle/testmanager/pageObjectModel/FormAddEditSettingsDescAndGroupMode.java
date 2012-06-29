package com.moodle.testmanager.pageObjectModel;


import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * The page object model for the Add Assignment form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FormAddEditSettingsDescAndGroupMode extends FormAddEdit {
	protected RemoteWebDriver driver;
	public FormAddEditSettingsDescAndGroupMode(RemoteWebDriver driver) {
		super(driver);
}
/**
 * Enter a value in the Forum introduction field.
 * @param text The value to be entered passed from the test.
 */
/*	public void enterIntroField(String message) {
		this.formActions.enterValueInTinyMCE(message);
	}*/
/**
 * Selects or deselects the "Display description on page" checkbox.  
 */
	public void checkboxDescOnCourse(){
		formObjects.checkBoxDescOnCourse();
	}
/**
 * Selects a value from the "Group mode" dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectGroupMode(String itemToSelect){
		formObjects.selectGroupMode(itemToSelect);
	}
/**
 * Selects a value from the "Visible" dropdown.
 * @param itemToSelect The value that you would like to select from the dropdown.
 */
	public void selectVisible(String itemToSelect){
		formObjects.selectVisible(itemToSelect);
	}
/**
 * Enters a vlaue in the "ID number" field.
 * @param idNumber The ID number that you want to send.
 */
	public void idNumber(String idNumber){
		formObjects.enterIDNumber(idNumber);
	}
}