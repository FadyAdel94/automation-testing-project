package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EyouthHomePage;

import java.time.Duration;

@Epic("Registration")
@Feature("Student Signup")
public class OpenStudentSignupTest {
    private WebDriver driver;
    private EyouthHomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://eyouthlearning.com/");
        homePage = new EyouthHomePage(driver);
    }

    @Test(description = "Open student signup page from main page")
    @Story("Join as student")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open signup page via 'Join as Student' button and verify page navigation.")
    public void testJoinAsStudentSignup() {
        Allure.step("Click 'Join as Student' button", () -> homePage.openJoinAsStudent());

        Allure.step("Wait and verify navigation to /signup", () -> {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/signup"));
            Assert.assertTrue(
                    driver.getCurrentUrl().contains("/signup"),
                    "User should navigate to /signup after clicking 'انضم لنا كطالب'"
            );
        });
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}