package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EyouthHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchField = By.cssSelector("input[placeholder='بحث'], input[type='search'], .search-icon input");
    private By searchButton = By.cssSelector(".search-button, button[type='submit'], .search-icon button");
    private By allCoursesMenu = By.cssSelector("a[href='/ar/courses']");
    private By joinUsButton = By.xpath("//a[@href='/ar/auth/register' and contains(text(),'حساب جديد')]");


    public EyouthHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchKeyword(String keyword) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        input.clear();
        input.sendKeys(keyword);

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a[href='/ar/courses/how-to-join-a-bank']"))
        );
        result.click();
    }

    public void clickAllCoursesMenu() throws InterruptedException {
        WebElement allCourses = wait.until(ExpectedConditions.elementToBeClickable(allCoursesMenu));
        allCourses.click();
        ((JavascriptExecutor) driver).executeScript(
                "document.dispatchEvent(new KeyboardEvent('keydown', {key: 'Escape', code: 'Escape', bubbles: true}));"
        );
        Thread.sleep(500);
    }

    public void openJoinAsStudent() {
        WebElement joinBtn = wait.until(ExpectedConditions.elementToBeClickable(joinUsButton));
        joinBtn.click();
    }
}