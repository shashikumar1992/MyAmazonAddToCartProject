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
 * Page Object Model class for the Home Page.
 * Encapsulates web elements and actions on the Home Page.
 */
public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Dropdown element for search category
    @FindBy(id = "searchDropdownBox")
    private WebElement dropdown;

    public WebElement getDropdownElement() {
        return dropdown;
    }

    // Search button element
    @FindBy(id = "nav-search-submit-button")
    private WebElement searchElement;

    public WebElement getSearchButton() {
        return searchElement;
    }

    /**
     * Returns the WebElement for a category heading by visible text.
     * Waits for visibility of the element containing the category text.
     * 
     * @param categoryValue Text to locate category heading
     * @return WebElement for the category heading
     */
    public WebElement getCategoryElement(String categoryValue) {
        return wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//h2[contains(text(),'" + categoryValue + "')]"))));
    }

    // List of category links
    @FindBy(xpath = "//a[@class='a-color-base a-link-normal']")
    private List<WebElement> categoryListElements;

    public List<WebElement> getCategoryListElements() {
        return categoryListElements;
    }

    /**
     * Returns the WebElement matching the exact device name from the category list.
     * 
     * @param device Name of the device to find
     * @return WebElement if found, otherwise null
     */
    public WebElement getCategoryElementByItemName(String device) {
        List<WebElement> visibleElements = wait.until(ExpectedConditions.visibilityOfAllElements(categoryListElements));
        for (WebElement element : visibleElements) {
            if (element.getText().equalsIgnoreCase(device)) {
                return element;
            }
        }
        return null;
    }

    // Product title element on product detail page
    @FindBy(id = "productTitle")
    private WebElement productTitle;

    public WebElement getProductTitleElement() {
        return productTitle;
    }

    // Add to cart button (second instance)
    @FindBy(xpath = "(//input[@id='add-to-cart-button'])[2]")
    private WebElement addToCartButton;

    public WebElement getAddToCartButton() {
        return wait.until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    // Go to cart button in cart info popup
    @FindBy(xpath = "//div[@id='attach-cart-info-content']//input")
    private WebElement goToCartButton;

    public WebElement getGoToCartButton() {
        return wait.until(ExpectedConditions.visibilityOf(goToCartButton));
    }

    // Proceed to buy button on cart page
    @FindBy(xpath = "//span[@id='sc-buy-box-ptc-button']")
    private WebElement proceedToBuyButton;

    public WebElement getProceedToBuyButton() {
        return wait.until(ExpectedConditions.visibilityOf(proceedToBuyButton));
    }

    // Sign in button (navigation)
    @FindBy(xpath = "//div[@id='nav-link-accountList']")
    private WebElement signInButton;

    public WebElement getSignInButton() {
        return signInButton;
    }

    // List of ordered product names
    @FindBy(xpath = "//span[@class='a-truncate-cut']")
    private List<WebElement> orderedProductElements;

    /**
     * Finds and returns the ordered product WebElement matching the product name.
     * 
     * @param product Name of the ordered product
     * @return WebElement if found, otherwise null
     */
    public WebElement getOrderedProduct(String product) {
        for (WebElement element : orderedProductElements) {
            System.out.println("Ordered product found: " + element.getText());
            if (element.getText().equals(product)) {
                return element;
            }
        }
        return null;
    }
}
