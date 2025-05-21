package com.Utill;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.BaseTest.BaseTest;

/**
 * Utility class providing reusable browser actions for interacting with web elements.
 */
public class BrowserEventHandler extends BaseTest {

    /**
     * Selects a value from a dropdown WebElement using visible text.
     *
     * @param element The dropdown WebElement.
     * @param value The value to select from the dropdown.
     * 
     */
	WebDriver driver;
	public BrowserEventHandler(WebDriver driver) {
		this.driver=driver;
	}
	
	
    public  void selectValueFromDropDown(WebElement element, String value) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        for (WebElement opEle : options) {
            if (opEle.getText().equals(value)) {
                opEle.click();
                break;
            }
        }
    }

    /**
     * Clicks on a given WebElement.
     *
     * @param element The WebElement to click.
     */
    public  void clickonElement(WebElement element) {
        element.click();
    }

    /**
     * Returns a WebElement from a list based on exact text match.
     *
     * @param list The list of WebElements to search.
     * @param value The text value to match.
     * @return The matching WebElement, or null if not found.
     */
    public  WebElement getListofTextFromWeb(List<WebElement> list, String value) {
        WebElement ele = null;
        for (WebElement webElement : list) {
            if (webElement.getText().equals(value)) {
                ele = webElement;
            }
        }
        return ele;
    }

    /**
     * Returns the current (parent) browser window handle.
     *
     * @return The parent window handle as a String.
     */
    public  String getParentWindowObject() {
        return driver.getWindowHandle();
    }

    /**
     * Switches the driver context to the child window (new tab or popup).
     *
     * @param parentWindowObject The parent window handle to exclude.
     */
    public  void switchtoWindow(String parentWindowObject) {
        Set<String> windowObjects = driver.getWindowHandles();
        for (String object : windowObjects) {
            if (!object.equals(parentWindowObject)) {
                driver.switchTo().window(object);
                break;
            }
        }
    }

    /**
     * Retrieves and returns the visible text of a WebElement.
     *
     * @param ele The WebElement from which to extract text.
     * @return The text content of the element.
     */
    public  String getTextFromTheWeb(WebElement ele) {
        return ele.getText();
    }

    /**
     * Sends input text to a given WebElement (e.g., a text field).
     *
     * @param loginEmail The WebElement to send input to.
     * @param phone The text/input value.
     */
    public  void enterInput(WebElement input, String phone) {
    	input.sendKeys(phone);
    }
}
