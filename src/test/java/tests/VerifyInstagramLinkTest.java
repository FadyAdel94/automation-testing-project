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
public class VerifyInstagramLinkTest {
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

    @Test(description = "Verify Instagram icon opens instagram.com")
    @Story("Footer Instagram Link")
    @Severity(SeverityLevel.NORMAL)
    @Description("Click footer Instagram icon, check that correct Instagram page opens")
    public void testInstagramFooterLink() {
        String originalWindow = driver.getWindowHandle();
        int initialWindows = driver.getWindowHandles().size();

        Allure.step("Click Instagram icon in footer", () -> footerPage.clickInstagramLink());

        // Wait for the new tab to open
        wait.until(d -> d.getWindowHandles().size() > initialWindows);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Allure.step("Assert new tab contains instagram.com", () -> {
            boolean foundInstagram = wait.until(wd ->
                    Objects.requireNonNull(wd.getCurrentUrl()).toLowerCase().contains("instagram.com"));
            Assert.assertTrue(foundInstagram,
                    "Instagram link did not redirect to a instagram.com URL. Actual URL: " + driver.getCurrentUrl());
        });
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}