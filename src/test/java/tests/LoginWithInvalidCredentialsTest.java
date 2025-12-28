package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;

@Epic("Authentication")
@Feature("Login")
public class LoginWithInvalidCredentialsTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://eyouthlearning.com/");
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify error message with invalid login credentials")
    @Story("Login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that the error message for invalid credentials appears when wrong credentials are used.")
    public void testInvalidLoginShowsError() {
        Allure.step("Open login popup", () -> loginPage.openLoginPopup());
        Allure.step("Enter invalid credentials and attempt to login", () -> {
            loginPage.enterCredentials("nofounduser", "wrongpassword");
            loginPage.clickLogin();
        });
        Allure.step("Assert error message is displayed", () ->
                Assert.assertTrue(loginPage.isErrorDisplayed(),
                        "Should display error message for invalid login credentials")
        );
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}