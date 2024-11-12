package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;

public class DashboardSteps {
    DashboardPage dashboardPage = new DashboardPage();

    @Then("login should be successful")
    public void verifyLogin() {
        Assert.assertTrue(dashboardPage.getDashboardPageUrl().contains("dashboard"), "Login unsuccessful");
    }

    @When("user clicks on Add to cart button")
    public void addProductToCart(){
        dashboardPage.getProductPrice();
        dashboardPage.addProductToCart();
    }
    @And("check added to cart message")
    public void verifyAddToCartAlert(){
        Assert.assertEquals(dashboardPage.getAlertText().trim(), "Product Added To Cart");
    }
    @And("navigate to cart")
    public void navigateToCart(){
        dashboardPage.navigateToCart();
    }
}
