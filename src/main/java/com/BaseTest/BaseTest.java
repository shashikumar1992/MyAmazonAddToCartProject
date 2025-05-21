package com.BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Utill.ReadProperties;

/**
 * BaseTest class to manage WebDriver lifecycle and common test utilities.
 * Handles browser setup and teardown before and after each test method.
 */
public class BaseTest {

    // WebDriver instance accessible to test classes extending BaseTest
    protected WebDriver driver;

    /**
     * Initializes the WebDriver instance before each test method.
     * Opens the browser specified by the 'browser' parameter (defaults to Chrome)
     * and navigates to the application URL from properties.
     * 
     * @param browser       Browser type ("chrome", "firefox", etc.), optional with default "chrome".
     * @param headlessmode  Flag indicating if browser should run in headless mode.
     */
    @Parameters({ "browser", "headlessmode" })
    @BeforeMethod(alwaysRun = true)
    public void setUpDriver(@Optional("chrome") String browser, @Optional("true") String headlessmode) {
        driver = DriverFactory.getDriver(browser, headlessmode);
        driver.get(ReadProperties.getProperties("URL"));
    }

    /**
     * Quits the WebDriver instance after each test method to close the browser.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
        }
    }
}
