package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class FooterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By facebookLink = By.cssSelector("a[href*='facebook.com']");
    private final By linkedinLink = By.cssSelector("a[href*='linkedin.com']");
    private final By instagramLink = By.cssSelector("a[href*='instagram.com']");

    public FooterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void clickFacebookLink() {
        WebElement fbLink = wait.until(ExpectedConditions.elementToBeClickable(facebookLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fbLink);

        try {
            fbLink.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbLink);
        }
    }

    public void clickLinkedinLink() {
        WebElement fbLink = wait.until(ExpectedConditions.elementToBeClickable(linkedinLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fbLink);

        try {
            fbLink.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbLink);
        }
    }

    public void clickInstagramLink() {
        WebElement fbLink = wait.until(ExpectedConditions.elementToBeClickable(instagramLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fbLink);

        try {
            fbLink.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fbLink);
        }
    }
}