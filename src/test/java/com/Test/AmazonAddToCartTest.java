package com.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.BaseTest.BaseTest;
import com.BaseTest.ExtentTestManager;
import com.BaseTest.TestListener;
import com.POM.HomePage;
import com.POM.LeftSideNavBarPage;
import com.POM.LogInpage;
import com.Utill.BrowserEventHandler;
import com.Utill.DataProviderClass;
import com.Utill.ReuseableMethods;

/**
 * Test class for verifying Amazon add-to-cart functionality.
 * Includes login, product search, add to cart, and cart verification.
 */
@Listeners(TestListener.class)
public class AmazonAddToCartTest extends BaseTest {

    private HomePage homePage;
    private LeftSideNavBarPage leftNavObject;
    private LogInpage loginPage;

    // Test credentials for login
    private static final String PHONE_NUMBER = "+919980823847";
    private static final String PASSWORD = "Shashi@123";

    private BrowserEventHandler browserEventHandler;

    /**
     * Initializes page objects before each test method.
     */
    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(driver);
        leftNavObject = new LeftSideNavBarPage(driver);
        loginPage = new LogInpage(driver);
        new ReuseableMethods();
        browserEventHandler = new BrowserEventHandler(driver);
    }

    /**
     * Test to verify that after login, the current URL contains "amazon"
     * and the page title is retrieved.
     */
    @Test
    public void testCurrentUrl() {
        performLogin();

        String url = driver.getCurrentUrl();
        String title = driver.getTitle();

        logStep("Current URL is: " + url);
        logStep("Page Title is: " + title);

        // Assert that the URL contains "amazon"
        assertTrue(url.contains("amazon"), "The current URL does not contain 'amazon'");
    }

    /**
     * Data-driven test for adding a product to the cart and verifying its presence.
     * 
     * @param category the main product category (e.g., Electronics)
     * @param item     the sub-category item (e.g., Smartphones)
     * @param device   the product/device name to be selected (e.g., Samsung Galaxy)
     */
    @Test(dataProvider = "Electronics", dataProviderClass = DataProviderClass.class)
    public void testProductExistsInCart(String category, String item, String device) {
        final String PRODUCT_NAME = "Samsung Galaxy S24 5G AI Smartphone (Marble Gray, 8GB, 256GB Storage)";

        performLogin();

        // Select category from dropdown and click search
        logStep("Selecting category: " + category);
        browserEventHandler.selectValueFromDropDown(homePage.getDropdownElement(), category);
        browserEventHandler.clickonElement(homePage.getSearchButton());

        // Navigate through item and device categories
        logStep("Navigating to item: " + item + " → Device: " + device);
        browserEventHandler.clickonElement(homePage.getCategoryElementByItemName(item));
        browserEventHandler.clickonElement(homePage.getCategoryElementByItemName(device));

        // Apply filters for Brand and Storage Capacity
        logStep("Applying filters - Brand: Samsung, Storage: 256 GB");
        browserEventHandler.clickonElement(leftNavObject.getLeftSideNavFilterElement("Brands", "Samsung"));
        browserEventHandler.clickonElement(leftNavObject.getLeftSideNavFilterElement("Storage Capacity", "256 GB"));

        // Click on the specified product
        logStep("Clicking on product: " + PRODUCT_NAME);
        browserEventHandler.clickonElement(leftNavObject.getProductDescriptionElement(PRODUCT_NAME));

        // Switch to the product window
        String parentWindow = browserEventHandler.getParentWindowObject();
        browserEventHandler.switchtoWindow(parentWindow);

        // Add product to cart and navigate to cart
        logStep("Adding product to cart");
        browserEventHandler.clickonElement(homePage.getAddToCartButton());

        logStep("Navigating to cart");
        browserEventHandler.clickonElement(homePage.getGoToCartButton());

        // Verify the product is present in the cart
        logStep("Verifying product exists in cart");
        String actualProductText = browserEventHandler.getTextFromTheWeb(homePage.getOrderedProduct(PRODUCT_NAME));
        assertEquals(actualProductText.trim(), PRODUCT_NAME, "Product in cart does not match expected.");
    }

    /**
     * Utility method to perform login using credentials.
     */
    private void performLogin() {
        logStep("Clicking Sign In button on Home Page");
        browserEventHandler.clickonElement(homePage.getSignInButton());

        logStep("Entering user credentials");
        browserEventHandler.enterInput(loginPage.getLoginEmailOrPhone(), PHONE_NUMBER);
        browserEventHandler.clickonElement(loginPage.getContinueButton());
        browserEventHandler.enterInput(loginPage.getLoginPassword(), PASSWORD);

        logStep("Clicking on Sign In");
        browserEventHandler.clickonElement(loginPage.getSignInButton());
    }

    /**
     * Helper method to log test steps to ExtentReport.
     * 
     * @param message the step description
     */
    private void logStep(String message) {
        ExtentTestManager.getTest().info("STEP: " + message);
    }
}
