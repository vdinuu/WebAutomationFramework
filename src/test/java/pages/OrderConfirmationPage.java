package pages;

import base.UIActions;
import org.openqa.selenium.By;
import utils.Constants;

public class OrderConfirmationPage extends UIActions {
    private By orderConfirmation = By.cssSelector("h1.hero-primary");
    private By productTitle = By.cssSelector(".line-item td:nth-child(2) .title");
    private By productPrice = By.cssSelector(".line-item td:nth-child(3) .title");

    public String getAlertText(){
        waitForElementVisible(orderConfirmation, Constants.smallWaitInSeconds);
        return getText(findElement(orderConfirmation));
    }
    public String getProductName(){
        return getText(productTitle);
    }
    public String getProductPrice(){
        return getFormattedText(getText(productPrice), 1);
    }
}
