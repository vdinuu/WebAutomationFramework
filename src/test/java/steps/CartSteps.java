package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.CartPage;

import static base.DataMap.dataMap;

public class CartSteps {
    CartPage cartPage = new CartPage();

    @Then("verify the product details in cart")
    public void verifyProductDetails(){
        Assert.assertEquals(cartPage.getProductTitle(), dataMap.get().get("productName"), "Wrong product name given");
        Assert.assertEquals(cartPage.getProductPrice(), dataMap.get().get("price"), "Wrong price displayed");
    }
    @And("continue checkout")
    public void continueCheckout(){
        cartPage.checkoutProduct();
    }
}
