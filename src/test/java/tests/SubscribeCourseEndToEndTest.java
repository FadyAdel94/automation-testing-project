package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EyouthHomePage;
import pages.LoginPage;
import pages.CoursesPage;
import pages.CartPage;

import java.time.Duration;

@Epic("Course Purchase")
@Feature("End-To-End Subscription")
public class SubscribeCourseEndToEndTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private EyouthHomePage homePage;
    private CoursesPage coursesPage;
    private CartPage cartPage;
    private WebDriverWait wait;

    // Replace these values or use a config in production!
    private final String validUsername = "FadyAdel94";
    private final String validPassword = "12345678";
    private final String baseUrl = "https://eyouthlearning.com/";
    private final String courseTitle = "Banking Risk Management Basics | أساسيات إدارة المخاطر المصرفية";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        homePage = new EyouthHomePage(driver);
        coursesPage = new CoursesPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(description="Subscribe to a course and validate it is in the cart")
    @Story("End-to-end subscription flow")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Logs in, subscribes to first course, and asserts course appears in cart.")
    public void testSubscribeCourseEndToEnd() {
        Allure.step("Login with valid credentials", () -> {
            loginPage.openLoginPopup();
            loginPage.enterCredentials(validUsername, validPassword);
            loginPage.clickLogin();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[text()='" + validUsername + "']")));
        });

        Allure.step("Navigate to all courses", () -> homePage.clickAllCoursesMenu());

        Allure.step("Subscribe to first course", () -> coursesPage.subscribeToFirstCourse());

        Allure.step("Assert course is in cart", () -> Assert.assertTrue(
                cartPage.isCoursePresentInCart(courseTitle),
                "The course should be present in the cart after subscription."
        ));
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}