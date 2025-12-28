package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EyouthHomePage;
import pages.CoursesPage;

import java.time.Duration;

@Epic("Courses Functionality")
@Feature("Course Details")
public class OpenCourseDetailsTest {
    private WebDriver driver;
    private EyouthHomePage eyouthHomePage;
    private CoursesPage coursesPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://eyouthlearning.com/");
        eyouthHomePage = new EyouthHomePage(driver);
    }

    @Test(description = "Open course details from all-courses page")
    @Story("Open course details page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that choosing a course opens the course details page and displays the correct section.")
    public void testOpenCourseDetails() {
        Allure.step("Navigate to all courses", () -> eyouthHomePage.clickAllCoursesMenu());

        Allure.step("Open the first course card", () -> {
            coursesPage = new CoursesPage(driver);
            coursesPage.openCourseCard();
        });

        Allure.step("Assert course details page URL", () ->
                Assert.assertTrue(driver.getCurrentUrl().contains("/details"),
                        "URL should contain '/details' on course details page")
        );
        Allure.step("Check 'حول الدورة التدريبية' section is present", () ->
                Assert.assertTrue(coursesPage.isCourseDetailsSectionVisible(),
                        "Section 'حول الدورة التدريبية' should be visible on course details page")
        );
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}