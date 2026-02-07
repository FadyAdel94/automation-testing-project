package tests;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FooterPage;

import java.time.Duration;
import java.util.Objects;

@Epic("UI/UX")
@Feature("Footer Social Links")
public class VerifyFacebookLinkTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private FooterPage footerPage;

    private final String baseUrl = "https://eyouthlearning.com/";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
        footerPage = new FooterPage(driver);
    }

    @Test(description = "Verify Facebook icon opens facebook.com")
    @Story("Footer Facebook Link")
    @Severity(SeverityLevel.NORMAL)
    @Description("Click footer Facebook icon, check that correct Facebook page opens")
    public void testFacebookFooterLink() {
        String originalWindow = driver.getWindowHandle();
        int initialWindows = driver.getWindowHandles().size();

        Allure.step("Click Facebook icon in footer", () -> footerPage.clickFacebookLink());

        // Wait for the new tab to open
        wait.until(d -> d.getWindowHandles().size() > initialWindows);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Allure.step("Assert new tab contains facebook.com", () -> {
            boolean foundFacebook = wait.until(wd ->
                    Objects.requireNonNull(wd.getCurrentUrl()).toLowerCase().contains("facebook.com"));
            Assert.assertTrue(foundFacebook,
                    "Facebook link did not redirect to a facebook.com URL. Actual URL: " + driver.getCurrentUrl());
        });
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}