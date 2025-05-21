package com.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Model class representing the Login Page. Encapsulates web
 * elements and actions related to user login.
 */
public class LogInpage {

	/**
	 * Constructor to initialize driver.
	 * @param driver WebDriver instance to interact with the browser.
	 */
	public LogInpage(WebDriver driver) {
	
		// Initialize WebElements using PageFactory
		PageFactory.initElements(driver, this);
	}

	/**
	 * Email or phone input field on the login page.
	 */
	@FindBy(id = "ap_email_login")
	private WebElement loginEmail;

	/**
	 * Getter method for email/phone input element.
	 * 
	 * @return WebElement for login email or phone input.
	 */
	public WebElement getLoginEmailOrPhone() {
		return loginEmail;
	}

	/**
	 * Password input field on the login page.
	 */
	@FindBy(id = "ap_password")
	private WebElement loginPassword;

	/**
	 * Getter method for password input element.
	 * 
	 * @return WebElement for login password input.
	 */
	public WebElement getLoginPassword() {
		return loginPassword;
	}

	/**
	 * Sign in button element to submit the login form.
	 */
	@FindBy(id = "signInSubmit")
	private WebElement signInButton;

	/**
	 * Getter method for sign-in button.
	 * 
	 * @return WebElement representing the sign in button.
	 */
	public WebElement getSignInButton() {
		return signInButton;
	}

	/**
	 * Continue button element used in some login flows.
	 */
	@FindBy(xpath = "//input[@class='a-button-input']")
	private WebElement continueButton;

	/**
	 * Getter method for continue button.
	 * 
	 * @return WebElement representing the continue button.
	 */
	public WebElement getContinueButton() {
		return continueButton;
	}
}
