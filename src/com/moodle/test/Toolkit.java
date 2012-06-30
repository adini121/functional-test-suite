package com.moodle.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This is a set of utilities to be used with Moodle.
 * @author Tim Barker 
 * @see <a href="http://www.gnu.org/copyleft/gpl.html">License: GNU GPL v3 or later</a>
 */
public class Toolkit {
	private RemoteWebDriver driver;
/**
 * Constructor for the page object.	
 * @param driver The driver that is used for the test. There is no need to specify the value for the driver here as the driver
 * is instantiated in the test using one of the com.moodle.seleniumutils.SeleniumManager constructors.
 */
	public Toolkit(RemoteWebDriver driver) {
		this.driver = driver;
	}
/**
 * Takes a screenshot and appends a timestamp.
 * @param fileDest The absolute path to the destination where the files will be saved.
 * @throws IOException
 */
	public void takeScreenshotWithTimestamp(String fileDest) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		String timeStamp = new SimpleDateFormat().format( new Date() );
		File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(fileDest+timeStamp));
	}
/**
 * Takes a screenshot and does not append a timestamp.
 * @param fileDest The absolute path to the destination where the files will be saved.
 * @throws IOException
 */
	public void takeScreenshotWithGivenLocationAndName(String fileDest) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(fileDest));
	}
/**
 * WebDriver locators will not accept integer values so it if you need to use an integer anywhere you need to cast it to a string to use it in a locator.
 * @param i The integer variable that you want to convert.
 * @return The string that is returned following the conversion.
 */
	public String convertInteger(int i) {
		return Integer.toString(i);
	}
}