package test.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class LoginTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test
    public void testLogin() {
        logger.info("Starting LoginTest - Navigating to Home Page");

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccount();
        logger.info("Clicked on Account section");

        homePage.clickOnSignIn();
        logger.info("Clicked on Sign-In button");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName("admin");
        logger.info("Entered username: admin");

        loginPage.enterPassword("admin");
        logger.info("Entered password");

        loginPage.clickLoginButton();
        logger.info("Clicked Login button");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-profile"))
        );
        logger.info("User profile element found");

        String actualTitle = driver.getTitle();
        logger.info("Retrieved page title: {}", actualTitle);

        Assert.assertEquals(actualTitle, "Sign in", "Title mismatch, Sign-In page not loaded.");
        logger.info("Test completed - Login functionality validated successfully");
    }
}
