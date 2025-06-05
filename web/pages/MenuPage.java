package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPage extends BasePage{

    private WebDriver driver;
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath ="//button[@class='filter active' and text()='Dessert']")
    private WebElement dessertTab;

    @FindBy(xpath = "//button[@class='filter active' and text()='Appetizer']\n")
    private WebElement appetizerTab;

    @FindBy(xpath = "//button[@class='filter active' and text()='Main Course']\n")
    private WebElement mainCourseTab;

    @FindBy(xpath = "//span[@class='highlight' and text()='Menu']\n")
    private WebElement menuHeader;

    @FindBy(xpath = "//div[@class='dishes-container']//div[@class='dish-card-wrapper']")
    private List<WebElement> dessertItems;

    // Locators for individual dessert data
    @FindBy(xpath = "//div[@class='dishes-container']//div[@class='dish-card-wrapper']//h3[@class='food-title']")
    private List<WebElement> dessertNames;

    @FindBy(xpath = "//div[@class='dishes-container']//div[@class='dish-card-wrapper']//span[@class='food-price']")
    private List<WebElement> dessertPrices;

    @FindBy(xpath = "//div[@class='dishes-container']//div[@class='dish-card-wrapper']//span[@class='food-weight']")
    private List<WebElement> dessertWeights;

    public void clickDessertTab() {
        dessertTab.click();
    }

    public void clickAppetizerTab() {
        appetizerTab.click();
    }

    public void clickMainCourseTab() {
        mainCourseTab.click();
    }

    public boolean isDessertsListVisible() {
        return !dessertItems.isEmpty();
    }

    public boolean verifyDessertsData() {

        if (dessertNames.size() == dessertPrices.size() && dessertNames.size() == dessertWeights.size()) {
            for (int i = 0; i < dessertNames.size(); i++) {
                String name = dessertNames.get(i).getText();
                String price = dessertPrices.get(i).getText();
                String weight = dessertWeights.get(i).getText();
                if (name.isEmpty() || price.isEmpty() || weight.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }



    public WebElement getMenuHeader() {
        return menuHeader;
    }
    public boolean isMenuHeaderDisplayed() {
        return menuHeader.isDisplayed();
    }
}

