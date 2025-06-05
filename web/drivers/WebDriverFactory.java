package web.drivers;
import exception.InValidBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private static WebDriver driver;
    // Private constructor
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE,
        EXPLORER
    }
    private WebDriverFactory() {
    }

    public static WebDriver getDriver(BrowserType browserType) throws InValidBrowser {
        if (driver == null) {
            switch (browserType) {

                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new InValidBrowser();
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
