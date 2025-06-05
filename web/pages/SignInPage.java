package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage{
    WebDriver driver;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Page Elements
    @FindBy(name = "email")  // Adjust locator according to the actual HTML name/id
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    private WebElement signInButton;

    @FindBy(linkText = "Create an Account")
    private WebElement createAccountLink;

    @FindBy(xpath = "//small[@class=\"error-text\"]")
    private WebElement errorText;

    @FindBy(xpath = "//img[@alt='Toggle Password Visibility' and contains(@class, 'password-toggle')]")
    private WebElement passwordToggle;

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getErrorText() {
        return errorText;
    }

    public WebElement getPasswordToggle() {
        return passwordToggle;
    }

    // Page Actions
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public WebElement getCreateAccountLink() {
        return createAccountLink;
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickCreateAccount() {
        createAccountLink.click();
    }

    public void clickPasswordToggle(){
        passwordToggle.click();
    }

    public boolean isPasswordVisible() {
        return passwordField.getAttribute("type").equals("text");
    }

    public MainPage userSignIn(String email,String password) throws InterruptedException {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
        return getMainPage();
    }

    public MainPage getMainPage(){
        return new MainPage(super.driver);
    }
}
