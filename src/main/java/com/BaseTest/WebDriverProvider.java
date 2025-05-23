package com.BaseTest;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory class responsible for creating configured WebDriver instances.
 * Supports Chrome, Firefox, and Edge browsers with optional headless mode.
 */
public class WebDriverProvider {

    // Private constructor to prevent instantiation of utility class
    private WebDriverProvider() {
    }

    /**
     * Creates a WebDriver instance for the specified browser and mode.
     *
     * @param browser      The browser type (e.g., "chrome", "firefox", "edge").
     * @param headLessMode "true" to enable headless mode, "false" otherwise.
     * @return Configured WebDriver instance.
     */
    public static WebDriver createDriver(String browser, String headLessMode) {
        WebDriver driver;
        boolean isHeadless = headLessMode.equalsIgnoreCase("true");

        switch (browser.toLowerCase()) {

            case "chrome":
                // Setup ChromeDriver
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                // Configure headless mode if enabled
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new"); // "new" mode is more stable
                    chromeOptions.addArguments("--disable-gpu"); // Needed for headless in some environments
                }

                // Disable automation detection banners and extensions
                chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);

                // Set custom user-agent for browser fingerprinting control
                chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                        + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                // Setup FirefoxDriver
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                // Configure headless mode if enabled
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                // Setup EdgeDriver
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                // Configure headless mode if enabled
                if (isHeadless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size=1920,1080"); // Ensure proper viewport
                }

                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                // Throw error for unsupported browser input
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Maximize browser window
        driver.manage().window().maximize();
        return driver;
    }
}
