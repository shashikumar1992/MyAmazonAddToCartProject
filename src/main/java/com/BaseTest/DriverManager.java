package com.BaseTest;

import org.openqa.selenium.WebDriver;

/**
 * Singleton-style utility class for managing WebDriver instances in a thread-safe manner.
 * Uses ThreadLocal to maintain separate WebDriver instances per thread.
 */
public class DriverManager {

    // Thread-safe storage for WebDriver instances
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverManager() {}

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return WebDriver instance assigned to the current thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Assigns the WebDriver instance to the current thread.
     *
     * @param webDriver the WebDriver instance to assign
     */
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
   
    }

    /**
     * Quits and removes the WebDriver instance associated with the current thread.
     * Ensures no memory leaks and proper shutdown after test execution.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();  // Prevent memory leaks
        }
    }
}
