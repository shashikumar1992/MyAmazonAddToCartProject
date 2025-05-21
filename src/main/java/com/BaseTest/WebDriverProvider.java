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
 */
public class WebDriverProvider {

	private WebDriverProvider() {
	}

	public static WebDriver createDriver(String browser, String headLessMode) {
		WebDriver driver;
		boolean isHeadless = headLessMode.equalsIgnoreCase("true");

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless=new"); // "new" is more stable
				chromeOptions.addArguments("--disable-gpu");
			}
			chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
					+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isHeadless) {
				firefoxOptions.addArguments("--headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless");
				edgeOptions.addArguments("--disable-gpu");
				edgeOptions.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();
		return driver;
	}
}
