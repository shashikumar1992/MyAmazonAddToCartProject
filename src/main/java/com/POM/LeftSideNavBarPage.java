package com.POM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model for the left side navigation bar on the page. Provides
 * methods to interact with filters and product descriptions.
 */
public class LeftSideNavBarPage {

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Constructor initializes the driver, wait, and page elements.
	 * 
	 * @param driver WebDriver instance to use
	 */
	public LeftSideNavBarPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// List of product description elements found by this XPath
	@FindBy(xpath = "//h2[@class='a-size-base-plus a-spacing-none a-color-base a-text-normal']")
	private List<WebElement> productDescriptionElements;

	/**
	 * Finds a filter element on the left side nav bar given a category and a
	 * description. Example: category = "Storage Capacity", description = "256"
	 * 
	 * @param category    The filter category (e.g. "Storage Capacity")
	 * @param description The filter description (e.g. "256")
	 * @return The WebElement matching the filter, visible and ready for interaction
	 */
	public WebElement getLeftSideNavFilterElement(String category, String description) {
		String xpath = String.format("//span[normalize-space()='%s']/ancestor::div[@class='a-section a-spacing-small']"
				+ "/following-sibling::ul//li[.//span[contains(text(),'%s')]]//a", category, description);

		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
	}

	/**
	 * Finds a product description element matching the given product description
	 * text.
	 * 
	 * @param productDescription The exact product description text to find
	 * @return The matching WebElement or null if none found
	 */
	public WebElement getProductDescriptionElement(String productDescription) {
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElements(productDescriptionElements));

		for (WebElement element : elements) {
			// System.out.println(element.getText());
			if (element.getText().equalsIgnoreCase(productDescription)) {
				return element;
			}
		}
		return null;
	}
}
