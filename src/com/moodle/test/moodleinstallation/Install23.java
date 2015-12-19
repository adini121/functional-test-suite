package com.moodle.test.moodleinstallation;
import com.moodle.test.TestRunSettings;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * Installer for 2.3
 * @author tim
 */
public class Install23 extends TestRunSettings {
	public static String usersData = "properties/data/user/Users/usersData.properties";
	private Map<String, String> properties = new HashMap<String, String>();
	//Load test data from properties file
	public Install23(){
		this.loadTestData();
	}
	public void loadTestData() {
		Properties testData = new Properties();
		try {
			testData.load(new FileInputStream(usersData));
		} catch (Exception e) {}
		this.properties.put("adminUser", testData.getProperty("adminUser"));
		this.properties.put("password", testData.getProperty("password"));
		this.properties.put("adminEmail", testData.getProperty("adminEmail"));
		this.properties.put("city", testData.getProperty("city"));
		this.properties.put("country", testData.getProperty("country"));
		this.properties.put("fullSiteName", testData.getProperty("fullSiteName"));
		this.properties.put("shortSiteName", testData.getProperty("shortSiteName"));
		}
	@Test
	public void install (){
		user.loginToSystem(this.properties.get("adminUser"), this.properties.get("password"));
		addNewUser.enterEmail(this.properties.get("adminEmail"));
		addNewUser.enterCity(this.properties.get("city"));
		addNewUser.enterCountry(this.properties.get("country"));
		addNewUser.clickUpdateProfile();
		installation.enterFullSiteName(this.properties.get("fullSiteName"));
		installation.enterShortSiteName(this.properties.get("shortSiteName"));
		installation.clickSaveChanges();
	}		
}