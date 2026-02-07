package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators based on your HTML
    private By loginNavButton = By.cssSelector("a[href*='/auth/login']");
    private By usernameInput = By.id("email");
    private By passwordInput = By.id("password");
    private By submitButton = By.cssSelector("[type='submit']");
    private By errorMessage = By.cssSelector(".text-\\[red\\]");
    private By usernameRequiredError = By.cssSelector(".text-\\[red\\]");
    private By passwordRequiredError = By.cssSelector(".text-\\[red\\]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLoginPopup() {
        // Click the navbar login button to reveal login form/popup
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginNavButton));
        loginBtn.click();
    }

    public void enterCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    public void clickLogin() {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitBtn.click();
    }

    public boolean isErrorDisplayed() {
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return err.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isUsernameRequiredErrorDisplayed() {
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredError));
            return err.getText().trim().contains("البريد الإلكتروني مطلوب");
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isPasswordRequiredErrorDisplayed() {
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRequiredError));
            return err.getText().trim().contains("البريد الإلكتروني مطلوب") || err.getText().trim().contains("كلمة المرور مطلوبة");
        } catch (TimeoutException e) {
            return false;
        }
    }
}