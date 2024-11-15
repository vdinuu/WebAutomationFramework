package steps;

import base.DriverFactory;
import base.UIActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;
import pojos.LoginPayload;
import utils.ConfigReader;
import utils.ExcelUtil;
import utils.RestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static base.DataMap.dataMap;

public class Hooks extends UIActions {

    DriverFactory driverFactory;
    private SoftAssert softAssert;
    private static ThreadLocal threadLocal = new ThreadLocal();
    private static Map<String, Map<String, Object>> parentDataMap;
    static String browser;
    @BeforeAll
    public static void before_all(){
        String env = System.getProperty("env") == null ? "QA": System.getProperty("env");
        parentDataMap = ExcelUtil.getExcelData(env+"/TestData.xlsx", "Sheet1");
        browser = ConfigReader.getBrowserType();
    }

    @Before(order = 0)
    public void initializeTest(Scenario scenario){
        switch (browser){
            case "chrome"-> DriverFactory.initializeDriver("chrome", prop.getProperty("url"), false);
            case "firefox" -> DriverFactory.initializeDriver("firefox", prop.getProperty("url"), false);
            case "edge"-> DriverFactory.initializeDriver("edge", prop.getProperty("url"), false);
        }
        softAssert = new SoftAssert();
        threadLocal.set(softAssert);
        dataMap.set(parentDataMap.get(scenario.getName()));
        System.out.println(parentDataMap.get(scenario.getName()));

    }
    @Before(order = 1)
    public void log(Scenario scenario){
        System.out.println("Starting : "+scenario.getName());
    }

    @Before(value = "@autoLogin", order = 2)
    public void autoLogin(){
        loginToApplication();
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screenshot = takeScreenshot();
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        if(null != DriverFactory.getDriver()){
            DriverFactory.getDriver().manage().deleteAllCookies();
            DriverFactory.getDriver().quit();
        }
        if(null != getSoftAssert()){
            getSoftAssert().assertAll();
        }
    }
    public static SoftAssert getSoftAssert(){
        return (SoftAssert) threadLocal.get();
    }

    private LoginPayload getLoginPayload(String username, String pwd){
        return new LoginPayload().toBuilder().userEmail(username).userPassword(pwd).build();
    }
    private void loginToApplication(){
        Response response = RestUtil.performPost("https://rahulshettyacademy.com/api/ecom/auth/login",
                getLoginPayload(prop.getProperty("username"), prop.getProperty("password")), new HashMap<>());
//        String token = response.then().extract().path("token").toString();
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("window.localStorage.setItem('token', '"+response.then().extract().path("token").toString()+"');");
        DriverFactory.getDriver().get("https://rahulshettyacademy.com/client/");
    }

}
