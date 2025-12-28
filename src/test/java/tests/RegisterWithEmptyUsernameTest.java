package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegistrationPage;

import java.time.Duration;

@Epic("Registration")
@Feature("Signup Validations")
public class RegisterWithEmptyUsernameTest {
    private WebDriver driver;
    private RegistrationPage regPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://eyouthlearning.com/signup");
        regPage = new RegistrationPage(driver);
    }

    @Test(description="Validation for empty username on registration")
    @Story("Registration with empty username")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensure validation error is shown if username is left empty during registration.")
    public void testEmptyUsernameShowsValidation() {
        Allure.step("Fill registration form except username", () -> regPage.fillRegistrationFormExceptUsername(
                "Test Name",
                "eg",
                "m",
                "Alexandria",
                "test@example.com",
                "01232223334",
                "SecretPass1@",
                "SecretPass1@"
        ));
        Allure.step("Submit the registration form", () -> regPage.clickSubmit());
        Allure.step("Assert username required error is present", () ->
                Assert.assertTrue(regPage.isUsernameRequiredErrorPresent(),
                        "Validation error 'اسم المستخدم مطلوب' should be displayed."));
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}