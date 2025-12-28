package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CoursesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By courseDetailsSection =  By.xpath("//section[contains(@class,'about')]//h2[contains(text(),'حول الدورة التدريبية')]");
    private final By courseCard = By.cssSelector(".course-card");
    private final By courseImage = By.cssSelector(".course-card .MuiCardMedia-root");
    private final By courseTitle = By.cssSelector(".course-card strong > p");
    private final By instructorName = By.cssSelector(".course-card span.text-capitalize strong");
    private final By subscribeButton = By.cssSelector(".course-card button");

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCourseCard() {
        WebElement firstCard = wait.until(ExpectedConditions.visibilityOfElementLocated(courseCard));
        firstCard.click();
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
        WebElement subscribeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(text(),'اشترك')])[1]")));
        subscribeBtn.click();
    }

    public WebElement getCourseCard() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(courseCard));
    }

    public boolean isCourseImageDisplayed() {
        WebElement imageDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(courseImage));
        String bgImage = imageDiv.getCssValue("background-image");
        // Assert it contains a URL (not 'none')
        return bgImage != null && bgImage.startsWith("url");
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