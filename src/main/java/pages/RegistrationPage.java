package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Fill all fields except username
    public void fillRegistrationFormExceptUsername(String name, String country, String gender, String government,
                                                   String email, String phone, String password, String confirmPassword) throws InterruptedException {
        // Name
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);

        // Country dropdown
        WebElement countryDropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@role='combobox' and .//span[contains(text(),'الدولة')]]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryDropdownButton);
        try {
            countryDropdownButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", countryDropdownButton);
        }
        Thread.sleep(500);

        List<WebElement> selects = driver.findElements(By.xpath("//select[@aria-hidden='true']"));
        WebElement countrySelect = selects.get(0); // COUNTRY is first select
        List<WebElement> countryOptions = countrySelect.findElements(By.tagName("option"));
        WebElement pickedCountryOption = null;
        if (country != null) {
            for (WebElement option : countryOptions) {
                if (country.equals(option.getAttribute("value"))) {
                    pickedCountryOption = option;
                    break;
                }
            }
        }
        if (pickedCountryOption == null) {
            for (WebElement option : countryOptions) {
                if (!option.getAttribute("value").isEmpty()) {
                    pickedCountryOption = option;
                    break;
                }
            }
        }
        if (pickedCountryOption != null) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                    countrySelect, pickedCountryOption.getAttribute("value"));
            ((JavascriptExecutor) driver).executeScript(
                    "document.dispatchEvent(new KeyboardEvent('keydown', {key: 'Escape', code: 'Escape', bubbles: true}));"
            );
        } else {
            throw new RuntimeException("No valid country option found.");
        }
        Thread.sleep(500);

        // Gov dropdown
        WebElement govDropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@role='combobox' and .//span[contains(text(),'المحافظة')]]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", govDropdownButton);
        try {
            govDropdownButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", govDropdownButton);
        }
        Thread.sleep(500);

        List<WebElement> selectsAfterGov = driver.findElements(By.xpath("//select[@aria-hidden='true']"));
        WebElement govSelect = selectsAfterGov.get(1); // GOVERNMENT is second select
        List<WebElement> govOptions = govSelect.findElements(By.tagName("option"));
        WebElement pickedGovOption = null;
        if (government != null) {
            for (WebElement option : govOptions) {
                if (government.equals(option.getAttribute("value"))) {
                    pickedGovOption = option;
                    break;
                }
            }
        }
        if (pickedGovOption == null) {
            for (WebElement option : govOptions) {
                if (!option.getAttribute("value").isEmpty()) {
                    pickedGovOption = option;
                    break;
                }
            }
        }
        if (pickedGovOption != null) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                    govSelect, pickedGovOption.getAttribute("value"));
            ((JavascriptExecutor) driver).executeScript(
                    "document.dispatchEvent(new KeyboardEvent('keydown', {key: 'Escape', code: 'Escape', bubbles: true}));"
            );
        } else {
            throw new RuntimeException("No valid government option found.");
        }
        Thread.sleep(500);

        // Gender dropdown
        WebElement genderDropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@role='combobox' and .//span[contains(text(),'النوع')]]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", genderDropdownButton);
        try {
            govDropdownButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderDropdownButton);
        }
        Thread.sleep(500);

        List<WebElement> selectsAfterGender = driver.findElements(By.xpath("//select[@aria-hidden='true']"));
        WebElement genderSelect = selectsAfterGender.get(2); // GOVERNMENT is second select
        List<WebElement> genderOptions = genderSelect.findElements(By.tagName("option"));
        WebElement pickedGenderOption = null;
        if (gender != null) {
            for (WebElement option : genderOptions) {
                if (gender.equals(option.getAttribute("value"))) {
                    pickedGenderOption = option;
                    break;
                }
            }
        }
        if (pickedGenderOption == null) {
            for (WebElement option : genderOptions) {
                if (!option.getAttribute("value").isEmpty()) {
                    pickedGenderOption = option;
                    break;
                }
            }
        }
        if (pickedGenderOption != null) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                    genderSelect, pickedGenderOption.getAttribute("value"));
            ((JavascriptExecutor) driver).executeScript(
                    "document.dispatchEvent(new KeyboardEvent('keydown', {key: 'Escape', code: 'Escape', bubbles: true}));"
            );
        } else {
            throw new RuntimeException("No valid gender option found.");
        }
        Thread.sleep(500);


        // Phone
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys(phone);


        // Password
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirm_password")).sendKeys(confirmPassword);

        // Accept ToS
        WebElement tos = driver.findElement(By.id("terms"));
        if (!tos.isSelected()) tos.click();
    }

    public void clickSubmit() {
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[type='submit']")
        ));
        submit.click();
    }

    public boolean isUsernameRequiredErrorPresent() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'البريد الإلكتروني مطلوب')]")
            ));
            return error.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}