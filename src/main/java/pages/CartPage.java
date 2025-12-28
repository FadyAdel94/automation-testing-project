package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to assert the course is present in the cart by its title
    public boolean isCoursePresentInCart(String courseTitle) {
        try {
            WebElement course = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cardContent')]//h5[contains(text(),'" + courseTitle + "')]")
            ));
            return course.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}