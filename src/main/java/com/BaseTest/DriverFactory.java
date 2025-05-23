package com.BaseTest;

import org.openqa.selenium.WebDriver;

/**
 * DriverFactory acts as a facade to manage WebDriver lifecycle using DriverManager and WebDriverProvider.
 * Ensures a thread-safe singleton WebDriver instance per test thread.
 */
public class DriverFactory {

    // Private constructor to prevent instantiation
    private DriverFactory() {}

    /**
     * Retrieves the current thread's WebDriver instance, or creates a new one if none exists.
     *
     * @param browser      the browser name (e.g., "chrome", "firefox", "edge")
     * @param headLessMode "true" to run in headless mode, "false" otherwise
     * @return WebDriver instance for the current thread
     */
    public static WebDriver getDriver(String browser, String headLessMode) {
        if (DriverManager.getDriver() == null) {
            // Create and assign new WebDriver instance to the thread
            WebDriver driver = WebDriverProvider.createDriver(browser, headLessMode);
            DriverManager.setDriver(driver);
            
        } else {
           
        }
        return DriverManager.getDriver();
    }

    /**
     * Quits the WebDriver instance associated with the current thread and removes it.
     * Ensures graceful shutdown and avoids memory leaks.
     */
    public static void quitDriver() {
        DriverManager.quitDriver();
    }
}
