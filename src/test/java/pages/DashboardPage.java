package pages;

import base.DriverFactory;
import base.UIActions;
import org.openqa.selenium.By;
import utils.Constants;

import static base.DataMap.dataMap;

public class DashboardPage extends UIActions {
    private By homeButton = By.cssSelector("button[routerlink='/dashboard/']");
    private By sideBar = By.xpath("//section[@id='sidebar']/p");
    private By addToCartBtn = By.xpath("//b[text()='ZARA COAT 3']/ancestor::div[@class='card-body']/button[text()=' Add To Cart']");
    private By alertMsg = By.cssSelector("div[role='alert']");
    private By cartBtn = By.cssSelector("button[routerLink*='cart']");
    private By productPrice = By.xpath("//b[text()='ZARA COAT 3']/ancestor::div[@class='card-body']/child::div/div[@class='text-muted']");
    public String getDashboardPageUrl(){
        waitForElementVisible(sideBar, Constants.smallWaitInSeconds);
        return DriverFactory.getDriver().getCurrentUrl();
    }
    public void addProductToCart(){
        click(addToCartBtn);
        waitForElementVisible(alertMsg, 10);
    }
    public String getAlertText(){
        return getText(findElement(alertMsg));
    }
    public void navigateToCart(){
        click(findElement(cartBtn));
    }
    public void getProductPrice(){
        dataMap.get().put("price", getFormattedText(getText(productPrice), 1));
    }
}
