package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.OrderConfirmationPage;

import static base.DataMap.dataMap;

public class OrderConfirmationSteps {
    private OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();

    @Then("confirm the order creation")
    public void verifyOrderCreation(){
        Assert.assertTrue(orderConfirmationPage.getAlertText().
                contains("THANKYOU FOR THE ORDER"), "Order is not created");
    }
    @And("verify product details in order confirmation")
    public void verifyProductDetails(){
        Assert.assertEquals(orderConfirmationPage.getProductName(),
                dataMap.get().get("productName"), "Wrong product name displayed");
        Assert.assertEquals(orderConfirmationPage.getProductPrice(),
                dataMap.get().get("price"), "Wrong product price displayed");
    }
}
