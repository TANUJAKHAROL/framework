package web.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // ---------- Form Fields ----------
    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(css = "button[type='submit']")
    private WebElement createAccountButton;

    @FindBy(linkText = "Login")
    private WebElement loginLink;

    // ---------- Error Messages ----------

    @FindBy(xpath = "//small[@class=\"error-text\"]")
    public  WebElement errorText;

    @FindBy(xpath = "//small[contains(text(), 'First name is required.')]")
    private WebElement firstNameRequiredError;

    @FindBy(xpath = "//small[contains(text(), 'First name must be up to 50 characters')]")
    private WebElement firstNameFormatError;

    @FindBy(xpath = "//small[contains(text(), 'Last name is required.')]")
    private WebElement lastNameRequiredError;

    @FindBy(xpath = "//small[contains(text(), 'Last name must be up to 50 characters')]")
    private WebElement lastNameFormatError;

    @FindBy(xpath = "//small[contains(text(), 'Email is required.')]")
    private WebElement emailRequiredError;

    @FindBy(xpath = "//small[contains(text(), 'Invalid email address')]")
    private WebElement emailFormatError;

    @FindBy(xpath = "//small[contains(text(), 'Confirm password must match new password')]")
    private WebElement confirmPasswordError;

    // ---------- Password Rule Validations (li with class valid/invalid) ----------
    @FindBy(xpath = "//li[contains(text(), 'At least one uppercase letter required')]")
    private WebElement passwordUppercaseMsg;

    @FindBy(xpath = "//li[contains(text(), 'At least one lowercase letter required')]")
    private WebElement passwordLowercaseMsg;

    @FindBy(xpath = "//li[contains(text(), 'At least one number required')]")
    private WebElement passwordNumberMsg;

    @FindBy(xpath = "//li[contains(text(), 'At least one special character required')]")
    private WebElement passwordSpecialCharMsg;

    @FindBy(xpath = "//li[contains(text(), 'Password must be 8-16 characters long')]")
    private WebElement passwordLengthMsg;

    @FindBy(id = "password-match")
    private WebElement confirmPasswordMatchMsg;

    // ---------- Actions ----------
    public void enterFirstName(String fName) {
        firstName.clear();
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        lastName.clear();
        lastName.sendKeys(lName);
    }

    public void enterEmail(String emailAddress) {
        email.clear();
        email.sendKeys(emailAddress);
    }

    public void enterPassword(String pwd) {
        password.clear();
        password.sendKeys(pwd);
    }

    public void enterConfirmPassword(String confirmPwd) {
        confirmPassword.clear();
        confirmPassword.sendKeys(confirmPwd);
    }

    public void submitForm() {
        createAccountButton.click();
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    // ---------- Get Error Messages ----------
    public String getFirstNameRequiredErrorText() {
        return firstNameRequiredError.isDisplayed() ? firstNameRequiredError.getText() : "";
    }

    public String getFirstNameFormatErrorText() {
        return firstNameFormatError.isDisplayed() ? firstNameFormatError.getText() : "";
    }

    public String getLastNameRequiredErrorText() {
        return lastNameRequiredError.isDisplayed() ? lastNameRequiredError.getText() : "";
    }

    public String getLastNameFormatErrorText() {
        return lastNameFormatError.isDisplayed() ? lastNameFormatError.getText() : "";
    }

    public String getEmailRequiredErrorText() {
        return emailRequiredError.isDisplayed() ? emailRequiredError.getText() : "";
    }

    public String getEmailFormatErrorText() {
        return emailFormatError.isDisplayed() ? emailFormatError.getText() : "";
    }

    // ---------- Password Rule Validation ----------
    private boolean isRuleValid(WebElement element) {
        String classAttr = element.getAttribute("class");
        return classAttr != null && classAttr.contains("valid");
    }

    private boolean isRuleInvalid(WebElement element) {
        String classAttr = element.getAttribute("class");
        return classAttr != null && classAttr.contains("invalid");
    }

    public boolean isUppercaseRuleInvalid() {
        return isRuleInvalid(passwordUppercaseMsg);
    }

    public boolean isLowercaseRuleInvalid() {
        return isRuleInvalid(passwordLowercaseMsg);
    }

    public boolean isNumberRuleInvalid() {
        return isRuleInvalid(passwordNumberMsg);
    }

    public boolean isSpecialCharRuleInvalid() {
        return isRuleInvalid(passwordSpecialCharMsg);
    }

    public boolean isLengthRuleInvalid() {
        return isRuleInvalid(passwordLengthMsg);
    }

    public boolean isConfirmPasswordMatchValid() {
        return isRuleValid(confirmPasswordMatchMsg);
    }

    public boolean isConfirmPasswordMatchInvalid() {
        return isRuleInvalid(confirmPasswordMatchMsg);
    }

    // ---------- Utility ----------
    public boolean isElementDisplayed(String elementName) {
        switch (elementName.toLowerCase()) {
            case "firstname": return firstName.isDisplayed();
            case "lastname": return lastName.isDisplayed();
            case "email": return email.isDisplayed();
            case "password": return password.isDisplayed();
            case "confirm password": return confirmPassword.isDisplayed();
            case "create account": return createAccountButton.isDisplayed();
            case "login": return loginLink.isDisplayed();
            default:
                throw new IllegalArgumentException("Unknown element name: " + elementName);
        }
    }
    public SignInPage signUp(){
        return new SignInPage(driver);
    }
}

