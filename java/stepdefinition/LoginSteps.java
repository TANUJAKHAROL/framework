package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.ui.BaseTest;
import io.qameta.allure.Step;

import java.time.Duration;

public class LoginSteps extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private WebDriverWait wait;
    @Step("Validating Home Page")
    @Given("I am at home page")
    public void makingSetup() {
        setup();
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(homePage.getAccountElement()));

    }

    @Step("Navigate to login Page")
    @When("I navigate to login page")
    public void navigatetoLogin(){
        homePage.clickOnAccount();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getSignInElement()));
        homePage.clickOnSignIn();
    }

    @Step("Entering username and password")
    @When("User enters Username as {string} and Password as {string}")
    public void userEntersUsernameAsAndPasswordAs(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }

    @Step("click on sign-in")
    @When("Clicks on Sign In")
        public void clickSignIn(){
        loginPage.clickLoginButton();
    }


    @Step("redirect to home page")
    @Then("Redirected to the home page again with successful signed-in message")
    public void redirect(){
        wait.until(ExpectedConditions.visibilityOf(homePage.getSuccessMessage()));
        Assert.assertTrue(homePage.checkSignInOrNot());
    }

    @Step("CLOSE BROWSER")
    @And("close the browser")
    public void closeBrowser() {
        driver.quit();
    }
}