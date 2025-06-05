package web.pages;

import com.epam.restaurant_site.ui.base.BasePage;
import com.epam.restaurant_site.ui.exceptions.InvalidLoginException;
import com.epam.restaurant_site.ui.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    WebDriver driver;
    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "1")
    WebElement errorEmail;  //div[@id="12"]

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "7")
    WebElement errorPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    WebElement loginBtn;

    @FindBy(xpath = "//a[contains(@href,'signup')]")
    WebElement createAccountLnk;

    @FindBy(xpath = "//h2")
    WebElement loginText;

    @FindBy(id = "3")
    WebElement authError;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void txt_email(String ema){
        WebElement element= Waits.waitForVisibility(email);
        element.clear();
        email.sendKeys(ema);
    }
    public void txt_password(String pwd){
        WebElement element = Waits.waitForVisibility(password);
        element.clear();
        element.sendKeys(pwd);
    }
    public void clickBtn(){
        WebElement element=Waits.waitForClickability(loginBtn);
        element.click();
    }

    public void clickCreateAccount(){
        WebElement element=Waits.waitForClickability(createAccountLnk);
        element.click();
    }

    public String getLoginTitle(){
        return Waits.waitForVisibility(loginText).getText();
    }
    public String getErrorMsg(String name){
        switch (name.toLowerCase()) {
            case "email":
                return Waits.waitForVisibility(errorEmail).getText();
            case "password":
                return Waits.waitForVisibility(errorPassword).getText();
            case "authentication":
                return Waits.waitForVisibility(authError).getText();
            default:
                throw new InvalidLoginException("Invalid field name for error message: " + name);
        }
    }


}
