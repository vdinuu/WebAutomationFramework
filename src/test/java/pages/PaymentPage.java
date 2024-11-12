package pages;

import base.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Constants;

import java.util.List;

public class PaymentPage extends UIActions {
    private By inputCardDetails = By.cssSelector("div.title +input");
    private By inputCountry = By.cssSelector("input[placeholder*='Country']");
    private By countryOptions = By.cssSelector("button.list-group-item span");
    private By submitBtn = By.cssSelector("a.action__submit");

    public void enterPaymentDetails(String creditCardNumber, String cvvCode, String name, String couponCode){
        List<WebElement> paymentFields = findElements(inputCardDetails);
        clearField(paymentFields.get(0));
        enterText(paymentFields.get(0), creditCardNumber);
        enterText(paymentFields.get(1), cvvCode);
        enterText(paymentFields.get(2), name);
        enterText(paymentFields.get(3), couponCode);
    }
    public void selectCountryDetails(String country){
        enterText(findElement(inputCountry), country);
        waitForElementsToLoad(countryOptions, Constants.smallWaitInSeconds, 1);
        selectDynamicDropDown(findElements(countryOptions), country);
    }
    public void placeOrder(){
        scrollToElement(findElement(submitBtn));
        click(submitBtn);
    }
}
