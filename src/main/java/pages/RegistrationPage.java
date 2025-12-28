package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Fill all fields except username
    public void fillRegistrationFormExceptUsername(String name, String country, String gender, String government,
                                                   String email, String phone, String password, String confirmPassword) {
        // Name
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);

        // Country dropdown
        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("country-select")));
        countryDropdown.click();
        WebElement countryOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='option' and @data-value='" + country + "']")));
        countryOption.click();

        // Gender dropdown
        WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='gender-select' or contains(@aria-labelledby,'gender')]")));
        genderDropdown.click();
        WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='option' and @data-value='" + gender + "']")));
        genderOption.click();

        // Government dropdown
        WebElement govDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("government-select")));
        govDropdown.click();
        WebElement govOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='option' and @data-value='" + government + "']")));
        govOption.click();

        // Email
        driver.findElement(By.id("email")).sendKeys(email);

        // Phone
        driver.findElement(By.id("phone_number")).sendKeys(phone);

        // Password
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmPassword")).sendKeys(confirmPassword);

        // Accept ToS
        WebElement tos = driver.findElement(By.id("accept"));
        if (!tos.isSelected()) tos.click();
    }

    public void clickSubmit() {
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".signup--btn[type='submit']")));
        submit.click();
    }

    public boolean isUsernameRequiredErrorPresent() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("username-helper-text")));
            return error.getText().trim().contains("اسم المستخدم مطلوب");
        } catch (TimeoutException e) {
            return false;
        }
    }
}