package com.moodle.testmanager.pageObjectModel;


import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * An add/edit settings form with a description field and no group mode.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public abstract class FormAddEditSettingsWithDesc extends FormAddEdit {
	public FormAddEditSettingsWithDesc(RemoteWebDriver driver) {
		super(driver);
	}
/**
 * Enter a value in the Forum introduction field.
 * @param text The value to be entered passed from the test.
 */
	public void enterIntroField(String message) {
		formActions.enterValueInTinyMCE(message);	
	}
/**
 * Selects or deselects the "Display description on page" checkbox.  
 */
	public void checkboxDescOnCourse(){
		formObjects.checkBoxDescOnCourse();
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