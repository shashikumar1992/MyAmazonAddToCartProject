package com.Utill;

import com.BaseTest.BaseTest;
import com.BaseTest.ExtentTestManager;
import com.POM.HomePage;
import com.POM.LeftSideNavBarPage;
import com.POM.LogInpage;

/**
 * Utility class containing reusable business methods such as login and product
 * selection for test scripts.
 */
public class ReuseableMethods extends BaseTest {

	private BrowserEventHandler browserEventHandler = new BrowserEventHandler(driver);
	HomePage homePage = new HomePage(driver);
	LogInpage loginPage = new LogInpage(driver);
	LeftSideNavBarPage leftnav = new LeftSideNavBarPage(driver);

	// Page object instances
	/**
	 * Performs the login process using given username and password.
	 *
	 * @param username the email or phone number of the user
	 * @param password the password of the user
	 */
	public void performLogin(String username, String password) {

		HomePage homePage = new HomePage(driver);
		LogInpage loginPage = new LogInpage(driver);
		ExtentTestManager.getTest().info("STEP: Clicking Sign In button on Home Page");
		browserEventHandler.clickonElement(homePage.getSignInButton());

		ExtentTestManager.getTest().info("STEP: Entering user credentials");
		browserEventHandler.enterInput(loginPage.getLoginEmailOrPhone(), username);
		browserEventHandler.clickonElement(loginPage.getContinueButton());
		browserEventHandler.enterInput(loginPage.getLoginPassword(), password);

		ExtentTestManager.getTest().info("STEP: Clicking on Sign In");
		browserEventHandler.clickonElement(loginPage.getSignInButton());
	}

	/**
	 * Selects a product category and filters, then navigates to the desired
	 * product.
	 *
	 * @param category the main dropdown category (e.g., "Electronics")
	 * @param item     the category/sub-item to select (e.g., "Mobiles")
	 * @param device   the specific device name to click on (e.g., "Samsung")
	 */
	public void selectProductFromCategory(String category, String item, String device) {
		logStep("Selecting category: " + category);
		browserEventHandler.selectValueFromDropDown(homePage.getDropdownElement(), category);
		browserEventHandler.clickonElement(homePage.getSearchButton());

		logStep("Navigating to item: " + item + " → Device: " + device);
		browserEventHandler.clickonElement(homePage.getCategoryElementByItemName(item));
		browserEventHandler.clickonElement(homePage.getCategoryElementByItemName(device));

		logStep("Applying filters - Brand: Samsung, Storage: 256 GB");
		browserEventHandler.clickonElement(leftnav.getLeftSideNavFilterElement("Brands", "Samsung"));
		browserEventHandler.clickonElement(leftnav.getLeftSideNavFilterElement("Storage Capacity", "256 GB"));
	}

	/**
	 * Logs a step into ExtentReport for better traceability.
	 *
	 * @param message the step message to log
	 */
	private static void logStep(String message) {
		ExtentTestManager.getTest().info("STEP: " + message);
	}
}
