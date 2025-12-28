package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.EyouthHomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("Courses Functionality")
@Feature("Search")
public class SearchTest {
    private WebDriver driver;
    private EyouthHomePage homePage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://eyouthlearning.com/");
        homePage = new EyouthHomePage(driver);
    }

    @Test(description = "Search for a course by keyword")
    @Story("Search with keyword")
    @Severity(SeverityLevel.NORMAL)
    @Description("Run search and ensure the resulting page title contains the keyword.")
    public void testSearchWithValidKeyword() {
        String keyword = "كيف تنضم إلى البنك";
        Allure.step("Enter the search keyword", () -> homePage.enterSearchKeyword(keyword));
        Allure.step("Click search", () -> homePage.clickSearch());

        Allure.step("Wait for page title to contain the keyword", () -> {
            boolean titleContainsKeyword = false;
            for (int i = 0; i < 10; i++) {
                if (driver.getTitle().contains(keyword)) {
                    titleContainsKeyword = true;
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            Assert.assertTrue(titleContainsKeyword, "Page title should contain '" + keyword + "' after search");
        });
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}