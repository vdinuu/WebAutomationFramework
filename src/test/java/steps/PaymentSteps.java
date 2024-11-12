package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.PaymentPage;

import static base.DataMap.dataMap;

public class PaymentSteps {
    private PaymentPage paymentPage = new PaymentPage();

    @When("user enters payment details")
    public void enterPaymentDetails() {
        paymentPage.enterPaymentDetails((String) dataMap.get().get("cardNumber"),
                (String) dataMap.get().get("cvv"), (String) dataMap.get().get("name"),
                (String) dataMap.get().get("coupon"));
    }
    @And("fills shipping details")
    public void fillShippingDetails(){
        paymentPage.selectCountryDetails(dataMap.get().get("country").toString());
    }
    @And("place order")
    public void placeOrder(){
        paymentPage.placeOrder();
    }
}
