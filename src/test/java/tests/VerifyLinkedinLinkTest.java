package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FooterPage;

import java.time.Duration;
import java.util.Objects;

@Epic("UI/UX")
@Feature("Footer Social Links")
public class VerifyLinkedinLinkTest {
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

    @Test(description = "Verify Linkedin icon opens linkedin.com")
    @Story("Footer Linkedin Link")
    @Severity(SeverityLevel.NORMAL)
    @Description("Click footer Linkedin icon, check that correct Linkedin page opens")
    public void testLinkedinFooterLink() {
        String originalWindow = driver.getWindowHandle();
        int initialWindows = driver.getWindowHandles().size();

        Allure.step("Click Linkedin icon in footer", () -> footerPage.clickLinkedinLink());

        // Wait for the new tab to open
        wait.until(d -> d.getWindowHandles().size() > initialWindows);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Allure.step("Assert new tab contains linkedin.com", () -> {
            boolean foundLinkedin = wait.until(wd ->
                    Objects.requireNonNull(wd.getCurrentUrl()).toLowerCase().contains("linkedin.com"));
            Assert.assertTrue(foundLinkedin,
                    "Linkedin link did not redirect to a linkedin.com URL. Actual URL: " + driver.getCurrentUrl());
        });
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}