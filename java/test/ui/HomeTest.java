package test.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomeTest.class);

    @Test
    public void testHome() {
        logger.info("Starting HomeTest - Navigating to Home Page");

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccount();
        logger.info("Clicked on Account section");

        homePage.clickOnSignIn();
        logger.info("Clicked on Sign-In button");

        String expectedTitle = "Sign in";
        String actualTitle = driver.getTitle();
        logger.info("Retrieved page title: {}", actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch, Sign-In page not loaded.");
        logger.info("Test completed - Home page navigation validated successfully");
    }
}
