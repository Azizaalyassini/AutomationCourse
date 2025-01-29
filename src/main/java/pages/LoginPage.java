package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators for username, password, and login button
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessageLocator = By.cssSelector(".error-message-container");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Method to enter username
    public void enterUsername(String username) {
       enterText(usernameField,username);
    }

    // Method to enter password
    public void enterPassword(String password) {
       enterText(passwordField,password);
    }

    // Method to click the login button
    public void clickLogin() {
      clickElement(loginButton);
    }

    // Method to perform a complete login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Method to verify if login failed (check error message)
    public boolean isErrorMessageDisplayed() {
        return isEmpty(errorMessageLocator);
    }
}
