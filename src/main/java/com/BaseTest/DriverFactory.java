package com.BaseTest;

import org.openqa.selenium.WebDriver;

/**
 * Facade class combining DriverManager and WebDriverProvider responsibilities.
 */
public class DriverFactory {

	private DriverFactory() {
	}

	/**
	 * Get or create a thread-safe WebDriver instance for the specified browser.
	 */
	public static WebDriver getDriver(String browser,String headLessMode) {
		if (DriverManager.getDriver() == null) {
			WebDriver driver = WebDriverProvider.createDriver(browser,headLessMode);
			DriverManager.setDriver(driver);
		}
		return DriverManager.getDriver();
	}

	public static void quitDriver() {
		DriverManager.quitDriver();
	}
}
