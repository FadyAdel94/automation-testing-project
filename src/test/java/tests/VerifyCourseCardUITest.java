package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CoursesPage;

import java.time.Duration;

@Epic("Courses Functionality")
@Feature("Course Card UI")
public class VerifyCourseCardUITest {
    private WebDriver driver;
    private CoursesPage coursesPage;
    private final String coursesUrl = "https://eyouthlearning.com/courses"; // Update if needed

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(coursesUrl);
        coursesPage = new CoursesPage(driver);
    }

    @Test(description="Verify course card displays image/title/instructor/subscribe button")
    @Story("Course Card UI Elements")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks one card on all-courses page for all required UI elements")
    public void testCourseCardUI() {
        Allure.step("Make sure the course card is loaded", () ->
                Assert.assertNotNull(coursesPage.getCourseCard(), "Course card is not displayed!"));

        Allure.step("Assert course image is present & valid", () ->
                Assert.assertTrue(coursesPage.isCourseImageDisplayed(), "Course image is not present!"));

        Allure.step("Assert course title is not empty", () -> {
            String title = coursesPage.getCourseTitle();
            Assert.assertFalse(title.isEmpty(), "Course title is missing!");
        });

        Allure.step("Assert instructor name is not empty", () -> {
            String instructor = coursesPage.getInstructorName();
            Assert.assertFalse(instructor.isEmpty(), "Instructor name is missing!");
        });

        Allure.step("Assert subscribe button exists and contains 'اشترك'", () ->
                Assert.assertTrue(coursesPage.isSubscribeButtonDisplayed(), "Subscribe button not found or incorrect!"));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}