package pages;

import org.openqa.selenium.By;
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
    private By allCoursesMenu = By.cssSelector("a[href='/all-courses']");
    private By joinUsButton = By.cssSelector(".navbar_btn_signup__z4Cok");
    private By joinStudentButton = By.xpath("//a[@href='/signup' and .//div[contains(text(),'انضم لنا كطالب')]]");


    public EyouthHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterSearchKeyword(String keyword) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        input.clear();
        input.sendKeys(keyword);

        wait.until(drv -> input.getAttribute("aria-expanded").equals("true"));
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("highlights-demo-option-0")));
        firstSuggestion.click();
    }

    public void clickSearch() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
    }

    public void clickAllCoursesMenu() {
        WebElement allCourses = wait.until(ExpectedConditions.elementToBeClickable(allCoursesMenu));
        allCourses.click();
    }

    public void openJoinAsStudent() {
        WebElement joinBtn = wait.until(ExpectedConditions.elementToBeClickable(joinUsButton));
        joinBtn.click();

        WebElement joinStudent = wait.until(ExpectedConditions.elementToBeClickable(joinStudentButton));
        joinStudent.click();
    }
}