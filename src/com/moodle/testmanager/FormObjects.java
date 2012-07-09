package com.moodle.testmanager;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Re-used form objects in one place.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class FormObjects {
	protected RemoteWebDriver driver;
	protected FormActions formActions = new FormActions(driver);
/**
 * Locator variables.	
 */
	private String locShowDesc = "id_showdescription";
	private String locSelectGpMode = "id_groupmode";
	private String locSelectVisible = "id_visible";
	private String locIDNo = "id_cmidnumber";

	public FormObjects(RemoteWebDriver driver) {
		this.driver = driver;
	}
/**
 * The "Display description on course page" checkbox.
 */
	public void checkBoxDescOnCourse() {
		this.driver.findElementById(locShowDesc).click();
	}
/**
 * The select group mode field.
 * @param itemToSelect
 */
	public void selectGroupMode(String itemToSelect) {
		this.formActions.selectDropdownItemByID(locSelectGpMode, itemToSelect);
	}
/**
 * The select visible field.
 * @param itemToSelect
 */
	public void selectVisible(String itemToSelect) {
		this.formActions.selectDropdownItemByID(locSelectVisible, itemToSelect);		
	}
/**
 * The ID number field.
 * @param idNumber
 */
	public void enterIDNumber(String idNumber) {
		this.driver.findElementById(locIDNo).sendKeys(idNumber);
	}
}
