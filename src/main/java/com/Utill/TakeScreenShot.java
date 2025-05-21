package com.Utill;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class TakeScreenShot {
	/**
	 * Takes a screenshot and saves it to the /src/test/resources/ss/ directory.
	 * Filename includes test method name and timestamp.
	 * 
	 * @param result ITestResult containing information about the test
	 * @return The full path of the saved screenshot file
	 * @throws IOException if file operations fail
	 */
	public static String takeScreenShot(WebDriver driver,ITestResult result) throws IOException {
		// Cast driver to TakesScreenshot interface
		TakesScreenshot ss = (TakesScreenshot) driver;

		// Capture screenshot as a file
		File source = ss.getScreenshotAs(OutputType.FILE);

		// Create unique filename
		String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";

		// Define destination path
		String path = System.getProperty("user.dir") + "/src/test/resources/ss/" + fileName;
		File dest = new File(path);

		// Copy screenshot to destination folder
		FileUtils.copyFile(source, dest);

		return path;
	}
}
