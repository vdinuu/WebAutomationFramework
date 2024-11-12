package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.LoginPage;
import utils.RestUtil;

import java.util.HashMap;

import static base.UIActions.prop;
import static steps.Hooks.getSoftAssert;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage();


    @Given("User is on login page")
    public void userIsOnLoginPage(){
        getSoftAssert().assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
    }
    @When("user enters login credentials {string} and {string}")
    public void enterCredentials(String username, String password){
        loginPage.enterCredentials(username, password);
    }

    @And("clicks on login button")
    public void clickLogin(){
        loginPage.clickLoginBtn();
    }

    @Given("User is logged in to the application")
    public void userIsLoggedIn(){
        Response response = RestUtil.performPost("https://rahulshettyacademy.com/api/ecom/auth/login",
                loginPage.getLoginPayload(prop.getProperty("username"), prop.getProperty("password")), new HashMap<>());
        String token = response.then().extract().path("token").toString();
        System.out.println(token);
    }
    @Given("user is successfully logged in")
    public void login(){
        userIsOnLoginPage();
        loginPage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
    }
}
