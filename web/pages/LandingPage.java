package web.pages;


import com.epam.restaurant_site.ui.base.BasePage;
import com.epam.restaurant_site.ui.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[starts-with(text(),'Sign In')]")
    private WebElement signInButton;

    @FindBy(xpath = "//h1[contains(text() ,'Fresh, Organic')]")
    private WebElement landingPageTitle;  //fresh orange xpath

    @FindBy(xpath = "//div[@class='relative']")
    private WebElement profileAvatar;   //visible after sign-in

    @FindBy(xpath = "//a[@href='/bookTable']")
    private WebElement reservationSection;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement signOutBtn;

    @FindBy(xpath = "//p[contains(@class,'text-sm font-medium')]")
    private WebElement ProfileNameAndRole;

    @FindBy(xpath = "//div[@id='1']")
    private WebElement successfullLoginToast;

    public void clickOnSignIn() {
        Waits.waitForClickability(signInButton).click();
    }

    public String getLandingPageTitle(){
        return Waits.waitForVisibility(landingPageTitle).getText();
    }

    public void clickProfileAvatar(){
        Waits.waitForClickability(profileAvatar).click();
    }

    public WebElement visibilityOfProfileAvatar(){
        return Waits.waitForVisibility(profileAvatar);
    }

    public void clickReservationSection(){
        Waits.waitForClickability(reservationSection).click();
    }

    public WebElement visibilityOfReservationSection(){
        return Waits.waitForVisibility(reservationSection);
    }

    public void clickOnSignOutButton(){
        signOutBtn.click();
    }

    public String getProfileNameAndRole(){
        return Waits.waitForVisibility(ProfileNameAndRole).getText();
    }
    public boolean visibiltyOfSuccessToast(){
        return Waits.waitForVisibility(successfullLoginToast).isDisplayed();
    }

}
