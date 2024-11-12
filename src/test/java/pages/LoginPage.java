package pages;

import base.UIActions;
import org.openqa.selenium.By;
import pojos.LoginPayload;

public class LoginPage extends UIActions {
    private By username = By.id("userEmail");
    private By password = By.id("userPassword");
    private By loginBtn = By.id("login");
    private By loginPageTitle = By.cssSelector("h1.login-title");


    public void loginToApplication(String userid, String pwd){
        enterCredentials(userid, pwd);
        click(findElement(loginBtn));
    }
    public void enterCredentials(String userid, String pwd){
        enterText(findElement(username), userid);
        enterText(findElement(password), pwd);
    }
    public boolean isLoginPageDisplayed(){
        waitForElementVisible(findElement(loginPageTitle));
        return findElement(loginPageTitle).isDisplayed();
    }
    public void clickLoginBtn(){
        click(findElement(loginBtn));
    }

    public LoginPayload getLoginPayload(String username, String pwd){
        return new LoginPayload().toBuilder().userEmail(username).userPassword(pwd).build();
    }
}
