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
public class LoginWithEmptyFieldsTest {
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

    @Test(description = "Verify required field validation for empty login fields")
    @Story("Login with empty fields")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that the username and password required errors appear when trying to login with empty fields.")
    public void testLoginWithEmptyFields() {
        Allure.step("Open login popup", () -> loginPage.openLoginPopup());
        Allure.step("Leave both fields empty and try to login", () -> {
            loginPage.enterCredentials("", "");
            loginPage.clickLogin();
        });

        Allure.step("Assert username required error is displayed", () ->
                Assert.assertTrue(loginPage.isUsernameRequiredErrorDisplayed(),
                        "Username required validation should be displayed (البريد الإلكتروني مطلوب)")
        );
        Allure.step("Assert password required error is displayed", () ->
                Assert.assertTrue(loginPage.isPasswordRequiredErrorDisplayed(),
                        "Password required validation should be displayed (كلمة المرور مطلوبة or اسم المستخدم مطلوب)")
        );
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}