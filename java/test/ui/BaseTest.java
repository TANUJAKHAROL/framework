package test.ui;
import exception.InValidBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import web.drivers.WebDriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @BeforeTest
     public void setup() {
        try {
            driver = WebDriverFactory.getDriver(WebDriverFactory.BrowserType.CHROME);
        } catch (InValidBrowser e){
            System.out.println("Browser type is invalid");  //implement logger here
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(System.getProperty("url", "http://localhost:9000/"));
    }


    @AfterTest
    public void tearDown(){

        driver.quit();
    }
}