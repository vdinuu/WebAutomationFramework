package pages;

import base.UIActions;
import org.openqa.selenium.By;
import utils.Constants;

public class CartPage extends UIActions {
    private By productTitle = By.cssSelector("div.cartSection h3");
    private By productPrice = By.cssSelector("div.prodTotal p");
    private By checkoutBtn = By.xpath("//button[text()='Checkout']");

    public String getProductTitle(){
        waitForElementVisible(productTitle, Constants.smallWaitInSeconds);
        return getText(findElement(productTitle));
    }
    public String getProductPrice(){
        return getFormattedText(getText(productPrice), 1);
    }
    public void checkoutProduct(){
        scrollToElement(findElement(checkoutBtn));
        click(checkoutBtn);
    }
}
