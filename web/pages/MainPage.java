package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[normalize-space(text())='Book a Table']")
    private WebElement bookTable;

    @FindBy(xpath = "//li[normalize-space(text())='Reservations']")
    private WebElement reservations;

    @FindBy(className = "profile-container")
    private WebElement profileIcon;

    @FindBy(xpath = "//li[@id='user-data']/p[@class='username']")
    private WebElement profileName;

    @FindBy(xpath = "//li[@id='user-data']/p[2]")
    private WebElement profileRole;

    @FindBy(xpath = "//li[@id='user-data']/small[@class='user-email']")
    private WebElement profileEmail;

    @FindBy(xpath = "//li[span[text()='My Profile']]")
    private WebElement profileLink;

    @FindBy(xpath = "//li[@class='sign-out']")
    private WebElement logoutButton;

    @FindBy(xpath = "//button[@class='view-menu']")
    private WebElement viewmenubutton;

    public WebElement getViewMenuButton() {
        return viewmenubutton;
    }

    public MenuPage clickViewMenuButton() {
        viewmenubutton.click();
        return new MenuPage(super.driver);
    }

    public WebElement getBookTable() {
        return bookTable;
    }

    public WebElement getReservations() {
        return reservations;
    }

    public WebElement getProfile() {
        return profileIcon;
    }

    public WebElement getProfileRole() {
        return profileRole;
    }

    public WebElement getProfileEmail() {
        return profileEmail;
    }

    public WebElement getProfileLink() {
        return profileLink;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public BookTablePage clickOnBookTable(){
        bookTable.click();
        return new BookTablePage(super.driver);
    }

    public void clickOnProfileIcon(){
        profileIcon.click();
    }

    public WaiterReservationPage clickOnReservationsForWaiter(){
        reservations.click();
        return new WaiterReservationPage(super.driver);
    }

}
