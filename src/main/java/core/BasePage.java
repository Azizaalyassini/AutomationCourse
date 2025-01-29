package core;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public static final Logger logger = Logger.getLogger(BasePage.class.getName());

    /**
     * Constructor for the BasePage class.
     * Initializes the driver and sets up the WebDriverWait.
     * @param driver The WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver instance cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Waits for an element to be visible on the page.
     * @param locator The locator of the element.
     * @param timeoutInSeconds The maximum time to wait for the element.
     */
    public void waitForElementToBeVisible(By locator, long timeoutInSeconds) {
        logger.info("Waiting for element to be visible: " + locator);
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Checks if an element is present on the page.
     * @param locator The locator of the element.
     * @return true if the element is present, false otherwise.
     */
    public boolean isElementPresent(By locator) {
        try {
            waitForElementToBeVisible(locator, 5);
            WebElement element = driver.findElement(locator);
            return element != null; // Element found
        } catch (NoSuchElementException e) {
            return false; // Element not found
        }
    }

    /**
     * Chooses an element from a list identified by a locator and clicks it by index.
     * @param locator The locator of the list elements.
     * @param index The index of the element to click.
     */
    public void chooseFromListByIndex(By locator, int index) {
        List<WebElement> elements = driver.findElements(locator);
        if (index >= 0 && index < elements.size()) {
            elements.get(index).click(); // Clicks on the element at the given index
        } else {
            throw new IndexOutOfBoundsException("Invalid index provided for the list.");
        }
    }

    /**
     * Retrieves the text of an element from a list by index.
     * @param locator The locator of the list elements.
     * @param index The index of the element.
     * @return The text of the element.
     */
    public String getTextFromListElementByIndex(By locator, int index) {
        List<WebElement> elements = driver.findElements(locator);
        if (index >= 0 && index < elements.size()) {
            return elements.get(index).getText(); // Returns the text of the element at the given index
        } else {
            throw new IndexOutOfBoundsException("Invalid index provided for the list.");
        }
    }

    /**
     * Retrieves the text from an element.
     * @param locator The locator of the element.
     * @return The text of the element, or null if an error occurs.
     */
    public String getTextFromElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getText();
        } catch (Exception e) {
            logger.severe("Failed to get text from element: " + e.getMessage());
            return null; // or handle as needed
        }
    }

    /**
     * Checks if an element is displayed on the page.
     * @param locator The locator of the element.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isDisplayed(By locator) {
        WebElement element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    /**
     * Checks if an element is clickable.
     * @param locator The locator of the element.
     * @return true if the element is clickable, false otherwise.
     */
    public boolean isElementClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true; // The element is clickable
        } catch (Exception e) {
            return false; // The element is not clickable
        }
    }

    /**
     * Clicks on an element located by the provided locator.
     * @param locator The locator of the element to be clicked.
     */
    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }
    public boolean isEmpty(By locator){
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * Enters text into an input field located by the provided locator.
     * @param locator The locator of the input field.
     * @param text The text to be entered.
     */
    public void enterText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }
}