package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BookTablePage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BookTablePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='form-group location-selector']//button")
    private WebElement locationDropdown;

    @FindBy(xpath = "//div[@class='form-group date-picker']//input[@type='date']")
    private WebElement datePicker;

    @FindBy(xpath = "//select[@class='time-select']")
    private WebElement timeDropdown;

    @FindBy(xpath = "//button[@aria-label='Increase guests']")
    private WebElement plusButton;

    @FindBy(xpath = "//button[@aria-label='Decrease guests']")
    private WebElement minusButton;

    @FindBy(xpath = "//button[contains(@class, 'find-table-button')]")
    private WebElement findTableButton;

    @FindBy(className = "tables-grid")
    private WebElement availableTables;

    @FindBy(className = "make-reservation-button")
    private WebElement makeReservationButton;

    @FindBy(className = "cancel-button")
    private WebElement cancelButton;

    @FindBy(xpath = "//h2[text()='Reservation Confirmed!']")
    private WebElement reservationConfirmed;

    @FindBy(xpath = "//button[@class='cancel-reservation-button']")
    private WebElement cancelReservationButton;

    @FindBy(xpath = "//button[@class='edit-reservation-button']")
    private WebElement editReservationButton;


    public WebElement getLocationDropdown() {
        return locationDropdown;
    }

    public WebElement getReservationConfirmed() {
        return reservationConfirmed;
    }

    public WebElement getCancelReservationButton() {
        return cancelReservationButton;
    }

    public WebElement getEditReservationButton() {
        return editReservationButton;
    }

    public void selectLocation(String location) {
        locationDropdown.click();
        WebElement dropdownLocation = super.driver.findElement( By.xpath("//div[@class='dropdown-item' and normalize-space(text())='" + location.trim() + "']"));
        dropdownLocation.click();
    }

    public void selectDate(String date) {
        datePicker.clear();
        datePicker.sendKeys(date);
    }

    public void selectTime(String time) {
        timeDropdown.click();
        super.driver.findElement(By.xpath("//option[text()='" + time + "']")).click();
    }

    public void increaseGuests(int count) {
        for (int i = 0; i < count; i++) {
            plusButton.click();
        }
    }

    public void clickFindTable() {
        findTableButton.click();
    }

    public boolean isTableDisplayed() {
        return availableTables.isDisplayed();
    }


    public void bookTable(String tableNumber, String slotTime) {
        String xpath = "//button[contains(@aria-label, 'Table " + tableNumber + "') and contains(@aria-label, '" + slotTime + "')]";
        WebElement tableButton = super.driver.findElement(By.xpath(xpath));
        tableButton.click();
    }

    public void clickMakeReservationButton(){
        makeReservationButton.click();
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public Boolean isConfirmationDisplayed(){
        return reservationConfirmed.isDisplayed();
    }

    public void clickCancelReservationButton(){
        cancelReservationButton.click();
    }
}

