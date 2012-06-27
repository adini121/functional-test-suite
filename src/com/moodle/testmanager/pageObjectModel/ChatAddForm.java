package com.moodle.testmanager.pageObjectModel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.moodle.seleniumutils.FormActions;
/**
 * This is the page object model for the add forum form.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class ChatAddForm extends FormAddEditSettings {
	protected Map<String, String> properties = new HashMap<String, String>();
/**
 * Constructor for the page object.	
 */
	public ChatAddForm(RemoteWebDriver driver) {
		super(driver);
		this.loadObjectData("properties/data/static/forumAdd.properties");
	}
/**
 * Loads data for the page object from the internationalization layer
 * where a selector requires a text string visible through the user interface e.g. value=button text, or link text.
 */
	public void loadObjectData(String datafile) {
		Properties dataLoad = new Properties();
		try {
			dataLoad.load(new FileInputStream("properties/data/static/forumAdd.properties"));
		} catch (Exception e) {}
		this.properties.put("xxxx", dataLoad.getProperty("xxxx"));
	}
	public void checkboxDescOnCourse(){
		//TODO
	}
	public void selectNextChatTime(){
		//TODO
	}
	public void selectRepeatSessions(){
		//TODO
	}
	public void selectSavePastSessions(){
		//TODO
	}
	public void selectEveryoneCanViewPastSessions(){
		//TODO
	}
	public void selectGroupMode(){
		//TODO
	}
	public void selectVisible(){
		//TODO
	}
	public void idNumber(){
		//TODO
	}
}