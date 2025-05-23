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
import com.POM.LogInpage;
import com.Utill.BrowserEventHandler;
import com.Utill.DataProviderClass;

/**
 * Test class to verify Amazon dropdown category selection and URL after login.
 */
@Listeners(TestListener.class)
public class AmazonDropDownTest extends BaseTest {

	private HomePage homePage;
	private LogInpage loginPage;

	private static final String PHONE_NUMBER = "+919980823847";
	private static final String PASSWORD = "Shashi@123";

	private BrowserEventHandler browserEventHandler;

	/**
	 * Initializes page objects before each test.
	 */
	@BeforeMethod
	public void initPageObjects() {
		homePage = new HomePage(driver);
		loginPage = new LogInpage(driver);

		browserEventHandler = new BrowserEventHandler(driver);
	}

	/**
	 * Utility method to perform login using stored credentials.
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
	 * Helper method for logging test steps to ExtentReport.
	 * 
	 * @param message Description of the test step.
	 */
	private void logStep(String message) {
		ExtentTestManager.getTest().info("STEP: " + message);
	}

	/**
	 * Verifies that after login, the URL contains 'amazon' and page title is
	 * retrieved.
	 */
	@Test
	public void testCurrentUrl() {
		performLogin();

		String url = driver.getCurrentUrl();
		String title = driver.getTitle();

		logStep("Current URL: " + url);
		logStep("Page Title: " + title);

		assertTrue(url.contains("amazon"), "The current URL does not contain 'amazon'");
	}

	/**
	 * Data-driven test to verify dropdown category selection works as expected.
	 * 
	 * @param category The category to select from dropdown.
	 * @param item     Additional item parameter (unused in test but provided by
	 *                 data provider).
	 */
	@Test(dataProvider = "Cat", dataProviderClass = DataProviderClass.class)
	public void testSearchDropdown(String category, String item) {
		performLogin();

		logStep("Selecting category from dropdown: " + category);
		browserEventHandler.selectValueFromDropDown(homePage.getDropdownElement(), category);

		logStep("Clicking Search button");
		browserEventHandler.clickonElement(homePage.getSearchButton());

		logStep("Verifying selected category is displayed on results page");
		String selectedValue = browserEventHandler.getTextFromTheWeb(homePage.getCategoryElement(category));
		assertEquals(selectedValue, category, "Dropdown category does not match selected value");
	}
}
