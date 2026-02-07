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
    public boolean isCoursePresentInCart(String courseUrl) {
        try {
            WebElement courseLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@href='" + courseUrl + "']"))
            );
            return courseLink.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}