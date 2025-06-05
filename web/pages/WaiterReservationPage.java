package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaiterReservationPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;

    public WaiterReservationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//div[@class='date-selector']")
    private WebElement dateSelector;

    @FindBy(xpath = "//div[@class='time-selector']//select")
    private WebElement timeSelector;

    @FindBy(xpath = "//div[@class='location-selector']//select")
    private WebElement locationSelector;

    @FindBy(xpath = "//div[@class='table-selector']//select")
    private WebElement tableSelector;

    @FindBy(xpath = "//button[@class='create-reservation-button']")
    private WebElement makeNewReservation;


    @FindBy(xpath = "//div[@class='form-group customer-type']//span[text()='Visitor']/ancestor::div[contains(@class,'option')]")
    private WebElement visitorButton;

    @FindBy(xpath = "//div[@class='form-group customer-type']//span[text()='Existing Customer']/ancestor::div[contains(@class,'option')]")
    private WebElement customerButton;

    @FindBy(css = "input.search-input")
    private WebElement customerSearchInput;

    @FindBy(xpath = "(//button[@class='counter-button'])[1]")
    private WebElement decreaseGuestButton;

    @FindBy(xpath = "(//button[@class='counter-button'])[2]")
    private WebElement increaseGuestButton;

    @FindBy(css = ".guest-number")
    private WebElement guestNumber;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement makeReservationButton;

    public WebElement getDecreaseGuestButton() {
        return decreaseGuestButton;
    }

    public WebElement getIncreaseGuestButton() {
        return increaseGuestButton;
    }

    public WebElement getGuestNumber() {
        return guestNumber;
    }

    // Select a date
    public void selectDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(dateSelector));
        dateSelector.clear();
        dateSelector.sendKeys(date);
    }

    // Select time from dropdown
    public void selectTime(String time) {
        wait.until(ExpectedConditions.visibilityOf(timeSelector));
        Select select = new Select(timeSelector);
        select.selectByVisibleText(time);
    }

    // Select location from dropdown
    public void selectLocation(String location) {
        wait.until(ExpectedConditions.visibilityOf(locationSelector));
        Select select = new Select(locationSelector);
        select.selectByVisibleText(location);
    }

    // Select table from dropdown
    public void selectTable(String table) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(tableSelector));
        Select select = new Select(tableSelector);
        WebElement optionToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='" + table + "']")));

        Thread.sleep(2000);
        select.selectByValue(table);
    }

    // Click to make a new reservation
    public void clickMakeNewReservation() {
        wait.until(ExpectedConditions.elementToBeClickable(makeNewReservation));
        makeNewReservation.click();
    }

    // Select Visitor type
    public void selectVisitor() {
        wait.until(ExpectedConditions.elementToBeClickable(visitorButton));
        visitorButton.click();
    }

    // Select Existing Customer type
    public void selectExistingCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(customerButton));
        customerButton.click();
    }

    // Search for existing customer by name
    public void searchCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOf(customerSearchInput));
        customerSearchInput.clear();
        customerSearchInput.sendKeys(customerName);
    }

    public void clickMakeReservationButton(){
        if (makeReservationButton.isEnabled()) makeReservationButton.click();
    }





}
