package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CoursesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By courseDetailsSection = By.xpath("//h3[contains(text(),'نظرة عامة')]");
    private final By courseCard = By.xpath("//a[@href='/ar/courses/power-bi-for-data-analysis']/parent::div[contains(@class,'border-light-blue')]");
    private final By courseImage = By.cssSelector(".rounded-lg img[alt*='تحليل البيانات عبر Power BI']");
    private final By courseTitle = By.cssSelector("h3.text-primary-blue-900");
    private final By instructorName = By.cssSelector("h6.text-secondary");
    private final By subscribeButton = By.xpath("//button[normalize-space(text())='اشترك الآن']");

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCourseCard() {
        WebElement firstCard = wait.until(ExpectedConditions.visibilityOfElementLocated(courseCard));
        firstCard.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isCourseDetailsSectionVisible() {
        try {
            WebElement detailsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(courseDetailsSection));
            return detailsSection.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void subscribeToFirstCourse() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "document.dispatchEvent(new KeyboardEvent('keydown', {key: 'Escape', code: 'Escape', bubbles: true}));"
        );

        By subscribeBtnLocator = By.xpath("//button[normalize-space(text())='اشترك الآن']");
        WebElement subscribeBtn = wait.until(ExpectedConditions.elementToBeClickable(subscribeBtnLocator));
        try {
            subscribeBtn.click();
        } catch (StaleElementReferenceException e) {
            // Re-locate and retry click
            subscribeBtn = wait.until(ExpectedConditions.elementToBeClickable(subscribeBtnLocator));
            subscribeBtn.click();
        }
    }

    public WebElement getCourseCard() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(courseCard));
    }

    public boolean isCourseImageDisplayed() {
        WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(courseImage));
        String src = image.getAttribute("src");
        return image.isDisplayed() && src != null && !src.trim().isEmpty();
    }

    public String getCourseTitle() {
        WebElement titleEl = wait.until(ExpectedConditions.visibilityOfElementLocated(courseTitle));
        return titleEl.getText().trim();
    }

    public String getInstructorName() {
        WebElement instrEl = wait.until(ExpectedConditions.visibilityOfElementLocated(instructorName));
        return instrEl.getText().trim();
    }

    public boolean isSubscribeButtonDisplayed() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeButton));
        return btn.isDisplayed() && btn.getText().contains("اشترك");
    }

}